package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.dto.DealerOutcomeDto;
import blackjack.dto.DealerDto;
import blackjack.dto.PlayerOutcomeDto;
import blackjack.dto.PlayerDto;
import blackjack.dto.PlayersOutcomeDto;
import blackjack.dto.PlayersDto;
import blackjack.view.mapper.OutcomeMapper;
import blackjack.view.mapper.SuitMapper;
import java.util.List;
import java.util.StringJoiner;

public class OutputView {

    private static final String DEALER = "딜러";

    public static void printInitialState(
            final DealerDto dealerResponseDto,
            final PlayersDto playersResponseDto
    ) {
        System.out.println(System.lineSeparator() + DEALER + "와 " + makePlayerName(playersResponseDto) + "에게 2장을 나누었습니다.");
        System.out.println(DEALER + "카드: " + makeCardsState(dealerResponseDto.getCards()));
        for (PlayerDto playerResponseDto : playersResponseDto.getValues()) {
            System.out.println(playerResponseDto.getName().value() + "카드: " + makeCardsState(playerResponseDto.getCards()));
        }
        System.out.println();
    }

    private static String makePlayerName(final PlayersDto playersResponseDto) {
        final StringJoiner nameJoiner = new StringJoiner(", ");
        for (PlayerDto playerResponseDto : playersResponseDto.getValues()) {
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

    public static void printCurrentState(final PlayerDto playerResponseDto) {
        System.out.println(playerResponseDto.getName().value() + "카드: " + makeCardsState(playerResponseDto.getCards()));
    }

    public static void printDealerDrawMessage() {
        System.out.println(System.lineSeparator() + DEALER + "는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printFinalState(final DealerDto dealerResponseDto, final PlayersDto playersResponseDto) {
        System.out.println(System.lineSeparator() + DEALER + "카드: " + makeCardsState(dealerResponseDto.getCards())
                + " - 결과: " + dealerResponseDto.getScore());
        for (PlayerDto playerResponseDto : playersResponseDto.getValues()) {
            System.out.println(playerResponseDto.getName().value() + "카드: " + makeCardsState(playerResponseDto.getCards())
                    + " - 결과: " + playerResponseDto.getScore());
        }
        System.out.println();
    }

    public static void printFinalOutcomes(
            final DealerOutcomeDto dealerDealerOutcomeResponseDto,
            final PlayersOutcomeDto playersOutcomeResponseDto
    ) {
        System.out.println("## 최종 승패");
        printDealerFinalOutcome(dealerDealerOutcomeResponseDto);
        printPlayersFinalOutcome(playersOutcomeResponseDto);
    }

    private static void printDealerFinalOutcome(final DealerOutcomeDto dealerDealerOutcomeResponseDto) {
        System.out.print(
                DEALER + ": " + dealerDealerOutcomeResponseDto.getWinCount() + "승 "
                        + dealerDealerOutcomeResponseDto.getLoseCount() + "패 ");
        if (dealerDealerOutcomeResponseDto.getPushCount() > 0) {
            System.out.print(dealerDealerOutcomeResponseDto.getPushCount() + "무");
        }
        System.out.println();
    }

    private static void printPlayersFinalOutcome(final PlayersOutcomeDto playersOutcomeResponseDto) {
        for (PlayerOutcomeDto playerOutcomeResponseDto : playersOutcomeResponseDto.getValues()) {
            System.out.println(
                playerOutcomeResponseDto.getName().value()
                        + ": " + OutcomeMapper.mapToViewName(playerOutcomeResponseDto.getOutcome()));
        }
    }
}
