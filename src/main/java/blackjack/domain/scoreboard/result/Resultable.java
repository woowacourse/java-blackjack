package blackjack.domain.scoreboard.result;

import blackjack.domain.card.Card;
import blackjack.domain.user.Name;

import java.util.List;

public interface Resultable {
    List<Card> getCards();
    Name getName();
    int getScore();
}
