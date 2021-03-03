package blackjack.util;

public class StringUtil {
    public static String deleteWhiteSpaces(String string) {
        return string.replaceAll("\\s+", "");
    }
}
