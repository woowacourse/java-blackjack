package blackjack.util;

public class StringUtils {
    private StringUtils(){
    }

    public static void notEmpty(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("입력이 비어있습니다.");
        }
    }
}
