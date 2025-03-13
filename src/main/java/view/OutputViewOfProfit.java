package view;

import domain.participant.GameResult;
import domain.participant.ParticipantsResult;
import domain.participant.Player;
import java.util.Map;

public class OutputViewOfProfit extends OutputView {

    private static final String RESULT_MESSAGE = "## 최종 수익";

    @Override
    public void printGameResult(ParticipantsResult participantsResult) {
        System.out.println();
        System.out.println(RESULT_MESSAGE);
        Map<Player, GameResult> playersResult = participantsResult.getPlayersResult();
        printDealerResult(playersResult);
        printPlayersResult(playersResult);
    }

    private void printDealerResult(Map<Player, GameResult> playersResult) {
        System.out.print("딜러: ");
        int sum = 0;
        for (Player player : playersResult.keySet()) {
            sum -= playersResult.get(player).getCalculateValue(player.getBettingAmount());
        }
        System.out.println(sum);
    }

    private void printPlayersResult(Map<Player, GameResult> playersResult) {
        for (Player player : playersResult.keySet()) {
            System.out.println(player.getName() + ": " + playersResult.get(player)
                .getCalculateValue(player.getBettingAmount()));
        }
    }
}
