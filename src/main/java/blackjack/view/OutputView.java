package blackjack.view;

import blackjack.dto.ParticipantStatus;
import blackjack.dto.PlayerGameResult;
import blackjack.dto.TotalWinningResult;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    
    public void printLine(String message) {
        System.out.println(message);
    }
    
    public void askGameMembers() {
        printLine("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }
    
    public void printInitialSetUp(List<ParticipantStatus> playersStatus) {
        printNewLine();
        String playerNicknames = playersStatus
                .stream()
                .map(ParticipantStatus::nickname)
                .collect(Collectors.joining(", "));
        String message = String.format("딜러와 %s에게 2장을 나누었습니다.", playerNicknames);
        printLine(message);
    }
    
    public void printInitialResult(List<ParticipantStatus> playersStatus) {
        playersStatus.forEach(
                playerStatus -> printLine(playersCardStatus(playerStatus)));
        
        printNewLine();
    }
    
    public void printPlayerStatus(ParticipantStatus playerStatus) {
        printLine(playersCardStatus(playerStatus));
    }
    
    private String playersCardStatus(ParticipantStatus participantStatus) {
        return String.format("%s카드: %s", participantStatus.nickname(), participantStatus.cardStatus());
    }
    
    private String playersTotalStatus(ParticipantStatus participantStatus) {
        return String.format("%s - 결과: %d", playersCardStatus(participantStatus), participantStatus.totalScore());
    }
    
    public void hitOrStand(ParticipantStatus drawablePlayer) {
        printLine(String.format("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", drawablePlayer.nickname()));
    }
    
    public void printTotalResult(List<ParticipantStatus> playersStatus) {
        printNewLine();
        playersStatus.forEach(
                playerStatus -> printLine(playersTotalStatus(playerStatus))
        );
    }
    
    public void printWinningResults(TotalWinningResult totalWinningResult) {
        printNewLine();
        printLine("## 최종 승패");
        printDealerWinning(totalWinningResult);
        printPlayerWinning(totalWinningResult.playerGameResults());
    }
    
    private void printDealerWinning(TotalWinningResult totalWinningResult) {
        printLine(String.format("딜러: %d승 %d패", totalWinningResult.dealerWin(), totalWinningResult.dealerLoss()));
    }
    
    private void printPlayerWinning(List<PlayerGameResult> playerGameResults) {
        playerGameResults
                .forEach(playerGameResult ->
                        printLine(String.format("%s: %s", playerGameResult.nickname(),
                                playerGameResult.gameResult().getMessage())));
    }
    
    public void printDealerTurn() {
        printNewLine();
        printLine("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }
    
    private void printNewLine() {
        System.out.println();
    }
}
