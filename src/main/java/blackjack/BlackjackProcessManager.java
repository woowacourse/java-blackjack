package blackjack;

import java.util.List;

public class BlackjackProcessManager {
    private static final int STARTING_CARD_SIZE = 2;
    private static final int ADDITIONAL_CARD_SIZE = 1;
    private final Deck deck;

    public BlackjackProcessManager(Deck deck) {
        this.deck = deck;
    }

    public void giveStartingCards(Hand hand) {
        List<Card> cards = deck.takeCards(STARTING_CARD_SIZE);
        cards.forEach(hand::takeCard);
    }

    public void giveCard(Hand hand) {
        List<Card> card = deck.takeCards(ADDITIONAL_CARD_SIZE);
        card.forEach(hand::takeCard);
    }
}
