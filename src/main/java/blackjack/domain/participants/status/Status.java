package blackjack.domain.participants.status;

import blackjack.domain.card.Card;
import blackjack.domain.game.ResultType;
import blackjack.domain.game.Score;

import java.util.List;

public interface Status {

    Status initCards(Card card1, Card card2);

    Status addCard(Card card);

    Status stay();

    boolean isBlackjack();

    boolean isBust();

    boolean isFinished();

    Score score();

    ResultType findResultType(Status opponent);

    List<Card> getCards();

}
