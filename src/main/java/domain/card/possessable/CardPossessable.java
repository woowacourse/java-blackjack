package domain.card.possessable;

import domain.card.Card;
import domain.card.providable.CardProvidable;
import domain.score.Calculatable;

import java.util.List;

public interface CardPossessable {
    void drawCard(CardProvidable cardProvidable);

    Calculatable calculateScore();

    Calculatable calculateDefaultSum();

    boolean hasAce();

    Card getOneCard();

    List<Card> getCards();
}
