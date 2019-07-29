package com.formafit.architecture.network.models

import com.google.gson.annotations.SerializedName

class RepositoriesResponseModel(val id: Int, private: Boolean, val name: String, val owner: Owner) {

    class Owner(val id: Int, @SerializedName("login") val name: String, @SerializedName("avatar_url") val avatarUrl: String?)
}