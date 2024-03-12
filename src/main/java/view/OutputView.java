package view;

import domain.Denomination;
import domain.Player;
import domain.Suit;
import dto.GameResult;
import dto.PlayerResult;
import dto.WinLose;

import java.util.ArrayList;
import java.util.List;

public class OutputView {
    public static void printPlayersStatus(final List<Player> players) {
        final List<String> names = players.stream()
                .map(Player::name)
                .toList();
        System.out.println("딜러와 " + String.join(", ", names) + "에게 2장을 나누었습니다.");

        for (final Player player : players) {
            printPlayerStatus(player);
        }
        System.out.println();
    }

    public static void printPlayerStatus(final Player player) {
        final StringBuilder stringBuilder = new StringBuilder(player.name() + ": ");
        final List<String> cardInfos = new ArrayList<>();
        for (final var card : player.hand()) {
            final String denomination = denominationToMessage(card.getDenomination());
            final String symbol = symbolToMessage(card.getSymbol());
            cardInfos.add(denomination + symbol);
        }
        stringBuilder.append(String.join(", ", cardInfos));
        System.out.println(stringBuilder);
    }


    public static void printResults(final List<Player> players) {
        System.out.println();
        players.forEach(player -> {
            final StringBuilder stringBuilder = new StringBuilder(player.name() + "카드: ");
            final List<String> cardInfos = player.hand()
                    .stream()
                    .map(card -> denominationToMessage(card.getDenomination()) + symbolToMessage(card.getSymbol()))
                    .toList();
            stringBuilder.append(String.join(", ", cardInfos));
            System.out.print(stringBuilder);
            System.out.println(" - 결과 : " + player.score());
        });
    }

    public static void printBlackjackResults(final GameResult gameResult) {
        System.out.println();
        System.out.println("## 최종 승패");
        System.out.println(gameResult.dealerResult().name() + " : " + gameResult.dealerResult().winCount() + "승" + gameResult.dealerResult().loseCount() + "패");
        for (final PlayerResult playerResult : gameResult.playerResults()) {
            System.out.println(playerResult.name() + " : " + (playerResult.winLose() == WinLose.WIN ? "승" : "패"));
        }
    }

    public static void printDealerHitMessage() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    private static void printWinOrLose(final Player player, final Integer win, final Integer lose) {
        if (player.name().equals("딜러")) {
            System.out.print(player.name() + ": " + win + "승" + lose + "패");
            System.out.println();
            return;
        }

        System.out.println(player.name() + ": " + determineWinOrLose(win));
    }

    private static String determineWinOrLose(final Integer win) {
        if (win > 0) {
            return "승";
        }
        return "패";
    }

    private static String denominationToMessage(final Denomination denomination) {
        if (denomination == Denomination.ACE) {
            return "A";
        }
        if (denomination == Denomination.JACK) {
            return "J";
        }
        if (denomination == Denomination.QUEEN) {
            return "Q";
        }
        if (denomination == Denomination.KING) {
            return "K";
        }
        return "" + denomination.getValue();
    }

    private static String symbolToMessage(final Suit symbol) {
        if (symbol == Suit.HEART) {
            return "하트";
        }
        if (symbol == Suit.CLUBS) {
            return "클로버";
        }
        if (symbol == Suit.SPADE) {
            return "스페이드";
        }
        return "다이아몬드";
    }
}
