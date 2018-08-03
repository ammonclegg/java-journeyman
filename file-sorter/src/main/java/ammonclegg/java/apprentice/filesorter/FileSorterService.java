package ammonclegg.java.apprentice.filesorter;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ammonclegg on 8/3/18.
 */
@Service
public class FileSorterService {
  private List<String> lines = new LinkedList<>();

  private void readLines(BufferedReader inputFile) throws IOException {
    String line = inputFile.readLine();
    while (line != null) {
      lines.add(line);
      line = inputFile.readLine();
    }
  }

  private void outputLines(BufferedWriter outputFile) throws IOException {
    for(String outputLines: lines) {
      outputFile.write(outputLines);
      outputFile.newLine();
    }
    outputFile.close();
  }

  public void sortFile(BufferedReader inputFile, BufferedWriter outputFile, boolean reverse) throws IOException {
    readLines(inputFile);

    if (reverse) {
      lines.sort(Collections.reverseOrder());
    }
    else {
      Collections.sort(lines);
    }

    outputLines(outputFile);
  }

  public void sortFile(String inputFileName, String outputFileName, boolean reverse) throws IOException {
    BufferedReader inputFile = new BufferedReader(new FileReader(inputFileName));
    BufferedWriter outputFile = new BufferedWriter(new FileWriter(outputFileName));
    sortFile(inputFile, outputFile, reverse);
  }
}
