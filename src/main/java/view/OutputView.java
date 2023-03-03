package view;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import domain.GameOutcome;
import java.util.Map;

public class OutputView {

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
}
