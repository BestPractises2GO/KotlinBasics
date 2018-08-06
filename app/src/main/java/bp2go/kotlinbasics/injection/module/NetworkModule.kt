package bp2go.kotlinbasics.injection.module

import bp2go.kotlinbasics.model.network.PostApi
import bp2go.kotlinbasics.model.network.UserWebservice
import bp2go.kotlinbasics.utils.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
@Suppress("unused")
object NetworkModule{

    @Provides
    @JvmStatic
    @Reusable
    internal fun providePostApi(retrofit: Retrofit) : PostApi {
        return retrofit.create(PostApi::class.java)
    }

    @Provides
    @JvmStatic
    @Reusable
    internal fun provideUserWebservice(retrofit: Retrofit): UserWebservice{
        return retrofit.create(UserWebservice::class.java)
    }

    @Provides
    @Reusable
    internal fun provideGeson() : Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Reusable
    internal fun provideRetrofi(gson: Gson) : Retrofit{
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
    }
}