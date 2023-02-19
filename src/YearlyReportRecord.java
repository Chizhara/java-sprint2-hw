public class YearlyReportRecord {
    Integer income;
    Integer expense;


    public void getInfoFromCsvRecord(String[] line){
        if(Boolean.parseBoolean(line[2]))
            expense = Integer.parseInt(line[1]);
        else
            income = Integer.parseInt(line[1]);
    }
}
