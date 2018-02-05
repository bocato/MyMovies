package br.com.zup.mymovies.view.adapters;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

import br.com.zup.mymovies.MyMoviesApplication;
import br.com.zup.mymovies.R;
import br.com.zup.mymovies.databinding.ListItemSearchResultBinding;
import br.com.zup.mymovies.model.OmdbVideoBasic;

/**
 * Created by rafaelneiva on 03/02/18.
 */

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {

    private List<OmdbVideoBasic> mItems;
    private SearchResultListener mListener;

    public SearchResultAdapter(List<OmdbVideoBasic> items) {
        this.mItems = items;
    }

    @Override
    public SearchResultAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_search_result, parent, false));
    }

    @Override
    public void onBindViewHolder(SearchResultAdapter.ViewHolder holder, int position) {
        holder.bindView(mItems.get(holder.getAdapterPosition()));

        holder.bind.clickableView.setOnClickListener(view -> {
            if (getListener() != null) getListener().onItemClick(getMoviesId(), holder.getAdapterPosition(), holder.bind.ivPoster);
        });
    }

    @NonNull
    private ArrayList<String> getMoviesId() {
        ArrayList<String> ids = new ArrayList<>();
        for (OmdbVideoBasic movie : mItems) {
            ids.add(movie.getId());
        }
        return ids;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ListItemSearchResultBinding bind;

        ViewHolder(View itemView) {
            super(itemView);
            bind = DataBindingUtil.bind(itemView);
        }

        void bindView(OmdbVideoBasic omdbVideoBasic) {
            bind.setResult(omdbVideoBasic);

            Target target = new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    bind.ivPoster.setImageBitmap(bitmap);

                    Palette.from(bitmap)
                            .generate(palette -> {
                                Palette.Swatch textSwatch = palette.getDominantSwatch();

                                if (textSwatch != null) {
                                    GradientDrawable drawable = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, new int[]{textSwatch.getRgb(), textSwatch.getTitleTextColor()});
                                    bind.ivShadow.setBackground(drawable);

                                    bind.tvTitle.setTextColor(textSwatch.getTitleTextColor());
                                    bind.tvYear.setTextColor(textSwatch.getBodyTextColor());
                                } else {
                                    bind.ivShadow.setBackgroundColor(Color.parseColor("#4d000000"));
                                }
                            });
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {

                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            };
            MyMoviesApplication.getInstance().getApiClient().getPicasso().load(omdbVideoBasic.getPoster()).into(target);
            bind.ivPoster.setTag(target);

        }
    }

    public SearchResultListener getListener() {
        return mListener;
    }

    public void setListener(SearchResultListener mListener) {
        this.mListener = mListener;
    }

    public interface SearchResultListener {
        void onItemClick(ArrayList<String> omdbIds, int clickedPos, View sharedElement);
    }
}
