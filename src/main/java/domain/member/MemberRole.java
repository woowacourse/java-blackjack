package domain.member;

import domain.card.Card;
import java.util.List;

public interface MemberRole {
    List<Card> showFirstCards(List<Card> cards);
    int getBettingAmount();
    void applyBonus();
}
