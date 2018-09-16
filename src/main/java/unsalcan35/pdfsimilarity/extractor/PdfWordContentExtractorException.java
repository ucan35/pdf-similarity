package unsalcan35.pdfsimilarity.extractor;

import unsalcan35.pdfsimilarity.parser.PdfTextParserException;

public class PdfWordContentExtractorException extends Exception {
    PdfWordContentExtractorException(String s, PdfTextParserException e) {
        super(s, e);
    }
}
