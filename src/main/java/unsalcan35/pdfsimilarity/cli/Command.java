package unsalcan35.pdfsimilarity.cli;

import picocli.CommandLine;

import java.io.File;
import java.util.List;

public class Command {

    @CommandLine.Parameters()
    private List<File> files;

    @CommandLine.Option(names = "-w")
    private int byTopNWords = 10;

    @CommandLine.Option(names = "-sa")
    private SimilarityAlgorithm similarityAlgorithm = SimilarityAlgorithm.apriori;

    @CommandLine.Option(names = "-h", usageHelp = true)
    private boolean helpRequested = false;


    public List<File> getFiles() {
        return files;
    }

    public int getByTopNWords() {
        return byTopNWords;
    }

    public SimilarityAlgorithm getSimilarityAlgorithm() {
        return similarityAlgorithm;
    }

    public boolean isHelpRequested() {
        return helpRequested;
    }
}
