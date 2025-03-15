package blackjack.domain;

import blackjack.domain.card.CardsShuffler;
import blackjack.domain.card.Deck;
import blackjack.domain.participants.BettingMoney.Profit;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.Players;

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
        dealer.prepareBlackjack(deck);
        players.prepareBlackjack(deck);
    }

    public void drawCard(Player player) {
        players.drawCard(deck, player);
    }

    public void drawAdditionalCardOfDealer() {
        dealer.drawAdditionalCard(deck);
    }

    public Profit createProfit() {
        return new Profit(
                dealer.calculateProfit(players),
                players.calculateAllProfit(dealer)
        );
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
