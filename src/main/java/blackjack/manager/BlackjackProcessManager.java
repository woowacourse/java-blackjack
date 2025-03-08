package blackjack.manager;

import blackjack.domain.Card;
import blackjack.domain.CardHolder;
import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Player;
import java.util.List;

public class BlackjackProcessManager {

    private static final int STARTING_CARD_SIZE = 2;
    private static final int ADDITIONAL_CARD_SIZE = 1;

    private final Deck deck;

    public BlackjackProcessManager(Deck deck) {
        this.deck = deck;
    }

    public void giveStartingCardsFor(Dealer dealer) {
        List<Card> cards = deck.takeCards(STARTING_CARD_SIZE);

        CardHolder cardHolder = dealer.getCardHolder();
        cards.forEach(cardHolder::takeCard);
    }

    public void giveStartingCardsFor(Player player) {
        List<Card> cards = deck.takeCards(STARTING_CARD_SIZE);

        CardHolder cardHolder = player.getCardHolder();
        cards.forEach(cardHolder::takeCard);
    }

    public void giveCard(CardHolder cardHolder) {
        List<Card> cards = deck.takeCards(ADDITIONAL_CARD_SIZE);

        cards.forEach(cardHolder::takeCard);
    }
}
