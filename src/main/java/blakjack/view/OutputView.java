package blakjack.view;

import blakjack.domain.BlackjackGame;
import blakjack.domain.card.Card;
import blakjack.domain.participant.Participant;

import java.util.ArrayList;
import java.util.List;

public class OutputView {
    private static final String DEALER_HIT_MESSAGE = "딜러는 16이하라 한 장의 카드를 더 받았습니다.";
    private static final String DELIMITER = ", ";
    private static final String PROFIT_INTRO_MESSAGE = "## 최종 수익";

    private OutputView() {
    }

    private static String convertFirstCard(final List<Card> dealerCards) {
        final Card firstCard = dealerCards.get(0);
        return firstCard.getFace() + firstCard.getSuit();
    }

    private static String convertCardsToString(final List<Card> cards) {
        final List<String> convertedCards = new ArrayList<>();
        for (final Card card : cards) {
            convertedCards.add(card.getFace() + card.getSuit());
        }
        return String.join(DELIMITER, convertedCards);
    }

    public static void printErrorMessage(final String message) {
        System.out.println(message);
    }

    public static void printDealerHit() {
        System.out.println(DEALER_HIT_MESSAGE);
    }

    public static void printCards(final Participant player) {
        System.out.printf("%s카드: %s%n", player.getName(), convertCardsToString(player.getCards()));
    }

    public static void printScore(final Participant dealer, final List<Participant> players) {
        System.out.printf("%s 카드: %s - 결과: %d%n", dealer.getName(), convertCardsToString(dealer.getCards()), dealer.getTotalScore());
        for (final Participant player : players) {
            System.out.printf("%s 카드: %s - 결과: %d%n", player.getName(), convertCardsToString(player.getCards()), player.getTotalScore());
        }
    }

    public static void printProfit(final Participant dealer, final List<Participant> players) {
        System.out.println(PROFIT_INTRO_MESSAGE);
        System.out.printf("%s: %d%n", dealer.getName(), getDealerProfit(dealer, players));
        for (final Participant player : players) {
            System.out.printf("%s: %d%n", player.getName(), player.getProfit(dealer));
        }
    }

    private static int getDealerProfit(final Participant dealer, final List<Participant> players) {
        int dealerProfit = 0;
        for (final Participant player : players) {
            dealerProfit += dealer.getProfit(player);
        }
        return dealerProfit;
    }

    public static void printInitCards(final BlackjackGame blackjackGame) {
        printNames(blackjackGame);
        printCards(blackjackGame);
    }

    private static void printNames(final BlackjackGame blackjackGame) {
        System.out.printf("%s와 %s에게 2장을 나누었습니다.%n", blackjackGame.getDealerName(), String.join(DELIMITER, blackjackGame.getPlayerNames()));
    }

    private static void printCards(final BlackjackGame blackjackGame) {
        final Participant dealer = blackjackGame.getDealer();
        final List<Participant> players = blackjackGame.getPlayers();

        System.out.printf("%s: %s%n", dealer.getName(), convertFirstCard(dealer.getCards()));
        for (final Participant player : players) {
            System.out.printf("%s: %s%n", player.getName(), convertCardsToString(player.getCards()));
        }
    }

    public static void printScore(final BlackjackGame blackjackGame) {
        printScore(blackjackGame.getDealer(), blackjackGame.getPlayers());
    }

    public static void printProfit(final BlackjackGame blackjackGame) {
        printProfit(blackjackGame.getDealer(), blackjackGame.getPlayers());
    }
}
