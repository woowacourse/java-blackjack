package blackjack.domain.game;

import blackjack.domain.card.Deck;
import blackjack.domain.gameplayer.Dealer;
import blackjack.domain.gameplayer.Player;
import blackjack.domain.gameplayer.Players;

public class Game {
    private final Deck deck;
    private final Dealer dealer;
    private final Players players;

    public Game(Deck deck, Dealer dealer, Players players) {
        this.deck = deck;
        this.dealer = dealer;
        this.players = players;
        init();
    }

    private void init() {
        initDealerCards();
        initPlayerCards();
    }

    private void initPlayerCards() {
        for (Player player : players) {
            giveCardTo(player);
            giveCardTo(player);
        }
    }

    private void initDealerCards() {
        giveCardToDealer();
        giveCardToDealer();
    }

    public GameResult createGameResult() {
        return new GameResult(dealer, players);
    }

    public boolean canContinueDealer() {
        return dealer.canContinue();
    }

    public void giveCardTo(Player player) {
        player.addCard(deck.draw());
    }

    public void giveCardToDealer() {
        dealer.addCard(deck.draw());
    }

    public Players getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
