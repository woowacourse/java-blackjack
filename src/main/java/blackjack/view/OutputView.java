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
    private static final String PRINT_OPEN_CARD_SUFFIX_MESSAGE = "에게 2장의 카드를 나누었습니다.\n";
    private static final String PRINT_JOINING_DELIMITER = ", ";
    private static final String PRINT_DEFAULT_FORMAT_MESSAGE = "%s: %s\n";
    private static final String PRINT_SHOW_CARD_FORMAT_MESSAGE = "%s카드: %s\n";
    private static final String PRINT_DEALER_RECEIVE_CARD = "\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n";
    private static final String PRINT_DEALER_NOT_RECEIVE_CARD = "\n딜러는 17이상이라 한장의 카드를 더 받지 못했습니다.\n";
    private static final String PRINT_FINAL_CARD_RESULT = "%s카드: %s - 결과: %d\n";
    private static final String PRINT_BLANK = " ";

    public static void printOpenCards(final BlackJackGame blackJackGame) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format(PRINT_OPEN_CARD_PREFIX_MESSAGE, Dealer.DEALER_NAME))
                .append(joinNames(blackJackGame))
                .append(PRINT_OPEN_CARD_SUFFIX_MESSAGE);

        List<Card> cards = blackJackGame.getDealer().openCards();
        stringBuilder.append(String.format(PRINT_DEFAULT_FORMAT_MESSAGE, Dealer.DEALER_NAME, joinCards(cards)));
        appendGamerFormat(blackJackGame.getGamers(), stringBuilder);

        System.out.println(stringBuilder);
    }

    private static String joinNames(BlackJackGame blackJackGame) {
        return blackJackGame.getGamers().stream()
                .map(Gamer::getName)
                .collect(joining(PRINT_JOINING_DELIMITER));
    }

    private static String joinCards(final List<Card> cards) {
        return cards.stream()
                .map(OutputView::printCard)
                .collect(joining(PRINT_JOINING_DELIMITER));
    }

    private static void appendGamerFormat(final List<Gamer> gamers, final StringBuilder stringBuilder) {
        for (Gamer gamer : gamers) {
            stringBuilder.append(String.format(PRINT_SHOW_CARD_FORMAT_MESSAGE,
                    gamer.getName(),
                    joinCards(gamer.openCards())));
        }
    }

    private static String printCard(final Card card) {
        return card.getDenomination().getName() + card.getSuit().getName();
    }

    public static void printErrorMessage(final String message) {
        System.out.println(message);
    }

    public static void printGamerCards(Gamer gamer) {
        System.out.printf(PRINT_SHOW_CARD_FORMAT_MESSAGE,
                gamer.getName(),
                joinCards(gamer.showCards()));
    }

    public static void printDealerReceive(final boolean receivable) {
        if (receivable) {
            System.out.println(PRINT_DEALER_RECEIVE_CARD);
            return;
        }
        System.out.println(PRINT_DEALER_NOT_RECEIVE_CARD);
    }

    public static void printFinalResult(BlackJackGame blackJackGame) {
        printDealerCardsResult(blackJackGame.getDealer());
        blackJackGame.getGamers().forEach(OutputView::printGamerCardsResult);
    }

    private static void printDealerCardsResult(Player dealer) {
        System.out.printf(PRINT_FINAL_CARD_RESULT,
                Dealer.DEALER_NAME,
                joinCards(dealer.showCards()),
                dealer.calculateResult());
    }

    private static void printGamerCardsResult(Gamer gamer) {
        System.out.printf(PRINT_FINAL_CARD_RESULT,
                gamer.getName(),
                joinCards(gamer.showCards()),
                gamer.calculateResult());
    }

    public static void printFinalResultBoard(final BlackJackGame blackJackGame) {
        System.out.println("\n## 최종 승패");
        Map<Gamer, Result> gamerResultBoard = blackJackGame.calculateResultBoard();

        System.out.print(dealerToString(gamerResultBoard));
        gamerResultBoard.forEach((key, value) -> System.out.printf(PRINT_DEFAULT_FORMAT_MESSAGE,
                key.getName(),
                value.getResult()));
    }

    private static String dealerToString(Map<Gamer, Result> gamerResultBoard) {
        return String.format(PRINT_DEFAULT_FORMAT_MESSAGE, Dealer.DEALER_NAME,
                calculateDealerResultBoard(gamerResultBoard).entrySet().stream()
                        .map(board -> dealerResultToString(board.getKey(),
                                board.getValue()))
                        .collect(joining(PRINT_BLANK)));
    }

    private static Map<Result, Integer> calculateDealerResultBoard(final Map<Gamer, Result> gamerResultBoard) {
        Map<Result, Integer> enumMap = new EnumMap<>(Result.class);
        for (Entry<Gamer, Result> gamerResultEntry : gamerResultBoard.entrySet()) {
            Result dealerResult = convertToDealerResult(gamerResultEntry.getValue());
            enumMap.put(dealerResult, enumMap.getOrDefault(dealerResult, 0) + 1);
        }
        return enumMap;
    }

    private static Result convertToDealerResult(Result result) {
        if (result == Result.WIN) {
            return Result.LOSE;
        }
        if (result == Result.LOSE) {
            return Result.WIN;
        }
        return Result.DRAW;
    }

    private static String dealerResultToString(Result result, int value) {
        return value + result.getResult();
    }
}
