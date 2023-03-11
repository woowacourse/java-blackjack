package domain.participant;

import static domain.BlackJackWinningResult.DRAW;
import static domain.BlackJackWinningResult.WIN;

import domain.BlackJackWinningResult;
import domain.card.Cards;
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

    public List<String> getPlayerNames() {
        return players.getNames();
    }

    public List<Cards> getPlayerCards() {
        return players.getCards();
    }

    public List<Double> getProfits() {
        List<Double> profits = new ArrayList<>();
        List<BlackJackWinningResult> result = getBlackJackWinningResult();
        for (int index = 0; index < players.size(); index++) {
            profits.add(makeProfits(result.get(index), players.getPlayer(index)));
        }
        return profits;
    }

    private double makeProfits(final BlackJackWinningResult result, final Player player) {
        if (result == WIN) {
            return -1.0;
        }
        if (result == DRAW) {
            return 0.0;
        }
        return checkBlackJack(player);
    }

    private double checkBlackJack(final Player player) {
        if (player.isBlackJack()) {
            return 1.5;
        }
        return 1.0;
    }

    private List<BlackJackWinningResult> getBlackJackWinningResult() {
        List<BlackJackWinningResult> winningResult = new ArrayList<>();
        for (int index = 0; index < players.size(); index++) {
            winningResult.add(dealer.checkWinningResult(players.getPlayer(index)));
        }
        return winningResult;
    }

    public String getDealerName() {
        return dealer.getName();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayersToList() {
        return players.getPlayers();
    }

    public String getDealerFirstCard() {
        return dealer.getCards().cardsToString().get(0);
    }

    public List<List<String>> copiedPlayersCardsToList() {
        return players.cardsToString();
    }

    public List<String> getDealerCards() {
        return dealer.getCards().cardsToString();
    }

    public int sumOfDealerCards() {
        return dealer.sumOfCards();
    }

}
