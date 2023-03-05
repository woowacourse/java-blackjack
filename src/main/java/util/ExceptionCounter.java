package util;

public class ExceptionCounter {
    private static final int MAXIMUM_EXCEPTION_COUNT = 20;
    private static int count = 0;

    public static void addCountHandledException() {
        count += 1;
        checkStackOver();
    }

    private static void checkStackOver() {
        if (count > MAXIMUM_EXCEPTION_COUNT) {
            throw new StackOverflowError("잘못된 입력이 너무 많이 반복되어 프로그램이 종료됩니다.");
        }
    }
}
