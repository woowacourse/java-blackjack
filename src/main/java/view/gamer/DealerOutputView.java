package view.gamer;

import domain.card.Card;
import dto.DealerDTO;
import java.util.List;
import java.util.stream.Collectors;
import view.Console;

public class DealerOutputView {
    public static void print(DealerDTO dealerDTO) {
        String outputWithoutSummationCardPoint = generateOutputWithoutSummationCardPoint(dealerDTO);
        String summationCardPointOutput = "결과: %d".formatted(dealerDTO.getSummationCardPoint());
        Console.printf("%s - %s%n", outputWithoutSummationCardPoint, summationCardPointOutput);
    }

    private static String generateOutputWithoutSummationCardPoint(DealerDTO dealerDTO) {
        String name = "딜러";
        List<Card> cards = dealerDTO.getHoldingCards();
        String nameOutput = name + "카드";
        String cardsOutput = cards.stream()
                .map(CardOutputGenerator::generate)
                .collect(Collectors.joining(", "));
        return "%s: %s".formatted(nameOutput, cardsOutput);
    }

    public static void printWithoutSummationCardPoint(DealerDTO dealerDTO) {
        String outputWithoutSummationCardPoint = generateOutputWithoutSummationCardPoint(dealerDTO);
        Console.println(outputWithoutSummationCardPoint);
    }
}
