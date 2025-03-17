package blackjack;

import blackjack.card.CardMachine;
import blackjack.participant.Dealer;

import java.lang.reflect.Field;

public class ConstantFixture {

    public static int getMinCardDeckCount(String purpose) {
        return getConstantIntValue(CardMachine.class, "MIN_CARD_DECK_COUNT", purpose);
    }

    public static int getDealerHitThresholdPoint(String purpose) {
        return getConstantIntValue(Dealer.class, "HIT_THRESHOLD_POINT", purpose);
    }

    public static int getInitialHideCardCount(String purpose) {
        return getConstantIntValue(Dealer.class, "INITIAL_HIDE_CARD_COUNT", purpose);
    }

    private static void validate(String purpose) {
        if (!"테스트 전용".equals(purpose)) {
            throw new UnsupportedOperationException("이 클래스는 테스트에서만 사용해야 합니다.");
        }
    }

    private static int getConstantIntValue(Class<?> clazz, String fieldName, String purpose) {
        validate(purpose);

        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.getInt(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(clazz.getSimpleName() + "의 " + fieldName + " 값을 가져올 수 없습니다.", e);
        }
    }


}
