package com.example.android.recipes.utilities;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Baraa Hesham on 6/9/2018.
 */
public class MySingleton {

    private static MySingleton singleton;
    private RequestQueue requestQueue;
    private static Context mcontext;

    private MySingleton(Context context){
        mcontext=context;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue() {
        if(requestQueue==null){
            requestQueue= Volley.newRequestQueue(mcontext.getApplicationContext());
        }return requestQueue;
    }
    public static synchronized MySingleton getInstance(Context context){
        if (singleton==null){
            singleton=new MySingleton(context);
        }return singleton;
    }
    public<T> void addToRequestQueue(Request<T> request){
        requestQueue.add(request);
    }
}
