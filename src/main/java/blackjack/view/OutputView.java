package blackjack.view;

import blackjack.domain.Participants;
import blackjack.domain.participant.ParticipantResult;
import blackjack.dto.WinningResult;
import java.util.List;

public class OutputView {

    public void printLine(String message) {
        System.out.println(message);
    }

    public void askGameMembers() {
        printLine("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public void printInitialSetUp(Participants participants) {
        String playersNickname = String.join(", ", participants.getAllPlayerNickname());
        printNewLine();
        String message = String.format("딜러와 %s에게 2장을 나누었습니다.", playersNickname);
        printLine(message);
    }

    public void printInitialResult(Participants participants) {
        for (ParticipantResult result : participants.getInitialResult()) {
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

    public void printWinner(List<WinningResult> winningResultList) {
        printNewLine();
        printLine("## 최종 승패");
        printLine(winningResultList.removeFirst().dealerWinningResult());
        winningResultList.forEach(winningResult -> printLine(winningResult.playerWinningResult()));
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
}
