package blackjack.domain.participant;

import blackjack.domain.card.Card;
import java.util.List;

public interface Actionable {

    List<Card> showCards();

    boolean canGetMoreCard();

    String getNickname();
}
