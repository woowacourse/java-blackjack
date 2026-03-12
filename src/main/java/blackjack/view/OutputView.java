package blackjack.view;

import blackjack.domain.participant.PlayerNicknames;
import blackjack.view.dto.DealerInitialHand;
import blackjack.view.dto.GameResultDisplayName;
import blackjack.view.dto.ParticipantHandScore;
import blackjack.view.dto.PlayerHand;
import blackjack.view.dto.TotalWinningResult;
import java.util.List;

public class OutputView {
    
    public void printLine(String message) {
        System.out.println(message);
    }
    
    private void printNewLine() {
        System.out.println();
    }
    
    public void askGameMembers() {
        printLine("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }
    
    public void printInitialSetUp(PlayerNicknames playerNames) {
        printNewLine();
        String playerNicknames = String.join(", ", playerNames.nicknames());
        printLine(String.format("딜러와 %s에게 2장을 나누었습니다.", playerNicknames));
    }
    
    public void printDealerInitialHand(DealerInitialHand dealerHand) {
        printParticipantInitialHand(dealerHand.nickname(), dealerHand.cardDisplayName());
    }
    
    public void printPlayersInitialHand(List<PlayerHand> playersHand) {
        playersHand.forEach(this::printParticipantInitialHand);
        printNewLine();
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
        printNewLine();
        participantsHandScore.forEach(this::printParticipantHandScore);
    }
    
    public void printParticipantHandScore(ParticipantHandScore playerStatus) {
        String nickname = playerStatus.nickname();
        String cardDisplayNames = String.join(", ", playerStatus.cardDisplayNames());
        String toTotalScore = String.valueOf(playerStatus.totalScore());
        printLine(String.format("%s카드: %s - 결과: %s", nickname, cardDisplayNames, toTotalScore));
    }
    
    public void hitOrStand(String nickname) {
        printLine(String.format("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", nickname));
    }
    
    public void printWinningResults(TotalWinningResult totalWinningResult) {
        printNewLine();
        printLine("## 최종 승패");
        printDealerWinningResults(totalWinningResult);
        printPlayerWinningResults(totalWinningResult);
    }
    
    public void printDealerWinningResults(TotalWinningResult totalWinningResult) {
        printDealerWinning(totalWinningResult);
    }
    
    private void printDealerWinning(TotalWinningResult totalWinningResult) {
        printLine(
                String.format("딜러: %d승 %d패", totalWinningResult.dealerWinCount(),
                        totalWinningResult.dealerLossCount()));
    }
    
    public void printPlayerWinningResults(TotalWinningResult totalWinningResult) {
        totalWinningResult.playerResults()
                .forEach(playerGameResult -> {
                    printPlayerWinningResult(
                            playerGameResult.nickname(),
                            GameResultDisplayName.from(playerGameResult.gameResult())
                    );
                });
    }
    
    private void printPlayerWinningResult(String nickname, GameResultDisplayName from) {
        printLine(String.format("%s: %s", nickname, from.displayName()));
    }
    
    public void printAskPlayerBettingAmount(String nickname) {
        printLine(String.format("%s의 배팅 금액은?", nickname));
    }
}
