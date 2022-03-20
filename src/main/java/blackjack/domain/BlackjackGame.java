package blackjack.domain;

import java.util.List;
import java.util.Map;

import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.result.Result;

public class BlackjackGame {

    private static final int NUMBER_OF_STARTING_CARDS = 2;

    private final Deck deck;
    private final Dealer dealer;
    private final Players players;

    public BlackjackGame(final Deck deck, final Dealer dealer, final Players players) {
        this.deck = deck;
        this.dealer = dealer;
        this.players = players;
    }

    public void initStartingCards() {
        drawStartingCards(dealer);
        for (Player player : players.getPlayers()) {
            drawStartingCards(player);
        }
    }

    private void drawStartingCards(final Gamer gamer) {
        for (int i = 0; i < NUMBER_OF_STARTING_CARDS; i++) {
            gamer.drawCard(deck.draw());
        }
    }

    public void drawCard(Gamer gamer) {
        gamer.drawCard(deck.draw());
    }

    public Map<Player, Result> judgeResult() {
        return dealer.judgeResult(players);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }
}
