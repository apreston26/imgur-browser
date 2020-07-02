package edu.cnm.deepdive.imgurbrowser;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.imgurbrowser.model.Gallery.Search;
import edu.cnm.deepdive.imgurbrowser.viewmodel.ListViewModel;

public class MainActivity extends AppCompatActivity {

  ListViewModel listViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    listViewModel = new ViewModelProvider(this)
        .get(ListViewModel.class);
    listViewModel.getSearchResult().observe(this, new Observer<Search>() {
      @Override
      public void onChanged(Search search) {
        listViewModel.loadData();
      }
    });
  }
}
