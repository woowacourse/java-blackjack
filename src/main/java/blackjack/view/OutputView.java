package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.dto.DealerResponseDto;
import blackjack.dto.OutcomeDto;
import blackjack.dto.OutcomesDto;
import blackjack.dto.PlayerResponseDto;
import blackjack.dto.PlayersResponseDto;
import blackjack.view.mapper.OutcomeMapper;
import blackjack.view.mapper.SuitMapper;
import java.util.List;
import java.util.StringJoiner;

public class OutputView {

    private static final String DEALER = "딜러";

    public static void printInitialState(final DealerResponseDto dealerResponseDto, final PlayersResponseDto playersResponseDto) {
        System.out.println(System.lineSeparator() + DEALER + "와 " + makePlayerName(playersResponseDto) + "에게 2장을 나누었습니다.");
        System.out.println(DEALER + "카드: " + makeCardsState(dealerResponseDto.getCards()));
        for (PlayerResponseDto playerResponseDto : playersResponseDto.getValues()) {
            System.out.println(playerResponseDto.getName().value() + "카드: " + makeCardsState(playerResponseDto.getCards()));
        }
        System.out.println();
    }

    private static String makePlayerName(final PlayersResponseDto playersResponseDto) {
        final StringJoiner nameJoiner = new StringJoiner(", ");
        for (PlayerResponseDto playerResponseDto : playersResponseDto.getValues()) {
            nameJoiner.add(playerResponseDto.getName().value());
        }
        return nameJoiner.toString();
    }

    private static String makeCardsState(final List<Card> cards) {
        final StringJoiner cardJoiner = new StringJoiner(", ");
        for (Card card : cards) {
            cardJoiner.add(card.number().getValue() + SuitMapper.mapToViewName(card.suit()));
        }
        return cardJoiner.toString();
    }

    public static void printCurrentState(final PlayerResponseDto playerResponseDto) {
        System.out.println(playerResponseDto.getName().value() + "카드: " + makeCardsState(playerResponseDto.getCards()));
    }

    public static void printDealerDrawMessage() {
        System.out.println(System.lineSeparator() + DEALER + "는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printFinalState(final DealerResponseDto dealerResponseDto, final PlayersResponseDto playersResponseDto) {
        System.out.println(System.lineSeparator() + DEALER + "카드: " + makeCardsState(dealerResponseDto.getCards())
                + " - 결과: " + dealerResponseDto.getScore());
        for (PlayerResponseDto playerResponseDto : playersResponseDto.getValues()) {
            System.out.println(playerResponseDto.getName().value() + "카드: " + makeCardsState(playerResponseDto.getCards())
                    + " - 결과: " + playerResponseDto.getScore());
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
                    outcomeDto.getName().value() + ": " + OutcomeMapper.mapToViewName(outcomeDto.getOutcome()));
        }
    }
}
