package unsalcan35.pdfsimilarity.model;

/**
 * +
 * Pdf benzerlik oranlarını bulunduran model sınıfı
 * Ayrıca mongodb'ye kayıtlar da bu model sınıfı üstünden gerçekleşmektedir
 */
public class PdfSimilarityScore implements Comparable<PdfSimilarityScore> {
    private final String pdf1Name;
    private final String pdf2Name;
    private final Double scoreByWords;
    private final Double scoreByTrigrams;
    private final String strategyType;

    public PdfSimilarityScore(String pdf1Name, String pdf2Name,
                              Double scoreByWords, Double scoreByTrigrams, String strategyType) {
        this.pdf1Name = pdf1Name;
        this.pdf2Name = pdf2Name;
        this.scoreByWords = scoreByWords;
        this.scoreByTrigrams = scoreByTrigrams;
        this.strategyType = strategyType;
    }

    public String getPdf1Name() {
        return pdf1Name;
    }

    public String getPdf2Name() {
        return pdf2Name;
    }

    public Double getScoreByWords() {
        return scoreByWords;
    }

    public Double getScoreByTrigrams() {
        return scoreByTrigrams;
    }

    public String getStrategyType() {
        return strategyType;
    }

    @Override
    public int compareTo(PdfSimilarityScore comp) {
        double thisScore = scoreByWords + scoreByTrigrams / 2d;
        double compScore = comp.getScoreByWords() + comp.getScoreByTrigrams() / 2d;
        double diff = thisScore - compScore;
        if (diff == 0) {
            return 0;
        }
        if (diff > 0) {
            return 1;
        }
        return -1;
    }

    @Override
    public String toString() {
        return "Pdf1: \"" + pdf1Name + "\", Pdf2: \"" + pdf2Name + "\" Similarity Score: " + (scoreByWords + scoreByTrigrams / 2d);
    }
}
