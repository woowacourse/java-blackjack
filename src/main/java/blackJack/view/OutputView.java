package blackJack.view;

import blackJack.domain.Card.Card;
import blackJack.domain.DealerScore;
import blackJack.domain.PlayerScore;
import blackJack.domain.Result;
import blackJack.domain.User.Dealer;
import blackJack.domain.User.Player;
import blackJack.domain.User.Players;
import blackJack.domain.User.User;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DRAW_MESSAGE = "딜러와 %s에게 2장의 나누었습니다.\n";
    private static final String CARD_FORMAT = "%s카드: %s";
    private static final String DELIMITER = ", ";
    private static final String ADD_DEALER_CARD_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String SCORE_FORMAT = " - 결과: %d";
    private static final String FINAL_RESULT_MESSAGE = "## 최종 승패";
    private static final String NAME_FORMAT = "%s:";
    private static final String DEALER_RESULT_FORMAT = "%d승 %d패 %d무";
    private static final String PLAYER_RESULT_FORMAT = "%s";
    public static final String NEW_LINE = System.getProperty("line.separator");

    public static void printDrawMessage(List<String> userNames) {
        System.out.printf(String.format(DRAW_MESSAGE, userNames.stream().collect(Collectors.joining(DELIMITER))));
    }

    public static void printTotalUserCards(Dealer dealer, Players players) {
        System.out.printf(NEW_LINE);
        System.out.println(String.format(CARD_FORMAT, dealer.getName(), dealer.getCards().get(0).getCardInfo()));
        for (Player player : players.getPlayers()) {
            printPlayerCard(player);
        }
    }

    public static void printPlayerCard(Player player) {
        String cards = player.getCards().stream()
                .map(Card::getCardInfo)
                .collect(Collectors.joining(DELIMITER));
        System.out.println(String.format(CARD_FORMAT, player.getName(), cards));
    }

    public static void printAddDealerCard() {
        System.out.println(ADD_DEALER_CARD_MESSAGE);
    }

    public static void printTotalResult(List<User> users) {
        System.out.printf(NEW_LINE);
        for (User user : users) {
            String cards = user.getCards().stream()
                    .map(Card::getCardInfo)
                    .collect(Collectors.joining(DELIMITER));
            System.out.println(String.format(CARD_FORMAT + SCORE_FORMAT, user.getName(), cards, user.getScore()));
        }
    }

    public static void printFinalResult(String dealerName, DealerScore dealerScore, PlayerScore playerScore) {
        System.out.printf(NEW_LINE);
        System.out.println(FINAL_RESULT_MESSAGE);

        Integer lose = dealerScore.getDealerScore().get(Result.LOSE);
        Integer draw = dealerScore.getDealerScore().get(Result.DRAW);
        Integer win = dealerScore.getDealerScore().get(Result.WIN);
        System.out.println(String.format(NAME_FORMAT + DEALER_RESULT_FORMAT, dealerName, win, lose, draw));
        for (Map.Entry<String, Result> playerResult : playerScore.getPlayersScore().entrySet()) {
            System.out.println(String.format(NAME_FORMAT + PLAYER_RESULT_FORMAT, playerResult.getKey(), playerResult.getValue().getPrintFormat()));
        }
    }
}

