package com.example.api_demo.ui.theme

/**
 * A class for creating a data class which will be used to parse the Json response using GSON.
 */
data class ResponseData(
    val message: String,
    val user_id: Int,
    val name: String,
    val email: String,
    val mobile: Long,
    val profile_details: ProfileDetails,
    val data_list: List<DataListDetail>
)

data class ProfileDetails(
    val is_profile_completed: Boolean,
    val rating: Double
)

data class DataListDetail(
    val id: Int,
    val value: String
)