package unsalcan35.pdfsimilarity.textwork.similarity;

/**
 * +
 * Apriori yöntemini kullanarak yazı benzerliğini bulur
 * Kesişimleri ele alarak 0 ile sonsuz arasında değer verir
 * Döndürülen değerin 0 olması benzerliğin hiç olmadığı anlamına gelir
 * Değer arttıkça benzerlik de artar
 */
public class AprioriTextSimilarityStrategy implements TextSimilarityStrategy {
    @Override
    public double calculate(TextInfoSet textSet1, TextInfoSet textSet2) {
        double intersectionWeight = 0;
        for (String text : textSet1.getTextList()) {
            int weight1 = textSet1.getTextWeightOrDefault(text, 0);
            int weight2 = textSet2.getTextWeightOrDefault(text, 0);
            intersectionWeight += Math.min(weight1, weight2);
        }
        return intersectionWeight;
    }
}
