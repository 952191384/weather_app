package com.example.myapplication;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Call;
import okhttp3.Response;

public class OkHttpUtils {
    //私有化构造函数: 防止外部代码直接实例化 OkHttpUtils 类。这是实现单例模式的关键部分。通过将构造函数设为私有，确保只有一个 OkHttpUtils 实例可以存在。
    private OkHttpUtils() {}
    //静态实例: 声明了一个私有的静态常量 instance，这是 OkHttpUtils 类的唯一实例。静态常量确保在整个应用生命周期内只有一个实例。
    private static final OkHttpUtils instance = new OkHttpUtils();

    private final OkHttpClient okHttpClient = new OkHttpClient();
    //获取单例实例: 提供一个公共的静态方法 getInstance() 来访问唯一的 OkHttpUtils 实例。这样做可以确保全局范围内只存在一个 OkHttpUtils 实例。
    public static OkHttpUtils getInstance() {
        return instance;
    }
    //执行 GET 请求的方法
    public String doGet(String url) throws Exception {
        //使用 Request.Builder 创建一个新的 Request 对象。url(url) 设置请求的 URL，build() 构建出 Request 实例。
        Request request = new Request.Builder().url(url).build();
        //newCall(request) 方法使用之前创建的 OkHttpClient 实例来创建一个新的 Call 对象。Call 对象用于执行 HTTP 请求。
        Call call = okHttpClient.newCall(request);
        //execute() 方法同步执行请求并返回 Response 对象。
        Response response = call.execute();
        //如果响应体不为空，则读取其内容并转换为字符串。如果响应体为空，则返回 null。response.body().string() 读取响应体的内容，并在读取后关闭 ResponseBody 对象。
        return response.body() != null ? response.body().string() : null;
    }
}
