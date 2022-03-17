package view;

import domain.GameResult;
import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Player;
import utils.CardConvertor;
import utils.GameResultConvertor;

import java.util.ArrayList;
import java.util.List;

public class OutputView {
    private static final String DEALER_HIT_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String GAME_RESULT_INTRO_MESSAGE = "## 최종 승패";
    private static final String CARD_OR_NAME_DELIMITER = ", ";
    private static final String INIT_CARD_SUFFIX_MESSAGE = "에게 2장을 나누었습니다 *^^*";
    public static final String BLANK = " ";

    private OutputView() {
    }

    public static void printInitCardsResult(final Dealer dealer, final List<Player> players) {
        printNames(dealer, players);
        printCardsWithName(dealer, players);
    }

    private static void printNames(final Dealer dealer, final List<Player> players) {
        final List<String> names = new ArrayList<>();
        names.add(dealer.getName());
        for (final Player player : players) {
            names.add(player.getName());
        }
        print(String.join(CARD_OR_NAME_DELIMITER, names) + INIT_CARD_SUFFIX_MESSAGE);
    }

    public static void printCardsWithName(final Dealer dealer, final List<Player> players) {
        System.out.printf("%s카드: %s%n", dealer.getName(), CardConvertor.convertToString(dealer.getCard()));
        for (final Player player : players) {
            final String cards = String.join(CARD_OR_NAME_DELIMITER, convertToCardsString(player.getCards()));
            System.out.printf("%s카드: %s%n", player.getName(), cards);
        }
    }

    public static void printPlayerCards(final Player player) {
        System.out.printf("%s카드: %s%n", player.getName(), convertToCardsString(player.getCards()));
    }

    private static String convertToCardsString(final List<Card> cards) {
        return String.join(CARD_OR_NAME_DELIMITER, CardConvertor.convertToString(cards));
    }

    public static void print(final String message) {
        System.out.println(message);
    }

    public static void printDealerHit(final boolean hitResult) {
        if (hitResult) {
            print(DEALER_HIT_MESSAGE);
        }
    }

    public static void printTotalScore(final Dealer dealer, final List<Player> players) {
        final String dealerCards = String.join(CARD_OR_NAME_DELIMITER, CardConvertor.convertToString(dealer.getCards()));
        System.out.printf("%s카드: %s - 결과: %d%n", dealer.getName(), dealerCards, dealer.getTotalScore());
        for (final Player player : players) {
            final String playerCards = String.join(CARD_OR_NAME_DELIMITER, CardConvertor.convertToString(player.getCards()));
            System.out.printf("%s카드: %s - 결과: %d%n", player.getName(), playerCards, player.getTotalScore());
        }
    }

    public static void printGameResult(final Dealer dealer, final List<Player> players) {
        print(GAME_RESULT_INTRO_MESSAGE);
        printDealerGameResult(dealer, players);
        for (final Player player : players) {
            final String playerResult = GameResultConvertor.convertToString(player.getGameResult(dealer));
            System.out.printf("%s: %s%n", player.getName(), playerResult);
        }
    }

    private static void printDealerGameResult(final Dealer dealer, final List<Player> players) {
        final List<GameResult> dealerResults = new ArrayList<>();
        for (final Player player : players) {
            dealerResults.add(dealer.getGameResult(player));
        }
        final var dealerResult = GameResultConvertor.convertToCountWithString(dealerResults);
        System.out.printf("%s: %s%n", dealer.getName(), String.join(BLANK, dealerResult));
    }
}
