package domain.member;

import domain.card.Card;
import java.util.List;

public class Dealer extends Member {

    private static final String DEFAULT_NAME = "딜러";

    public Dealer() {
        super(DEFAULT_NAME);
    }

    @Override
    public List<Card> showFirstCards() {
        return List.of(super.handCards().getFirst());
    }

    @Override
    public boolean isDealer() {
        return true;
    }

    @Override
    public int getBettingAmount() {
        throw new UnsupportedOperationException("딜러는 배팅 금액을 가지지 않습니다.");
    }

    @Override
    public void applyBlackjackBonus() {
        throw new UnsupportedOperationException("딜러에게는 블랙잭 보너스를 적용할 수 없습니다.");
    }
}
