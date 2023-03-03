package view;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import domain.GameOutcome;
import java.util.List;
import java.util.Map;

public class OutputView {

    public void printParticipantCards(String name, List<String> cards) {
        System.out.print(name + "카드: ");
        System.out.println(String.join(", ", cards));
    }

    public void printGameOutcomes(Map<String, GameOutcome> playerOutcomes) {
        System.out.println("## 최종 승패");
        Map<GameOutcome, Long> dealerOutcome = playerOutcomes.values()
                .stream()
                .collect(groupingBy(GameOutcome::oppositeOutcome, counting()));
        printDealerOutcome(dealerOutcome);
        playerOutcomes.forEach((name, outcome) -> System.out.println(name + ": " + outcome.value()));
    }

    private void printDealerOutcome(Map<GameOutcome, Long> dealerOutcome) {
        System.out.print("딜러:");
        dealerOutcome.forEach((outcome, count) -> System.out.print(" " + count + outcome.value()));
        System.out.println();
    }

    public void printDealerHandOutInfo() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }
}
