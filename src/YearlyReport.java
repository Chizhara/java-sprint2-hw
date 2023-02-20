import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class YearlyReport {
    HashMap<Integer ,YearlyReportRecord> yearlyReportRecords = new HashMap<>();

    public void getInfoFromCsvFile(List<String> line){
        YearlyReportRecord yearlyReportRecord;
        int yearNumBuf;

        for(int i = 1; i < line.size(); i++){
            String[] contents = line.get(i).split(",");
            yearNumBuf = Integer.parseInt(contents[0]);

            if(yearlyReportRecords.containsKey(yearNumBuf))
                yearlyReportRecords.get(yearNumBuf).getInfoFromCsvRecord(contents);
            else{
                yearlyReportRecord = new YearlyReportRecord(contents);
                yearlyReportRecords.put(yearNumBuf ,yearlyReportRecord);
            }
        }
    }

    public void printYearlyReport(){
        YearlyReportRecord yearlyReportRecord;
        for(Integer monthNum : yearlyReportRecords.keySet()){
            yearlyReportRecord = yearlyReportRecords.get(monthNum);
            System.out.println("Прибыль за месяц №" + monthNum + ":\n\t" + (yearlyReportRecord.income - yearlyReportRecord.expense));
        }

        int[] avgIncomesAndExpenses = getAvgIncomeAndExpenses();
        System.out.println("\nСредний доход за все месяцы: " + avgIncomesAndExpenses[0]);
        System.out.print("Средний расход за все месяцы: " + avgIncomesAndExpenses[1] + "\n");
    }

    private int[] getAvgIncomeAndExpenses(){
        int[] avgIncomesAndExpenses = {0 , 0};

        for(int i = 0; i < yearlyReportRecords.size(); i++)
            for(YearlyReportRecord yearlyReportRecord : yearlyReportRecords.values()){
                avgIncomesAndExpenses[0] += yearlyReportRecord.income;
                avgIncomesAndExpenses[1] += yearlyReportRecord.expense;
            }

        avgIncomesAndExpenses[0] /=  yearlyReportRecords.size();
        avgIncomesAndExpenses[1] /=  yearlyReportRecords.size();

        return avgIncomesAndExpenses;
    }
}
