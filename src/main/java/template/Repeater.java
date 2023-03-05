package template;

public class Repeater {

    public static <T> T repeat(InputRepeater<T> inputRepeater) {
        T result = null;
        while (result == null) {
            try {
                result = inputRepeater.execute();
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
        return result;
    }
}
