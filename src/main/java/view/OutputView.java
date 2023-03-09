package view;

import domain.PlayerGameResult;
import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.participant.Participant;

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
    private static final String WIN = "승";
    private static final String DRAW = "무";
    private static final String LOSE = "패";
    private static final int DEALER_VISIBLE_CARD = 1;

    private OutputView() {
    }

    public static void printStartMessage(List<String> playersName) {
        System.out.println("\n딜러와 " + String.join(", ", playersName) + "에게 2장을 나누었습니다.");
    }

    public static void printDealerCard(Participant dealer) {
        System.out.println(dealer.getName() + ": " + makeCardView(dealer.getCards().get(DEALER_VISIBLE_CARD)));
    }

    public static void printPlayersCard(List<Participant> players) {
        players.stream()
                .map(OutputView::makePlayerHandView)
                .forEach(System.out::println);
        System.out.println();
    }

    private static String makePlayerHandView(Participant player) {
        return player.getName() + "카드: " + makeCardsView(player.getCards());
    }

    private static String makeCardsView(List<Card> cards) {
        return cards.stream()
                .map(OutputView::makeCardView)
                .collect(Collectors.joining(", "));
    }

    public static void printPlayerCard(Participant player) {
        System.out.println(makePlayerHandView(player));
    }

    private static String makeCardView(Card card) {
        return makeRankView(card.getRank()) + makeSuitView(card.getSuit());
    }

    private static String makeSuitView(Suit suit) {
        if (suit.equals(Suit.DIAMOND)) {
            return DIAMOND;
        }
        if (suit.equals(Suit.HEART)) {
            return HEART;
        }
        if (suit.equals(Suit.SPADE)) {
            return SPADE;
        }

        return CLUB;
    }

    private static String makeRankView(Rank rank) {
        if (rank.equals(Rank.ACE)) {
            return ACE;
        }
        if (rank.equals(Rank.JACK)) {
            return JACK;
        }
        if (rank.equals(Rank.QUEEN)) {
            return QUEEN;
        }
        if (rank.equals(Rank.KING)) {
            return KING;
        }

        return String.valueOf(rank.getScore());
    }

    public static void printDealerHit() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printAllHands(Participant dealer, List<Participant> players) {
        String dealerCards = makeCardsView(dealer.getCards());
        System.out.println("\n" + dealer.getName() + " 카드: " + dealerCards + " - 결과: " + dealer.calculateScore());

        players.stream()
                .map(player -> makePlayerHandView(player) + " - 결과: " + player.calculateScore())
                .forEach(System.out::println);
    }

    public static void printParticipantsResult(Map<String, PlayerGameResult> playersResult) {
        System.out.println("\n## 최종 승패");
        printDealerResult(playersResult);
        printPlayersResult(playersResult);
    }

    private static void printDealerResult(Map<String, PlayerGameResult> playersResult) {
        System.out.print("딜러: ");

        Map<String, Long> dealerResult = playersResult.values().stream()
                .collect(Collectors.groupingBy(OutputView::makeDealerWinningView, Collectors.counting()));

        printDealerResultByWinning(dealerResult, WIN);
        printDealerResultByWinning(dealerResult, DRAW);
        printDealerResultByWinning(dealerResult, LOSE);
    }

    private static void printDealerResultByWinning(Map<String, Long> dealerResult, String winning) {
        if (dealerResult.containsKey(winning)) {
            System.out.print(dealerResult.get(winning) + winning + " ");
        }
    }

    private static void printPlayersResult(Map<String, PlayerGameResult> playersResult) {
        System.out.println();
        playersResult.entrySet().stream()
                .map(player -> player.getKey() + ": " + makePlayerWinningView(player.getValue()))
                .forEach(System.out::println);
    }

    private static String makeDealerWinningView(PlayerGameResult result) {
        if (result.equals(PlayerGameResult.LOSE)) {
            return WIN;
        }
        if (result.equals(PlayerGameResult.DRAW)) {
            return DRAW;
        }
        return LOSE;
    }

    private static String makePlayerWinningView(PlayerGameResult result) {
        if (result.equals(PlayerGameResult.WIN)) {
            return WIN;
        }
        if (result.equals(PlayerGameResult.DRAW)) {
            return DRAW;
        }
        return LOSE;
    }

    public static void printError(Exception e) {
        System.out.println(e.getMessage());
    }
}
