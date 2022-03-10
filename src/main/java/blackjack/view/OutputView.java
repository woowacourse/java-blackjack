package blackjack.view;

import static java.util.stream.Collectors.joining;

import blackjack.domain.BlackJackGame;
import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Gamer;
import blackjack.domain.Player;
import blackjack.domain.Result;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {

    private static final String PRINT_OPEN_CARD_PREFIX_MESSAGE = "\n%s와 ";
    private static final String PRINT_OPEN_CARD_SUFFIX_MESSAGE = "%s에게 2장의 카드를 나누었습니다.\n";
    private static final String PRINT_JOINING_DELIMITER = ", ";
    private static final String PRINT_DEFAULT_FORMAT_MESSAGE = "%s: %s\n";
    private static final String PRINT_SHOW_CARD_FORMAT_MESSAGE = "%s카드: %s\n";
    private static final String PRINT_DEALER_RECEIVE_CARD = "\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n";
    private static final String PRINT_DEALER_NOT_RECEIVE_CARD = "\n딜러는 17이상이라 한장의 카드를 더 받지 못했습니다.\n";
    private static final String PRINT_FINAL_CARD_RESULT = "%s카드: %s - 결과: %d\n";
    private static final String PRINT_BLANK = " ";

    public static void printOpenCards(final Player dealer, final List<Player> gamers) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format(PRINT_OPEN_CARD_PREFIX_MESSAGE, Dealer.DEALER_NAME))
                .append(String.format(PRINT_OPEN_CARD_SUFFIX_MESSAGE, joinNames(gamers)))
                .append(String.format(PRINT_DEFAULT_FORMAT_MESSAGE, Dealer.DEALER_NAME,
                        joinCards(dealer.openCards())));
        appendGamerFormat(gamers, stringBuilder);

        System.out.println(stringBuilder);
    }

    private static String joinNames(final List<Player> gamers) {
        return gamers.stream()
                .map(Player::showName)
                .collect(joining(PRINT_JOINING_DELIMITER));
    }

    private static String joinCards(final List<Card> cards) {
        return cards.stream()
                .map(OutputView::cardToString)
                .collect(joining(PRINT_JOINING_DELIMITER));
    }

    private static void appendGamerFormat(final List<Player> gamers, final StringBuilder stringBuilder) {
        for (Player gamer : gamers) {
            stringBuilder.append(String.format(PRINT_SHOW_CARD_FORMAT_MESSAGE,
                    gamer.showName(),
                    joinCards(gamer.openCards())));
        }
    }

    private static String cardToString(final Card card) {
        return card.getDenomination().getName() + card.getSuit().getName();
    }

    public static void printGamerCards(final Player gamer) {
        System.out.printf(PRINT_SHOW_CARD_FORMAT_MESSAGE,
                gamer.showName(),
                joinCards(gamer.showCards()));
    }

    public static void printDealerReceive(final boolean receivable) {
        if (receivable) {
            System.out.println(PRINT_DEALER_RECEIVE_CARD);
            return;
        }
        System.out.println(PRINT_DEALER_NOT_RECEIVE_CARD);
    }

    public static void printFinalResult(final Player dealer, final List<Player> gamers) {
        printPlayerCardsResult(dealer);
        gamers.forEach(OutputView::printPlayerCardsResult);
    }

    private static void printPlayerCardsResult(final Player player) {
        System.out.printf(PRINT_FINAL_CARD_RESULT,
                player.showName(),
                joinCards(player.showCards()),
                player.calculateResult());
    }

    public static void printFinalResultBoard(final Map<Player, Result> gamerResultBoard ) {
        System.out.println("\n## 최종 승패");
        System.out.print(dealerToString(gamerResultBoard));
        gamerResultBoard.forEach((key, value) -> System.out.printf(PRINT_DEFAULT_FORMAT_MESSAGE,
                key.showName(),
                value.getResult()));
    }

    private static String dealerToString(final Map<Player, Result> gamerResultBoard) {
        return String.format(PRINT_DEFAULT_FORMAT_MESSAGE, Dealer.DEALER_NAME,
                calculateDealerResultBoard(gamerResultBoard).entrySet().stream()
                        .map(board -> dealerResultToString(board.getKey(),
                                board.getValue()))
                        .collect(joining(PRINT_BLANK)));
    }

    private static Map<Result, Integer> calculateDealerResultBoard(final Map<Player, Result> gamerResultBoard) {
        Map<Result, Integer> enumMap = new EnumMap<>(Result.class);
        for (Entry<Player, Result> gamerResultEntry : gamerResultBoard.entrySet()) {
            Result dealerResult = convertToDealerResult(gamerResultEntry.getValue());
            enumMap.put(dealerResult, enumMap.getOrDefault(dealerResult, 0) + 1);
        }
        return enumMap;
    }

    private static Result convertToDealerResult(final Result result) {
        if (result == Result.WIN) {
            return Result.LOSE;
        }
        if (result == Result.LOSE) {
            return Result.WIN;
        }
        return Result.DRAW;
    }

    private static String dealerResultToString(final Result result, final int value) {
        return value + result.getResult();
    }

    public static void printErrorMessage(final String message) {
        System.out.println(message);
    }
}
