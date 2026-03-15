package blackjack.view;

import blackjack.dto.DealerGameResult;
import blackjack.dto.ParticipantResult;
import blackjack.dto.PlayerGameResult;
import blackjack.dto.PlayerNicknamesResult;
import blackjack.dto.TotalGameResult;
import java.util.List;

public class OutputView {

    public void printLine(String message) {
        System.out.println(message);
    }

    public void askGameMembers() {
        printLine("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public void printInitialSetUp(PlayerNicknamesResult playerNicknames) {
        String playersNickname = String.join(", ", playerNicknames.nicknames());
        printNewLine();
        String message = String.format("딜러와 %s에게 2장을 나누었습니다.", playersNickname);
        printLine(message);
    }

    public void printInitialResult(List<ParticipantResult> participantsInitialResult) {
        for (ParticipantResult result : participantsInitialResult) {
            printLine(result.toString());
        }
        printNewLine();
    }

    public void hitOrStand(String nickname) {
        printLine(String.format("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", nickname));
    }

    public void printPlayerStatus(String drawablePlayerNickname, String statusByDisplayName) {
        printLine(String.format("%s카드: %s", drawablePlayerNickname, statusByDisplayName));
    }

    public void printGameResult(List<ParticipantResult> participantResult) {
        printNewLine();
        for (ParticipantResult result : participantResult) {
            printLine(result.toFullString());
        }
    }

    public void printDealerTurn() {
        printNewLine();
        printLine("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    private void printNewLine() {
        System.out.println();
    }

    public void askBetAmount(String playerName) {
        printNewLine();
        printLine(String.format("%s의 배팅 금액은?", playerName));
    }

    public void printTotalProfitResult(TotalGameResult totalGameResult) {
        printNewLine();
        printLine("## 최종 수익");
        printDealerProfitResult(totalGameResult.dealerGameResult());
        printPlayersProfitResult(totalGameResult.playerGameResult());
    }

    private void printPlayersProfitResult(List<PlayerGameResult> playerGameResults) {
        for (PlayerGameResult playerGameResult : playerGameResults) {
            printLine(String.format("%s: %s", playerGameResult.nickname(), playerGameResult.profit()));
        }
    }

    private void printDealerProfitResult(DealerGameResult dealerGameResult) {
        printLine(String.format("딜러: %s", dealerGameResult.profit()));
    }
}
