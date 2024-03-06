package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.dto.DealerDto;
import blackjack.domain.dto.PlayerDto;
import blackjack.domain.dto.PlayersDto;
import java.util.List;
import java.util.StringJoiner;

public class OutputView {

    public static void printInitialState(DealerDto dealerDto, PlayersDto playersDto) {
        System.out.println("딜러" + "와 " + makePlayerName(playersDto) + "에게 2장을 나누었습니다.");
        System.out.println("딜러" + ": " + makeCardsState(dealerDto.getValues()));
        for (PlayerDto playerDto : playersDto.getValues()) {
            System.out.println(playerDto.getName().value() + ": " + makeCardsState(playerDto.getCards()));
        }
        System.out.println();
    }

    private static String makePlayerName(PlayersDto playersDto) {
        final StringJoiner nameJoiner = new StringJoiner(", ");
        for (PlayerDto playerDto : playersDto.getValues()) {
            nameJoiner.add(playerDto.getName().value());
        }
        return nameJoiner.toString();
    }

    private static String makeCardsState(List<Card> cards) {
        final StringJoiner cardJoiner = new StringJoiner(", ");
        for (Card card : cards) {
            cardJoiner.add(card.number().getValue() + SuitTranslator.translate(card.suit()));
        }
        return cardJoiner.toString();
    }
}
