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
            System.out.println();
        }

        System.out.println();
    }

    private static String makeInitialDrawTitleString(List<Status> dtos) {
        return "\n" + dtos.get(0).getName() +
                "와 " +
                dtos.subList(1, dtos.size()).stream()
                        .map(Status::getName)
                        .collect(Collectors.joining(", ")) +
                "에게 2장을 나누었습니다.\n";
    }

    public static void printTotalScore(List<Status> dtos) {
        System.out.println();
        for (Status dto : dtos) {
            printStatus(dto);
            System.out.print("- 결과: " + dto.getScore() + "\n");
        }
    }

    public static void printStatus(Status status) {
        StringBuilder result = new StringBuilder();

        String name = status.getName();
        List<CardDto> cardDtos = status.getCardDtos();


        result.append(name);
        result.append("카드: ");

        result.append(cardDtos.stream()
                .map(c -> (c.getDenomination() + c.getSymbol()))
                .collect(Collectors.joining(", ")));

        System.out.println(result);
    }

    public static void printDealerAdded(String name) {
        System.out.println("\n" + name + "는 " + Dealer.BOUND_FOR_ADDITIONAL_CARD
                + "이하라 한장의 카드를 더 받았습니다.");
    }
}
