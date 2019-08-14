package team.lf.cryptoapp.di

import dagger.Module
import dagger.Provides
import team.lf.cryptoapp.chart.LatestChart
import team.lf.cryptoapp.formatters.YearValueFormatter
import javax.inject.Singleton

@Module
class ChartModule {
    @Provides
    @Singleton
    fun provideLatestChart() = LatestChart()

    @Provides
    @Singleton
    fun provideYearFormatter() = YearValueFormatter()
}