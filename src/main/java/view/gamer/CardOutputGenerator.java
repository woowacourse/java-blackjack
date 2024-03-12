package view.gamer;

import domain.card.Card;
import domain.card.CardName;
import domain.card.CardType;

class CardOutputGenerator {
    static String generate(Card card) {
        CardName cardName = card.cardName();
        CardType cardType = card.cardType();
        ViewCardName viewCardName = ViewCardName.of(cardName);
        ViewCardType viewCardType = ViewCardType.of(cardType);
        return viewCardName.getOutput() + viewCardType.getOutput();
    }
}
