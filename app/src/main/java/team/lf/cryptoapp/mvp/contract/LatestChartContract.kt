package team.lf.cryptoapp.mvp.contract

class LatestChartContract {
    interface View:BaseContract.View{
        fun addEntryToChart(value:Float, date:String="")
        fun addEntryToChart(date:Float, value:Float)
        fun showProgress()
        fun showErrorMessage(error: String?)
        fun refresh()
        fun hideProgress()
    }

    abstract class Presenter:BaseContract.Presenter<View>(){
        abstract fun makeChart(id:String)
        abstract fun refreshChart()
    }
}