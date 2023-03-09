package domain.game;

import domain.deck.Card;
import domain.deck.Deck;
import domain.player.Dealer;
import domain.player.Player;
import domain.player.Players;
import java.util.List;

public class BlackJackGame {
    private final Deck deck;
    private final Dealer dealer;
    private final Players players;

    public BlackJackGame(final Deck deck, final List<String> playerNames, final List<Integer> amounts) {
        this.deck = deck;
        this.dealer = new Dealer();
        this.players = new Players(playerNames, amounts);
        distributeTwoCards();
    }

    private void distributeTwoCards() {
        dealer.drawCard(deck.popCard());
        dealer.drawCard(deck.popCard());

        for (Player player : players.getPlayers()) {
            player.drawCard(deck.popCard());
            player.drawCard(deck.popCard());
        }
    }

    public void drawCardDealer() {
        dealer.drawCard(deck.popCard());
    }

    public void drawCardPlayer(final String playerName) {
        players.getPlayer(playerName)
                .drawCard(deck.popCard());
    }

    public boolean isDealerDraw() {
        return dealer.isDealerDraw();
    }

    public List<Card> getPlayerCards(final String playerName) {
        return players.getPlayer(playerName).cards();
    }

    public List<Card> getDealerCards() {
        return dealer.cards();
    }

    public int getPlayerScore(final String playerName) {
        return players.getPlayer(playerName).score();
    }

    public int getDealerScore() {
        return dealer.score();
    }
}
