package com.github.ejchathuranga.kotlin_mvvm_android.models

data class SearchResult(val data: ArrayList<TemperJob>, val aggregations: Aggregations)
data class TemperJob(
    val starts_at: String,
    val ends_at: String,
    val distance: Double,
    val job: JobData,
    val earnings_per_hour: EarningsPerHour
)

data class JobData(val project: Project, val category: JobCategory)
data class Project(val client: Client)
data class Client(val name: String, val links: Links)
data class Links(val hero_image: String)
data class JobCategory(val name: String)
data class EarningsPerHour(val currency: String, val amount: Double)
data class Aggregations(val count: Int)
