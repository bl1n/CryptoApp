package team.lf.cryptoapp.mvp.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import team.lf.cryptoapp.R
import team.lf.cryptoapp.adapter.BaseAdapter
import team.lf.cryptoapp.adapter.CurrenciesAdapter
import team.lf.cryptoapp.di.App
import team.lf.cryptoapp.mvp.contract.CurrenciesContract
import team.lf.cryptoapp.mvp.presenter.CurrenciesPresenter
import javax.inject.Inject

class CurrenciesListFragment : BaseFragment(), CurrenciesContract.View {

    @Inject
    lateinit var presenter: CurrenciesPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_currencies_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        App.appComponent.inject(this)
        presenter.attach(this)
        presenter.makeList()


    }

    override fun createAdapterInstance(): BaseAdapter<*> = CurrenciesAdapter()

    override fun addCurrency(currency: CurrenciesAdapter.Currency) {
        viewAdapter.add(currency)
    }

    override fun notifyAdapter() {
        viewAdapter.notifyDataSetChanged()
    }

    override fun showProgress() {
        requireActivity().progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        requireActivity().progress.visibility = View.INVISIBLE
    }

    override fun showErrorMessage(error: String?) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    override fun refresh() {
        viewAdapter.items.clear()
        viewAdapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        presenter.attach(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.detach()
    }


}