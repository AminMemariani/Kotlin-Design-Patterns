import gateway.Service.LiveData.LiveDataCallAdapterFactory
import gateway.Service.WebService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    private lateinit var retrofit: Retrofit
    init {
        retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())

                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addCallAdapterFactory( LiveDataCallAdapterFactory())
//                .client(client)
                .build();
    }

    fun getWebService(): WebService {
        return retrofit.create(WebService::class.java)
    }

    companion object {
        private var myInstance: RetrofitClient? = null
        private const val BASE_URL = "http://185.156.173.111/~twannas/"

        @Synchronized
        fun getInstance(): RetrofitClient? {

            if (myInstance == null) {
                myInstance = RetrofitClient()
            }
            return myInstance
        }
    }
}
