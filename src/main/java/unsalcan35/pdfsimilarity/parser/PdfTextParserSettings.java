package unsalcan35.pdfsimilarity.parser;

public class PdfTextParserSettings {
    final String tokenRegex;

    /**
     * +
     *
     * @param tokenRegex Sözcüklerin nasıl parçalanacağının bilginini içerir
     */
    public PdfTextParserSettings(String tokenRegex) {
        this.tokenRegex = tokenRegex;
    }
}
