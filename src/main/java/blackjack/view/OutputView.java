package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.dto.DealerDto;
import blackjack.dto.OutcomeDto;
import blackjack.dto.OutcomesDto;
import blackjack.dto.PlayerDto;
import java.util.List;
import java.util.StringJoiner;

public class OutputView {

    private static final String DEALER = "딜러";

    public static void printInitialState(DealerDto dealerDto, List<PlayerDto> playerDtos) {
        System.out.println(System.lineSeparator() + DEALER + "와 " + makePlayerName(playerDtos) + "에게 2장을 나누었습니다.");
        System.out.println(DEALER + "카드: " + makeCardsState(dealerDto.getCards()));
        for (PlayerDto playerDto : playerDtos) {
            System.out.println(playerDto.getName().value() + "카드: " + makeCardsState(playerDto.getCards()));
        }
        System.out.println();
    }

    private static String makePlayerName(List<PlayerDto> playerDtos) {
        final StringJoiner nameJoiner = new StringJoiner(", ");
        for (PlayerDto playerDto : playerDtos) {
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
        System.out.println(playerDto.getName().value() + "카드: " + makeCardsState(playerDto.getCards()));
    }

    public static void printDealerDrawMessage() {
        System.out.println(DEALER + "는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printFinalState(final DealerDto dealerDto, final List<PlayerDto> playerDtos) {
        System.out.println(DEALER + "카드: " + makeCardsState(dealerDto.getCards())
                + " - 결과: " + dealerDto.getScore());
        for (PlayerDto playerDto : playerDtos) {
            System.out.println(playerDto.getName().value() + "카드: " + makeCardsState(playerDto.getCards())
                    + " - 결과: " + playerDto.getScore());
        }
        System.out.println();
    }

    public static void printFinalOutcomes(final OutcomesDto dealerOutcomesDto, final List<OutcomeDto> outcomeDtos) {
        System.out.println("## 최종 승패");
        printDealerFinalOutcome(dealerOutcomesDto);
        printPlayersFinalOutcome(outcomeDtos);
    }

    private static void printDealerFinalOutcome(final OutcomesDto dealerOutcomesDto) {
        System.out.print(
                DEALER + ": " + dealerOutcomesDto.getWinCount() + "승 " + dealerOutcomesDto.getLoseCount() + "패 ");
        if (dealerOutcomesDto.getPushCount() > 0) {
            System.out.print(dealerOutcomesDto.getPushCount() + "무");
        }
        System.out.println();
    }

    private static void printPlayersFinalOutcome(final List<OutcomeDto> outcomeDtos) {
        for (OutcomeDto outcomeDto : outcomeDtos) {
            System.out.println(
                    outcomeDto.getName().value() + ": " + OutcomeTranslator.translate(outcomeDto.getOutcome()));
        }
    }
}
