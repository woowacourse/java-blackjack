package blackjack.view;

import blackjack.dto.DealerDto;
import blackjack.dto.GamerDto;
import blackjack.dto.GamersDto;

import blackjack.dto.PlayerDto;
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

    public static void printStartCardsDistributionResult(DealerDto dealer, GamersDto gamers) {
        printDealerName(dealer.getName());
        printGamerNames(gamers.getGamers());
        printDealerNameAndFirstCard(dealer.getName(), dealer.getCards());
        printGamerNamesAndCards(gamers.getGamers());
    }

    private static void printDealerName(String name) {
        System.out.printf(DEALER_MESSAGE_FORMAT, name);
    }

    private static void printGamerNames(List<GamerDto> gamers) {
        StringJoiner nameJoiner = new StringJoiner(", ");
        for (GamerDto gamer : gamers) {
            nameJoiner.add(gamer.getName());
        }
        System.out.printf(GAMERS_MESSAGE_FORMAT, nameJoiner);
    }

    private static void printDealerNameAndFirstCard(String name, List<String> cards) {
        System.out.printf(DEALER_AND_CARD_MESSAGE_FORMAT, name, cards.get(0));
    }

    private static void printGamerNamesAndCards(List<GamerDto> gamers) {
        for (GamerDto gamer : gamers) {
            printGamerNameAndCards(gamer.getName(), gamer.getCards());
        }
    }

    private static void printGamerNameAndCards(String name, List<String> cards) {
        System.out.printf(GAMER_AND_CARDS_MESSAGE_FORMAT, name, cards.get(0), cards.get(1));
    }

    public static void printCurrentTurnHitResult(PlayerDto gamer) {
        if (gamer.getName().equals("딜러")) {
            printDealerHitResult(gamer);
            return;
        }
        printCurrentGamerHitResult(gamer);
    }

    private static void printCurrentGamerHitResult(PlayerDto gamer) {
        StringJoiner cardJoiner = new StringJoiner(", ");
        for (String card : gamer.getCards()) {
            cardJoiner.add(card);
        }
        System.out.printf(GAMER_HIT_MESSAGE_FORMAT, gamer.getName(), cardJoiner);
    }

    private static void printDealerHitResult(PlayerDto dealer) {
        int addCount = dealer.getAddedCardCount();
        if (addCount > 0) {
            System.out.printf(DEALER_HIT_MESSAGE_FORMAT, dealer.getName(), addCount);
        }
    }

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
}
