package com.example.budgetfriend.viewmodels

import androidx.lifecycle.ViewModel
import com.example.budgetfriend.domain.Income
import com.example.budgetfriend.repositories.IncomeRepository
import com.example.budgetfriend.utils.convertStringToCategory
import com.example.budgetfriend.utils.convertStringToCurrency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class IncomesViewModel @Inject constructor(
    private val incomeRepository: IncomeRepository
): ViewModel() {

    private val _incomes = MutableStateFlow(IncomesViewModelData())
    val incomes: StateFlow<IncomesViewModelData> = _incomes.asStateFlow()

    init {
        _incomes.value = IncomesViewModelData(
            this.incomeRepository.getIncomes(), this.incomeRepository.getIncomes()
        )
    }

    private fun createIncome(incomeId: String? = null,
                              source: String,
                              amount: Int,
                              currency: String,
                              date: String) : Income
    {
        val incomeDate = if (date.isEmpty()) {
            null
        } else {
            SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse(date)
        }

        return Income(
            incomeId = incomeId ?: UUID.randomUUID().toString(),
            source = source,
            amount = amount,
            currency = convertStringToCurrency(currency),
            date = incomeDate,
        )
    }

    fun addIncome(source: String,
                  amount: Int,
                  currency: String,
                  date: String)
    {
        val incomeToAdd = this.createIncome(
            source = source,
            amount = amount,
            currency = currency,
            date = date,
        )

        _incomes.value = _incomes.value.copy(
            incomes = _incomes.value.incomes + incomeToAdd,
            filteredIncome = _incomes.value.filteredIncome + incomeToAdd,
            filterKey = ""
        )

        this.incomeRepository.addIncome(incomeToAdd)
    }

    fun updateIncome(incomeId: String,
                      source: String,
                      amount: Int,
                      currency: String,
                      date: String,
    )
    {
        val incomeToUpdate = this.createIncome(
            incomeId, source, amount, currency, date
        )

        val incomes = this._incomes.value.incomes.map {
            if (it.incomeId == incomeId)
                incomeToUpdate
            else
                it
        }

        this.incomeRepository.updateIncome(incomeToUpdate)
    }

    fun deleteIncome(incomeId: String)
    {
        this._incomes.value = this._incomes.value.copy(
            incomes = this._incomes.value.incomes.filter { it.incomeId != incomeId },
            filteredIncome = this._incomes.value.filteredIncome.filter { it.incomeId != incomeId }
        )
        this.incomeRepository.deleteIncome(incomeId)
    }

    fun updateFilterKey(newFilterKey: String) {
        this._incomes.value = this._incomes.value.copy(filterKey = newFilterKey,
            filteredIncome = this._incomes.value.incomes.filter { it.source.contains(newFilterKey) })
    }

    fun findIncomeById(incomeId: String?): Income? {
        return this._incomes.value.incomes.find { it.incomeId == incomeId }
    }
}