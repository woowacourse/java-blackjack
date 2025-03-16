package view;

import card.Card;
import card.CardNumber;
import card.CardType;
import java.util.List;
import java.util.Map;
import participant.Dealer;
import participant.GameResult;
import participant.Player;
import participant.Players;

public class OutputView {

    private static final String CLOVER = "클로버";
    private static final String SPADE = "스페이드";
    private static final String DIAMOND = "다이아몬드";
    private static final String HEART = "하트";
    private static final String WIN = "승";
    private static final String LOSE = "패";
    private static final String DRAW = "무";
    private static final String ACE = "A";
    private static final String JACK = "J";
    private static final String QUEEN = "Q";
    private static final String KING = "K";
    private static final String NICKNAME_SEPARATOR = ", ";

    private static final String ALL_CARDS = "%s: %s";
    private static final String RESULT = " - 결과: %s";

    private OutputView() {
    }

    public static void printInitialCards(Dealer dealer, Players players) {
        printNewLine();
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.%n", String.join(NICKNAME_SEPARATOR, players.getPlayers().stream()
                .map(Player::getNickname)
                .toList()));

        System.out.printf("딜러카드: %s%n", cardToString(dealer.firstRoundCard()));
        for (Player player : players.getPlayers()) {
            printPlayerCards(player);
        }
        printNewLine();
    }

    public static void printPlayerCards(Player player) {
        System.out.printf(ALL_CARDS, player.getNickname(), allCardToString(player.cards()));
        printNewLine();
    }

    public static void printDealerReceivedCard() {
        printNewLine();
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printAllCardAndScore(Players players, Dealer dealer) {
        printNewLine();
        printDealerCardsWithScore(dealer);

        for (Player player : players.getPlayers()) {
            printPlayerCardsWithScore(player);
        }
        printNewLine();
    }

    private static void printDealerCardsWithScore(Dealer dealer) {
        System.out.printf(ALL_CARDS, "딜러", allCardToString(dealer.cards()));
        System.out.printf(RESULT, dealer.score());
        printNewLine();
    }

    private static void printPlayerCardsWithScore(Player player) {
        System.out.printf(ALL_CARDS, player.getNickname(), allCardToString(player.cards()));
        System.out.printf(RESULT, player.score());
        printNewLine();
    }

    private static String allCardToString(List<Card> cards) {
        return String.join(NICKNAME_SEPARATOR, cards.stream()
                .map(OutputView::cardToString)
                .toList());
    }

    public static void printResult(int dealerProfit, Map<Player, Integer> playersResult) {
        println("## 최종 수익");
        System.out.printf("딜러: %d%n", dealerProfit);

        for (Player player : playersResult.keySet()) {
            System.out.printf("%s: %d%n", player.getNickname(), playersResult.get(player));
        }
    }

    private static String gameResultToString(GameResult gameResult) {
        if (gameResult.equals(GameResult.WIN)) {
            return WIN;
        }
        if (gameResult.equals(GameResult.LOSE)) {
            return LOSE;
        }
        return DRAW;
    }

    private static String cardToString(Card card) {
        return cardNumberToString(card) + cardTypeToString(card);
    }

    private static String cardNumberToString(Card card) {
        if (card.getCardNumber() == CardNumber.ACE) {
            return ACE;
        }
        if (card.getCardNumber() == CardNumber.QUEEN) {
            return QUEEN;
        }
        if (card.getCardNumber() == CardNumber.JACK) {
            return JACK;
        }
        if (card.getCardNumber() == CardNumber.KING) {
            return KING;
        }
        return String.valueOf(card.getCardNumber().getValues().get(0));
    }

    private static String cardTypeToString(Card card) {
        if (card.getCardType() == CardType.CLOVER) {
            return CLOVER;
        }
        if (card.getCardType() == CardType.DIAMOND) {
            return DIAMOND;
        }
        if (card.getCardType() == CardType.SPADE) {
            return SPADE;
        }
        return HEART;
    }

    private static void println(String message) {
        System.out.println(message);
    }

    private static void printNewLine() {
        System.out.println();
    }
}
