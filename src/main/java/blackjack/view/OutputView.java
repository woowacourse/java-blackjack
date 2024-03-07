package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.dto.DealerDto;
import blackjack.domain.dto.OutcomesDto;
import blackjack.domain.dto.PlayerDto;
import blackjack.domain.dto.PlayersDto;
import java.util.List;
import java.util.StringJoiner;

public class OutputView {

    private static final String DEALER = "딜러";

    public static void printInitialState(DealerDto dealerDto, PlayersDto playersDto) {
        System.out.println(System.lineSeparator() + DEALER + "와 " + makePlayerName(playersDto) + "에게 2장을 나누었습니다.");
        System.out.println(DEALER + ": " + makeCardsState(dealerDto.getCards()));
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

    public static void printCurrentState(final PlayerDto playerDto) {
        System.out.println(playerDto.getName().value() + ": " + makeCardsState(playerDto.getCards()));
    }

    public static void printDealerDrawMessage() {
        System.out.println(DEALER + "는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printFinalState(final DealerDto dealerDto, final PlayersDto playersDto) {
        System.out.println(DEALER + ": " + makeCardsState(dealerDto.getCards())
                + " - 결과: " + dealerDto.getScore());
        for (PlayerDto playerDto : playersDto.getValues()) {
            System.out.println(playerDto.getName().value() + ": " + makeCardsState(playerDto.getCards())
                    + " - 결과: " + playerDto.getScore());
        }
        System.out.println();
    }

    public static void printFinalOutcomes(final OutcomesDto dealerOutcomesDto) {
        System.out.println(System.lineSeparator() + "## 최종 승패");
        System.out.print(
                DEALER + ": " + dealerOutcomesDto.getWinCount() + "승 " + dealerOutcomesDto.getLoseCount() + "패 ");
        if (dealerOutcomesDto.getPushCount() > 0) {
            System.out.print(dealerOutcomesDto.getPushCount() + "무");
        }
        System.out.println();
    }
}
