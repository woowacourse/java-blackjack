package domain;

import java.util.ArrayList;
import java.util.List;

public abstract class BlackjackPlayer {

    private static final String INVALID_CARD_STATE = "비정상적인 카드 추가입니다. 플레이어는 21장 이상 받을 수 없습니다";
    private static final int BURST_STANDARD = 21;
    private static final int ACE_DIFF = 10;
    private final List<TrumpCard> trumpCards;

    public BlackjackPlayer() {
        trumpCards = new ArrayList<>();
    }

    public void addDraw(TrumpCard trumpCard) {
        if (isBurst(calculateCardSum())) {
            throw new IllegalStateException(INVALID_CARD_STATE);
        }
        trumpCards.add(trumpCard);
    }

    public int calculateCardSum() {
        int sum = trumpCards.stream()
                .map(TrumpCard::cardNumberValue)
                .reduce(Integer::sum)
                .orElse(0);
        int aceCount = (int) trumpCards.stream()
                .filter(TrumpCard::isAce)
                .count();
        if (aceCount != 0) {
            return calculateAceIncludeSum(aceCount, sum);
        }
        return sum;
    }

    private int calculateAceIncludeSum(int aceCount, int sum) {
        if (isBurst(sum) && aceCount != 0) {
            return calculateAceIncludeSum(aceCount - 1, sum - ACE_DIFF);
        }
        return sum;
    }

    private boolean isBurst(int number) {
        return BURST_STANDARD < number;
    }

    public boolean isDrawable() {
        int sum = calculateCardSum();
        return !isBurst(sum);
    }
}
