package view;

import model.judgement.DealerResult;
import model.judgement.PlayerResult;
import view.mapper.EnumMapper;

public class WinLossReportView {

    private WinLossReportView() {}

    public static void printFinalResultHeader() {
        System.out.println();
        System.out.println("## 최종 승패");
    }

    public static void printResultByDealer(DealerResult dealerResult) {
        System.out.print("딜러: ");
        String result = extractWinMessage(dealerResult.winCount())
                + extractDrawMessage(dealerResult.drawCount())
                + extractLoseMessage(dealerResult.loseCount());
        System.out.println(result.trim());
    }

    private static String extractWinMessage(int winCount) {
        if (winCount > 0) {
            return "승 ";
        }
        return "";
    }

    private static String extractDrawMessage(int drawCount) {
        if (drawCount > 0) {
            return "무 ";
        }
        return "";
    }

    private static String extractLoseMessage(int loseCount) {
        if (loseCount > 0) {
            return "패 ";
        }
        return "";
    }

    public static void printResultByPlayers(PlayerResult playerResult) {
        playerResult.result()
                .forEach((player, status) ->
                        System.out.printf("%s: %s%n", player.getName(), EnumMapper.RESULT_STATUS_MAPPER.get(status)));
    }
}
