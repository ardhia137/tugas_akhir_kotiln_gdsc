import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import retrofit2.http.POST
import retrofit2.http.Path


interface ApiService {
    @GET("api/setoran-sampah")
    fun Sampahs(): Call<ApiResponse>
    @GET("api/setoran-sampah/{id}")
    fun SingleSampah(@Path("id") id: String): Call<SingleApiResponse>
    @POST("api/setoran-sampah")
    fun createSampah(@Body sampah: CreateSampah): Call<CreateSampahResponse>
}
val apiService = apiClient.getClient().create(ApiService::class.java)

fun getSampah(){
    apiService.Sampahs().enqueue(object : Callback<ApiResponse> {
        override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
            if (response.isSuccessful) {
                val apiResponse = response.body()
                val sampah = apiResponse?.data
                println("===== Seluruh Data Sampah =====")
                sampah?.forEach {
                    println("Nama : " + it.nama + "\nBerat : " + it.berat.toString() + "\ntanggal setor : "+ it.tanggal)
                    println("==================================")
                }
            }
        }

        override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
            println("Error: " + t.message)
        }
    })
}

fun GetSingleSampah(id: String){
    val call = apiService.SingleSampah(id)

    call.enqueue(object : Callback<SingleApiResponse> {
        override fun onResponse(call: Call<SingleApiResponse>, response: Response<SingleApiResponse>) {
            val apiResponse = response.body()
            val rsp = apiResponse?.data
            println("===== Data Spesifik Pembuang Sampah =====")
            println("Nama : " + rsp?.nama + "\nBerat : " + rsp?.berat.toString() + "\ntanggal setor : "+ rsp?.tanggal)
            println("==================================")
        }

        override fun onFailure(call: Call<SingleApiResponse>, t: Throwable) {

        }
    })
}

fun postData(){
    val currentDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    val newData = CreateSampah(currentDate.format(formatter).toString(),"Sukimin",30)
    val response = apiService.createSampah(newData).execute()
    val apiResponse = response.body()

    println("Berhasil Menambahkan Data")
    }

fun main() {
    //fungsi untuk memanggil get all data
    getSampah()
    // fungsi untuk memanggil get spesifik data
    GetSingleSampah("8e873905-3b2e-432c-ae52-e90908e8821d")
    // fungsi untuk memanggil post data
    postData()
}