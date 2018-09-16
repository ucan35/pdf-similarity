package unsalcan35.pdfsimilarity;

import picocli.CommandLine;
import unsalcan35.pdfsimilarity.cli.Command;
import unsalcan35.pdfsimilarity.examiner.PdfSimilarityExaminer;
import unsalcan35.pdfsimilarity.examiner.PdfSimilarityExaminerException;
import unsalcan35.pdfsimilarity.examiner.PdfSimilarityExaminerSettings;
import unsalcan35.pdfsimilarity.model.PdfSimilarityScore;
import unsalcan35.pdfsimilarity.textwork.similarity.AprioriTextSimilarityStrategy;
import unsalcan35.pdfsimilarity.textwork.similarity.JaccardTextSimilarityStrategy;
import unsalcan35.pdfsimilarity.textwork.similarity.TextSimilarityStrategy;

import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args) throws PdfSimilarityExaminerException {

        Command command = CommandLine.populateCommand(new Command(), args);
        if (command.isHelpRequested()) {
            String help = "Usage: pdfsimilarity [-h] [-sa=<apriori, jaccard>] [-w=<byTopNWords>] [<files>...]\n" +
                    "  [<files>...]\t\t\t\tthe 2 files to examine similarity\n" +
                    "  -sa=<apriori, jaccard>\tsimilarity algorithm to use (default: apriori)\n" +
                    "  -w=<byTopNWords>   \t\texamine by top N words (default: 10)\n" +
                    "  -h                  \t\tdisplay a help message";
            System.out.println(help);
            return;
        }

        List<File> files = command.getFiles();
        if (files == null || files.size() < 2) {
            System.out.println("Please specify 2 pdf files");
            return;
        }

        TextSimilarityStrategy similarityStrategy = null;
        switch (command.getSimilarityAlgorithm()) {
            case apriori:
                similarityStrategy = new AprioriTextSimilarityStrategy();
                break;
            case jaccard:
                similarityStrategy = new JaccardTextSimilarityStrategy();
                break;
        }

        PdfSimilarityExaminer similarityExaminer = new PdfSimilarityExaminer(
                new PdfSimilarityExaminerSettings(similarityStrategy, command.getByTopNWords(), true));

        java.util.logging.Logger
                .getLogger("org.apache.pdfbox").setLevel(java.util.logging.Level.OFF);
        System.out.println("Examining two files: " + 
files.get(0).getName() + ", " + files.get(1).getName() + " ...");
        PdfSimilarityScore examine = similarityExaminer.examine(files.get(0), files.get(1));
        System.out.println("Score: " + (examine.getScoreByTrigrams() + examine.getScoreByWords()) / 2);
    }
}
