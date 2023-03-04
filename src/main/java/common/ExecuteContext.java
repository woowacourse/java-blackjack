package common;

public class ExecuteContext {

    public static <T> T workWithExecuteStrategy(final ExecuteStrategy<T> executeStrategy) {
        T result = null;
        while (result == null) {
            result = tryCatchStrategy(executeStrategy, result);
        }
        return result;
    }

    private static <T> T tryCatchStrategy(ExecuteStrategy<T> executeStrategy, T result) {
        try {
            result = executeStrategy.execute();
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
        return result;
    }
}
