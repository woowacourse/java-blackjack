package blackjack.view;

import blackjack.domain.participant.PlayerNicknames;
import blackjack.view.dto.DealerInitialHand;
import blackjack.view.dto.ParticipantHandScore;
import blackjack.view.dto.PlayerHand;
import blackjack.view.dto.PlayerProfit;
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
    
    public void printAskPlayerBettingAmount(String nickname) {
        printNewLine();
        printLine(String.format("%s의 배팅 금액은?", nickname));
    }
    
    public void printInitialSetUp(PlayerNicknames playerNames) {
        printNewLine();
        String playerNicknames = String.join(", ", playerNames.nicknames());
        printLine(String.format("딜러와 %s에게 2장을 나누었습니다.", playerNicknames));
    }
    
    public void printDealerInitialHand(DealerInitialHand dealerHand) {
        printDealerInitialHand(dealerHand.nickname(), dealerHand.cardDisplayName());
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
    
    private void printDealerInitialHand(String nickname, String cardNames) {
        printLine(String.format("%s: %s", nickname, cardNames));
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
        printDealerHandScore(participantsHandScore.getFirst());
        participantsHandScore.subList(1, participantsHandScore.size()).forEach(this::printPlayerHandScore);
    }
    
    public void printDealerHandScore(ParticipantHandScore playerStatus) {
        String nickname = playerStatus.nickname();
        String cardDisplayNames = String.join(", ", playerStatus.cardDisplayNames());
        String toTotalScore = String.valueOf(playerStatus.totalScore());
        printLine(String.format("%s 카드: %s - 결과: %s", nickname, cardDisplayNames, toTotalScore));
    }
    
    public void printPlayerHandScore(ParticipantHandScore playerStatus) {
        String nickname = playerStatus.nickname();
        String cardDisplayNames = String.join(", ", playerStatus.cardDisplayNames());
        String toTotalScore = String.valueOf(playerStatus.totalScore());
        printLine(String.format("%s카드: %s - 결과: %s", nickname, cardDisplayNames, toTotalScore));
    }
    
    public void hitOrStand(String nickname) {
        printLine(String.format("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", nickname));
    }
    
    public void printFinalProfit(TotalWinningResult totalWinningResult) {
        printNewLine();
        printLine("## 최종 승패");
        printDealerProfit(totalWinningResult);
        printPlayersProfit(totalWinningResult);
    }
    
    private void printDealerProfit(TotalWinningResult totalWinningResult) {
        printLine(String.format("딜러: %d", totalWinningResult.dealerProfit()));
    }
    
    public void printPlayersProfit(TotalWinningResult totalWinningResult) {
        totalWinningResult.playerResults().forEach(this::printPlayersProfit);
    }
    
    private void printPlayersProfit(PlayerProfit playerProfit) {
        printLine(String.format("%s: %d", playerProfit.nickname(), playerProfit.profit()));
    }
}
