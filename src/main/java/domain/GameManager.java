package domain;

import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Player;
import java.util.List;

public class GameManager {

    private final Dealer dealer;
    private final List<Player> players;

    public GameManager(final Dealer dealer, final List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void receiveCardToDealer(){
        while(dealer.isLessThen(16)){
            dealer.receiveCard();
        }
    }

    public static GameManager create(Dealer dealer, List<Player> players) {
        return new GameManager(dealer, players);
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
}
