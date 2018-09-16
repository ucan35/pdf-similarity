# Pdf Similarity

A school project. Parses and extracts words in given 2 pdf file. Finds similarity score with those words based on specified similarity 
algorithm. Used Apache PDFBox for pdf parsing and picocli for command line interface.  

* [Apriori](https://en.wikipedia.org/wiki/Apriori_algorithm)
* [Jaccard](https://en.wikipedia.org/wiki/Jaccard_index)
* [Apache PDFBox](https://pdfbox.apache.org/)
* [picocli](http://picocli.info/)

# Usage & Example

```sh
$ java -jar pdfsimilarity.jar -h
Usage: pdfsimilarity [-h] [-sa=<apriori, jaccard>] [-w=<byTopNWords>] [<files>...]
  [<files>...]				the 2 files to examine similarity
  -sa=<apriori, jaccard>	similarity algorithm to use (default: apriori)
  -w=<byTopNWords>   		examine by top N words (default: 10)
  -h                  		display a help message
$ java -jar pdfsimilarity.jar a.pdf b.pdf -sa apriori -w 10
Examining two files: a.pdf, b.pdf ...
Score: 74.0
```

# Build & Run

You can skip tests with -DskipTests if you want.

```sh
$ mvn install
$ java -jar target/pdfsimilarity.jar a.pdf b.pdf
```
