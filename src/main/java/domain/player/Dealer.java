package domain.player;

import domain.ParticipantCards;
import domain.card.CardDeck;

public class Dealer {
    private final String name;
    public static final int INITIAL_CARD_NUMBER = 2;
    private ParticipantCards cards;

    public Dealer() {
        this.name = "딜러";
        this.cards = new ParticipantCards();
    }

    public void initialDistribution(CardDeck cardDeck) {
        for (int i = 0; i < INITIAL_CARD_NUMBER; i++) {
            cards.add(cardDeck.draw());
        }
    }

    public String getName() {
        return this.name;
    }

    public String toStringCard() {
        return cards.toString();
    }
}
