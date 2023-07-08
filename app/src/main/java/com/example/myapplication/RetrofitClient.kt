import com.example.myapplication.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://api.exchangeratesapi.io/v1/"
    private const val BASE_API_NINJAS_URL = "https://api.api-ninjas.com/v1/"

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .build()
    }

    private val retrofitExchangeRates by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val retrofitApiNinjas by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_API_NINJAS_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiExchangeRates: ApiService by lazy {
        retrofitExchangeRates.create(ApiService::class.java)
    }

    val apiNinjasConvert: ApiService by lazy {
        retrofitApiNinjas.create(ApiService::class.java)
    }
}
