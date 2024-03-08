package view;

import domain.BlackjackResultDTO;
import domain.Card;
import domain.Denomination;
import domain.Player;
import domain.Symbol;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {
    public static void printPlayersStatus(List<Player> players) {
        List<String> names = players.stream()
                .map(Player::getName)
                .toList();
        System.out.println("딜러와 " + String.join(", ", names) + "에게 2장을 나누었습니다.");

        for (Player player : players) {
            printPlayerStatus(player);
        }
        System.out.println();
    }

    public static void printPlayerStatus(final Player player) {
        StringBuilder stringBuilder = new StringBuilder(player.getName() + ": ");
        List<String> cardInfos = new ArrayList<>();
        for (var card : player.getCards()) {
            String denomination = denominationToMessage(card.getDenomination());
            String symbol = symbolToMessage(card.getSymbol());
            cardInfos.add(denomination + symbol);
        }
        stringBuilder.append(String.join(", ", cardInfos));
        System.out.println(stringBuilder);
    }


    public static void printResults(List<Player> players) {
        System.out.println();
        players.forEach(player -> {
            StringBuilder stringBuilder = new StringBuilder(player.getName() + "카드: ");
            List<String> cardInfos = player.getCards()
                    .stream()
                    .map(card -> denominationToMessage(card.getDenomination()) + symbolToMessage(card.getSymbol()))
                    .toList();
            stringBuilder.append(String.join(", ", cardInfos));
            System.out.print(stringBuilder);
            System.out.println(" - 결과 : " + player.calculateScore());
        });
    }

    public static void printGameResults(BlackjackResultDTO blackjackResult) {
        System.out.println();
        System.out.println("## 최종 승패");
        for (var player : blackjackResult.results().keySet()) {
            Integer win = blackjackResult.getWin(player);
            Integer lose = blackjackResult.getLose(player);
            printWinOrLose(player, win, lose);
        }
    }

    public static void printDealerStatus() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    private static void printWinOrLose(final Player player, final Integer win, final Integer lose) {
        if (player.getName().equals("딜러")) {
            System.out.print(player.getName() + ": " + win + "승" + lose + "패");
            System.out.println();
            return;
        }

        System.out.println(player.getName() + ": " + determineWinOrLose(win));
    }

    private static String determineWinOrLose(final Integer win) {
        if (win > 0) {
            return "승";
        }
        return "패";
    }

    private static String denominationToMessage(Denomination denomination) {
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
        return "" + denomination.getValue(0);
    }

    private static String symbolToMessage(Symbol symbol) {
        if (symbol == Symbol.HEART) {
            return "하트";
        }
        if (symbol == Symbol.CLOVER) {
            return "클로버";
        }
        if (symbol == Symbol.SPADE) {
            return "스페이드";
        }
        return "다이아몬드";
    }
}
