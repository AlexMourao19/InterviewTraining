package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class URLShortenerTest {
    private URLShortener urlShortener;

    @BeforeEach
    void setUp() {
        urlShortener = new URLShortener(10, "alex.Test", new IncrementalGenerator());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void generateShortUrl_overCapacity_throwsExeption() {
        // When
        urlShortener = new URLShortener(0, "alex.Test");
        // Act
        // Assert
        assertThrows(IllegalStateException.class, () -> urlShortener.generateShortUrl("https://meet.google.com/wng-rqtu-hnf"));
    }

    @Test
    public void generateShortUrl_sameLongUrl_returnsSameShortUrl() {
        // When

        String sameUrl = "https://meet.google.com/wng-rqtu-hnf";
        // Act
        String firstShortUrl = urlShortener.generateShortUrl(sameUrl);
        String secondShortUrl = urlShortener.generateShortUrl(sameUrl);
        // Assert
        assertThat(firstShortUrl).isEqualTo(secondShortUrl);
        assertThat(firstShortUrl).isNotEmpty();
    }

    @Test
    public void generateShortUrl_size1_returnsSameShortUrl() {
        // When
        urlShortener = new URLShortener(1, "alex.Test");
        String sameUrl = "https://meet.google.com/wng-rqtu-hnf";
        // Act
        String firstShortUrl = urlShortener.generateShortUrl(sameUrl);
        String secondShortUrl = urlShortener.generateShortUrl(sameUrl);
        // Assert
        assertThat(firstShortUrl).isEqualTo(secondShortUrl);
        assertThat(firstShortUrl).isNotEmpty();
    }

    @Test
    public void generateShortUrl_differentLongUrl_returnsDifferentShortUrl() {
        // When
        String url1 = "https://meet.google.com/wng-rqtu-hnf";
        String url2 = "https://meet.google.com/okv-vupk-pmw";
        // Act
        String firstShortUrl = urlShortener.generateShortUrl(url1);
        String secondShortUrl = urlShortener.generateShortUrl(url2);
        // Assert
        assertThat(firstShortUrl).isNotEqualTo(secondShortUrl);
        assertThat(firstShortUrl).isNotEmpty();
        assertThat(secondShortUrl).isNotEmpty();
    }

    @Test
    public void generateShortUrl_longUrl_returnsDifferentShortUrl() {
        // When
        String longUrl = "https://meet.google.com/wng-rqtu-hnf";
        // Act
        String shortUrl = urlShortener.generateShortUrl(longUrl);
        // Assert
        assertThat(shortUrl).isNotEqualTo(longUrl);
    }

    @Test
    public void generateShortUrl_shortUrl_returnsDifferentShortUrl() {
        // When
        String longUrl = "https://meet.google.com/wng-rqtu-hnf";
        // Act
        String shortUrl = urlShortener.generateShortUrl(longUrl);
        // Assert
        assertThat(shortUrl).isNotEqualTo(longUrl);
    }
}