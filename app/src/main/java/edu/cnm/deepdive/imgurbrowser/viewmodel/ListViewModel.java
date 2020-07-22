package edu.cnm.deepdive.imgurbrowser.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import edu.cnm.deepdive.imgurbrowser.BuildConfig;
import edu.cnm.deepdive.imgurbrowser.model.Gallery;
import edu.cnm.deepdive.imgurbrowser.service.ImgurService;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

public class ListViewModel extends AndroidViewModel {

  private final MutableLiveData<List<Gallery>> galleries;
  private final MutableLiveData<Throwable> throwable;
  private final ImgurService imgurService;
  private final CompositeDisposable pending;

  public ListViewModel(@NonNull Application application) {
    super(application);
    galleries = new MutableLiveData<List<Gallery>>();
    throwable = new MutableLiveData<Throwable>();
    imgurService = ImgurService.getInstance();
    pending = new CompositeDisposable();
    loadData();
  }

  public LiveData<List<Gallery>> getGalleries() {
    return galleries;
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  public void loadData() {

    pending.add(
        imgurService.getSearchResult(BuildConfig.CLIENT_ID, "cars")
            .subscribeOn(Schedulers.io())
            .map((result) -> {
              List<Gallery> galleries = result.getData();
              galleries.removeIf((gallery) ->
                  gallery.getImages() == null || gallery.getImages().isEmpty());
              return galleries;
            })
            .subscribe(
                value -> this.galleries.postValue(value),
                throwable -> this.throwable.postValue(throwable.getCause())
            )
    );

  }



  @Override
  protected void onCleared() {
    super.onCleared();
    pending.clear();
  }
}
