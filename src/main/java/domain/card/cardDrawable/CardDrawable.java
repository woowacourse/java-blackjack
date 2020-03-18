package domain.card.cardDrawable;

import domain.card.providable.CardProvidable;

@FunctionalInterface
public interface CardDrawable {
    void drawCard(CardProvidable cardProvidable);
}
