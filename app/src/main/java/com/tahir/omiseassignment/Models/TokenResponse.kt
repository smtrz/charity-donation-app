package com.tahir.omiseassignment.Models

import com.google.gson.annotations.SerializedName

class TokenResponse {


    var id: String? = null
    @SerializedName("object")
    var object_val: String? = null
    var code: String? = null
    var message: String? = null


}