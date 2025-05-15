package Util;

import Model.Book;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class GoogleBooksApi {

    private static final String GOOGLE_BOOKS_API = "https://www.googleapis.com/books/v1/volumes?q=";

    public static List<Book> searchBooks(String query) {
        List<Book> books = new ArrayList<>();

        try {
            String encodedQuery = URLEncoder.encode(query, "UTF-8");
            URL url = new URL(GOOGLE_BOOKS_API + encodedQuery);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
            );
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();
            connection.disconnect();

            JSONObject json = new JSONObject(content.toString());
            JSONArray items = json.optJSONArray("items");
            if (items != null) {
                for (int i = 0; i < items.length(); i++) {
                    JSONObject volumeInfo = items.getJSONObject(i).getJSONObject("volumeInfo");

                    String title = volumeInfo.optString("title", "N/A");
                    String author = "Unknown";
                    JSONArray authorsArray = volumeInfo.optJSONArray("authors");
                    if (authorsArray != null && authorsArray.length() > 0) {
                        author = authorsArray.getString(0);
                    }

                    String publisher = volumeInfo.optString("publisher", "Unknown");

                    int year = 0;
                    if (volumeInfo.has("publishedDate")) {
                        String pubDate = volumeInfo.getString("publishedDate");
                        if (pubDate.length() >= 4) {
                            try {
                                year = Integer.parseInt(pubDate.substring(0, 4));
                            } catch (NumberFormatException e) {
                                year = 0;
                            }
                        }
                    }

                    String genre = "Unknown";

                    Book book = new Book(title, author, publisher, year, genre);
                    books.add(book);
                }
            }

        } catch (Exception e) {
            System.err.println("Lỗi khi truy vấn Google Books API: " + e.getMessage());
        }

        return books;
    }
}
