package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.GameResult;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String DELIMITER = ", ";
    private static final String NAME_SEPARATOR = ": ";
    private static final String HAND_OUT_CARDS_MESSAGE_FORMAT = "\n%s와 %s에게 두 장의 카드를 나눠줬습니다.\n";
    private static final String PRINT_CARDS_FORMAT = "%s카드: %s\n";
    private static final String DEALER_ONE_MORE_DRAW_MESSAGE = "\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n\n";
    private static final String PRINT_CARDS_SCORE_FORMAT = "%s카드: %s - 결과: %d\n";
    private static final String FINAL_RESULT_MESSAGE = "\n## 최종승패";
    private static final String WIN_MESSAGE = "승 ";
    private static final String DRAW_MESSAGE = "무 ";
    private static final String LOSE_MESSAGE = "패 ";
    private static final int INITIAL_HAND_OUT_CARD_COUNT = 2;

    public static void printHandOutCardsMessage(Dealer dealer, List<Player> players) {
        List<String> playerNames = new ArrayList<>();

        for (Player player : players) {
            playerNames.add(player.getName());
        }
        System.out.printf(HAND_OUT_CARDS_MESSAGE_FORMAT, dealer.getName(),
            String.join(DELIMITER, playerNames));
    }

    public static void printDealerCards(Dealer dealer) {
        Card card = dealer.getFirstCard();
        List<String> results = new ArrayList<>();
        results.add(card.findTypeName() + card.findDenominationName());
        System.out.printf(PRINT_CARDS_FORMAT, dealer.getName(), String.join(DELIMITER, results));
    }

    public static void printPlayerCards(Player player) {
        Cards cards = player.getCards();
        List<String> results = new ArrayList<>();

        for (Card card : cards.getCards()) {
            results.add(card.findTypeName() + card.findDenominationName());
        }
        System.out.printf(PRINT_CARDS_FORMAT, player.getName(), String.join(DELIMITER, results));
    }

    public static void printDealerCardsScore(Dealer dealer) {
        Cards cards = dealer.getCards();
        if (cards.cardsSize() > INITIAL_HAND_OUT_CARD_COUNT) {
            System.out.print(DEALER_ONE_MORE_DRAW_MESSAGE);
        }
        List<String> results = cards.getCards().stream()
            .map(card -> card.findTypeName() + card.findDenominationName())
            .collect(Collectors.toList());

        System.out.printf(PRINT_CARDS_SCORE_FORMAT, dealer.getName(),
            String.join(DELIMITER, results), cards.getScore());
    }

    public static void printPlayerCardsScore(Player player) {
        Cards cards = player.getCards();
        List<String> results = cards.getCards().stream()
            .map(card -> card.findTypeName() + card.findDenominationName())
            .collect(Collectors.toList());

        System.out.printf(PRINT_CARDS_SCORE_FORMAT, player.getName(),
            String.join(DELIMITER, results), cards.getScore());
    }

    public static void printGameResult(List<Player> players, Dealer dealer) {
        System.out.println(FINAL_RESULT_MESSAGE);
        printDealerGameResult(dealer);
        printPlayersGameResults(players);
    }

    private static void printDealerGameResult(Dealer dealer) {
        System.out.print(dealer.getName() + " ");
        if (dealer.getResultCount(GameResult.WIN) > 0) {
            System.out.print(dealer.getResultCount(GameResult.WIN) + WIN_MESSAGE);
        }
        if (dealer.getResultCount(GameResult.DRAW) > 0) {
            System.out.print(dealer.getResultCount(GameResult.DRAW) + DRAW_MESSAGE);
        }
        if (dealer.getResultCount(GameResult.LOSE) > 0) {
            System.out.print(dealer.getResultCount(GameResult.LOSE) + LOSE_MESSAGE);
        }
        System.out.print(NEW_LINE);
    }

    private static void printPlayersGameResults(List<Player> players) {
        for (Player player : players) {
            System.out.println(player.getName() + NAME_SEPARATOR + player.getResult().getName());
        }
    }

    public static void printNewLine() {
        System.out.println(NEW_LINE);
    }
}
