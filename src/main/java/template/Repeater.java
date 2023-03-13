package template;

public final class Repeater {
    private static final String ERROR_HEAD = "[ERROR] ";

    private Repeater() {
        throw new AssertionError();
    }

    public static <T> T repeat(InputRepeater<T> inputRepeater) {
        T result = null;
        while (result == null) {
            try {
                result = inputRepeater.execute();
            } catch (Exception exception) {
                System.out.println(ERROR_HEAD + exception.getMessage());
            }
        }
        return result;
    }
}
