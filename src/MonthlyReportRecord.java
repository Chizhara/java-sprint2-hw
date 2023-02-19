public class MonthlyReportRecord {
    String itemName;
    Boolean isExpense;
    Integer quantity;
    Integer sumOfOne;

    public void getInfoFromCsvRecord(String[] line){
        itemName = line[0];
        isExpense = Boolean.parseBoolean(line[1]);
        quantity = Integer.parseInt(line[2]);
        sumOfOne = Integer.parseInt(line[3]);
    }

    public int getProfit(){
        return quantity * sumOfOne;
    }
}

