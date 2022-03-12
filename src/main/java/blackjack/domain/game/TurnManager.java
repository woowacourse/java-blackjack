package blackjack.domain.game;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Player;

public class TurnManager {

    private final Deck deck = new Deck();
    private final List<Player> players;
    private int currentPlayerIndex;

    public TurnManager(List<Player> players) {
        currentPlayerIndex = 0;
        this.players = new ArrayList<>(players);
    }

    public boolean isAllTurnEnd() {
        return currentPlayerIndex == players.size();
    }

    public boolean canHitStatus() {
        return players.get(currentPlayerIndex).canHit();
    }

    public boolean doPlayerTurn(boolean isHit) {
        if (!isHit) {
            return false;
        }
        players.get(currentPlayerIndex).addCard(deck.pickCard());
        return true;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public void turnToNext() {
        currentPlayerIndex++;
    }
}
