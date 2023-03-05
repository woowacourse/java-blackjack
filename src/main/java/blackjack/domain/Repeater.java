package blackjack.domain;

public class Repeater {

    public static <T> T repeat(ExceptionHandler<T> exceptionHandler) {
        T result = null;
        while (result == null) {
            try {
                result = exceptionHandler.execute();
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
        return result;
    }
}
