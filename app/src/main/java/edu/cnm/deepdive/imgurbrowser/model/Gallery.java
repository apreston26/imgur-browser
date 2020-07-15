package edu.cnm.deepdive.imgurbrowser.model;

import androidx.annotation.NonNull;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.Arrays;

public class Gallery {

  private String id;
  @Expose
  private String title;
  @Expose
  private String description;
  private long datetime;
  @SerializedName("images_count")
  @Expose
  private int imagesCount;
  @Expose
  private Tag[] tags;
  @Expose
  private Image[] images;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public long getDatetime() {
    return datetime;
  }

  public void setDatetime(long datetime) {
    this.datetime = datetime;
  }

  public int getImagesCount() {
    return imagesCount;
  }

  public void setImagesCount(int imagesCount) {
    this.imagesCount = imagesCount;
  }

  public Tag[] getTags() {
    return tags;
  }

  public void setTags(Tag[] tags) {
    this.tags = tags;
  }

  public Image[] getImages() {
    return images;
  }

  public void setImages(Image[] images) {
    this.images = images;
  }

  public static class Search {

    @Expose
    private Gallery[] data;

    public Gallery[] getData() {
      return data;
    }

    public void setData(Gallery[] data) {
      this.data = data;
    }

    @NonNull
    @Override
    public String toString() {
      return "SearchResult{" + "data=" + Arrays.toString(getData());
    }
  }
}
