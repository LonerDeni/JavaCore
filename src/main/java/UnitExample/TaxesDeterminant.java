package UnitExample;

import java.util.Scanner;

public class TaxesDeterminant {
    public static void main(String[] args) {
        int earnings = 0; // доходы
        int spendings = 0; // расходы
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Выберите операцию и введите её номер: ");
            System.out.println("1. Добавить новый доход\n" +
                    "2. Добавить новый расход\n" +
                    "3. Выбрать ситему налогооблажения");
            System.out.println("Для выхода из системы введите \"end\"");
            String operation = scanner.nextLine();
            if (operation.equals("end")) {
                break;
            }
            try {
                int numberOperation = Integer.parseInt(operation);

                switch (numberOperation) {
                    case 1:
                        System.out.println("Введите сумму дохода: ");
                        int moneyEarnings = Integer.parseInt(scanner.nextLine());
                        earnings += moneyEarnings;
                        break;
                    case 2:
                        System.out.println("Введите сумму расходов: ");
                        int moneySpendings = Integer.parseInt(scanner.nextLine());
                        spendings += moneySpendings;
                        break;
                    case 3:
                        int resultTaxEarningsMinusSpendings = taxEarningsMinusSpendings(earnings, spendings);
                        int resultTaxEarningsSixPercent = taxEarningsSixPercent(earnings);
                        changeSystem(resultTaxEarningsMinusSpendings, resultTaxEarningsSixPercent);
                        break;
                    default:
                        System.out.println("Такой операции нет");
                }
            } catch (NumberFormatException e) {
                System.out.println("Введеное значение не равно \"end\" и не может быть преобразовано в число");
            }
        }
        System.out.println("Программа завершена!");
    }

    public static int taxEarningsMinusSpendings(int earnings, int spendings) {
        int tax = (earnings - spendings) * 15 / 100;
        if (tax >= 0) {
            return tax;
        } else {
            return 0;
        }
    }

    public static int taxEarningsSixPercent(int earnings) {
        int tax = (earnings) * 6 / 100;
        if (tax >= 0) {
            return tax;
        } else {
            return 0;
        }
    }

    public static String changeSystem(int taxEarningsMinusSpendings, int taxEarningsSixPercent) {
        if (taxEarningsMinusSpendings > taxEarningsSixPercent) {
            return "Мы советуем вам УСН доходы";
        } else if (taxEarningsMinusSpendings < taxEarningsSixPercent) {
            return "Мы советуем вам УСН доходы минус расходы";
        } else {
            return "Налог по обеим системам одинаковый, выберите любую систему налогооблажения";
        }
    }
}