package com.example.gamezone.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.gamezone.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GameCustomAdapter extends RecyclerView.Adapter<GameCustomAdapter.ViewHolder> {

    private final List<Game> games;
    private final Context context;
    private ImageView gameImage;
    private TextView gameName;
    private TextView gameKeywords;
    private RatingBar rating;

    // Click listener object created for recycler view item click
    private onRecyclerViewItemClickListener itemListener;

    // Interface to perform action for click on item in recycler view
    public interface onRecyclerViewItemClickListener {
        void onItemClickEvent(int position);
    }

    public void setOnItemClickListener(onRecyclerViewItemClickListener itemListener) {
        this.itemListener=itemListener;
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View view) {
            super(view);
        }
    }

    // Step 1 : Initialize the dataset of the Adapter
    public GameCustomAdapter(Context context,List<Game> games) {
        this.context=context;
        this.games=games;
    }

    // Step 2 : Create new views (invoked by the layout manager)
    @Override
    public @NotNull ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.game_details_layout, viewGroup, false);

        gameImage = (ImageView) view.findViewById(R.id.gameImage);
        gameName = (TextView) view.findViewById(R.id.gameName);
        gameKeywords = (TextView) view.findViewById(R.id.gameKeywords);
        rating = (RatingBar) view.findViewById(R.id.gameRating);

        return new ViewHolder(view);
    }

    // Step 3 : Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        // Image from URL
        Glide.with(context)
                .applyDefaultRequestOptions(
                        new RequestOptions()
                                .placeholder(R.drawable.default_game_image)
                                .error(R.drawable.default_game_image)
                )
                .load(games.get(position).getImageUrl())
                .into(gameImage);

        // Set TextViews
        gameName.setText(games.get(position).getName());

        String key="";
        List<String> keyNames=games.get(position).getKeyWords();
        for (int i=0;i<keyNames.size();i++)
            key=key+keyNames.get(i)+" | ";
        if (key.length()==0)
            gameKeywords.setText("None");
        else
            gameKeywords.setText(key.substring(0,key.length()-3));

        rating.setRating((float) games.get(position).getRating());

        // Add click listener on item clicked
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemListener.onItemClickEvent(position);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return games.size();
    }

    // Recyclerview items doesn't change while scroll
    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
