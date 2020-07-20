package edu.cnm.deepdive.imgurbrowser;

import android.app.Application;
import com.squareup.picasso.Picasso;

public class ImgurApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Picasso.setSingletonInstance(
        new Picasso.Builder(this)
            .loggingEnabled(true) //loggingEnabled should be removed once at production
            .build()
    );
  }
}
