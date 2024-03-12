package view.gamer;

import domain.card.Card;
import dto.PlayerDTO;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerOutputView {

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
}
