package com.example.popularmoviesstage1.utilities;

import com.example.popularmoviesstage1.model.Movie;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class JsonUtils {


    public static String getMovieJSON(String popularMoviesJSON, int index){

        String result = "";

        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(popularMoviesJSON);
            JSONArray movieObjects = (JSONArray) jsonObject.get("results");
            result = movieObjects.get(index).toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }




    public static ArrayList<Movie> parseMovieJSON(String popularMoviesJSON){
        ArrayList<Movie> newMovieList = new ArrayList<>();


        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(popularMoviesJSON);
            JSONArray movieObjects = (JSONArray) jsonObject.get("results");

            Gson gson = new Gson();
            for(int i = 0; i < movieObjects.length(); i++ ){

                Movie currentMovie = gson.fromJson( movieObjects.get(i).toString() , Movie.class);
                newMovieList.add( currentMovie );
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return newMovieList;


    }


}
