package domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Participants {

    private static final int DEALER_COUNT = 1;
    private static final int MAX_BOUNDARY_SCORE = 21;
    private static final int DEALER_BOUNDARY_SCORE = 17;
    private final Player dealer;
    private final List<Player> players;


    public Participants(Player dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public int count() {
        return players.size() + DEALER_COUNT;
    }

    public int countPlayers() {
        return players.size();
    }
    public void receiveCard(Card card, int playerIndex) {
        players.get(playerIndex).receiveCard(card);
    }

    public void receiveDealerCard(Card card) {
        dealer.receiveCard(card);
    }

    public void receiveInitialCards(List<Deck> decks) {
        Deck dealerDeck = decks.remove(decks.size() - 1);
        dealer.receiveDeck(dealerDeck);

        for (int index = 0; index < players.size(); index++) {
            Deck currentDeck = decks.get(index);
            players.get(index).receiveDeck(currentDeck);
        }
    }



    public Map<Player, Boolean> calculateResult() {
        Map<Player, Boolean> result = new LinkedHashMap<>();
        players.forEach(player -> result.put(player, calculateResult(player)));
        return result;
    }

    private boolean calculateResult(Player player) {
        if (player.getDeck().calculateTotalScore() > MAX_BOUNDARY_SCORE) {
            return false;
        }
        if (dealer.getDeck().calculateTotalScore() > MAX_BOUNDARY_SCORE) {
            return true;
        }
        return dealer.getDeck().calculateTotalScore() < player.getDeck().calculateTotalScore();
    }

    public boolean isPlayerNotOver(int playerIndex) {
        return players.get(playerIndex).isNotOver(MAX_BOUNDARY_SCORE);
    }

    public boolean isDealerNotOver() {
        return dealer.isNotOver(DEALER_BOUNDARY_SCORE);
    }

    public Name getPlayerName(int playerIndex) {
        return players.get(playerIndex).getName();
    }


    public Player getPlayer(int playerIndex) {
        return players.get(playerIndex);
    }


    public Player getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
