package com.example.budgetfriend.repositories

import com.example.budgetfriend.domain.Income
import com.example.budgetfriend.utils.sampleIncomes

class IncomeRepository {
    private val incomes: MutableList<Income> = sampleIncomes.toMutableList()

    fun getIncomes(): List<Income>
    {
        return this.incomes.toList()
    }

    fun addIncome(incomeToAdd: Income)
    {
        this.incomes.add(incomeToAdd)
    }

    fun updateIncome(incomeToUpdate: Income)
    {
        this.incomes.forEachIndexed { index, income ->
            income.takeIf { it.incomeId == incomeToUpdate.incomeId }?.let{
                this.incomes[index] = it.copy(
                    incomeId = incomeToUpdate.incomeId,
                    source = incomeToUpdate.source,
                    amount = incomeToUpdate.amount,
                    currency = incomeToUpdate.currency,
                    date = incomeToUpdate.date
                )
            }
        }
    }

    fun deleteIncome(incomeId: String)
    {
        this.incomes.removeIf { it.incomeId == incomeId}
    }
}