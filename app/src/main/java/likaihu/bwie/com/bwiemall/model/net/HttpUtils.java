package likaihu.bwie.com.bwiemall.model.net;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtils {

    private NetDataCallBackIFace mNetDataCallBackIFace;
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    mNetDataCallBackIFace.requestSuccess(msg.obj);
                    break;
                case 1:
                    mNetDataCallBackIFace.requestSuccess(msg.obj);//此时数据在主线程了，回调的数据可以直接更新UI
                    break;

            }
        }
    };


    //get请求方式
    public <T> void getDataGetFromServer(String url, NetDataCallBackIFace netDataCallBackIFace, final Class<T> clazz) {

        this.mNetDataCallBackIFace = netDataCallBackIFace;
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();
        final Request request = new Request.Builder()
                .url(url)
                .build();


        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {


            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Gson gson = new Gson();
                T t = gson.fromJson(response.body().string(), clazz);
                Message message = Message.obtain();
                message.what = 0;
                message.obj = t;
                handler.sendMessage(message);
            }
        });
    }

    //post请求方式
    public <T> void getDataPostFromServer(String url, NetDataCallBackIFace netDataCallBackIFace, Map<String, String> params, final Class<T> clazz) {

        this.mNetDataCallBackIFace = netDataCallBackIFace;


        RequestBody requestBody = null;

        OkHttpClient client = new OkHttpClient();


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();

        if (params == null) {
            params = new HashMap<String, String>();
        }
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey().toString();
            String value = null;
            if (entry.getValue() == null) {
                value = "";
            } else {
                value = entry.getValue().toString();
            }
            Log.e("=====", "getDataPostFromServer: " + key + " ," + value);
            builder.add(key, value);
        }
        requestBody = builder.build();
        final Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("=====", "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Log.e("=====", "onResponse: " + response.body().toString());

                Gson gson = new Gson();
                T t = gson.fromJson(response.body().string(), clazz);
                Message message = Message.obtain();
                message.what = 1;
                message.obj = t;
                handler.sendMessage(message);
            }
        });

    }


    //定义一个接口
    public interface NetDataCallBackIFace<T> {


        void requestSuccess(T t);

        void requestFailure(int errCode, String errMsg);
    }

}
