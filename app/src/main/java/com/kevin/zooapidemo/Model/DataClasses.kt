package com.kevin.zooapidemo.Model

import com.google.gson.annotations.SerializedName

data class resourceAquire(
    var result:result?
)

data class result(
    var count:String?,
    var limit:String?,
    var offset:String?,
    var sort:String?,
    var results:ArrayList<results>?
)

data class results(
    var E_Category:String?,
    var E_Geo:String?,
    var E_Info:String?,
    var E_Memo:String?,
    var E_Name:String?,
    var E_no:String?,
    var E_Pic_URL:String?,
    var E_URL:String?,
    var _id:String?,
    @SerializedName("\uFEFFF_Name_Ch") var F_Name_Ch:String?,
    var F_AlsoKnown:String?,
    var F_Brief:String?,
    var F_Family:String?,
    var F_Feature:String?,
    @SerializedName("F_Function＆Application") var F_Functionn:String?,
    var F_Genus:String?,
    var F_Geo:String?,
    var F_Location:String?,
    var F_Name_En:String?,
    var F_Name_Latin:String?,
    var F_pdf01_ALT:String?,
    var F_pdf01_URL:String?,
    var F_pdf02_ALT:String?,
    var F_pdf02_URL:String?,
    var F_Pic01_ALT:String?,
    var F_Pic01_URL:String?,
    var F_Pic02_ALT:String?,
    var F_Pic02_URL:String?,
    var F_Pic03_ALT:String?,
    var F_Pic03_URL:String?,
    var F_Pic04_ALT:String?,
    var F_Pic04_URL:String?,
    var F_Summary:String?,
    var F_Update:String?
)


