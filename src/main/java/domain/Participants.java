package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 우가
 * @version 1.0.0
 * @Created by 우가 on 2023/03/07
 */
public class Participants {

    private final Dealer dealer;
    private final Players players;

    public Participants(final Dealer dealer, final Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public List<String> getNames() {
        String dealerName = dealer.getName();
        List<String> playersName = players.getNames();
        playersName.add(0, dealerName);
        return playersName;
    }

    public List<Cards> getCards() {
        Cards cards = dealer.getCards();
        List<Cards> cardss = players.getCardss();
        cardss.add(0, cards);
        return cardss;
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Integer> getWinningResult() {
        List<Integer> winningResult = new ArrayList<>();
        for (int index = 0; index < players.size(); index++) {
            winningResult.add(dealer.checkWinningResult(players.getPlayer(index)));
        }
        return winningResult;
    }
}
