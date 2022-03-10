package blackjack.view;

import blackjack.domain.dto.CardDto;
import blackjack.domain.dto.Status;
import blackjack.domain.player.Dealer;

import java.util.List;
import java.util.stream.Collectors;

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

    public static void printDealerAdded(String name) {
        System.out.println("\n" + name + "는 " + Dealer.BOUND_FOR_ADDITIONAL_CARD
                + "이하라 한장의 카드를 더 받았습니다.");
    }
}
