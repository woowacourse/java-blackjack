package domain.user;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import domain.result.WinningResult;

public class Dealer extends User {

    public static final String DEALER = "딜러";
    private static final int PIVOT = 17;

    private Dealer() {
        super(DEALER);
    }

    public static Dealer appoint() {
        return new Dealer();
    }

    public String getFirstDrawResult() {
        return DEALER + "카드: " + cards.getFirstCard().getName();
    }

    @Override
    public boolean isAvailableToDraw() {
        return !cards.isBust() && !cards.isBlackJack() && !cards.isBlackJackPoint()
                && cards.calculatePointAccordingToHasAce() < PIVOT;
    }
}
