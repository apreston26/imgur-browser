package edu.cnm.deepdive.imgurbrowser.controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import edu.cnm.deepdive.imgurbrowser.R;
import edu.cnm.deepdive.imgurbrowser.model.Image;

public class ImageDetailDialogFragment extends DialogFragment {

    private Image image;

    public static ImageDetailDialogFragment newInstance(Image image) {
        ImageDetailDialogFragment fragment = new ImageDetailDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable("image", image);
        fragment.setArguments(args);
        return  fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            image = (Image) getArguments().getSerializable("image");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext())
            .inflate(R.layout.fragment_image_detail_dialog, null, false);
        ImageView imageView = view.findViewById(R.id.image_detail);
        TextView imageDescription = view.findViewById(R.id.image_description);
        TextView imageId = view.findViewById(R.id.image_id);
        TextView imageUrl = view.findViewById(R.id.image_url);
        TextView imageDateTime = view.findViewById(R.id.image_datetime);
        TextView imageType = view.findViewById(R.id.image_type);
        TextView imageWidth = view.findViewById(R.id.image_width);
        TextView imageHeight = view.findViewById(R.id.image_height);
        TextView imageViews = view.findViewById(R.id.image_views);
        TextView imageBandWidth = view.findViewById(R.id.image_bandwidth);

        if (image.getUrl() != null) {
            Picasso.get().load(image.getUrl()).into(imageView);
        }
        imageDescription.setText((image.getDescription() != null) ? image.getDescription() : "Description N/A");
        imageId.setText((image.getImageId() != null) ? "Id: " + image.getImageId() : "Image Id N/A");
        imageUrl.setText((image.getUrl() != null) ? "Image Url: " + image.getUrl() : "Url N/A");
        imageDateTime.setText(
            (image.getImageDateTime() != null) ? "Submitted: " + convertTime(image.getImageDateTime())
                : "DateTime N/A");
        imageType.setText((image.getType() != null) ? "Type of Image: " + image.getType() : "Type N/A");
        imageWidth.setText((image.getWidth() != null) ? "Image Width: " + image.getWidth() : "Image Width N/A");
        imageHeight.setText((image.getHeight() != null) ? "Image Height: " + image.getHeight() : "Image Height N/A");
        imageViews.setText((image.getViews() != null) ? "Views: " + image.getViews() : "Views N/A");
        imageBandWidth.setText((image.getBandwidth() != null) ? "Bandwidth: " + image.getBandwidth() : "Bandwidth N/A");

        AlertDialog dialog = new AlertDialog.Builder(getContext())
            .setTitle((image.getTitle() != null) ? image.getTitle() : "Untitled")
            .setView(view)
            .setPositiveButton(R.string.close_image_dialog, (dlg, which) -> {

            })
            .create();
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        return null;
    }

    private String convertTime(long epoch) {
        return new java.text.SimpleDateFormat("MMM-dd-yyyy hh:mm aa z")
            .format(new java.util.Date(epoch * 1000));
    }
}
