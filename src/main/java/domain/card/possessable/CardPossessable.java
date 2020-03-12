package domain.card.possessable;

import domain.card.providable.CardProvidable;

public interface CardPossessable {
    void drawCard(CardProvidable cardProvidable);

    int calculateScore();

    int getCardAmount();
}
