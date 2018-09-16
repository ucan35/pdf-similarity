package unsalcan35.pdfsimilarity.examiner;


import unsalcan35.pdfsimilarity.extractor.PdfWordContentExtractor;
import unsalcan35.pdfsimilarity.extractor.PdfWordContentExtractorException;
import unsalcan35.pdfsimilarity.extractor.PdfWordContentExtractorSettings;
import unsalcan35.pdfsimilarity.model.PdfSimilarityScore;
import unsalcan35.pdfsimilarity.model.PdfWordContent;
import unsalcan35.pdfsimilarity.textwork.similarity.TextInfoSet;
import unsalcan35.pdfsimilarity.textwork.similarity.TextSimilarityCalculator;
import unsalcan35.pdfsimilarity.textwork.similarity.TextSimilarityStrategy;

import java.io.File;

/**
 * +
 * Pdf benzerliklerinin incelendiği sınıf
 * İkili(binary)dir, bir anda sadece 2 tane pdf'in birbirine olan benzerliğini hesaplar
 */
public class PdfSimilarityExaminer {

    /**
     * +
     *
     * @param settings Benzerlik hesaplamasında kullanılacak bazı kriterleri bulundurur
     */
    public PdfSimilarityExaminer(PdfSimilarityExaminerSettings settings) {
        initializeExtractor(settings.byTopNWords, settings.ignoreCase);
        initializeTextSimilarityCalculator(settings.strategy);
    }

    private PdfWordContentExtractor extractor;

    private void initializeExtractor(int byTopNWords, boolean ignoreCase) {
        extractor = new PdfWordContentExtractor(
                new PdfWordContentExtractorSettings(byTopNWords, ignoreCase));
    }

    private TextSimilarityCalculator calculator;
    private String strategyType;

    private void initializeTextSimilarityCalculator(TextSimilarityStrategy strategy) {
        calculator = new TextSimilarityCalculator(strategy);
        strategyType = strategy.getClass().getSimpleName();
    }

    /**
     * +
     *
     * @param file1
     * @param file2
     * @return İçerisinde benzerlik skorunu bulunduran PdfSimilarityScore nesnesi döndürür
     * @throws PdfSimilarityExaminerException
     */
    public PdfSimilarityScore examine(File file1, File file2) throws PdfSimilarityExaminerException {
        PdfWordContent content1;
        PdfWordContent content2;
        try {
            content1 = extractor.extract(file1);
            content2 = extractor.extract(file2);
        } catch (PdfWordContentExtractorException e) {
            // TODO Logging
            throw new PdfSimilarityExaminerException("Failed to extract pdf contents", e);
        }
        double scoreByWords = getSimilarityScoreByWords(content1, content2);
        double scoreByTrigrams = getSimilarityScoreByTrigrams(content1, content2);
        return new PdfSimilarityScore(file1.getName(), file2.getName(), scoreByWords, scoreByTrigrams, strategyType);
    }

    private double getSimilarityScoreByWords(PdfWordContent content1, PdfWordContent content2) {
        TextInfoSet wordSet1 = new TextInfoSet(content1.getTopWords());
        TextInfoSet wordSet2 = new TextInfoSet(content2.getTopWords());
        return calculator.calculate(wordSet1, wordSet2);
    }

    private double getSimilarityScoreByTrigrams(PdfWordContent content1, PdfWordContent content2) {
        TextInfoSet wordSet1 = new TextInfoSet(content1.getTopTrigrams());
        TextInfoSet wordSet2 = new TextInfoSet(content2.getTopTrigrams());
        return calculator.calculate(wordSet1, wordSet2);
    }
}
