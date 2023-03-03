package view;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import domain.GameOutcome;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

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

    public void printParticipantsInitialState(Map<String, List<String>> participantsCards) {
        printInitializedState(participantsCards);
        printDealerInitialState(participantsCards);
        participantsCards.keySet()
                .stream()
                .filter((name) -> !Objects.equals("딜러", name))
                .forEach((name) -> printParticipantCards(name, participantsCards.get(name)));

    }

    private void printInitializedState(Map<String, List<String>> participantsCards) {
        StringBuilder stringbuilder = new StringBuilder();
        participantsCards.forEach((name, cards) -> stringbuilder.append(name).append(", "));
        stringbuilder.delete(stringbuilder.lastIndexOf(","), stringbuilder.length());
        System.out.print(stringbuilder);
        System.out.println("에게 2장을 나누었습니다.");
    }

    private void printDealerInitialState(Map<String, List<String>> participantsCards) {
        participantsCards.keySet()
                .stream()
                .filter((name) -> Objects.equals("딜러", name))
                .forEach((name) -> printParticipantCards(name, participantsCards.get(name).subList(0, 1)));
    }

    public void printParticipantsResults(Map<String, List<String>> participantsCards, List<Integer> scores) {
        AtomicInteger integer = new AtomicInteger(0);
        participantsCards.forEach((name, cards) -> {
            System.out.print(name + "카드: ");
            System.out.print(String.join(", ", cards));
            System.out.println("-결과: " + scores.get(integer.getAndIncrement()));
        });
    }
}
