import java.util.Date

data class Sampah(
    val id: String,
    val tanggal: String,
    val nama: String,
    val berat: String,
)
data class ApiResponse(
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    val data: List<Sampah>
)

data class SingleApiResponse(
    val data: Sampah
)

data class CreateSampah(
    val tanggal: String,
    val nama: String,
    val berat: Int
    )

data class CreateSampahResponse(
    val nama: String,
    val id: String,
    val Tanggal: Date,
    val Berat: Int,
    val createdAt: Date,
    val updatedAt: Date
    )