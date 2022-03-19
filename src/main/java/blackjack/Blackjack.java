package blackjack;

import blackjack.user.Dealer;
import blackjack.user.Participant;

public class Blackjack {
    private final Deck deck;

    private Blackjack() {
        deck = Deck.makeRandomShuffledDeck();
    }

    private Blackjack(Deck deck) {
        this.deck = deck;
    }

    public static Blackjack generate() {
        return new Blackjack();
    }

    public static Blackjack generateWithDeck(Deck deck) {
        return new Blackjack(deck);
    }

    public void distributeInitCards(Dealer dealer, Players players) {
        dealer.drawInitialCards(deck);
        players.drawInitialCards(deck);
    }

    public boolean isPossibleToGetCard(Participant player) {
        return player.isHit();
    }

    public void distributeAdditionalCard(Participant player) {
        player.drawAdditionalCard(deck);
    }
}
