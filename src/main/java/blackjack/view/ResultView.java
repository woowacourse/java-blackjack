package blackjack.view;

import blackjack.model.player.Player;
import java.util.List;
import java.util.StringJoiner;

public class ResultView {
    private static final String DEALER_MESSAGE_FORMAT = "\n%s와 ";
    private static final String GAMERS_MESSAGE_FORMAT = "%s에게 2장의 카드를 나누었습니다.\n";
    private static final String DEALER_AND_CARD_MESSAGE_FORMAT = "%s : %s\n";
    private static final String GAMER_AND_CARDS_MESSAGE_FORMAT = "%s : %s, %s\n";
    private static final String GAMER_HIT_MESSAGE_FORMAT = "\n%s : %s\n";
    private static final String DEALER_HIT_MESSAGE_FORMAT = "\n%s는 16이하라 %d장의 카드를 더 받았습니다.\n";
    public static final String PLAYER_SCORE_MESSAGE_FORMAT = "\n%s 카드: %s - 결과: %d\n";
    public static final String PLAYER_MATCH_MESSAGE_FORMAT = "\n%s: %s";

    public static void printStartResult(Player dealer, List<Player> gamers) {
        printNameOf(dealer);
        printNamesOf(gamers);
        printNameAndFirstCardOf(dealer);
        printNamesAndCardsOf(gamers);
    }

    private static void printNameOf(Player dealer) {
        System.out.printf(DEALER_MESSAGE_FORMAT, dealer.getName());
    }

    private static void printNamesOf(List<Player> gamers) {
        StringJoiner nameJoiner = new StringJoiner(", ");
        for (Player gamer : gamers) {
            nameJoiner.add(gamer.getName());
        }
        System.out.printf(GAMERS_MESSAGE_FORMAT, nameJoiner);
    }

    private static void printNameAndFirstCardOf(Player dealer) {
        System.out.printf(DEALER_AND_CARD_MESSAGE_FORMAT, dealer.getName(), dealer.getCards().get(0));
    }

    private static void printNamesAndCardsOf(List<Player> gamers) {
        for (Player gamer : gamers) {
            print(gamer.getName(), gamer.getCards());
        }
    }

    private static void print(String name, List<String> cards) {
        System.out.printf(GAMER_AND_CARDS_MESSAGE_FORMAT, name, cards.get(0), cards.get(1));
    }

    public static void printCurrentTurnHitResult(Player player) {
        if (player.getName().equals("딜러")) {
            printDealerHitResult(player);
            return;
        }
        printGamerHitResult(player);
    }

    private static void printGamerHitResult(Player gamer) {
        StringJoiner cardJoiner = new StringJoiner(", ");
        for (String card : gamer.getCards()) {
            cardJoiner.add(card);
        }
        System.out.printf(GAMER_HIT_MESSAGE_FORMAT, gamer.getName(), cardJoiner);
    }

    private static void printDealerHitResult(Player dealer) {
        int count = dealer.countAddedCards();
        if (count > 0) {
            System.out.printf(DEALER_HIT_MESSAGE_FORMAT, dealer.getName(), count);
        }
    }
/*
    public static void printFinalScores(DealerDto dealer, GamersDto gamers) {
        printDealerScore(dealer);
        printGamerScores(gamers);
    }

    private static void printDealerScore(DealerDto dealer) {
        StringJoiner cardJoiner = new StringJoiner(", ");
        for (String card : dealer.getCards()) {
            cardJoiner.add(card);
        }
        System.out.printf(PLAYER_SCORE_MESSAGE_FORMAT, dealer.getName(), cardJoiner, dealer.getScore());
    }

    private static void printGamerScores(GamersDto gamers) {
        for (GamerDto gamer : gamers.getGamers()) {
            printGamerScore(gamer);
        }
    }

    private static void printGamerScore(GamerDto gamer) {
        StringJoiner cardJoiner = new StringJoiner(", ");
        for (String card : gamer.getCards()) {
            cardJoiner.add(card);
        }
        System.out.printf(PLAYER_SCORE_MESSAGE_FORMAT, gamer.getName(), cardJoiner, gamer.getScore());
    }

    public static void printMatchResult(DealerMatchResultsDto dealerMatchResults,
                                        GamerMatchResultsDto gamerMatchResults) {
        printDealerMatchResult(dealerMatchResults);
        gamerMatchResults.getGamerMatchResults().forEach((name, result) -> {
            System.out.printf(PLAYER_MATCH_MESSAGE_FORMAT, name, result);
        });
    }

    private static void printDealerMatchResult(DealerMatchResultsDto dealerMatchResults) {
        StringJoiner matchResultJoiner = new StringJoiner(" ");
        dealerMatchResults.getDealerMatchResults()
                .forEach((result, count) -> matchResultJoiner.add(count + result));
        System.out.printf(PLAYER_MATCH_MESSAGE_FORMAT, dealerMatchResults.getName(), matchResultJoiner);
    }
*/
}
