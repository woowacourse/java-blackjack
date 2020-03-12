package domain.card.possessable;

import domain.card.providable.CardProvidable;
import domain.score.Calculatable;

public interface CardPossessable {
    void drawCard(CardProvidable cardProvidable);

    Calculatable calculateScore();

    Calculatable calculateDefaultSum();

    boolean hasAce();

    int getCardAmount();
}
