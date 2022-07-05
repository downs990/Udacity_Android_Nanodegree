package com.example.popularmoviesstage1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.Gson;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.popularmoviesstage1.model.Movie;
import com.squareup.picasso.Picasso;




public class DetailsActivity extends AppCompatActivity{

    private Movie clickedMovie;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        TextView titleTextView = findViewById(R.id.tv_movie_title);
        TextView yearTextView = findViewById(R.id.tv_year);
        TextView overviewTextView = findViewById(R.id.tv_overview);
        TextView voteAverageTextView = findViewById(R.id.tv_vote_average);
        ImageView posterImageTextView = findViewById(R.id.iv_movie);


        Gson gson = new Gson();
        Intent intent = getIntent();

        if (intent != null) {
            String clickedJSON = intent.getStringExtra("CLICKED_JSON");
            clickedMovie = gson.fromJson(clickedJSON, Movie.class);
        }


        titleTextView.setText(clickedMovie.getOriginal_title());
        yearTextView.setText(clickedMovie.getRelease_date());
        overviewTextView.setText(clickedMovie.getOverview());
        voteAverageTextView.setText(clickedMovie.getVote_average() + "/10");

        String starWarsImage = "https://image.tmdb.org/t/p/original" + clickedMovie.getPoster_path();// NOTE: Always HTTPS not HTTP
        Picasso.with(this.getBaseContext())
                .load(starWarsImage)
                .into(posterImageTextView);



    }
}
