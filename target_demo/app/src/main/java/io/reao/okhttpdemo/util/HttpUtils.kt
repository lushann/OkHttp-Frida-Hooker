package io.reao.okhttpdemo.util

import android.util.Log
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class HttpUtils {

    private var client = OkHttpClient()

    fun okhttpGet(): Response? {

        val TAG = "okhttpGet"
        var response: Response? = null

        Thread {
            try {
                val request: Request = Request.Builder()
                    .url("https://en18elmo0ebxklf.m.pipedream.net") //请求接口。如果需要传参拼接到接口后面。
                    .build() //创建Request 对象
                response = client.newCall(request).execute() //得到Response 对象
                if (response!!.isSuccessful) {
                    Log.d(TAG, " Response Code : "  + response!!.code)
                    Log.d(TAG, " Response Body : "  + (response!!.body?.string() ?: "Response is null"))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()

        return response
    }

    fun okhttpPost(): Response? {
        var response: Response? = null
        val TAG = "okhttpPost"

        val json = "{\"id\":1,\"name\":\"John\"}"
        val body: RequestBody =
            json.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())

        Thread{
            try {
                val request: Request = Request.Builder()
                    .url("https://en18elmo0ebxklf.m.pipedream.net") //请求接口。如果需要传参拼接到接口后面。
                    .post(body)
                    .build() //创建Request 对象

                response = client.newCall(request).execute() //得到Response 对象
                if (response!!.isSuccessful) {
                    Log.d(TAG, " Response Code : "  + response!!.code)
                    Log.d(TAG, " Response Body : "  + (response!!.body?.string() ?: "Response is null"))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }.start()
        return response
    }
}