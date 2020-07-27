package edu.cnm.deepdive.imgurbrowser.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.squareup.picasso.Picasso;
import edu.cnm.deepdive.imgurbrowser.R;
import edu.cnm.deepdive.imgurbrowser.model.Image;
import java.util.List;

public class GalleryImageAdapter extends ArrayAdapter<Image> {

  public GalleryImageAdapter(@NonNull Context context,
      List<Image> imageList) {
    super(context, 0, imageList);
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    return initView(position, convertView, parent);
  }

  @Override
  public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    return initView(position, convertView, parent);
  }

  private View initView(int position, View convertView, ViewGroup parent) {

    if (convertView == null) {
      convertView = LayoutInflater.from(getContext()).inflate(
          R.layout.custom_gallery_search_spinner_item, parent, false
      );
    }

    ImageView imageView = convertView.findViewById(R.id.custom_spinner_image);
    TextView title = convertView.findViewById(R.id.custom_spinner_description);
    TextView description = convertView.findViewById(R.id.custom_spinner_description);
    TextView url = convertView.findViewById(R.id.custom_spinner_url);

    Image currentItem = getItem(position);

    if (currentItem.getUrl() != null) {
      if (position == 0 ) {
        Picasso.get().load(R.drawable.gallery).into(imageView);
      } else {
        Picasso.get().load(currentItem.getUrl()).into(imageView);
      }
      if (currentItem.getTitle() != null) {
        title.setText(currentItem.getTitle());
      }
      if (currentItem.getDescription() != null) {
        description.setText(currentItem.getDescription());
      }
    }

    return convertView;
  }
}
