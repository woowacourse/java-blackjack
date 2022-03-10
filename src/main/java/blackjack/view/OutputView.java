package blackjack.view;

public class OutputView {

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    public static void printFatalErrorMessage(String message) {
        System.err.println(message);
        System.err.println("심각한 오류가 발생하여 프로그램을 종료합니다.");
    }
}
