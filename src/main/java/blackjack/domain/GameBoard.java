package blackjack.domain;

import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Name;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.Players;
import blackjack.domain.participants.Victory;
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
        return players.isOnePlayerNotOver(playerIndex);
    }

    public boolean isDealerNotOver() {
        return dealer.isNotOver(Dealer.DEALER_BOUNDARY_SCORE);
    }

    public void distributeInitialHand() {
        dealer.setInitialHand();
        for (int playerIndex = 0; playerIndex < players.size(); playerIndex++) {
            distributeOnePlayerInitialHand(playerIndex);
        }
    }

    private void distributeOnePlayerInitialHand(int playerIndex) {
        for (int cardCount = 0; cardCount < INITIAL_CARD_COUNT; cardCount++) {
            players.receiveOnePlayerCard(dealer.drawCard(), playerIndex);
        }
    }

    public void addCardToPlayer(int playerIndex) {
        players.receiveOnePlayerCard(dealer.drawCard(), playerIndex);
    }

    public void addCardToDealer() {
        dealer.receiveCard(dealer.drawCard());
    }

    public Map<Player, Victory> calculateVictory() {
        return players.calculateVictory(dealer.calculateScore(), dealer.isBlackjack());
    }

    public void calculateBettingMoney(Map<Player, Victory> victory) {
        for(Player onePlayer: victory.keySet()) {
            calculateOnePlayerBettingMoney(onePlayer, victory.get(onePlayer));
        }
    }

    private void calculateOnePlayerBettingMoney(Player onePlayer, Victory victory) {
        float benefit = calculateBenefit(victory);
        if (victory.equals(Victory.LOSE)) {
            dealer.gainMoney(onePlayer.getMoney());

        }
        onePlayer.checkBettingMoney(benefit);
        if(victory.equals(Victory.BLACKJACK_WIN) || victory.equals(Victory.WIN)) {
            dealer.loseMoney(onePlayer.getMoney());

        }
    }

    private float calculateBenefit(Victory victory) {
        return Stream.of(Victory.BLACKJACK_WIN, Victory.WIN, Victory.TIE)
                .filter(targetVictory -> targetVictory.equals(victory))
                .findFirst()
                .map(Victory::getBenefit)
                .orElse(Victory.LOSE.getBenefit());
    }

    public Name getPlayerName(int playerIndex) {
        return players.getOnePlayerName(playerIndex);
    }

    public Players getPlayers() {
        return players;
    }

    public Player getPlayer(int playerIndex) {
        return players.getOnePlayer(playerIndex);
    }

    public Dealer getDealer() {
        return dealer;
    }
}
