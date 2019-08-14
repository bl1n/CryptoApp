package team.lf.cryptoapp.di

import dagger.Module
import dagger.Provides
import team.lf.cryptoapp.mvp.presenter.CurrenciesPresenter
import team.lf.cryptoapp.mvp.presenter.LatestChartPresenter
import javax.inject.Singleton

@Module
class MvpModule {
    @Provides
    @Singleton
    fun provideCurrenciesPresenter(): CurrenciesPresenter = CurrenciesPresenter()

    @Provides
    @Singleton
    fun provideLatestChartPresenter(): LatestChartPresenter = LatestChartPresenter()
}