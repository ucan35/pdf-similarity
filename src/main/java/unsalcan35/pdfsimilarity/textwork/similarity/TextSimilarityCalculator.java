package unsalcan35.pdfsimilarity.textwork.similarity;

public class TextSimilarityCalculator {
    private final TextSimilarityStrategy strategy;

    public TextSimilarityCalculator(TextSimilarityStrategy strategy) {
        this.strategy = strategy;
    }

    public double calculate(TextInfoSet textSet1, TextInfoSet textSet2) {
        return strategy.calculate(textSet1, textSet2);
    }
}
