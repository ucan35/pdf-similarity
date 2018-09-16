package unsalcan35.pdfsimilarity.parser;

import java.io.IOException;

public class PdfTextParserException extends Exception {
    PdfTextParserException(String s, IOException e) {
        super(s, e);
    }
}
