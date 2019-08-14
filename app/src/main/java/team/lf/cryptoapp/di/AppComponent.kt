package team.lf.cryptoapp.di

import dagger.Component
import team.lf.cryptoapp.MainActivity
import team.lf.cryptoapp.activities.ChartActivity
import team.lf.cryptoapp.chart.LatestChart
import team.lf.cryptoapp.mvp.fragments.CurrenciesListFragment
import team.lf.cryptoapp.mvp.presenter.CurrenciesPresenter
import team.lf.cryptoapp.mvp.presenter.LatestChartPresenter
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class, RestModule::class, MvpModule::class, ChartModule::class))
@Singleton
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(presenter: CurrenciesPresenter)
    fun inject(presenter: LatestChartPresenter)
    fun inject(fragment: CurrenciesListFragment)
    fun inject(latestChart: LatestChart)
    fun inject(chartActivity: ChartActivity)
}