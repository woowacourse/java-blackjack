package blackjack.view;

import blackjack.dto.DealerInitialHand;
import blackjack.dto.ParticipantHandScore;
import blackjack.dto.PlayerGameResult;
import blackjack.dto.PlayerHand;
import blackjack.dto.PlayerNames;
import blackjack.dto.TotalWinningResult;
import java.util.List;

public class OutputView {

    public void printLine(String message) {
        System.out.println(message);
    }

    public void askGameMembers() {
        printLine("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public void printInitialSetUp(PlayerNames playerNames) {
        printNewLine();
        printLine(String.format("딜러와 %s에게 2장을 나누었습니다.", playerNames));
    }

    public void printDealerInitialHand(DealerInitialHand dealerHand) {
        printParticipantInitialHand(dealerHand.nickname(), dealerHand.cardDisplayName());
    }

    public void printPlayersInitialHand(List<PlayerHand> playersHand) {
        playersHand.forEach(this::printParticipantInitialHand);
    }

    public void printParticipantInitialHand(PlayerHand playerHand) {
        String nickname = playerHand.nickname();
        String cardNames = String.join(", ", playerHand.cardNames());
        printParticipantInitialHand(nickname, cardNames);
    }

    private void printParticipantInitialHand(String nickname, String cardNames) {
        printLine(String.format("%s카드: %s", nickname, cardNames));
    }

    public void printDealerTurn() {
        printNewLine();
        printLine("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printParticipantsHandScore(List<ParticipantHandScore> participantsHandScore) {
        participantsHandScore.forEach(this::printParticipantHandScore);
    }

    public void printParticipantHandScore(ParticipantHandScore playerStatus) {
        printLine(String.format("%s카드: %s - 결과: %d", playerStatus.nickname(), playerStatus.cardNames(),
                playerStatus.totalScore()));
    }

    public void hitOrStand(String nickname) {
        printLine(String.format("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", nickname));
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

    private void printNewLine() {
        System.out.println();
    }
}
