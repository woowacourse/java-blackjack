package presentation.ui;

import presentation.dto.GameResult;
import presentation.dto.HitInfo;
import presentation.dto.MemberStatus;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class OutputView {

    public void printInitialStatus(HitInfo dealerStatus, List<HitInfo> playerStatuses) {
        System.out.println();
        printDistributeMessage(playerStatuses);
        printMemberHandCard(dealerStatus);
        playerStatuses.forEach(this::printMemberHandCard);
        System.out.println();
    }

    public void printHandCard(HitInfo info) {
        String cardNames = String.join(", ", info.cards());
        System.out.printf("%s카드: %s\n", info.memberName(), cardNames);
    }

    public void printDealerDrawResult() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printFinalMemberStatus(MemberStatus dealer, List<MemberStatus> statuses) {
        System.out.println();
        printFinalMemberCardAndResult(dealer);
        statuses.forEach(this::printFinalMemberCardAndResult);
        System.out.println();
    }

    public void printGameResult(GameResult gameResult) {
        System.out.println("## 최종 수익");
        for (Entry<String, Integer> round : gameResult.memberAmount().entrySet()) {
            System.out.printf("%s: %d\n", round.getKey(), round.getValue());
        }
    }

    private void printDistributeMessage(List<HitInfo> memberStatuses) {
        String playerNames = memberStatuses.stream()
                .map(HitInfo::memberName)
                .collect(Collectors.joining(", "));
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.\n", playerNames);
    }

    private void printMemberHandCard(HitInfo memberStatus) {
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

    public void printBustMessage(String name) {
        System.out.println(name + "는 버스트 되었습니다.");
    }
}
