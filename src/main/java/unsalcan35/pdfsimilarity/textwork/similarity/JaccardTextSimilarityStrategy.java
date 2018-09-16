package unsalcan35.pdfsimilarity.textwork.similarity;

/**
 * +
 * Jaccard yöntemini kullanarak yazı benzerliğini bulur(Ağırlıklarıyla)
 * Kesişim ve birleşimleri ele alır
 * 0 ila 1 arasında değer döndürür
 */
public class JaccardTextSimilarityStrategy implements TextSimilarityStrategy {
    @Override
    public double calculate(TextInfoSet textSet1, TextInfoSet textSet2) {
        double unionWeight = 0;
        double intersectionWeight = 0;
        for (String text : textSet1.getTextList()) {
            int weight1 = textSet1.getTextWeightOrDefault(text, 0);
            int weight2 = textSet2.getTextWeightOrDefault(text, 0);
            unionWeight += Math.max(weight1, weight2);
            intersectionWeight += Math.min(weight1, weight2);
        }
        for (String text : textSet2.getTextList()) {
            int weight = textSet1.getTextWeightOrDefault(text, 0);
            if (weight == 0) {
                unionWeight += textSet2.getTextWeightOrDefault(text, 0);
            }
        }
        return intersectionWeight / unionWeight;
    }
}
