package com.udacity.sandwichclub.model;

import java.util.ArrayList;
import java.util.List;

public class Sandwich {

    private String mainName;
    private List<String> alsoKnownAs = new ArrayList<>();
    private String placeOfOrigin;
    private String description;
    private String image;
    private List<String> ingredients = new ArrayList<>();

    /**
     * No args constructor for use in serialization
     */
    public Sandwich() {
    }

    public Sandwich(String mainName, List<String> alsoKnownAs, String placeOfOrigin, String description, String image, List<String> ingredients) {
        this.mainName = mainName;
        this.alsoKnownAs = alsoKnownAs;
        this.placeOfOrigin = placeOfOrigin;
        this.description = description;
        this.image = image;
        this.ingredients = ingredients;
    }

    public String getMainName() {
        return mainName;
    }

    public void setMainName(String mainName) {
        this.mainName = mainName;
    }

    public List<String> getAlsoKnownAs() {
        return alsoKnownAs;
    }

    public void setAlsoKnownAs(List<String> alsoKnownAs) {
        this.alsoKnownAs = alsoKnownAs;
    }

    public String getPlaceOfOrigin() {
        return placeOfOrigin;
    }

    public void setPlaceOfOrigin(String placeOfOrigin) {
        this.placeOfOrigin = placeOfOrigin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getAlsoKnowAsString() {

        String alsoKnowAsString = "";
        if (this.getAlsoKnownAs().isEmpty() == false) {

            for (int i = 0; i < this.getAlsoKnownAs().size(); i++) {
                alsoKnowAsString += this.getAlsoKnownAs().get(i) + "\n";
            }
        } else {
            alsoKnowAsString = "Empty";
        }

        return alsoKnowAsString;
    }

    public String getIngredientsString() {
        String ingredientsString = "";
        if (this.getIngredients().isEmpty() == false) {

            for (int i = 0; i < this.getIngredients().size(); i++) {
                ingredientsString += this.getIngredients().get(i) + "\n";
            }
        } else {
            ingredientsString = "Empty";
        }

        return ingredientsString;
    }

    @Override
    public String toString() {
        return "Sandwich{" +
                "mainName='" + mainName + '\'' +
                ", alsoKnownAs=" + getAlsoKnowAsString() +
                ", placeOfOrigin='" + placeOfOrigin + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", ingredients=" + getIngredientsString() +
                '}';
    }
}
