package blackjack.util;

public class GlobalValidator {
    
    public static void validateNotNull(final Class<?> target, final Object... arguments) {
        for (final Object argument : arguments) {
            if (argument == null) {
                throw new IllegalArgumentException(parseExceptionMessage(target));
            }
        }
    }
    
    private static String parseExceptionMessage(final Class<?> target) {
        return "%s의 인자는 null이 될 수 없습니다.".formatted(target.getSimpleName());
    }
}
