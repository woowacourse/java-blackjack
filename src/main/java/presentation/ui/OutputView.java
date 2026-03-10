package presentation.ui;

import presentation.dto.GameResult;
import domain.vo.RoundResult;
import domain.card.Card;
import presentation.dto.MemberStatus;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class OutputView {

    public void printInitialStatus(String dealerName, List<MemberStatus> playerStatuses) {
        System.out.println();
        printDistributeMessage(dealerName, playerStatuses);
        playerStatuses.forEach(this::printMemberHandCard);
        System.out.println();
    }

    public void printHandCard(String playerName, List<String> cards) {
        String cardNames = String.join(", ", cards);
        System.out.printf("%s카드: %s\n", playerName, cardNames);
    }

    public void printDealerDrawResult() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        System.out.println();
    }

    public void printFinalMemberStatus(List<MemberStatus> statuses) {
        statuses.forEach(this::printFinalMemberCardAndResult);
        System.out.println();
    }

    public void printGameResult(GameResult gameResult) {
        System.out.println("## 최종 승패");
        printDealerGameResult(
                gameResult.dealerWinAmount(),
                gameResult.dealerLoseAmount()
        );
        printPlayerGameResult(gameResult.playerResults());
    }

    private void printDealerGameResult(int winAmount, int loseAmount) {
        System.out.printf("딜러: %d승 %d패\n", winAmount, loseAmount);
    }

    private void printPlayerGameResult(Map<String, RoundResult> roundResults) {
        for (Entry<String, RoundResult> round : roundResults.entrySet()) {
            System.out.printf("%s: %s\n", round.getKey(), round.getValue().result());
        }
    }

    private void printDistributeMessage(String dealerName, List<MemberStatus> playerStatuses) {
        String playerNames = playerStatuses.stream()
                .map(MemberStatus::playerName)
                .filter(s -> !s.equals(dealerName))
                .collect(Collectors.joining(", "));
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.\n", playerNames);
    }

    private void printMemberHandCard(MemberStatus playerStatus) {
        System.out.println(
                playerStatus.playerName()
                        + ": "
                        + playerStatus.cards().stream()
                        .map(Card::getCardName)
                        .collect(Collectors.joining(", "))
        );
    }

    private void printFinalMemberCardAndResult(MemberStatus status) {
        String cards = status.cards().stream().map(Card::getCardName).collect(Collectors.joining(", "));
        System.out.printf("%s카드: %s - 결과: %d\n", status.playerName(), cards, status.totalValue());
    }
}
