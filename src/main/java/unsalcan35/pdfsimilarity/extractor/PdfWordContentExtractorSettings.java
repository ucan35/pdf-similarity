package unsalcan35.pdfsimilarity.extractor;

/**
 * +
 * PdfWordContentExtractor için kullanılacak ayarları barındırır
 */
public class PdfWordContentExtractorSettings {
    final boolean ignoreCase;
    final int wordAndTrigramSize;

    public PdfWordContentExtractorSettings(int wordAndTrigramSize, boolean ignoreCase) {
        this.wordAndTrigramSize = wordAndTrigramSize;
        this.ignoreCase = ignoreCase;
    }
}
