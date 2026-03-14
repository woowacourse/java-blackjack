package domain.member;

import domain.card.Card;
import java.util.List;

public class DealerRole implements MemberRole {

    @Override
    public List<Card> showFirstCards(List<Card> cards) {
        return List.of(cards.getFirst());
    }

    @Override
    public void applyBonus() {
    }

    @Override
    public int getBettingAmount() {
        return 0;
    }
}
