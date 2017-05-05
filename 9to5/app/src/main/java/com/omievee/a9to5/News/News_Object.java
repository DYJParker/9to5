package com.omievee.a9to5.News;

import com.omievee.a9to5.RecyclerView.AbstractBaseInformationObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by omievee on 5/5/17.
 */

public class News_Object extends AbstractBaseInformationObject {

    List<String[]> articles = new ArrayList();

    public void addArticles(String title, String description, String source ) {
        articles.add(new String[]{title, description,source});


    }

    public List<String[]> getArticles() {
        return articles;

    }



}
