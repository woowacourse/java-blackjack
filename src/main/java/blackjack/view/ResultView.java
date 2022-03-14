package blackjack.view;

import blackjack.model.result.BlackJackGameResult;
import blackjack.model.result.MatchResult;
import blackjack.model.player.Player;
import java.util.List;
import java.util.Map;
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

    public static void printStartResult(final Player dealer, final List<Player> gamers) {
        printNameOf(dealer);
        printNamesOf(gamers);
        printNameAndFirstCardOf(dealer);
        printNamesAndCardsOf(gamers);
    }

    private static void printNameOf(final Player dealer) {
        System.out.printf(DEALER_MESSAGE_FORMAT, dealer.getName());
    }

    private static void printNamesOf(final List<Player> gamers) {
        final StringJoiner nameJoiner = new StringJoiner(", ");
        for (Player gamer : gamers) {
            nameJoiner.add(gamer.getName());
        }
        System.out.printf(GAMERS_MESSAGE_FORMAT, nameJoiner);
    }

    private static void printNameAndFirstCardOf(final Player dealer) {
        System.out.printf(DEALER_AND_CARD_MESSAGE_FORMAT, dealer.getName(), dealer.getCards().get(0));
    }

    private static void printNamesAndCardsOf(final List<Player> gamers) {
        for (Player gamer : gamers) {
            print(gamer.getName(), gamer.getCards());
        }
    }

    private static void print(final String name, final List<String> cards) {
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

    public static void printFinalResult(Player dealer, List<Player> gamers) {
        printResultOf(dealer);
        for (Player gamer : gamers) {
            printResultOf(gamer);
        }
    }

    private static void printResultOf(Player dealer) {
        StringJoiner cardJoiner = new StringJoiner(", ");
        for (String card : dealer.getCards()) {
            cardJoiner.add(card);
        }
        System.out.printf(PLAYER_SCORE_MESSAGE_FORMAT, dealer.getName(), cardJoiner, dealer.sumCardsScore());
    }

    public static void printMatchResult(final BlackJackGameResult blackJackGameResult) {
        printDealerMatchResult(blackJackGameResult.getDealerMatchResult());
        blackJackGameResult.getGamersMatchResult().forEach((name, matchResult) -> {
            System.out.printf(PLAYER_MATCH_MESSAGE_FORMAT, name, matchResult);
        });
    }

    private static void printDealerMatchResult(Map<MatchResult, Integer> dealerMatchResult) {
        StringJoiner matchResultJoiner = new StringJoiner(" ");
        dealerMatchResult.forEach((matchResult, count) -> matchResultJoiner.add(count + matchResult.getValue()));
        System.out.printf(PLAYER_MATCH_MESSAGE_FORMAT, "딜러", matchResultJoiner);
    }
}
