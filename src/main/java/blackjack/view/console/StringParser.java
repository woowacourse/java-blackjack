package blackjack.view.console;

import blackjack.controller.dto.response.HandResponseDto;
import blackjack.domain.card.Card;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class StringParser {

    private static final String COMMA = ",";

    static String parseHandToString(List<Card> hand) {
        return hand.stream()
                .map(Card::toString)
                .collect(Collectors.joining(COMMA));
    }

    static List<String> splitWithComma(String names) {
        return Arrays.asList(names.split(COMMA));
    }

    static String parseNamesToString(List<HandResponseDto> handResponseDtos) {
        return handResponseDtos.stream()
                .map(HandResponseDto::getOwnerName)
                .collect(Collectors.joining(", "));
    }

}
