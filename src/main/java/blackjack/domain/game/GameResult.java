package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameResult {

    public static final int BLACKJACK_VALUE = 21;
    protected static final int BLACKJACK_COUNT = 2;
    private static final int DIVISION_NUMBER_FOR_WINNER = 2;
    private static final int MULTIPLE_NUMBER_FOR_WINNER = 3;
    private static final long DEFAULT_BETTING_PRIZE = 0L;

    private final Map<Player, Long> bettingResult = new LinkedHashMap<>();

    public GameResult(Participants participants) {
        initGameResult(participants.getPlayers(), participants.getDealer());
    }

    private void initGameResult(List<Player> players, Dealer dealer) {
        for (Player player : players) {
            bettingResult.put(player, calculateBettingResult(player, dealer));
        }
    }

    private Long calculateBettingResult(Player player, Dealer dealer) {
        ParticipantGameInfo playerGameInfo = new ParticipantGameInfo(player.getCards());
        ParticipantGameInfo dealerGameInfo = new ParticipantGameInfo(dealer.getCards());

        if (playerGameInfo.isBlackJack() && !dealerGameInfo.isBlackJack()) {
            return player.getBettingAmount() / DIVISION_NUMBER_FOR_WINNER * MULTIPLE_NUMBER_FOR_WINNER;
        }

        if (compareCards(playerGameInfo, dealerGameInfo)) {
            return player.getBettingAmount();
        }

        if (compareCards(dealerGameInfo, playerGameInfo)) {
            return -player.getBettingAmount();
        }

        return DEFAULT_BETTING_PRIZE;
    }

    /**
     * 파라미터로 주어진 카드 목록 두 개를 비교하고, 첫 번째 카드 목록이 승리할 경우 true 반환.
     *
     * @param participantGameInfo1 - 참가자의 게임 정보, participantGameInfo2 - participantGameInfo1와 비교할 카드 목록
     * @return participantGameInfo1이 승리 조건에 부합하면 true
     */
    private boolean compareCards(ParticipantGameInfo participantGameInfo1, ParticipantGameInfo participantGameInfo2) {
        Status status1 = participantGameInfo1.getStatus();
        Status status2 = participantGameInfo2.getStatus();

        return (status1 == Status.BLACKJACK && status2 != Status.BLACKJACK)
                || (status1 != Status.BUST && status2 == Status.BUST)
                || (status1 == Status.NONE && status2 == Status.NONE
                && participantGameInfo1.getScore() > participantGameInfo2.getScore());
    }

    public long getDealerProfit() {
        return bettingResult.values()
                .stream()
                .mapToLong(value -> -value)
                .sum();
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
