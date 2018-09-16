package unsalcan35.pdfsimilarity.extractor;


import org.javatuples.Pair;
import unsalcan35.pdfsimilarity.collections.CollectionsUtil;
import unsalcan35.pdfsimilarity.model.PdfWordContent;
import unsalcan35.pdfsimilarity.parser.PdfTextParser;
import unsalcan35.pdfsimilarity.parser.PdfTextParserException;
import unsalcan35.pdfsimilarity.parser.PdfTextParserSettings;
import unsalcan35.pdfsimilarity.textwork.Ngram;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * +
 * Pdf içerisindeki sözcükleri ve sözcüklerin tekrar sayısını bulmaya yarayan sınıf
 * PdfTextParser sınıfını kullanarak hangi sözcüğün ne kadar geçtiğini bulur
 * Aynı zamanda geçen sözcüklerin trigramlarını ve tekrar sayılarını bulur
 */
public class PdfWordContentExtractor {
    private static final CollectionsUtil COLLECTIONS_UTIL = CollectionsUtil.getInstance();
    private final PdfWordContentExtractorSettings extractorSettings;

    public PdfWordContentExtractor(PdfWordContentExtractorSettings settings) {
        extractorSettings = settings;
        initializeExtractor();
    }

    private static final String WORD_REGEX = "[a-zA-Z]+";
    private PdfTextParserSettings parserSettings;

    private void initializeExtractor() {
        parserSettings = new PdfTextParserSettings(WORD_REGEX);
    }

    @SuppressWarnings("unchecked")
    public PdfWordContent extract(File file) throws PdfWordContentExtractorException {
        PdfTextParser parser;
        try {
            parser = new PdfTextParser(file, parserSettings);
        } catch (PdfTextParserException e) {
            // TODO Logging
            throw new PdfWordContentExtractorException("Failed to extract", e);
        }
        Pair<Map, Map> wordsAndTrigrams = readWordsAndTrigramsWithCounts(parser, extractorSettings.ignoreCase);
        LinkedHashMap topWords = COLLECTIONS_UTIL.sortMapByValue(wordsAndTrigrams.getValue0(), true);
        LinkedHashMap topTrigrams = COLLECTIONS_UTIL.sortMapByValue(wordsAndTrigrams.getValue1(), true);
        return new PdfWordContent(file.getName(), COLLECTIONS_UTIL.subMap(topWords, 0, extractorSettings.wordAndTrigramSize),
                COLLECTIONS_UTIL.subMap(topTrigrams, 0, extractorSettings.wordAndTrigramSize));
    }

    private Pair<Map, Map> readWordsAndTrigramsWithCounts(PdfTextParser parser, boolean ignoreCase) {
        Map<String, Integer> words = new HashMap<>();
        Map<String, Integer> triGrams = new HashMap<>();
        for (; parser.hasNext(); ) {
            String word = ignoreCase ? parser.next().toLowerCase() : parser.next();
            words.put(word, words.getOrDefault(word, 0) + 1);
            for (String trigram : Ngram.generate(word, 3)) {
                triGrams.put(trigram, triGrams.getOrDefault(trigram, 0) + 1);
            }
        }
        return new Pair<>(words, triGrams);
    }
}
