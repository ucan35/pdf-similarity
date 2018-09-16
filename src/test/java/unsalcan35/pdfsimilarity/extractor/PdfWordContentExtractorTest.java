package unsalcan35.pdfsimilarity.extractor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import unsalcan35.pdfsimilarity.model.PdfWordContent;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;

public class PdfWordContentExtractorTest {
    private static final String SAMPLE_PDF_URI = "src/test/resources/pdf-sample.pdf";
    private static final String SAMPLE_PDF12PAGED_URI = "src/test/resources/p283-babu.pdf";
    private File pdf;
    private File pdf12Paged;

    private static final int WORD_TRIGRAM_SIZE = 5;
    private static final PdfWordContentExtractorSettings settings =
            new PdfWordContentExtractorSettings(WORD_TRIGRAM_SIZE, true);

    @Before
    public void loadPdf() throws IOException {
        pdf = new File(SAMPLE_PDF_URI);
        pdf12Paged = new File(SAMPLE_PDF12PAGED_URI);
    }

    @Test
    public void extractPdfWordContent() throws PdfWordContentExtractorException, IOException {
        PdfWordContentExtractor extractor = new PdfWordContentExtractor(settings);
        PdfWordContent content = extractor.extract(pdf);
        Assert.assertThat(content.getTopWords().keySet(), is(getExpectedTop5Words()));
        Assert.assertThat(content.getTopTrigrams().keySet(), is(getExpectedTop5Trigrams()));
    }

    private Set<String> getExpectedTop5Words() {
        return new HashSet<>(Arrays.asList("the", "pdf", "and", "files", "of"));
    }

    private Set<String> getExpectedTop5Trigrams() {
        return new HashSet<>(Arrays.asList("_an", "_th", "the", "ile", "fil"));
    }

    @Test
    public void extractPdfWordContentFrom12PagedPdf() throws PdfWordContentExtractorException, IOException {
        PdfWordContentExtractor extractor = new PdfWordContentExtractor(settings);
        extractor.extract(pdf12Paged);
    }
}