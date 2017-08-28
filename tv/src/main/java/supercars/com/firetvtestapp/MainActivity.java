/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package supercars.com.firetvtestapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import android.view.View;

import android.widget.Button;
import android.widget.PopupWindow;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/*
 * MainActivity class that loads MainFragment
 */
public class MainActivity extends Activity implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */
    private String ENQUIRE_URL = "http://192.168.1.55:8080/Cars_Sample_App/enquire.do?query=save";
    private String SEARCH_URL = "http://192.168.1.55:8080/Cars_Sample_App/search.do?query=search";
    private String HOME_PAGE_URL = "http://192.168.1.55:8080/Cars_Sample_App/home.do";

    //private String ENQUIRE_URL = "http://192.168.1.55:8080/Cars_Sample_App/enquire.jsp?query=save";
    //private String SEARCH_URL = "http://192.168.1.55:8080/Cars_Sample_App/search.jsp";
    PopupWindow popUpWindow;


    Button ENQUIRE_BTN;
    Button SEARCH_BTN;
    Button CRASH_BTN;
    Button HOME_BTN;

    RequestQueue queue;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.appd_buttons);

        queue = Volley.newRequestQueue(this);
        //Intialization Button

        HOME_BTN = (Button) findViewById(R.id.home_do);
        HOME_BTN.setOnClickListener(this);

        ENQUIRE_BTN = (Button) findViewById(R.id.enquire_btn);
        ENQUIRE_BTN.setOnClickListener(this);

        SEARCH_BTN = (Button) findViewById(R.id.search_btn);
        SEARCH_BTN.setOnClickListener(this);

        CRASH_BTN = (Button) findViewById(R.id.crash_btn);
        CRASH_BTN.setOnClickListener(this);

        popUpWindow = new PopupWindow(this);

        //Here MainActivity.this is a Current Class Reference (context)
    }
    @Override
    public void onClick(View v) {
        String viewID = Integer.toString(v.getId());

        switch (v.getId()) {
            case R.id.enquire_btn:
                makeEnquireRequest();
                break;
            case R.id.search_btn:
              makeSearchRequest();
                break;
            case R.id.crash_btn:
                crashApp();
                break;
            case R.id.home_do:
                homePage();
                break;
            default:
                System.out.println("ERROR");
                break;
        }
    }


    private void crashApp() {
        Integer i = null;
        i.byteValue();
    }
    public void makeEnquireRequest() {

// Instantiate the RequestQueue.
        Log.d("Making request...", ENQUIRE_URL);

        StringRequest postRequest = new StringRequest(Request.Method.POST, ENQUIRE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.i("Success!", response);

                }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        SEARCH_BTN.setText("FAIL");
                        Log.d("Error.Response", error.getMessage());
                    }
                }
        ) {

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
            @Override
            protected Map<String, String> getParams() {
                Random random = new Random();
                int number = random.nextInt(1000);
                String randoms = String.format("%03d", number);
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", "NEW ANDROID TEST_" + random);
                params.put("email", randoms + "@somedomain.com");
                params.put("carName", "null");
                params.put("carId", "null");
                params.put("comment", randoms);
                return params;
            }


        };
        queue.add(postRequest);
    }
    public void makeSearchRequest() {
// Instantiate the RequestQueue.
        StringRequest postRequest = new StringRequest(Request.Method.POST, SEARCH_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        SEARCH_BTN.setText("FAIL");
                        Log.d("Error.Response", error.getMessage());
                    }
                }
        ) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
            @Override
            protected Map<String, String> getParams() {
                Random random = new Random();
                int number = random.nextInt(1000);
                String randoms = String.format("%03d", number);
                Map<String, String> params = new HashMap<String, String>();
                params.put("criteria", "Test");
                return params;
            }
        };
        queue.add(postRequest);
    }
    public void homePage() {
// Instantiate the RequestQueue.
        StringRequest getRequest = new StringRequest(Request.Method.GET, HOME_PAGE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        SEARCH_BTN.setText("FAIL");
                        Log.d("Error.Response", error.getMessage());
                    }
                }
        ) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };
        queue.add(getRequest);
    }

}
