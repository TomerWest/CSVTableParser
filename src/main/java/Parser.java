import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Parser {
    public static Parser instance;
    private String file;
    private String[] headers;

    private CSVParser csvParser = null;

    public static Parser getInstance() {
        if(instance == null) {
            instance = new Parser();
        }

        return instance;
    }

    public void setFile(String file, String... headers) {
        this.file = file;
        this.headers = headers;
    }

    public void setHeaders(String... headers) {
        this.headers = headers;
    }

    public String[] getHeaders() {
        return headers;
    }

    private void initParser() {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(file));
            csvParser = new CSVParser(reader, CSVFormat.EXCEL.withFirstRecordAsHeader()
            .withIgnoreHeaderCase()
            .withTrim()
            );
        } catch (IOException e) {
            System.err.println("Error while init parser");
            System.exit(1);
        }

    }

    public double[][] parse() {
        initParser();
        ArrayList<double[]> parsed = new ArrayList<double[]>();

        for (CSVRecord csvRecord: csvParser) {
            double[] temp = new double[headers.length];
            for (int i = 0; i < headers.length; i++) {
                temp[i] = Double.parseDouble(csvRecord.get(headers[i]));
            }
            parsed.add(temp);
        }

        double[][] ret = new double[parsed.size()][headers.length];
        for (int i = 0; i < ret.length; i++) {
            for (int j = 0; j < ret[i].length; j++) {
                ret[i][j] = parsed.get(i)[j];
            }
        }

        return ret;
    }
}