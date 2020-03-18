package first.domain.card.possessable;

import first.domain.card.Card;
import first.domain.card.providable.CardProvidable;
import first.domain.score.Calculatable;

import java.util.List;

public interface CardPossessable {
    void drawCard(CardProvidable cardProvidable);

    Calculatable calculateScore();

    Calculatable calculateDefaultSum();

    boolean hasAce();

    Card getOneCard();

    List<Card> getCards();
}
