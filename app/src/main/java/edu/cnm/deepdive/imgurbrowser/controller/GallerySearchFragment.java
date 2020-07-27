package edu.cnm.deepdive.imgurbrowser.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.imgurbrowser.R;
import edu.cnm.deepdive.imgurbrowser.model.Gallery;
import edu.cnm.deepdive.imgurbrowser.model.Image;
import edu.cnm.deepdive.imgurbrowser.view.GalleryListAdapter;
import edu.cnm.deepdive.imgurbrowser.view.GalleryListAdapter.OnItemSelectedHelper;
import edu.cnm.deepdive.imgurbrowser.viewmodel.ListViewModel;

public class GallerySearchFragment extends Fragment implements OnItemSelectedHelper {

  private RecyclerView galleryArray;
  private ListViewModel viewModel;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.gallery_list, container, false);
    galleryArray = view.findViewById(R.id.recycler_view);
    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(getActivity()).get(ListViewModel.class);
    viewModel.getGalleries().observe(getViewLifecycleOwner(), galleries -> {
      if (galleries != null) {
        galleryArray.setAdapter(new GalleryListAdapter(getContext(), galleries, this));
      }
    });
  }

  @Override
  public void onSelected(Gallery gallery, Image image) {
    ImageDetailDialogFragment fragment = ImageDetailDialogFragment.newInstance(image);
    fragment.show(getChildFragmentManager(), fragment.getClass().getName());
  }
}
