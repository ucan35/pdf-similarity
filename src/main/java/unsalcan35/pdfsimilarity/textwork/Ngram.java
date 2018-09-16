package unsalcan35.pdfsimilarity.textwork;

import java.util.ArrayList;
import java.util.List;

/**
 * +
 * Ngram üreten yardımcı sınıf
 */
public class Ngram {
    public static String[] generate(String word, int n) {
        if (word == null) {
            return null;
        }
        word = "_" + word + "_";
        int len = word.length();
        if (len <= n) {
            return new String[]{word};
        }
        List<String> ngrams = new ArrayList<>();
        for (int i = 0; i < len - n + 1; i++) {
            ngrams.add(word.substring(i, i + n));
        }
        return ngrams.toArray(new String[0]);
    }
}
