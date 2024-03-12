package blackjack.view;

import blackjack.domain.Name;
import blackjack.domain.Outcome;
import blackjack.domain.card.Card;
import blackjack.dto.DealerDto;
import blackjack.dto.PlayerDto;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringJoiner;

public class OutputView {

    private static final String DEALER = "딜러";
    public static final long NO_COUNT = 0L;

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
            cardJoiner.add(card.getNumber().getValue() + SuitTranslator.translate(card.getSuit()));
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

    public static void printFinalOutcomes(final Map<Outcome, Long> dealerOutcome, final Map<Name, Outcome> playerOutcomes) {
        System.out.println("## 최종 승패");
        printDealerOutcome(dealerOutcome);
        printPlayersOutcome(playerOutcomes);
    }

    private static void printDealerOutcome(final Map<Outcome, Long> dealerOutcome) {
        System.out.print(DEALER + ": " +
                        dealerOutcome.getOrDefault(Outcome.WIN, NO_COUNT) + "승 " +
                        dealerOutcome.getOrDefault(Outcome.LOSE, NO_COUNT) + "패 ");

        final Long pushCount = dealerOutcome.getOrDefault(Outcome.PUSH, NO_COUNT);
        if (pushCount != NO_COUNT) {
            System.out.print(pushCount + "무");
        }
        System.out.println();
    }

    private static void printPlayersOutcome(final Map<Name, Outcome> playerOutcomes) {
        for (final Entry<Name, Outcome> entry : playerOutcomes.entrySet()) {
            System.out.println(entry.getKey().value() + ": " + OutcomeTranslator.translate(entry.getValue()));
        }
    }

    public static void printLineSeparator(){
        System.out.println();
    }
}
