package utils;

public class StringUtils {
    public static int parseInt(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("숫자만 입력 가능합니다.");
        }
    }
}
