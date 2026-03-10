package presentation.ui;

import presentation.dto.GameResult;
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
        System.out.println("## 최종 수익");
        printDealerGameResult(
                gameResult.dealerAmount()
        );
        printPlayerGameResult(gameResult.playerAmounts());
    }

    private void printDealerGameResult(int amount) {
        System.out.printf("딜러: %d\n", amount);
    }

    private void printPlayerGameResult(Map<String, Integer> roundResults) {
        for (Entry<String, Integer> round : roundResults.entrySet()) {
            System.out.printf("%s: %d\n", round.getKey(), round.getValue());
        }
    }

    private void printDistributeMessage(String dealerName, List<MemberStatus> memberStatuses) {
        String playerNames = memberStatuses.stream()
                .map(MemberStatus::memberName)
                .filter(memberName -> !memberName.equals(dealerName))
                .collect(Collectors.joining(", "));
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.\n", playerNames);
    }

    private void printMemberHandCard(MemberStatus memberStatus) {
        System.out.println(
                memberStatus.memberName()
                        + ": "
                        + String.join(", ", memberStatus.cards())
        );
    }

    private void printFinalMemberCardAndResult(MemberStatus memberStatus) {
        String cards = String.join(", ", memberStatus.cards());
        System.out.printf("%s카드: %s - 결과: %d\n", memberStatus.memberName(), cards, memberStatus.totalValue());
    }
}
