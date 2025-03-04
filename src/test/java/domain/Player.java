package domain;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final List<TrumpCard> trumpCards;
    private final DrawPolicy drawPolicy;

    public Player(DrawPolicy drawPolicy) {
        this.drawPolicy = drawPolicy;
        trumpCards = new ArrayList<>();
    }

    public void addDraw(TrumpCard trumpCard) {
        if (isBurst()) {
            throw new IllegalStateException("비정상적인 카드 추가입니다. 플레이어는 21장 이상 받을 수 없습니다");
        }
        trumpCards.add(trumpCard);
    }

    public boolean isBurst() {
        int sum = trumpCards.stream()
                .map(TrumpCard::cardNumberValue)
                .reduce(Integer::sum)
                .orElse(0);
        return drawPolicy.isBurst(sum);
    }
}
