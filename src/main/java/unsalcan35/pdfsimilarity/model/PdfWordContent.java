package unsalcan35.pdfsimilarity.model;

import java.util.Collections;
import java.util.Map;

/**
 * +
 * Pdf içerisindeki sözcük/trigramları ve tekrar sayılarını barındıran model sınıf
 * Ayrıca mongodb'ye kayıtlar da bu model sınıfı üstünden gerçekleşmektedir
 */
public class PdfWordContent {
    private final String pdfName;
    private final Map<String, Integer> topWords;
    private final Map<String, Integer> topTrigrams;

    public PdfWordContent(String pdfName, Map<String, Integer> topWords, Map<String, Integer> topTrigrams) {
        this.pdfName = pdfName;
        this.topWords = topWords;
        this.topTrigrams = topTrigrams;
    }

    public String getPdfName() {
        return pdfName;
    }

    public Map<String, Integer> getTopWords() {
        if (topWords == null) {
            return null;
        }
        return Collections.unmodifiableMap(topWords);
    }

    public Map<String, Integer> getTopTrigrams() {
        if (topTrigrams == null) {
            return null;
        }
        return Collections.unmodifiableMap(topTrigrams);
    }
}
