package domain.game;

import domain.deck.Card;
import domain.deck.Deck;
import domain.player.Dealer;
import domain.player.Player;
import domain.player.Players;

import java.util.List;

public class BlackJackGame {
    private final Deck deck;
    private final Players players;

    public BlackJackGame(final Deck deck, final List<String> playerNames) {
        this.deck = deck;
        this.players = new Players(playerNames);
        players.drawTwoCardsAtFirstTime(deck);
    }

    public void drawCard(final String playerName) {
        players.findPlayer(playerName).drawCard(deck.popCard());
    }

    public boolean isDealerDraw() {
        return findDealer().isDealerDraw();
    }

    public Dealer findDealer() {
        return players.findDealer();
    }

    public boolean isEqualOrLargerThanBlackJackNumber(final String playerName) {
        if(players.isEqualOrLargerThanBlackJackNumber(playerName)) {
            return true;
        }
        return false;
    }

    public List<Card> getCards(final String playerName) {
        return players.findPlayer(playerName).getCards();
    }

    public int getScore(final String playerName) {
        return players.findPlayer(playerName).getScore();
    }

    public List<Player> getPlayersWithOutDealer() {
        return players.getPlayersWithOutDealer();
    }
}
