package tech.codingclub.helix.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import tech.codingclub.helix.global.HttpUrlConnection;

public class WikipediaDownloader  {

    private String keyword;

    public WikipediaDownloader() {

    }

    public WikipediaDownloader(String keyword) {
        this.keyword = keyword;
    }

    public  WikiResult getResult() {
        //1 Get Clean keyword.
        //2 Get url for Wikipedia
        //3 Make get request to wikipedia
        //4 Parsing useful results using jsoup
        //5 showing results
        if (this.keyword == null || this.keyword.length() == 0) {
            return null;
        }

        this.keyword = this.keyword.trim().replaceAll("[ ]+", "_");

        String wikiUrl = getWikipediaUrlForQuery(this.keyword);

        String response = "";
        String imageUrl = null;

        try {

            String wikipediaResponseHTML = HttpUrlConnection.sendGet(wikiUrl);
            // System.out.println(wikipediaResponseHTML);

            Document document = Jsoup.parse(wikipediaResponseHTML ,"https://en.wikipedia.org");
            Elements childElements = document.body().select(".mw-parser-output > *");
             //int childElements them alt + enter it automatically changes the datatype


            //this below code implements automata
            int state = 0;
            for (Element chileElement : childElements) {
                if (state == 0) {
                    if (chileElement.tagName().equals("table")) {
                        state = 1;
                    }
                } else if (state == 1) {
                    if (chileElement.tagName().equals("p")) {
                        {
                            state = 2;
                            response = chileElement.text();
                            break;
                        }
                    }
                }
                try {
                    imageUrl = document.body().select(".infobox img").get(0).attr("src");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }




        //you can push result into database as of now lets just use json

        WikiResult wikiresult = new WikiResult(this.keyword,response,imageUrl);

        //below code return data in json format
        /*Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(wikiresult);
        return json;*/

        //below code returns html

        return wikiresult;





    }


    private String getWikipediaUrlForQuery(String cleanKeyword) {
        return "https://en.wikipedia.org/wiki/" + cleanKeyword;
    }



}
