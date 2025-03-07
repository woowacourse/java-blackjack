package view;

import java.util.List;
import java.util.Map;
import model.card.Card;
import model.card.CardNumber;
import model.card.CardType;
import model.participant.Dealer;
import model.GameResult;
import model.participant.Participant;
import model.participant.Player;
import model.participant.Players;

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
        print("딜러와 ");
        print(String.join(NICKNAME_SEPARATOR, players.getPlayers().stream()
                .map(Participant::getNickname)
                .toList())
        );
        println("에게 2장을 나누었습니다.");

        System.out.printf("딜러카드: %s%n", cardToString(dealer.getCard(1)));
        for (Player player : players.getPlayers()) {
            printPlayerCards(player);
        }
        printNewLine();
    }

    public static void printPlayerCards(Participant player) {
        System.out.printf(ALL_CARDS, player.getNickname(), allCardToString(player.getCards()));
        printNewLine();
    }

    public static void printDealerReceivedCard() {
        printNewLine();
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printAllCardAndScore(Players players, Dealer dealer) {
        printNewLine();
        printPlayerCardsWithScore(dealer);

        for (Player player : players.getPlayers()) {
            printPlayerCardsWithScore(player);
        }
        printNewLine();
    }

    private static void printPlayerCardsWithScore(Participant player) {
        System.out.printf(ALL_CARDS, player.getNickname(), allCardToString(player.getCards()));
        System.out.printf(RESULT, player.score());
        printNewLine();
    }

    private static String allCardToString(List<Card> cards) {
        return String.join(NICKNAME_SEPARATOR, cards.stream()
                .map(OutputView::cardToString)
                .toList());
    }

    public static void printResult(Map<GameResult, Integer> dealerResults, Map<Player, GameResult> playerResults) {
        println("## 최종 승패");
        print("딜러:");
        for (GameResult gameResult : GameResult.values()) {
            if (dealerResults.get(gameResult) != 0) {
                System.out.printf(" %d%s", dealerResults.get(gameResult), gameResultToString(gameResult));
            }
        }
        printNewLine();

        for (Player player : playerResults.keySet()) {
            System.out.printf(ALL_CARDS, player.getNickname(), gameResultToString(playerResults.get(player)));
            printNewLine();
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

    private static void print(String message) {
        System.out.print(message);
    }

    private static void println(String message) {
        System.out.println(message);
    }

    private static void printNewLine() {
        System.out.println();
    }
}
