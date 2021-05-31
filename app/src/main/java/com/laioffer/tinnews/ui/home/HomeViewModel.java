package com.laioffer.tinnews.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.laioffer.tinnews.model.Article;
import com.laioffer.tinnews.model.NewsResponse;
import com.laioffer.tinnews.repository.NewsRepository;

public class HomeViewModel extends ViewModel {
    private final NewsRepository repository;
    private final MutableLiveData<String> countryInput = new MutableLiveData<>();

    public HomeViewModel(NewsRepository repository) {
        this.repository = repository;
    }

    public void setCountryInput(String country) {
        countryInput.setValue(country);
    }

    public LiveData<NewsResponse> getTopHeadlines() {
        //当countryInput有update时，会传给repository里的getTopHeadlines
        //::method reference
        return Transformations.switchMap(countryInput, repository::getTopHeadlines);
    }
    //The difference this time is that we don’t need to expose the observing result.
    // So we don’t have to do the Transformations.switchMap trick.
    // This is a plain simple direct call to the repository.
    public void setFavoriteArticleInput(Article article) {
        repository.favoriteArticle(article);
    }

}
