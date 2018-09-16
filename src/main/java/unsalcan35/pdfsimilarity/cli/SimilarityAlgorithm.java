package unsalcan35.pdfsimilarity.cli;

public enum SimilarityAlgorithm {
    apriori("apriori"), jaccard("jaccard");

    private final String name;

    SimilarityAlgorithm(String name) {
        this.name = name;
    }
}
