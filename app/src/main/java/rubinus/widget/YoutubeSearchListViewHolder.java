package rubinus.widget;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Video;

import rubinus.ui.R;
import rubinus.ui.YoutubeFragment;
import rubinus.util.App;
import rubinus.util.TextUtil;

public class YoutubeSearchListViewHolder extends RecyclerView.ViewHolder {
    private ImageView thumbnailImageView;
    private TextView titleTextView;
    private TextView durationTextView;
    private TextView viewCountTextView;

    public YoutubeSearchListViewHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.viewholder_youtube_search_list, parent, false));

        thumbnailImageView = (ImageView) itemView.findViewById(R.id.search_thumbnail_image_view);
        titleTextView = (TextView) itemView.findViewById(R.id.search_title_text_view);
        durationTextView = (TextView) itemView.findViewById(R.id.search_duration_text_view);
        viewCountTextView = (TextView) itemView.findViewById(R.id.search_view_count_text_view);
    }

    public void bind(SearchResult searchResult, Video video) {
        String url = searchResult.getSnippet().getThumbnails().getHigh().getUrl();
        new YoutubeFragment.DownloadImageAsyncTask().execute(new Pair<>(url, thumbnailImageView));

        titleTextView.setText(searchResult.getSnippet().getTitle());
        durationTextView.setText(TextUtil.toDescriptiveTimeFormat(video.getContentDetails().getDuration()));

        String viewCountText = video.getStatistics().getViewCount().toString() + " " +
                               App.context().getString(R.string.view_text);
        viewCountTextView.setText(viewCountText);
    }


}
