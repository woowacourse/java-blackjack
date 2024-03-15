package blackjack.domain;

import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.GamerInformation.Name;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.Players;
import blackjack.domain.participants.Outcome;
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
            distributePlayerInitialHand(playerIndex);
        }
    }

    private void distributePlayerInitialHand(int playerIndex) {
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
            Map<Player, Outcome> outcome = calculateOutcome();
        for (Player player : outcome.keySet()) {
            calculatePlayerBettingMoney(player, outcome.get(player));
        }
    }

    private Map<Player, Outcome> calculateOutcome() {
        return players.calculateOutcome(dealer.calculateScore(), dealer.isBlackjack());
    }

    private void calculatePlayerBettingMoney(Player player, Outcome outcome) {
        float benefit = calculateBenefit(outcome);
        calculateDealerGainMoney(player, outcome);
        player.checkBettingMoney(benefit);
        calculateDealerLoseMoney(player, outcome);
    }

    private float calculateBenefit(Outcome outcome) {
        return Stream.of(Outcome.BLACKJACK_WIN, Outcome.WIN, Outcome.TIE)
                .filter(targetVictory -> targetVictory.equals(outcome))
                .findFirst()
                .map(Outcome::getBenefit)
                .orElse(Outcome.LOSE.getBenefit());
    }

    private void calculateDealerGainMoney(Player player, Outcome outcome) {
        if (outcome.equals(Outcome.LOSE)) {
            dealer.gainMoney(player.getGamblingMoney());
        }
    }

    private void calculateDealerLoseMoney(Player player, Outcome outcome) {
        if (outcome.equals(Outcome.BLACKJACK_WIN) || outcome.equals(Outcome.WIN)) {
            dealer.loseMoney(player.getGamblingMoney());
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
