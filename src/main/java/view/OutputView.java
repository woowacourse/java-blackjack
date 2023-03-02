package view;

import domain.Card;
import domain.Dealer;
import domain.Participant;
import domain.Player;
import domain.Players;
import domain.Result;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DEALER_NAME = "딜러";
    private static final String GAME_INIT_MESSAGE = "딜러와 %s에게 2장을 나누었습니다.";
    private static final String PLAYER_CARDS_FORMAT = "%s카드: %s";
    private static final String SCORE_FORMAT = " - 결과: %d";
    private static final String BUSTED_FORMAT = "%s는 버스트 되었습니다.";
    private static final String FINAL_RESULT = "## 최종 승패";
    private static final String PLAYER_RESULT_FORMAT = "%s: %s";
    private static final String DELIMITER = ", ";
    private static final String DEALER_NO_MORE_CARD = "딜러의 카드합이 17이상이라 카드를 더 받지 않았습니다.";
    private static final String DEALER_MORE_CARDS_FORMAT = "딜러는 16이하라 %d장의 카드를 더 받았습니다.";
    public static final String LINE_SEPARATOR = System.lineSeparator();

    public void printInitCards(Dealer dealer, Players players) {
        String namesFormat = players.getPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.joining(DELIMITER));

        String format = String.format(LINE_SEPARATOR + GAME_INIT_MESSAGE, namesFormat);
        System.out.println(format);
        printInitDealerCards(dealer);
        printInitPlayerCards(players);
        System.out.println();
    }

    private void printInitDealerCards(Dealer dealer) {
        Card dealerFirstCard = dealer.getDealerFirstCard();

        String format = String.format(PLAYER_CARDS_FORMAT, DEALER_NAME, getCardFormat(dealerFirstCard));
        System.out.println(format);
    }

    private void printInitPlayerCards(Players players) {
        for (Player player : players.getPlayers()) {
            String format = String.format(PLAYER_CARDS_FORMAT, player.getName(), getCardsFormat(player.getCards()));
            System.out.println(format);
        }
    }

    private String getCardsFormat(List<Card> cards) {
        return cards.stream()
                .map(this::getCardFormat)
                .collect(Collectors.joining(DELIMITER));
    }

    private String getCardFormat(Card card) {
        return card.getNumberName() + card.getShapeName();
    }

    public void printPlayerCards(Player player) {
        String format = String.format(PLAYER_CARDS_FORMAT, player.getName(), getCardsFormat(player.getCards()));
        System.out.println(format);
    }

    public void printBusted(String name) {
        String format = String.format(BUSTED_FORMAT, name);
        System.out.println(format);
    }

    public void printDealerHitCount(int hitCardCount) {
        if (hitCardCount == 0) {
            System.out.println(DEALER_NO_MORE_CARD);
            return;
        }
        String format = String.format(LINE_SEPARATOR + DEALER_MORE_CARDS_FORMAT, hitCardCount);
        System.out.println(format);
    }

    public void printCardsWithScore(Dealer dealer, Players players) {
        System.out.println();
        printDealerCardsWithScore(dealer);
        printPlayersCardsWithScore(players);
    }

    private void printDealerCardsWithScore(Dealer dealer) {
        System.out.println(getCardsWithScoreFormat(dealer, DEALER_NAME));
    }

    private void printPlayersCardsWithScore(Players players) {
        for (Player player : players.getPlayers()) {
            System.out.println(getCardsWithScoreFormat(player, player.getName()));
        }
    }

    private String getCardsWithScoreFormat(Participant participant, String name) {
        return String.format(PLAYER_CARDS_FORMAT + SCORE_FORMAT, name,
                getCardsFormat(participant.getCards()), participant.calculateScore());
    }

    public void printFinalResult(Dealer dealer) {
        System.out.println(LINE_SEPARATOR + FINAL_RESULT);
        System.out.println(getDealerResultFormat(dealer));

        dealer.getPlayerResultMap().forEach(
                (key, value) -> printPlayerResult(key, value.convertToOpposite())
        );
    }

    private void printPlayerResult(Player player, Result result) {
        String format = String.format(PLAYER_RESULT_FORMAT, player.getName(), result.getValue());
        System.out.println(format);
    }

    private String getDealerResultFormat(Dealer dealer) {
        StringBuilder dealerResultsFormat = new StringBuilder();
        for (Result result : Result.values()) {
            dealerResultsFormat.append(getResultFormat(result, dealer.getResultCount(result)));
        }

        return String.format(PLAYER_RESULT_FORMAT, DEALER_NAME, dealerResultsFormat);
    }

    private String getResultFormat(Result result, int resultCount) {
        if (resultCount == 0) {
            return "";
        }

        return resultCount + result.getValue();
    }
}
