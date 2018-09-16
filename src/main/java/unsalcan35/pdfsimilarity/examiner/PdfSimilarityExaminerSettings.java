package unsalcan35.pdfsimilarity.examiner;


import unsalcan35.pdfsimilarity.textwork.similarity.TextSimilarityStrategy;

/**
 * +
 * PdfSimilarityExaminer'da kullanılacak olan ayarları içerir
 */
public class PdfSimilarityExaminerSettings {
    final TextSimilarityStrategy strategy;
    final int byTopNWords;
    final boolean ignoreCase;

    /**
     * +
     *
     * @param strategy
     * @param byTopNWords En çok tekrarlarnan N sözcüğü alarak karşılaştırma yapmasını söyler
     * @param ignoreCase
     */
    public PdfSimilarityExaminerSettings(TextSimilarityStrategy strategy, int byTopNWords, boolean ignoreCase) {
        this.strategy = strategy;
        this.byTopNWords = byTopNWords;
        this.ignoreCase = ignoreCase;
    }
}
