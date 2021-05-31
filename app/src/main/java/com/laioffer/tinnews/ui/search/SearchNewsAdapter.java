package com.laioffer.tinnews.ui.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.laioffer.tinnews.R;
import com.laioffer.tinnews.databinding.SearchNewsItemBinding;
import com.laioffer.tinnews.model.Article;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchNewsAdapter extends RecyclerView.Adapter<SearchNewsAdapter.SearchNewsViewHolder> {
    //define a callback listener
    interface ItemCallback {
        void onOpenDetails(Article article);
    }

    private ItemCallback itemCallback;

    public void setItemCallback(ItemCallback itemCallback) {
        this.itemCallback = itemCallback;
    }


    // 1. Supporting data:
    private List<Article> articles = new ArrayList<>();

    public void setArticles(List<Article> newsList) {
        articles.clear();
        articles.addAll(newsList);
        // It’s a method from the base adapter class, which we will extend just in a bit.
        // So the idea is that every time a new list is set,
        // we call notifyDataSetChanged to let the adapter refresh and re-render the data.
        notifyDataSetChanged();
    }

    // 2. Adapter overrides:
    //create
    //onCreateViewHolder is for providing the generated item views
    //onCreateViewHolder被call的次数 = screen size / view size 是固定的
    //Only as many view holders as needed to display the on-screen portion of the dynamic content are created.
    @NonNull
    @Override
    public SearchNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_news_item, parent, false);
        return new SearchNewsViewHolder(view);
    }

    //reuse
    //onBindViewHolder is for binding the data with a view
    //onBindViewHolder被call的次数 = 手滑动距离，更新次数是不确定的
    @Override
    public void onBindViewHolder(@NonNull SearchNewsViewHolder holder, int position) {
        //position from 0 to articles.zie() -1
        Article article = articles.get(position);
        holder.favoriteImageView.setImageResource(R.drawable.ic_favorite_24dp);
        holder.itemTitleTextView.setText(article.title);
        Picasso.get().load(article.urlToImage).resize(500,500).into(holder.itemImageView);
        holder.itemView.setOnClickListener(v->itemCallback.onOpenDetails(article));
    }

    @Override
    //getItemCount is for providing the current data collection size
    public int getItemCount() {//告诉有多少数据，根据滑动速度计算更新速度
        return articles.size();
    }

    // 3. SearchNewsViewHolder:
    //It’s for holding the view references
    // binding缓存的作用
    //不需要每次都searchbyId
    public static class SearchNewsViewHolder extends RecyclerView.ViewHolder {

        ImageView favoriteImageView;
        ImageView itemImageView;
        TextView itemTitleTextView;

        public SearchNewsViewHolder(@NonNull View itemView) {
            super(itemView);
            SearchNewsItemBinding binding = SearchNewsItemBinding.bind(itemView);
            //android:id="@+id/search_item_image"
            //android:id="@+id/search_item_favorite"
            //android:id="@+id/search_item_title"
            favoriteImageView = binding.searchItemFavorite;
            itemImageView = binding.searchItemImage;
            itemTitleTextView = binding.searchItemTitle;
        }
    }

}
