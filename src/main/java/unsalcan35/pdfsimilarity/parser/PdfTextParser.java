package unsalcan35.pdfsimilarity.parser;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * +
 * Pdf'i yazıya(String) çeviren sınıf
 * Iterator deseni kullanılarak oluşturulmuştur, böylelikle sözcükler üstünde işlem yapılması kolaylaştırılmıştır
 * <p>
 * İlk olarak bütün pdf'i PdfBox aracılığı ile string'e çevirir
 * Daha sonra oluşturulan stringi REGEX ile parçalar ve bu parçaları iterator deseni
 * kullanarak kullanıcısına iletir
 */
public class PdfTextParser implements Iterator<String> {
    public PdfTextParser(File file, PdfTextParserSettings settings) throws PdfTextParserException {
        try {
            initializeParser(PDDocument.load(file), settings);
        } catch (IOException e) {
            // TODO Logging
            throw new PdfTextParserException("Failed to initialize parser", e);
        }
    }

    private Iterator<String> iterator;

    private void initializeParser(PDDocument pdDocument, PdfTextParserSettings settings) throws IOException {
        String pages = readPages(pdDocument);
        List<String> tokens = parseTokens(pages, settings.tokenRegex);
        iterator = tokens.iterator();
    }

    private String readPages(PDDocument pdDocument) throws IOException {
        PDFTextStripper textStripper = new PDFTextStripper();
        return textStripper.getText(pdDocument);
    }

    private List<String> parseTokens(String pages, String tokenRegex) {
        Matcher matcher = Pattern.compile(tokenRegex).matcher(pages);
        List<String> tokens = new ArrayList<>();
        while (matcher.find()) {
            tokens.add(matcher.group());
        }
        return tokens;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public String next() {
        return iterator.next();
    }
}
