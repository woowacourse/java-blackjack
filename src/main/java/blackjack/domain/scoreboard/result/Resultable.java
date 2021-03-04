package blackjack.domain.scoreboard.result;

import blackjack.domain.card.Card;

import java.util.List;

public interface Resultable {
    List<Card> getCards();
    String getName();
    int getScore();
}
