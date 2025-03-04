package domain;

import java.util.ArrayList;
import java.util.List;

public class Dealer {

    private final List<TrumpCard> trumpCards;
    private final DrawPolicy drawPolicy;
    private final int DEALER_STOP_HIT_STANDARD = 17;
    private final String INVALID_CARD_STATE = "비정상적인 카드 추가입니다. 플레이어는 21장 이상 받을 수 없습니다";

    public Dealer(DrawPolicy drawPolicy) {
        this.drawPolicy = drawPolicy;
        trumpCards = new ArrayList<>();
    }

    public void addDraw(TrumpCard trumpCard) {
        if (isBurst()) {
            throw new IllegalStateException(INVALID_CARD_STATE);
        }
        trumpCards.add(trumpCard);
    }

    public boolean isBurst() {
        int sum = calculateCardSum();
        return drawPolicy.isBurst(sum);
    }

    private Integer calculateCardSum() {
        return trumpCards.stream()
                .map(TrumpCard::cardNumberValue)
                .reduce(Integer::sum)
                .orElse(0);
    }

    public boolean isDrawable() {
        int sum = calculateCardSum();
        if (sum >= DEALER_STOP_HIT_STANDARD) {
            return false;
        }
        return true;
    }
}
