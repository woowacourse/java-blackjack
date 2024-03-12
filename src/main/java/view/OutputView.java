package view;

import domain.blackjack.GameResult;
import domain.card.Card;
import domain.player.Dealer;
import domain.player.Player;
import domain.player.PlayerResult;
import domain.card.Rank;
import domain.player.Participant;
import domain.card.Suit;

import domain.player.Players;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class OutputView {
    private static final String DEALER_NAME = "딜러";

    public static void printInitialStatus(final Dealer dealer, final Players players) {
        final List<String> playerNames = players.stream()
                .map(Player::getName)
                .toList();

        System.out.println(DEALER_NAME + "와 " + String.join(", ", playerNames) + "에게 2장을 나누었습니다.");

        printStatus(dealer);
        players.getValue().forEach(OutputView::printStatus);
        System.out.println();
    }

    public static void printStatus(final Participant participant) {
        final String name = determineName(participant);
        final StringBuilder stringBuilder = new StringBuilder(name + "카드: ");
        final List<String> cardInfos = new ArrayList<>();
        List<Card> hands = participant.getHands();
        if (participant.isDealer()) {
            hands = List.of(hands.get(0));
        }
        for (final var card : hands) {
            final String denomination = denominationToMessage(card.getRank());
            final String symbol = symbolToMessage(card.getSymbol());
            cardInfos.add(denomination + symbol);
        }
        stringBuilder.append(String.join(", ", cardInfos));
        System.out.println(stringBuilder);
    }


    public static void printResults(final Dealer dealer, final Players players) {
        System.out.println();

        printStatusWithResult(dealer);
        players.getValue().forEach(OutputView::printStatusWithResult);
    }

    private static void printStatusWithResult(final Participant participant) {
        final String name = determineName(participant);
        final StringBuilder stringBuilder = new StringBuilder(name + "카드: ");
        final List<String> cardInfos = participant.getHands()
                .stream()
                .map(card -> denominationToMessage(card.getRank()) + symbolToMessage(card.getSymbol()))
                .toList();
        stringBuilder.append(String.join(", ", cardInfos));
        System.out.print(stringBuilder);
        System.out.println(" - 결과 : " + participant.calculateScore());
    }

    private static String determineName(final Participant participant) {
        if (participant.isPlayer()) {
            return participant.getName();
        }
        return DEALER_NAME;
    }

    public static void printWinOrLose(final GameResult blackjackResult) {
        System.out.println();
        System.out.println("## 최종 승패");
        final Set<Entry<Player, PlayerResult>> playerEntries = blackjackResult.playerResult().entrySet();
        printDealerWinOrLose(blackjackResult);
        printPlayerWinOrLose(playerEntries);
    }

    private static void printDealerWinOrLose(final GameResult blackjackResult) {
        System.out.print(DEALER_NAME + ": ");
        final List<Integer> printOrder = List.of(blackjackResult.dealerWin(), blackjackResult.dealerLose(),
                blackjackResult.dealerTie());
        final List<String> suffix = List.of("승", "패", "무");

        for (int order = 0; order < printOrder.size(); order++) {
            printResultWithCounter(printOrder.get(order), suffix.get(order));
        }
        System.out.println();
    }

    private static void printResultWithCounter(final int resultCount, final String suffix) {
        if (resultCount <= 0) {
            return;
        }
        System.out.print(resultCount + " " + suffix + " ");
    }

    private static void printPlayerWinOrLose(final Set<Entry<Player, PlayerResult>> playerEntries) {
        for (final var entry : playerEntries) {
            final Player player = entry.getKey();
            final PlayerResult playerResult = entry.getValue();
            printPlayerResult(player, playerResult);
        }
    }

    public static void printDealerHitMessage() {
        System.out.println();
        System.out.println(DEALER_NAME + "는 16이하라 한장의 카드를 더 받았습니다.");
    }

    private static void printPlayerResult(final Player player, final PlayerResult playerResult) {
        System.out.println(player.getName() + ": " + determineWinOrLose(playerResult));
    }

    private static String determineWinOrLose(final PlayerResult playerResult) {
        if (playerResult == PlayerResult.WIN) {
            return "승";
        }
        if (playerResult == PlayerResult.LOSE) {
            return "패";
        }
        return "무";
    }

    private static String denominationToMessage(final Rank rank) {
        if (rank == Rank.ACE) {
            return "A";
        }
        if (rank == Rank.JACK) {
            return "J";
        }
        if (rank == Rank.QUEEN) {
            return "Q";
        }
        if (rank == Rank.KING) {
            return "K";
        }
        return String.valueOf(rank.getValue());
    }

    private static String symbolToMessage(final Suit suit) {
        if (suit == Suit.HEARTS) {
            return "하트";
        }
        if (suit == Suit.CLUBS) {
            return "클로버";
        }
        if (suit == Suit.SPADES) {
            return "스페이드";
        }
        return "다이아몬드";
    }
}
