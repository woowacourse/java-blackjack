package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.domain.user.User;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String NEWLINE = System.lineSeparator();
    private static final String STRING_DELIMITER = ", ";
    private static final String INITIAL_CARD_MESSAGE = "딜러와 %s에게 카드를 2장씩 나누었습니다.";
    private static final String DEALER_CARD = "딜러 카드: %s";
    private static final String PLAYER_CARD = "%s 카드: %s";
    private static final String DEALER_RESULT = "딜러: %s";
    private static final String PLAYER_RESULT = "%s: %s";
    private static final String SCORE_RESULT = " - 결과: ";
    private static final String DEALER_DRAW_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String BUST_MESSAGE = "버스트";
    private static final String BLACKJACK_MESSAGE = "블랙잭";

    private OutputView() {
    }

    public static void showInitiate(Dealer dealer, Players players) {
        showPlayerNames(players);
        showCards(dealer, players);
        System.out.println();
    }

    private static void showCards(Dealer dealer, Players players) {
        String dealerCard = dealer.getCards().get(0).getCard();
        System.out.printf(DEALER_CARD + NEWLINE, dealerCard);
        for (Player player : players.getPlayers()) {
            showPlayerCard(player);
        }
    }

    private static void showPlayerNames(Players players) {
        List<String> playerNames = new ArrayList<>();
        for (Player player : players.getPlayers()) {
            playerNames.add(player.getName());
        }
        String nameCollect = String.join(STRING_DELIMITER, playerNames);
        System.out.printf(INITIAL_CARD_MESSAGE + NEWLINE, nameCollect);
    }

    public static void showPlayerCard(Player player) {
        String cards = combineAllCard(player);
        System.out.printf(PLAYER_CARD + NEWLINE, player.getName(), cards);
    }

    private static String combineAllCard(User user) {
        List<String> allCards = new ArrayList<>();
        for (Card card : user.getCards()) {
            allCards.add(card.getCard());
        }
        return String.join(STRING_DELIMITER, allCards);
    }

    public static void showDealerDraw() {
        System.out.println(DEALER_DRAW_MESSAGE);
    }

    public static void showScoreResult(Dealer dealer, Players players) {
        System.out.println();
        showDealerEntireCard(dealer);
        showPlayersEntireCard(players);
        System.out.println();
    }

    private static void showPlayersEntireCard(Players players) {
        for (Player player : players.getPlayers()) {
            String dealerCards =
                String.format(
                    PLAYER_RESULT, player.getName(), combineAllCard(player))
                    + SCORE_RESULT
                    + getScore(player);
            System.out.println(dealerCards);
        }
    }

    private static void showDealerEntireCard(Dealer dealer) {
        String dealerCards =
            String.format(
                DEALER_RESULT, combineAllCard(dealer))
                + SCORE_RESULT
                + getScore(dealer);
        System.out.println(dealerCards);
    }

    private static String getScore(User user) {
        int userScore = user.score();
        if (user.isBlackjack()) {
            return BLACKJACK_MESSAGE;
        }
        if (user.isBust()) {
            return BUST_MESSAGE;
        }
        return Integer.toString(user.score());
    }

    public static void showEarning(Map<Player, Double> playerEarning) {
        for (Map.Entry<Player, Double> player : playerEarning.entrySet()) {
            System.out.printf(
                PLAYER_RESULT + NEWLINE,
                player.getKey().getName(),
                formatEarning(player.getValue())
            );
        }
    }

    public static void showEarning(double money) {
        System.out.printf(DEALER_RESULT + NEWLINE, formatEarning(money));
    }

    private static String formatEarning(double earning) {
        DecimalFormat decimalFormat = new DecimalFormat("#");
        return decimalFormat.format(earning);
    }
}
