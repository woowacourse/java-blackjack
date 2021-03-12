package blackjack.util;

public abstract class StringUtil {
    public static String deleteWhiteSpaces(String string) {
        return string.replaceAll("\\s+", "");
    }

    public static boolean isBlank(String name) {
        return "".equals(deleteWhiteSpaces(name));
    }
}
