import java.util.List;
import java.util.ArrayList;

public class MonthlyReport {
    ArrayList<MonthlyReportRecord> monthlyReportRecords = new ArrayList<>();

    public void getInfoFromCsvFile(List<String> line){
        MonthlyReportRecord monthlyReportRecord;

        for(int i = 1; i < line.size(); i++){
            String[] contents = line.get(i).split(",");
            monthlyReportRecord = new MonthlyReportRecord(contents);

            monthlyReportRecords.add(monthlyReportRecord);

            //System.out.println("\tЗапись №" + i + " " + contents[0] + " " + contents[1] + " " + contents[2] + " " + contents[3]);
        }
    }

    public String getInfoMostProfitableProduct(){
        String resultLine;

        if(!monthlyReportRecords.isEmpty()){
            MonthlyReportRecord monthlyMaxProfitReport = null;
            MonthlyReportRecord monthlyConcreteReport;

            for(int i = 1; i < monthlyReportRecords.size(); i++){
                monthlyConcreteReport = monthlyReportRecords.get(i);

                if(monthlyConcreteReport.isExpense)
                    if(monthlyMaxProfitReport == null)
                        monthlyMaxProfitReport = monthlyConcreteReport;
                    else if(monthlyMaxProfitReport.getProfit() > monthlyConcreteReport.getProfit())
                        monthlyMaxProfitReport = monthlyConcreteReport;
            }

            resultLine = "Название - " + monthlyMaxProfitReport.itemName + ", Сумма -  " + monthlyMaxProfitReport.getProfit();
        } else
            resultLine = "За данный месяц отсутствуют статьи отчетности";

        return resultLine;
    }

    public String getInfoMostExpense(){
        String resultLine;

        if(!monthlyReportRecords.isEmpty()){
            MonthlyReportRecord maxExpenseReport = monthlyReportRecords.get(0);

            for(MonthlyReportRecord monthlyReportRecord : monthlyReportRecords)
                if(monthlyReportRecord.sumOfOne > maxExpenseReport.sumOfOne)
                    maxExpenseReport = monthlyReportRecord;

            resultLine = "Название - " + maxExpenseReport.itemName + ", Сумма -  " + maxExpenseReport.getProfit();
        } else
            resultLine = "За данный месяц отсутствуют статьи отчетности";

        return resultLine;
    }

    public int getSummaryIncome(){
        int sumIncome = 0;
        for(MonthlyReportRecord monthlyReportRecord : monthlyReportRecords)
            if(!monthlyReportRecord.isExpense)
                sumIncome += monthlyReportRecord.getProfit();

            return sumIncome;
    }

    public int getSummaryExpense(){
        int sumExpense = 0;
        for(MonthlyReportRecord monthlyReportRecord : monthlyReportRecords)
            if(monthlyReportRecord.isExpense)
                sumExpense += monthlyReportRecord.getProfit();

        return sumExpense;
    }
}