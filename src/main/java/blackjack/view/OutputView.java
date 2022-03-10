package blackjack.view;

import blackjack.domain.dto.Status;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class OutputView {

    public static void printInitialStatus(List<Status> dtos) {
        System.out.println(makeInitialDrawTitleString(dtos));

        for (Status dto : dtos) {
            printStatus(dto);
        }

        System.out.println();
    }

    private static String makeInitialDrawTitleString(List<Status> dtos) {
        return "\n" + dtos.get(0).getName() +
                "와 " +
                dtos.subList(1, dtos.size()).stream()
                        .map(Status::getName)
                        .collect(Collectors.joining(", ")) +
                "에게 2장을 나누었습니다.";
    }

    public static void printTotalScore(List<Status> dtos) {
        System.out.println();
        for (Status status : dtos) {
            System.out.print(makeStatusString(status) + " - 결과: " + status.getScore() + "\n");
        }
    }

    public static void printStatus(Status status) {
        System.out.println(makeStatusString(status));
    }

    private static String makeStatusString(Status status) {
        return status.getName() + "카드: " +
                status.getCardDtos().stream()
                        .map(cardDto -> (cardDto.getDenomination() + cardDto.getSymbol()))
                        .collect(Collectors.joining(", "));
    }

    public static void printResult(Map<String, String> playerResults, List<String> dealerResult) {
        System.out.println("## 최종 승패");
        System.out.println(makeDealerResultString(dealerResult));

        playerResults.forEach(
                (name, result) -> System.out.println(name + ": " + result)
        );
    }

    private static String makeDealerResultString(List<String> dealerResult) {
        Map<String, Long> countMap = dealerResult.stream()
                .collect(groupingBy(Function.identity(), Collectors.counting()));
        return "딜러 : " + countMap.getOrDefault("승", 0L) + "승 " +
                countMap.getOrDefault("패", 0L);
    }

    public static void printDealerHitMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }
}
