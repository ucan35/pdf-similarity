package unsalcan35.pdfsimilarity.textwork.similarity;

/**
 * +
 * Strateji deseni için oluşturulmuş interface
 */
public interface TextSimilarityStrategy {
    double calculate(TextInfoSet textSet1, TextInfoSet textSet2);
}
