package blackjack.domain;

import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Name;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.Players;
import blackjack.domain.participants.Result;
import java.util.Map;
import java.util.stream.Stream;

public class GameBoard {
    public static final int INITIAL_CARD_COUNT = 2;

    private final Dealer dealer;
    private final Players players;

    public GameBoard(Players players) {
        this.dealer = new Dealer();
        this.players = players;
    }

    public int countPlayers() {
        return players.size();
    }

    public boolean isPlayerNotOver(int playerIndex) {
        return players.isPlayerNotOver(playerIndex);
    }

    public boolean isDealerNotOver() {
        return dealer.isNotOver();
    }

    public void distributeInitialHand() {
        dealer.setInitialHand();
        for (int playerIndex = 0; playerIndex < players.size(); playerIndex++) {
            distributeOnePlayerInitialHand(playerIndex);
        }
    }

    private void distributeOnePlayerInitialHand(int playerIndex) {
        for (int cardCount = 0; cardCount < INITIAL_CARD_COUNT; cardCount++) {
            players.receivePlayerCard(dealer.drawCard(), playerIndex);
        }
    }

    public void addCardToPlayer(int playerIndex) {
        players.receivePlayerCard(dealer.drawCard(), playerIndex);
    }

    public void addCardToDealer() {
        dealer.receiveCard(dealer.drawCard());
    }

    public void calculateBettingMoney() {
        Map<Player, Result> victory = calculateResult();
        for (Player onePlayer : victory.keySet()) {
            calculateOnePlayerBettingMoney(onePlayer, victory.get(onePlayer));
        }
    }

    private Map<Player, Result> calculateResult() {
        return players.calculateResult(dealer.calculateScore(), dealer.isBlackjack());
    }

    private void calculateOnePlayerBettingMoney(Player onePlayer, Result result) {
        float benefit = calculateBenefit(result);
        calculateDealerGainMoney(onePlayer, result);
        onePlayer.checkBettingMoney(benefit);
        calculateDealerLoseMoney(onePlayer, result);
    }

    private float calculateBenefit(Result result) {
        return Stream.of(Result.BLACKJACK_WIN, Result.WIN, Result.TIE)
                .filter(targetVictory -> targetVictory.equals(result))
                .findFirst()
                .map(Result::getBenefit)
                .orElse(Result.LOSE.getBenefit());
    }

    private void calculateDealerGainMoney(Player onePlayer, Result result) {
        if (result.equals(Result.LOSE)) {
            dealer.gainMoney(onePlayer.getGamblingMoney());
        }
    }

    private void calculateDealerLoseMoney(Player onePlayer, Result result) {
        if (result.equals(Result.BLACKJACK_WIN) || result.equals(Result.WIN)) {
            dealer.loseMoney(onePlayer.getGamblingMoney());
        }
    }

    public Name getPlayerName(int playerIndex) {
        return players.getPlayerName(playerIndex);
    }

    public Players getPlayers() {
        return players;
    }

    public Player getPlayer(int playerIndex) {
        return players.getPlayer(playerIndex);
    }

    public Dealer getDealer() {
        return dealer;
    }
}
