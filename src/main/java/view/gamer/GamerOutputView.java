package view.gamer;

import domain.card.Card;
import dto.DealerDTO;
import dto.PlayerDTO;
import java.util.List;
import java.util.stream.Collectors;

public class GamerOutputView {

    public static void print(PlayerDTO playerDTO) {
        String outputWithoutSummationCardPoint = generateOutputWithoutSummationCardPoint(playerDTO);
        String summationCardPointOutput = "결과: %d".formatted(playerDTO.getSummationCardPoint());
        System.out.printf("%s - %s%n", outputWithoutSummationCardPoint, summationCardPointOutput);
    }

    private static String generateOutputWithoutSummationCardPoint(PlayerDTO playerDTO) {
        String name = playerDTO.getName();
        List<Card> cards = playerDTO.getHoldingCards();
        String nameOutput = name + "카드";
        String cardsOutput = cards.stream()
                .map(CardOutputGenerator::generate)
                .collect(Collectors.joining(", "));
        return "%s: %s".formatted(nameOutput, cardsOutput);
    }

    public static void printWithoutSummationCardPoint(PlayerDTO playerDTO) {
        String outputWithoutSummationCardPoint = generateOutputWithoutSummationCardPoint(playerDTO);
        System.out.println(outputWithoutSummationCardPoint);
    }

    public static void print(DealerDTO dealerDTO) {
        String outputWithoutSummationCardPoint = generateOutputWithoutSummationCardPoint(dealerDTO);
        String summationCardPointOutput = "결과: %d".formatted(dealerDTO.getSummationCardPoint());
        System.out.printf("%s - %s%n", outputWithoutSummationCardPoint, summationCardPointOutput);
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
        System.out.println(outputWithoutSummationCardPoint);
    }
}
