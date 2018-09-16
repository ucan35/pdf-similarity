package unsalcan35.pdfsimilarity.examiner;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import unsalcan35.pdfsimilarity.model.PdfSimilarityScore;
import unsalcan35.pdfsimilarity.textwork.similarity.AprioriTextSimilarityStrategy;
import unsalcan35.pdfsimilarity.textwork.similarity.JaccardTextSimilarityStrategy;

import java.io.File;
import java.io.IOException;

public class PdfSimilarityExaminerTest {
    private final static JaccardTextSimilarityStrategy JACCARD_STRATEGY = new JaccardTextSimilarityStrategy();
    private final static AprioriTextSimilarityStrategy APRIORI_STRATEGY = new AprioriTextSimilarityStrategy();

    private static final String SAMPLE_PDF_URI = "src/test/resources/pdf-sample.pdf";
    private static final String SAMPLE_PDF_SIMILAR_URI = "src/test/resources/pdf-sample-similar.pdf";
    private static final String SAMPLE_PDF12PAGED_URI = "src/test/resources/p283-babu.pdf";
    private File pdf;
    private File pdfSimilar;
    private File pdfNotSimilar;

    @Before
    public void loadPdf() throws IOException {
        pdf = new File(SAMPLE_PDF_URI);
        pdfSimilar = new File(SAMPLE_PDF_SIMILAR_URI);
        pdfNotSimilar = new File(SAMPLE_PDF12PAGED_URI);
    }

    @Test
    public void examineWithJaccardStrategy() throws PdfSimilarityExaminerException, IOException {
        PdfSimilarityExaminer examiner = new PdfSimilarityExaminer(getJaccardSettings());
        PdfSimilarityScore scoreOnSimilar = examiner.examine(pdf, pdfSimilar);
        PdfSimilarityScore scoreOnDifferent = examiner.examine(pdf, pdfNotSimilar);
        Assert.assertEquals("JaccardTextSimilarityStrategy", scoreOnSimilar.getStrategyType());
        Assert.assertTrue(scoreOnSimilar.getScoreByWords() > scoreOnDifferent.getScoreByWords());
        Assert.assertTrue(scoreOnSimilar.getScoreByTrigrams() > scoreOnDifferent.getScoreByTrigrams());
    }

    @Test
    public void examineWithAprioriStrategy() throws PdfSimilarityExaminerException, IOException {
        PdfSimilarityExaminer examiner = new PdfSimilarityExaminer(getAprioriSettings());
        PdfSimilarityScore scoreOnSimilar = examiner.examine(pdf, pdfSimilar);
        PdfSimilarityScore scoreOnDifferent = examiner.examine(pdf, pdfNotSimilar);
        Assert.assertEquals("AprioriTextSimilarityStrategy", scoreOnSimilar.getStrategyType());
        Assert.assertTrue(scoreOnSimilar.getScoreByWords() > scoreOnDifferent.getScoreByWords());
        Assert.assertTrue(scoreOnSimilar.getScoreByTrigrams() > scoreOnDifferent.getScoreByTrigrams());
    }

    private PdfSimilarityExaminerSettings getJaccardSettings() {
        return new PdfSimilarityExaminerSettings(JACCARD_STRATEGY, 10, true);
    }

    private PdfSimilarityExaminerSettings getAprioriSettings() {
        return new PdfSimilarityExaminerSettings(APRIORI_STRATEGY, 10, true);
    }
}