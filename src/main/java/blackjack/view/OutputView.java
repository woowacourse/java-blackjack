package blackjack.view;

import blackjack.dto.PlayerResult;
import blackjack.dto.WinningResult;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    
    public void printLine(String message) {
        System.out.println(message);
    }
    
    public void askGameMembers() {
        printLine("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }
    
    public void printInitialSetUp(String playerNicknames) {
        String message = String.format("딜러와 %s에게 2장을 나누었습니다.", playerNicknames);
        printLine(message);
    }
    
    public void printInitialResult(String initialResults) {
        printLine(initialResults);
        printNewLine();
    }
    
    public void hitOrStand(String nickname) {
        printLine(String.format("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", nickname));
    }
    
    public void printPlayerStatus(String drawablePlayerNickname, String statusByDisplayName) {
        printLine(String.format("%s카드: %s", drawablePlayerNickname, statusByDisplayName));
    }

    public void printTotalResult(String totalResult) {
        printNewLine();
        printLine(totalResult);
    }

    public void printWinningResults(WinningResult winningResult) {
        printNewLine();
        printLine("## 최종 승패");
        printDealerWinning(winningResult);
        printPlayerWinning(winningResult.playerResults());
    }

    private void printDealerWinning(WinningResult winningResult) {
        printLine(String.format("딜러: %d승 %d패", winningResult.dealerWin(), winningResult.dealerLoss()));
    }

    private void printPlayerWinning(List<PlayerResult> playerResults) {
        playerResults
                .forEach(playerResult ->
                        printLine(String.format("%s: %s", playerResult.nickname(),
                                playerResult.gameResult().getMessage())));
    }

    public void printDealerTurn() {
        printNewLine();
        printLine("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }
    
    private void printNewLine() {
        System.out.println();
    }
}
