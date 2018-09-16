package unsalcan35.pdfsimilarity.examiner;


import unsalcan35.pdfsimilarity.extractor.PdfWordContentExtractorException;

public class PdfSimilarityExaminerException extends Exception {
    PdfSimilarityExaminerException(String s, PdfWordContentExtractorException e) {
        super(s, e);
    }
}
