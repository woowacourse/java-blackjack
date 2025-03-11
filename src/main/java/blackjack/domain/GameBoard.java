package blackjack.domain;

import blackjack.domain.card.CardsShuffler;
import blackjack.domain.card.Deck;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.Players;
import blackjack.domain.winning.Victory;

public class GameBoard {

    private final Deck deck;
    private final Dealer dealer;
    private final Players players;

    public GameBoard(Deck deck, Dealer dealer, Players players) {
        this.deck = deck;
        this.dealer = dealer;
        this.players = players;
    }

    public void prepareGame(CardsShuffler cardsShuffler) {
        deck.shuffleCards(cardsShuffler);
        dealer.prepareCards(deck);
        players.prepareCards(deck);
    }

    public void drawCard(Player player) {
        players.drawCard(deck, player);
    }

    public void drawAdditionalCardOfDealer() {
        dealer.drawAdditionalCard(deck);
    }

    public Victory createVictory() {
        return Victory.create(dealer, players);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }

    public int getDealerCardSize() {
        return dealer.getCardSize();
    }
}
