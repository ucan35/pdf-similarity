package unsalcan35.pdfsimilarity.textwork.similarity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * +
 * Sözcüklerin ve sözcük tekrar sayılarının tutulduğu sınıf
 * Ağırlık(weight) olarak bahsedilenler sözcüklerin tekrar sayısını temsil eder
 */
public class TextInfoSet {
    private final Map<String, Integer> textMap;

    public TextInfoSet(Map<String, Integer> textMap) {
        this.textMap = textMap;
    }

    List<String> getTextList() {
        return new ArrayList<>(textMap.keySet());
    }

    int getTextWeightOrDefault(String text, int defaultValue) {
        return textMap.getOrDefault(text, defaultValue);
    }
}
