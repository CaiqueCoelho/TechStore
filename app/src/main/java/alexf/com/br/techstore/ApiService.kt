package alexf.com.br.techstore

import retrofit2.Retrofit

interface ApiService {
    companion object {
        val instance: ApiService by lazy {
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .build()
            retrofit.create(ApiService::class.java)
        }
    }
}