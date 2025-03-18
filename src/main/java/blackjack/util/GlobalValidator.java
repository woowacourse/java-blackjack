package blackjack.util;

public final class GlobalValidator {
    
    public static void validateNotNull(final Object... arguments) {
        for (final Object argument : arguments) {
            if (argument == null) {
                throw new IllegalArgumentException("필수 입력값 중 하나가 null입니다.");
            }
        }
    }
}
