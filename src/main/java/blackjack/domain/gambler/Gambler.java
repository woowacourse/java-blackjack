package blackjack.domain.gambler;

import blackjack.domain.card.Card;
import java.util.List;

public interface Gambler {
    void addCard(Card card);
    int calculateScore();
    boolean isScoreBelow(int criteria);
    int calculateScoreDifference(Gambler other);
    boolean isBlackjack();
    boolean isNameEquals(Name name);
    Name getName();
    List<Card> getCards();
    List<Card> getInitialCards();
}
