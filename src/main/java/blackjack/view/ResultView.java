package blackjack.view;

import blackjack.model.BlackjackGame;
import blackjack.model.player.Participant;
import java.util.List;
import java.util.StringJoiner;

public class ResultView {
    private static final String DEALER_MESSAGE_FORMAT = "\n%s와 ";
    private static final String GAMERS_MESSAGE_FORMAT = "%s에게 2장의 카드를 나누었습니다.\n";
    private static final String DEALER_AND_CARD_MESSAGE_FORMAT = "%s : %s\n";
    private static final String GAMER_AND_CARDS_MESSAGE_FORMAT = "%s : %s, %s\n";
    private static final String GAMER_HIT_MESSAGE_FORMAT = "\n%s : %s\n";
    private static final String DEALER_ADD_CARD_MESSAGE_FORMAT = "\n%s는 16이하라 %d장의 카드를 더 받았습니다.\n";
    private static final String PLAYER_SCORE_MESSAGE_FORMAT = "\n%s 카드: %s - 결과: %d\n";
    private static final String PLAYER_MATCH_MESSAGE_FORMAT = "\n%s: %s";

    private static final String DEALER_NAME = "딜러";
    private static final String DELIMITER = ", ";
    private static final int START_CARD_COUNT = 2;

    public static void printStartResult(final BlackjackGame blackjackGame) {
        final Participant dealer = blackjackGame.getDealer();
        final List<Participant> players = blackjackGame.getPlayers();
        printNameOf(dealer);
        printNamesOf(players);
        printNameAndFirstCardOf(dealer);
        printNamesAndCardsOf(players);
    }

    private static void printNameOf(final Participant dealer) {
        System.out.printf(DEALER_MESSAGE_FORMAT, dealer.getName());
    }

    private static void printNamesOf(final List<Participant> gamers) {
        final StringJoiner nameJoiner = new StringJoiner(DELIMITER);
        for (Participant gamer : gamers) {
            nameJoiner.add(gamer.getName());
        }
        System.out.printf(GAMERS_MESSAGE_FORMAT, nameJoiner);
    }

    private static void printNameAndFirstCardOf(final Participant dealer) {
        System.out.printf(DEALER_AND_CARD_MESSAGE_FORMAT, dealer.getName(), dealer.getCards().get(0));
    }

    private static void printNamesAndCardsOf(final List<Participant> gamers) {
        for (Participant gamer : gamers) {
            print(gamer.getName(), gamer.getCards());
        }
    }

    private static void print(final String name, final List<String> cards) {
        System.out.printf(GAMER_AND_CARDS_MESSAGE_FORMAT, name, cards.get(0), cards.get(1));
    }

    public static void printCurrentTurnResult(Participant player) {
        if (player.getName().equals(DEALER_NAME)) {
            printDealerHitResult(player.getName(), player.getCards());
            return;
        }
        printGamerHitResult(player.getName(), player.getCards());
    }

    private static void printGamerHitResult(String name, List<String> cards) {
        StringJoiner cardJoiner = new StringJoiner(DELIMITER);
        for (String card : cards) {
            cardJoiner.add(card);
        }
        System.out.printf(GAMER_HIT_MESSAGE_FORMAT, name, cardJoiner);
    }

    private static void printDealerHitResult(String name, List<String> cards) {
        int addedCount = cards.size() - START_CARD_COUNT;
        if (addedCount > 0) {
            System.out.printf(DEALER_ADD_CARD_MESSAGE_FORMAT, name, addedCount);
        }
    }

    public static void printFinalResult(Participant dealer, List<Participant> gamers) {
        printResultOf(dealer);
        for (Participant gamer : gamers) {
            printResultOf(gamer);
        }
    }

    private static void printResultOf(Participant dealer) {
        StringJoiner cardJoiner = new StringJoiner(DELIMITER);
        for (String card : dealer.getCards()) {
            cardJoiner.add(card);
        }
        System.out.printf(PLAYER_SCORE_MESSAGE_FORMAT, dealer.getName(), cardJoiner);
    }
/*
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

 */
}
