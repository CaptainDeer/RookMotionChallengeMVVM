package com.captaindeer.rookmotionchallengemvvm.data.remote.services

import com.captaindeer.rookmotionchallengemvvm.data.remote.responses.UsersResponse
import retrofit2.Response
import retrofit2.http.GET

interface APIService {

    @GET(value = "api/users?page=2")
    suspend fun getAllUsers(): Response<UsersResponse>

}