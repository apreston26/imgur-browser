package edu.cnm.deepdive.imgurbrowser.controller;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import edu.cnm.deepdive.imgurbrowser.R;
import edu.cnm.deepdive.imgurbrowser.viewmodel.ListViewModel;

public class MainActivity extends AppCompatActivity {

  ListViewModel listViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }
}
