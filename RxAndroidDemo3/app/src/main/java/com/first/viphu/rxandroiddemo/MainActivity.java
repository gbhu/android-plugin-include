package com.first.viphu.rxandroiddemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yummy.adapter.MoviesAdapter;
import com.yummy.modal.MovieModal;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

import static android.os.Process.THREAD_PRIORITY_BACKGROUND;
public class MainActivity extends Activity {

    private static final String TAG = "RxAndroidSamples";

    private Looper backgroundLooper;

    private ListView listView;
    private ProgressBar progressbar;
    private TextView internet_error;
    private List<MovieModal>arrayList=new ArrayList<MovieModal>();
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        BackgroundThread backgroundThread = new BackgroundThread();
        backgroundThread.start();
        backgroundLooper = backgroundThread.getLooper();
        listView=(ListView)findViewById(R.id.lv);
        progressbar= (ProgressBar) findViewById(R.id.progressbar);
        internet_error= (TextView) findViewById(R.id.internet_error);
        findViewById(R.id.button_run_scheduler).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRunSchedulerExampleButtonClicked();
            }
        });
    }

    void onRunSchedulerExampleButtonClicked() {
        progressbar.setVisibility(View.VISIBLE);
        sampleMoviesObservable()
                // Run on a background thread
                .subscribeOn(AndroidSchedulers.from(backgroundLooper))
                        // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<MovieModal>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted()");

                        progressbar.setVisibility(View.GONE);
                        if (arrayList != null && arrayList.size() > 0) {
                            MoviesAdapter MoviesAdapter = new MoviesAdapter(arrayList, MainActivity.this);
                            listView.setAdapter(MoviesAdapter);
                        } else {
                            internet_error.setVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError()", e);
                        internet_error.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onNext(List<MovieModal> movieModals) {
                        arrayList = movieModals;
                    }
                });
    }

    static Observable<List<MovieModal>> sampleMoviesObservable() {

        Observable<List<MovieModal>> myObservable = Observable.create(new Observable.OnSubscribe<List<MovieModal>>() {

            @Override
            public void call(Subscriber<? super List<MovieModal>> subscriber) {
                subscriber.onNext(getMoivesList("https://raw.githubusercontent.com/facebook/react-native/master/docs/MoviesExample.json"));
                subscriber.onCompleted();
            }
        }
        );

        return myObservable;
    }



    /**
     * 获取网络数据
     * @param url
     * @return
     * @throws IOException
     */
public static List<MovieModal> getMoivesList(String url){

    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder().url(url).build();

    Response response = null;
    List<MovieModal> movieModals=null;
    try {
        response = client.newCall(request).execute();
        if (response.isSuccessful()){
            String json=response.body().string();
            System.out.print(json);
            Gson gson=new Gson();
            JSONObject root = null;
            try {
                root = new JSONObject(json);
                String returnValue = root.getString("movies");
                Type type = new TypeToken<List<MovieModal>>() {
                }.getType();

                movieModals = gson.fromJson(returnValue,type);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }else{

        }
    } catch (IOException e) {
        e.printStackTrace();
    }

        return movieModals;

}
    static class BackgroundThread extends HandlerThread {
        BackgroundThread() {
            super("SchedulerSample-BackgroundThread", THREAD_PRIORITY_BACKGROUND);
        }
    }
}
