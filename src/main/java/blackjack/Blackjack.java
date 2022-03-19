package blackjack;

import blackjack.user.Dealer;
import blackjack.user.Participant;
import java.util.List;

public class Blackjack {
    private final Dealer dealer;
    private final Players players;
    private final Deck deck;

    private Blackjack(List<String> playerNames) {
        dealer = Dealer.generate();
        players = Players.generateWithNames(playerNames);
        deck = Deck.makeRandomShuffledDeck();
    }

    private Blackjack(List<String> playerNames, Deck deck) {
        dealer = Dealer.generate();
        players = Players.generateWithNames(playerNames);
        this.deck = deck;
    }

    public static Blackjack generate(List<String> playerNames) {
        return new Blackjack(playerNames);
    }

    public static Blackjack generateWithDeck(List<String> playerNames, Deck deck) {
        return new Blackjack(playerNames, deck);
    }

    public void distributeInitCards() {
        dealer.drawInitialCards(deck);
        players.drawInitialCards(deck);
    }

    public Players players() {
        return players;
    }

    public Dealer dealer() {
        return dealer;
    }

    public boolean isPossibleToGetCard(Participant player) {
        return player.isHit();
    }

    public void distributeAdditionalCard(Participant player) {
        player.drawAdditionalCard(deck);
    }
}
