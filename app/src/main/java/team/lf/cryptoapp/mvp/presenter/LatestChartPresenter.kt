package team.lf.cryptoapp.mvp.presenter

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import team.lf.cryptoapp.di.App
import team.lf.cryptoapp.mvp.contract.LatestChartContract
import team.lf.cryptoapp.rest.CoinGeckoApi
import javax.inject.Inject

class LatestChartPresenter: LatestChartContract.Presenter() {
    @Inject
    lateinit var geckoApi: CoinGeckoApi

    init {
        App.appComponent.inject(this)
    }

    override fun makeChart(id: String) {
        subscribe(geckoApi.getCoinMarketChart(id)
            .map { it.prices }
            .flatMap { Observable.fromIterable(it)}
            .doOnComplete{
                view.hideProgress()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.hideProgress()
                view.addEntryToChart(it[0], it[1])
            },{
                view.hideProgress()
                view.showErrorMessage(it.message)
                it.printStackTrace()
            }))
    }

    override fun refreshChart() {
        view.refresh()
    }
}
