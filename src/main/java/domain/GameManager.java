package domain;

import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Player;
import java.util.List;

public class GameManager {

    private final Dealer dealer;
    private final List<Player> players;

    private GameManager(final Dealer dealer, final List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public int giveCardsToDealer(){
        int count = 0;
        while(dealer.isLessThen(16)){
            dealer.receiveCard();
            count++;
        }
        return count;
    }

    public static GameManager create(Dealer dealer, List<Player> players) {
        validateDuplicatePlayer(players);
        return new GameManager(dealer, players);
    }

    private static void validateDuplicatePlayer(final List<Player> players) {
        if (isDuplicate(players)) {
            throw new IllegalArgumentException();
        }
    }

    private static boolean isDuplicate(final List<Player> players) {
        return players.stream()
                .distinct()
                .count() != players.size();
    }


    public boolean isAbleToHit(final Gamer gamer) {
        return !gamer.isBust();
    }


    public void giveCardToGamer(final Gamer gamer) {
        gamer.receiveCard();
    }

    public GameResult calculateResult(final int index) {
        final Player player = players.get(index);
        if(dealer.isBust() && player.isBust()) return GameResult.DRAW;
        return player.calculateGameResult(dealer.calculateScore());
    }


    public int calculatePlayerSize() {
        return players.size();
    }

    public Player findPlayerByIndex(final int index) {
        return players.get(index);
    }
}
