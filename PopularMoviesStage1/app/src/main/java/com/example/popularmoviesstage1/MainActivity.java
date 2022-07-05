package com.example.popularmoviesstage1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.popularmoviesstage1.model.Movie;
import com.example.popularmoviesstage1.utilities.JsonUtils;
import com.example.popularmoviesstage1.utilities.NetworkUtils;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements MovieAdapter.ListItemClickListener{


    private TextView mErrorDisplayTextView;
    private ProgressBar mLoadingIndicator;
    private String mMoviesJSON = "";



    private RecyclerView mMovieRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mLoadingIndicator = findViewById(R.id.pb_loading_indicator);
        mErrorDisplayTextView = findViewById(R.id.tv_error_message_display);
        mMovieRecyclerView = findViewById(R.id.rv_movies_grid);


        getMovieDataJSON("popular");


    }


    private ArrayList<String> getMovieImageTitles(String moviesJSON){
        ArrayList<String> popularMoviesImages = new ArrayList<>();

        ArrayList<Movie> movieObjects = JsonUtils.parseMovieJSON(moviesJSON);
        for(Movie movie : movieObjects){
            String posterPath = movie.getPoster_path();
            popularMoviesImages.add(posterPath);
        }
        return popularMoviesImages;
    }

    private void getMovieDataJSON(String sortType){

        // TODO: Please add your own movie db Key here.
        String API_KEY = "";


        URL movieSearchURL = NetworkUtils.buildUrl(API_KEY, sortType);
        MovieQueryTask myMovieQuery = new MovieQueryTask();
        myMovieQuery.execute(movieSearchURL);
    }


    private void populateGridWithMovieImages(String moviesJSON){

        int columns = 2;
        GridLayoutManager layoutManager = new GridLayoutManager(this, columns );
        mMovieRecyclerView.setLayoutManager(layoutManager);
        mMovieRecyclerView.setHasFixedSize(true);

        MovieAdapter mAdapter = new MovieAdapter(getMovieImageTitles(moviesJSON), this, this.getBaseContext());
        mMovieRecyclerView.setAdapter(mAdapter);
    }

    private void showJsonDataView() {
        mErrorDisplayTextView.setVisibility(View.INVISIBLE);
    }


    private void showErrorMessage() {
        mErrorDisplayTextView.setVisibility(View.VISIBLE);
    }




    @Override
    public void onListItemClick(int clickedItemIndex) {

        String movieClicked = JsonUtils.getMovieJSON(mMoviesJSON, clickedItemIndex);

        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        intent.putExtra("CLICKED_JSON", movieClicked);
        startActivity(intent);
    }






    public class MovieQueryTask extends AsyncTask<URL, Void, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);

        }

        @Override
        protected String doInBackground(URL... params) {
            URL movieUrl = params[0];
            String movieJSONResults = null;
            try {
                movieJSONResults = NetworkUtils.getResponseFromHttpUrl(movieUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return movieJSONResults;
        }

        @Override
        protected void onPostExecute(String movieSearchResults) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (movieSearchResults != null && !movieSearchResults.equals("")) {
                showJsonDataView();

                mMoviesJSON = movieSearchResults;
                populateGridWithMovieImages(mMoviesJSON);
            } else {
                showErrorMessage();
            }
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();


        if (itemThatWasClickedId == R.id.action_sort_popularity) {
            getMovieDataJSON("popular");
            return true;

        }else if (itemThatWasClickedId == R.id.action_sort_rating) {
            getMovieDataJSON("top_rated");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}