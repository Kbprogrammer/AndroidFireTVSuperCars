package supercars.com.firetvtestapp;

import android.app.Application;

import com.appdynamics.eumagent.runtime.Instrumentation;
import com.appdynamics.eumagent.runtime.AgentConfiguration;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Start the AppDynamics Instrumentation
        Instrumentation.start(AgentConfiguration.builder()
                .withContext(getApplicationContext())
                .withAppKey("AD-AAB-AAE-TWZ")
                .build());


    }
}