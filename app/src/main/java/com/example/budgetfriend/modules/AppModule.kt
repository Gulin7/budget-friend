package com.example.budgetfriend.modules

import com.example.budgetfriend.repositories.ExpenseRepository
import com.example.budgetfriend.repositories.IncomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideExpenseRepository(): ExpenseRepository
    {
        return ExpenseRepository()
    }

    @Provides
    @Singleton
    fun provideIncomeRepository(): IncomeRepository
    {
        return IncomeRepository()
    }
}