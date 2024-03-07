package blackjack.view;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.List;

public class OutputView {

    public void printStartCards(Dealer dealer, Players players) {
        System.out.println("딜러:" + dealer.getStartCards());
        List<Player> allPlayers = players.getPlayers();
        for (Player player : allPlayers) {
            System.out.println(player.getName() + ": " + player.getStartCards());
        }
    }

    public void printCards() {
    }

}
