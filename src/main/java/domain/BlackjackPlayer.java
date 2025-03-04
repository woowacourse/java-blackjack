package domain;

import java.util.ArrayList;
import java.util.List;

public abstract class BlackjackPlayer {

    private final String INVALID_CARD_STATE = "비정상적인 카드 추가입니다. 플레이어는 21장 이상 받을 수 없습니다";
    private final List<TrumpCard> trumpCards;
    private final int BURST_STANDARD = 21;

    public BlackjackPlayer() {
        trumpCards = new ArrayList<>();
    }

    public void addDraw(TrumpCard trumpCard) {
        if (isBurst()) {
            throw new IllegalStateException(INVALID_CARD_STATE);
        }
        trumpCards.add(trumpCard);
    }

    protected Integer calculateCardSum() {
        return trumpCards.stream()
                .map(TrumpCard::cardNumberValue)
                .reduce(Integer::sum)
                .orElse(0);
    }

    public boolean isBurst() {
        int sum = calculateCardSum();
        return BURST_STANDARD < sum;
    }

    public boolean isDrawable() {
        return isBurst();
    }
}
