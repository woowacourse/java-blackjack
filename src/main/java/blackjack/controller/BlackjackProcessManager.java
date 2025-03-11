package blackjack.controller;

import blackjack.domain.Card;
import blackjack.domain.CardHolder;
import blackjack.domain.Deck;
import java.util.List;

public class BlackjackProcessManager {

    private static final int STARTING_CARD_SIZE = 2;
    private static final int ADDITIONAL_CARD_SIZE = 1;

    private final Deck deck;

    public BlackjackProcessManager(Deck deck) {
        this.deck = deck;
    }

    public void giveStartingCardsFor(CardHolder cardHolder) {
        List<Card> cards = deck.takeCards(STARTING_CARD_SIZE);

        cards.forEach(cardHolder::takeCard);
    }

    public void giveCard(CardHolder cardHolder) {
        List<Card> cards = deck.takeCards(ADDITIONAL_CARD_SIZE);

        cards.forEach(cardHolder::takeCard);
    }
}
