package blackjack.domain.game;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Status;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameResult {
    private static final int DIVISION_NUMBER_FOR_WINNER = 2;
    private static final int MULTIPLE_NUMBER_FOR_WINNER = 3;
    private static final long DEFAULT_BETTING_PRIZE = 0L;

    private final Map<Player, Long> bettingResult = new LinkedHashMap<>();

    public GameResult(Participants participants) {
        initGameResult(participants.getPlayers(), participants.getDealer());
    }

    private void initGameResult(List<Player> players, Dealer dealer) {
        for (Player player : players) {
            bettingResult.put(player, elicitBettingResult(player, dealer));
        }
    }

    private Long elicitBettingResult(Player player, Dealer dealer) {
        Cards playerCards = player.getCards();
        Cards dealerCards = dealer.getCards();

        Status playerStatus = playerCards.getStatus();
        Status dealerStatus = dealerCards.getStatus();

        if (playerStatus == Status.BLACKJACK && dealerStatus != Status.BLACKJACK) {
            return player.getBettingAmount() / DIVISION_NUMBER_FOR_WINNER * MULTIPLE_NUMBER_FOR_WINNER;
        }

        if (compareCards(playerCards, dealerCards)) {
            return player.getBettingAmount();
        }

        if (compareCards(dealerCards, playerCards)) {
            return -player.getBettingAmount();
        }

        return DEFAULT_BETTING_PRIZE;
    }

    /**
     * 파라미터로 주어진 카드 목록 두 개를 비교하고, 첫 번째 카드 목록이 승리할 경우 true 반환.
     *
     * @param cards1 - 카드 목록, cards2 - cards1과 비교할 카드 목록
     * @return cards1이 승리 조건에 부합하면 true
     */
    private boolean compareCards(Cards cards1, Cards cards2) {
        Status status1 = cards1.getStatus();
        Status status2 = cards2.getStatus();

        return (status1 == Status.BLACKJACK && status2 != Status.BLACKJACK)
                || (status1 != Status.BUST && status2 == Status.BUST)
                || (status1 == Status.NONE && status2 == Status.NONE && cards1.sum() > cards2.sum());
    }

    public long getDealerProfit() {
        long allBettingAmount = DEFAULT_BETTING_PRIZE;
        for (Map.Entry<Player, Long> entry : bettingResult.entrySet()) {
            allBettingAmount -= entry.getValue();
        }
        return allBettingAmount;
    }

    public Long getBettingResult(Player player) {
        return bettingResult.get(player);
    }

    public Map<Player, Long> getBettingResult() {
        return Collections.unmodifiableMap(bettingResult);
    }

    @Override
    public String toString() {
        return "GameResult{" +
                "bettingResult=" + bettingResult +
                '}';
    }
}
