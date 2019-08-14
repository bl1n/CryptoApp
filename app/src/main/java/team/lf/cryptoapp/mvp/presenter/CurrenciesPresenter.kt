package team.lf.cryptoapp.mvp.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import team.lf.cryptoapp.di.App
import team.lf.cryptoapp.mvp.contract.CurrenciesContract
import team.lf.cryptoapp.rest.CoinGeckoApi
import io.reactivex.Observable
import team.lf.cryptoapp.adapter.CurrenciesAdapter
import team.lf.cryptoapp.formatters.formatThousands
import javax.inject.Inject

class CurrenciesPresenter : CurrenciesContract.Presenter() {
    @Inject
    lateinit var geckoApi: CoinGeckoApi

    init {
        App.appComponent.inject(this)
    }

    override fun makeList() {
        view.showProgress()
        subscribe(geckoApi.getCoinMarket()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap { Observable.fromIterable(it) }
            .doOnNext {
                view.addCurrency(
                    CurrenciesAdapter.Currency(
                        it.id,
                        it.symbol,
                        it.name,
                        it.image,
                        it.current_price,
                        it.market_cap.formatThousands(),
                        it.market_cap_rank,
                        it.total_volume,
                        it.price_change_percentage_24h,
                        it.market_cap_change_percentage_24h,
                        it.circulating_supply,
                        it.total_supply,
                        it.ath,
                        it.ath_change_percentage
                    )
                )
            }
            .doOnComplete {
                view.hideProgress()
            }
            .subscribe({
                view.hideProgress()
                view.notifyAdapter()
            }, {
                view.showErrorMessage(it.message)
                view.hideProgress()
                it.printStackTrace()
            })
        )

    }

    override fun refreshList() {
        view.refresh()
        makeList()
    }
}