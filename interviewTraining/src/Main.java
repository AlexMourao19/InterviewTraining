import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

class URLShortener {
    private static final String CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final ConcurrentHashMap<Long, String> URLMap = new ConcurrentHashMap<>();
    // private static final HashSet<String> URLSet = new HashSet<>(); // concurrency is not a problem can use HashSet
    private static final ConcurrentHashMap<String, Boolean> URLSet = new ConcurrentHashMap<>();
    static String URLProcessor(String URL) {  // Se usar syncronized aqui nao e a mesma coisa que usar os ConcurrentHashMap?
        if (URLSet.putIfAbsent(URL, true) != null) { // putIfAbsent is an atomic operation
            System.out.println("URL already shortened");
            return null;
        }
        SnowflakeIDGenerator idGenerator = new SnowflakeIDGenerator(1);
        long id = idGenerator.nextId();
        String shortURL = idToShortURL(id);
        URLMap.putIfAbsent(id, URL);

        return shortURL;
    }

    static String idToShortURL(long n) {
        char [] map = CHARS.toCharArray();
        StringBuffer newURL = new StringBuffer();
        while (n > 0) {
            newURL.append(map[(int)(n % 62)]);
            n = n / 62;
        }
        String shortURL = "www.https://shorturl.at/" + newURL.reverse();

        return shortURL;
    }

    static Long shortURLToId(String URL) {
        String prefix = "www.https://shorturl.at/";
        String shortURL = URL.replaceFirst(prefix, "");

        Long id = 0L;
        for (int i = 0; i < shortURL.length(); i++) {
            char URLChar = shortURL.charAt(i);
            if ('a' <= URLChar && URLChar <= 'z') {
                id = id * 62 + URLChar - 'a';
            }
            if ('A' <= URLChar && URLChar <= 'Z') {
                id = id * 62 + URLChar - 'A' + 26;
            }
            if ('0' <= URLChar && URLChar <= '9') {
                id = id * 62 + URLChar - '0' + 52;
            }
        }
        return id;
    }

    static void HashPrinter() {
        System.out.println("HashMap: ");
        System.out.println(URLMap);
    }
}


public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Number of URLs: ");
        int tc = Integer.parseInt(br.readLine().trim());

        while (tc-- > 0) {
            System.out.println("Write an URL: ");
            String input = br.readLine().trim();
            String url = new URLShortener().URLProcessor(input);
            System.out.println("Result: ");
            System.out.println(url);
            new URLShortener().HashPrinter();
            System.out.println("-------------");
        }
    }
}
