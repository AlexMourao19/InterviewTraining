package org.example;

import java.util.HashMap;
import java.util.Map;
public class URLShortener {
    private int capacity;
    final Map<String, String> longUrlToShortUrl = new HashMap<>();
    private String domain;
    public IdGenerator idGenerator;
    public URLShortener(int capacity, String domain, IdGenerator idGenerator) {
        this.domain = domain;
        this.capacity = capacity;
        this.idGenerator = idGenerator;
    }

    public String generateShortUrl(String url) {
        if (longUrlToShortUrl.containsKey(url)) {
            return longUrlToShortUrl.get(url);
        }

        if (longUrlToShortUrl.size() >= this.capacity) {
            throw new IllegalStateException("Reached maximum capacity");
        }
        String shortUrl = idToShortUrl(this.idGenerator.generateId());
        longUrlToShortUrl.put(url, shortUrl);
        return longUrlToShortUrl.get(url);
    }

    private String idToShortUrl(String id) {
        return String.format("https://%s/%s", this.domain, id);
    }

}