package blackjack.view;

import static java.util.stream.Collectors.joining;

import blackjack.domain.card.Card;
import blackjack.domain.player.Bet;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamer;
import blackjack.domain.player.Player;
import blackjack.domain.result.CompareResult;
import java.util.List;
import java.util.Map;

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

    public static void printOpenCards(final Player dealer, final List<Gamer> gamers) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format(PRINT_OPEN_CARD_PREFIX_MESSAGE, Dealer.DEALER_NAME))
                .append(String.format(PRINT_OPEN_CARD_SUFFIX_MESSAGE, joinNames(gamers)))
                .append(String.format(PRINT_DEFAULT_FORMAT_MESSAGE, Dealer.DEALER_NAME,
                        joinCards(dealer.openCards())));
        appendGamerFormat(gamers, stringBuilder);

        System.out.println(stringBuilder);
    }

    private static String joinNames(final List<Gamer> gamers) {
        return gamers.stream()
                .map(Player::getName)
                .collect(joining(PRINT_JOINING_DELIMITER));
    }

    private static String joinCards(final List<Card> cards) {
        return cards.stream()
                .map(OutputView::cardToString)
                .collect(joining(PRINT_JOINING_DELIMITER));
    }

    private static void appendGamerFormat(final List<Gamer> gamers, final StringBuilder stringBuilder) {
        for (Player gamer : gamers) {
            stringBuilder.append(String.format(PRINT_SHOW_CARD_FORMAT_MESSAGE,
                    gamer.getName(),
                    joinCards(gamer.openCards())));
        }
    }

    private static String cardToString(final Card card) {
        return card.getDenomination().getName() + card.getSuit().getName();
    }

    public static void printGamerCards(final Player gamer) {
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

    public static void printFinalResult(final Player dealer, final List<Gamer> gamers) {
        printPlayerCardsResult(dealer);
        gamers.forEach(OutputView::printPlayerCardsResult);
    }

    private static void printPlayerCardsResult(final Player player) {
        System.out.printf(PRINT_FINAL_CARD_RESULT,
                player.getName(),
                joinCards(player.showCards()),
                player.calculateResult());
    }

    public static void printFinalResultBoard(final int dealerResult,
                                             final Map<Player, Bet> gamerResultBoard) {
        System.out.println("\n## 최종 승패");
        System.out.printf(PRINT_DEFAULT_FORMAT_MESSAGE, Dealer.DEALER_NAME,
                dealerResult);
        gamerResultBoard.forEach((key, value) -> System.out.printf(PRINT_DEFAULT_FORMAT_MESSAGE,
                key.getName(),
                value.getAmount()));
    }

    public static void printErrorMessage(final String message) {
        System.out.println(message);
    }
}
