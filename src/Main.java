import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Вас приветствует приложение \"Автоматизация бухгалтерии \"");

        Scanner scanner = new Scanner(System.in);
        FileReader fileReader = new FileReader();

        ReportHandler reportHandler = new ReportHandler(fileReader);

        String answer;

        while(true){
            printMenu();
            answer = scanner.nextLine();

            if(answer.equals("1")){
                reportHandler.getMonthInfoFromFiles();
            } else if (answer.equals("2")) {
                reportHandler.getYearInfoFromFiles();
            } else if (answer.equals("3")) {
                reportHandler.printReportsComparison();
            } else if (answer.equals("4")) {
                reportHandler.printInfoFromReportsByMonth();
            } else if (answer.equals("5")) {
                reportHandler.printYearlyReport();
            } else if (answer.equals("0")) {
                break;
            } else {
                System.out.println("\nВведено неккоректное значение. Пожалуйста, повторите попытку.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n\nВыбери один из следующих пунктов:");

        System.out.println("[1] - Считать все месячные отчёты");
        System.out.println("[2] - Считать годовой отчёт");
        System.out.println("[3] - Сверить отчёты");
        System.out.println("[4] - Вывести информацию о всех месячных отчётах");
        System.out.println("[5] - Вывести информацию о годовом отчёте");
        System.out.println("\n[0] - Выход");
    }
}

