package com.daming.wordkids.service;

import com.daming.wordkids.bean.Card;
import com.daming.wordkids.bean.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by dashu on 2016/12/21.
 */

public interface WordService {
    @GET("wordcard/categories")
    Call<List<Category>> getCategories();

    @GET("wordcard/cards/{category_id}")
    Call<List<Card>> getCards(@Path("category_id") int id);
}
