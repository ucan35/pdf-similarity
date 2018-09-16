package unsalcan35.pdfsimilarity.parser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class PdfTextParserTest {
    private static final String WORD_REGEX = "[a-zA-Z]+";
    private final PdfTextParserSettings settings = new PdfTextParserSettings(WORD_REGEX);

    private static final String SAMPLE_PDF_URI = "src/test/resources/pdf-sample.pdf";
    private File pdf;

    @Before
    public void loadPdf() throws IOException {
        pdf = new File(SAMPLE_PDF_URI);
    }

    @Test
    public void parseWords() throws PdfTextParserException {
        PdfTextParser parser = new PdfTextParser(pdf, settings);
        Assert.assertEquals(true, parser.hasNext());
        Assert.assertEquals("Adobe", parser.next());
        for (int i = 0; i < 168; i++) {
            parser.next();
        }
        Assert.assertEquals(true, parser.hasNext());
        Assert.assertEquals("Web", parser.next());
        Assert.assertEquals(false, parser.hasNext());
    }
}