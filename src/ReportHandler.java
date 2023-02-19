import java.util.HashMap;
import java.util.List;


public class ReportHandler {
    int year = 2021;
    FileReader fileReader;
    HashMap<Integer , MonthlyReport> monthlyReports = new HashMap<>();

    YearlyReport yearlyReport = null;

    ReportHandler(FileReader fileReader){
        this.fileReader = fileReader;
    }
    public void getMonthInfoFromFiles(){
        MonthlyReport monthlyReport;

        for(int i = 1; i <= 12; i++){
            List<String> fileInfo = fileReader.readFileContents(fileReader.defineMonthReportFileName(i, year));
            if(!fileInfo.isEmpty()){
                monthlyReport = new MonthlyReport();
                monthlyReport.getInfoFromCsvFile(fileInfo);

                monthlyReports.put(i, monthlyReport);
                System.out.println("Файл по месяцу №" + i + " успешно считался");
            } else
                System.out.println("Невозможно прочитать файл с месячным отчётом №" + i + ". Возможно файл не находится в нужной директории.");
        }
    }

    public void getYearInfoFromFiles(){
        yearlyReport = new YearlyReport();
        List<String> fileInfo = fileReader.readFileContents(fileReader.defineYearReportFileName(year));

        if(!fileInfo.isEmpty()){
            System.out.println("\nФайл по " + year + " году успешно считался");
            yearlyReport.getInfoFromCsvFile(fileInfo);
        } else
            System.out.println("\nНевозможно прочитать файл с годовым отчётом. Возможно файл не находится в нужной директории.");
    }

    public void printInfoFromReportsByMonth(){
        System.out.println("\nСтатьи отчетности по " + year + " году");

        if(!monthlyReports.isEmpty()){
            for(Integer monthNum : monthlyReports.keySet()){
                System.out.println("Месяц №" + monthNum + ":\n\tСамый прибыльный товар: " + monthlyReports.get(monthNum).getInfoMostProfitableProduct());
                System.out.println("\tСамая большая трата: " + monthlyReports.get(monthNum).getInfoMostExpense());
            }
        } else
            System.out.println("Требуется считать данные из файла. В данный момент буфер данных пуст");

    }

    public void printYearlyReport(){
        if(yearlyReport != null){
            System.out.println("Отчетность по " + year + " году:");
            yearlyReport.printYearlyReport();
        } else
            System.out.println("Требуется считать данные. В данный момент буфер данных пуст");
    }

    public void printReportsComparison(){
        if(yearlyReport != null && !monthlyReports.isEmpty())
            if(isReportsComparison())
                System.out.println("Данные соответствуют. Проверка прошла успешно");
            else
                System.out.println("Обнаружена ошибка. Пожалуйста, проверьте данные");
        else
            System.out.println("Вы не считали данные за год или месяц");
    }

    private boolean isReportsComparison(){
        boolean result = true;

        for(int monthNum : monthlyReports.keySet())
            if(yearlyReport.yearlyReportRecords.containsKey(monthNum)){
                YearlyReportRecord yearlyReportRecord = yearlyReport.yearlyReportRecords.get(monthNum);

                if(yearlyReportRecord.income != monthlyReports.get(monthNum).getSummaryIncome() || yearlyReportRecord.expense != monthlyReports.get(monthNum).getSummaryExpense())
                    result = false;
            } else
                result = false;

        return result;
    }
}
