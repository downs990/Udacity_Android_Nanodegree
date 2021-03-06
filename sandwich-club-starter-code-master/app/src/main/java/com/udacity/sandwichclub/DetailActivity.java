package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private TextView mainnameTextView;
    private TextView alsoKnownAsTextView;
    private TextView ingredientsTextView;
    private TextView placeOfOriginsTextView;
    private TextView descriptionTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        mainnameTextView = findViewById(R.id.main_name_tv);
        alsoKnownAsTextView = findViewById(R.id.also_known_tv);
        ingredientsTextView = findViewById(R.id.ingredients_tv);
        placeOfOriginsTextView = findViewById(R.id.origin_tv);
        descriptionTextView = findViewById(R.id.description_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];


        Log.d("JSON-TAG", json);
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        Log.d("SANDWICH-TAG", sandwich.toString());




        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        // update UI data.
        populateTextViews(sandwich);



        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }


    private void populateTextViews(Sandwich currentSandwich){

        String alsoKnowAsString = currentSandwich.getAlsoKnowAsString();
        String ingredientsString = currentSandwich.getIngredientsString();

        mainnameTextView.setText(currentSandwich.getMainName());
        alsoKnownAsTextView.setText(alsoKnowAsString);
        ingredientsTextView.setText(ingredientsString);
        placeOfOriginsTextView.setText(currentSandwich.getPlaceOfOrigin());
        descriptionTextView.setText(currentSandwich.getDescription());
    }


    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

}
