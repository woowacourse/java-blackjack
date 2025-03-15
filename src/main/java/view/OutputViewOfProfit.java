package view;

import domain.participant.GameResult;
import domain.participant.Participant;
import domain.participant.ParticipantsResult;
import java.util.Map;

public class OutputViewOfProfit extends OutputView {

    private static final String RESULT_MESSAGE = "## 최종 수익";

    @Override
    public void printGameResult(ParticipantsResult participantsResult) {
        System.out.println();
        System.out.println(RESULT_MESSAGE);
        Map<Participant, GameResult> playersResult = participantsResult.getPlayersResult();
        printDealerResult(playersResult);
        printPlayersResult(playersResult);
    }

    private void printDealerResult(Map<Participant, GameResult> playersResult) {
        System.out.print("딜러: ");
        int sum = 0;
        for (Participant player : playersResult.keySet()) {
            sum -= playersResult.get(player).getCalculateValue(player.getBettingAmount());
        }
        System.out.println(sum);
    }

    private void printPlayersResult(Map<Participant, GameResult> playersResult) {
        for (Participant player : playersResult.keySet()) {
            System.out.println(player.getName() + ": " + playersResult.get(player)
                .getCalculateValue(player.getBettingAmount()));
        }
    }
}
