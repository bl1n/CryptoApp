package team.lf.cryptoapp.di

import dagger.Component
import team.lf.cryptoapp.MainActivity
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class, RestModule::class, MvpModule::class, ChartModule::class))
@Singleton
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}