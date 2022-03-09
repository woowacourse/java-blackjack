package blackjack.view;

import blackjack.domain.CardDto;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class OutputView {

    public static void printInitCard(Map<String, List<CardDto>> cardStatus) {
        cardStatus.forEach((key, value) -> System.out.println(key + ": " + printCard(value)));
    }

    private static String printCard(List<CardDto> cardDtos) {
        return cardDtos.stream()
                .map(card -> card.getDenomination() + card.getSuit())
                .collect(Collectors.joining(", "));
    }
}
