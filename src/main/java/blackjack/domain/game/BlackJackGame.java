package blackjack.domain.game;

import blackjack.domain.card.Deck;
import blackjack.domain.money.Money;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Players;
import java.util.List;

public class BlackJackGame {

    private final Players players;
    private final Deck deck;

    public BlackJackGame(List<String> names, List<Integer> bets) {
        this.players = Players.from(names, bets);
        this.deck = Deck.createShuffledDeck();
    }

    public void drawStartCards() {
        players.drawStartCards(deck);
    }

    public Money calculateDealerPrize() {
        return players.calculateDealerPrize();
    }

    public Players getPlayers() {
        return this.players;
    }

    public Dealer getDealer() {
        return players.getDealer();
    }

    public Deck getDeck() {
        return deck;
    }
}
