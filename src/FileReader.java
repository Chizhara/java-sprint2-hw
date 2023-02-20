import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public class FileReader {
    public List<String> readFileContents(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    public String defineMonthReportFileName(int monthNum, int yearNum){
        String fileNameResult = "./resources/m." + yearNum;

        if(monthNum < 10)
            fileNameResult +="0" + monthNum + ".csv";
        else
            fileNameResult += monthNum + ".csv";
        return fileNameResult;
    }

    public String defineYearReportFileName(int yearNum){
        return "./resources/y." + yearNum + ".csv";
    }
}
