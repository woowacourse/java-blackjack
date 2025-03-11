package blackjack.util;

public class GlobalValidator {
    
    public static void validateNotNull(final Object target, final Object... arguments) {
        for (final Object argument : arguments) {
            if (argument == null) {
                throw new IllegalArgumentException(parseExceptionMessage(target));
            }
        }
    }
    
    private static String parseExceptionMessage(final Object target) {
        return "%s의 인자는 null이 될 수 없습니다.".formatted(target.getClass().getSimpleName());
    }
}
