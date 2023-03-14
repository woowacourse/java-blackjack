package view;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import domain.participant.Dealer;
import domain.participant.Player;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String DIAMOND = "다이아몬드";
    private static final String CLUB = "클로버";
    private static final String SPADE = "스페이드";
    private static final String HEART = "하트";
    private static final String ACE = "A";
    private static final String JACK = "J";
    private static final String QUEEN = "Q";
    private static final String KING = "K";
    private static final String ENTER_LINE = System.lineSeparator();

    private OutputView() {
    }

    public static void printStartMessage(List<String> playersName) {
        System.out.println(ENTER_LINE + "딜러와 " + String.join(", ", playersName) + "에게 2장을 나누었습니다.");
    }

    public static void printDealerCard(Dealer dealer) {
        System.out.println(dealer.getName() + ": " + makeCardView(dealer.getCardWithInvisible()));
    }

    public static void printPlayersCard(List<Player> players) {
        players.stream()
                .map(OutputView::makePlayerHandView)
                .forEach(System.out::println);
        System.out.println();
    }

    private static String makePlayerHandView(Player player) {
        return player.getName() + "카드: " + makeCardsView(player.getCards());
    }

    private static String makeCardsView(List<Card> cards) {
        return cards.stream()
                .map(OutputView::makeCardView)
                .collect(Collectors.joining(", "));
    }

    public static void printPlayerCard(Player player) {
        System.out.println(makePlayerHandView(player));
    }

    private static String makeCardView(Card card) {
        return makeDenominationView(card.getDenomination()) + makeSuitView(card.getSuit());
    }

    private static String makeSuitView(Suit suit) {
        if (Suit.DIAMOND.equals(suit)) {
            return DIAMOND;
        }
        if (Suit.HEART.equals(suit)) {
            return HEART;
        }
        if (Suit.SPADE.equals(suit)) {
            return SPADE;
        }

        return CLUB;
    }

    private static String makeDenominationView(Denomination denomination) {
        if (Denomination.ACE.equals(denomination)) {
            return ACE;
        }
        if (Denomination.JACK.equals(denomination)) {
            return JACK;
        }
        if (Denomination.QUEEN.equals(denomination)) {
            return QUEEN;
        }
        if (Denomination.KING.equals(denomination)) {
            return KING;
        }

        return String.valueOf(denomination.getScore());
    }

    public static void printDealerHit() {
        System.out.println(ENTER_LINE + "딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printAllHands(Dealer dealer, List<Player> players) {
        String dealerCards = makeCardsView(dealer.getCards());
        System.out.println(ENTER_LINE + dealer.getName() + " 카드: " + dealerCards + " - 결과: " + dealer.calculateScore());

        players.stream()
                .map(player -> makePlayerHandView(player) + " - 결과: " + player.calculateScore())
                .forEach(System.out::println);
    }

    public static void printBettingResult(Map<String, Integer> playerBettingResult, int dealerBettingResult) {
        System.out.println(ENTER_LINE + "## 최종 수익");
        printDealerResult(dealerBettingResult);
        printPlayersResult(playerBettingResult);
    }

    private static void printDealerResult(int dealerBettingResult) {
        System.out.println("딜러: " + dealerBettingResult);
    }


    private static void printPlayersResult(Map<String, Integer> bettingResult) {
        bettingResult.forEach((playerName, betAmount) -> System.out.println(playerName + ": " + betAmount));
    }

    public static void printError(Exception e) {
        System.out.println(e.getMessage());
    }
}
