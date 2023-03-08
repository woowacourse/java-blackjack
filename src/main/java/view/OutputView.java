package view;

import domain.blackjack.Result;
import domain.card.Card;
import domain.player.Dealer;
import domain.player.Participant;
import domain.player.Player;
import domain.player.Players;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DELIMITER = ", ";
    private static final String DEALER_NO_MORE_CARD_MESSAGE = "딜러의 카드합이 17이상이라 카드를 더 받지 않았습니다.";
    private static final String FINAL_RESULT_MESSAGE = "## 최종 승패";
    private static final String FINAL_PROFIT_MESSAGE = "## 최종 수익";
    private static final String NEW_LINE = System.lineSeparator();

    public void printInitialCards(Dealer dealer, Players players) {
        String namesFormat = players.getPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.joining(DELIMITER));

        String format = String.format(Format.CARD_DISTRIBUTION.format, namesFormat);
        System.out.println(NEW_LINE + format);
        printInitialDealerCards(dealer);
        printInitialPlayerCards(players);
    }

    private void printInitialDealerCards(Dealer dealer) {
        Card dealerFirstCard = dealer.getFirstCard();

        String message = String.format(Format.CARDS.format,
                dealer.getName(), getCardFormat(dealerFirstCard));
        System.out.println(message);
    }

    private void printInitialPlayerCards(Players players) {
        for (Player player : players.getPlayers()) {
            String message = String.format(Format.CARDS.format,
                    player.getName(), getCardsFormat(player.getCards()));
            System.out.println(message + NEW_LINE);
        }
    }

    private String getCardsFormat(List<Card> cards) {
        return cards.stream()
                .map(this::getCardFormat)
                .collect(Collectors.joining(DELIMITER));
    }

    private String getCardFormat(Card card) {
        return card.getCardRankName() + card.getCardShapeName();
    }

    public void printPlayerCards(Player player) {
        String message = String.format(Format.CARDS.format,
                player.getName(), getCardsFormat(player.getCards()));
        System.out.println(message);
    }

    public void printBusted(String name) {
        String message = String.format(Format.BUSTED.format, name);
        System.out.println(message);
    }

    public void printDealerHitCount(int hitCardCount) {
        if (hitCardCount == 0) {
            System.out.println(NEW_LINE + DEALER_NO_MORE_CARD_MESSAGE + NEW_LINE);
            return;
        }

        String message = String.format(Format.DEALER_MORE_CARDS.format, hitCardCount);
        System.out.println(NEW_LINE + message + NEW_LINE);
    }

    public void printCardsWithScore(Dealer dealer, Players players) {
        printDealerCardsWithScore(dealer);
        printPlayersCardsWithScore(players);
    }

    private void printDealerCardsWithScore(Dealer dealer) {
        System.out.println(getCardsWithScoreFormat(dealer, dealer.getName()));
    }

    private void printPlayersCardsWithScore(Players players) {
        for (Player player : players.getPlayers()) {
            System.out.println(getCardsWithScoreFormat(player, player.getName()));
        }
    }

    private String getCardsWithScoreFormat(Participant participant, String name) {
        return String.format(Format.CARDS_WITH_SCORE.format,
                name, getCardsFormat(participant.getCards()), participant.calculateScore());
    }

    public void printFinalResult(Dealer dealer) {
        System.out.println(NEW_LINE + FINAL_RESULT_MESSAGE);
        System.out.println(getDealerResultFormat(dealer));

        dealer.getGameResult().forEach(
                (key, value) -> printPlayerResult(key, value.convertToOpposite())
        );

        printFinalProfit(dealer);
    }

    private String getDealerResultFormat(Dealer dealer) {
        StringBuilder dealerResultsFormat = new StringBuilder();
        for (Result result : Result.values()) {
            dealerResultsFormat.append(getResultFormat(result, dealer.getResultCount(result)));
        }

        return String.format(Format.RESULT.format, dealer.getName(), dealerResultsFormat);
    }

    private String getResultFormat(Result result, int resultCount) {
        if (resultCount == 0) {
            return "";
        }

        return resultCount + result.getValue();
    }

    private void printPlayerResult(Player player, Result result) {
        String message = String.format(Format.RESULT.format,
                player.getName(), result.getValue());
        System.out.println(message);
    }

    private void printFinalProfit(Dealer dealer) {
        System.out.println(NEW_LINE + FINAL_PROFIT_MESSAGE);

        printDealerProfit(dealer);
        dealer.getGameResult()
                .forEach((player, result) -> printPlayerProfit(player, result.convertToOpposite()));
    }

    private void printDealerProfit(Dealer dealer) {
        String message = String.format(Format.RESULT.format, dealer.getName(), dealer.getProfit());
        System.out.println(message);
    }

    private void printPlayerProfit(Player player, Result result) {
        String message = String.format(Format.RESULT.format, player.getName(), player.getProfit(result));
        System.out.println(message);
    }

    public void printErrorMessage(String errorMessage) {
        String message = String.format(Format.ERROR.format, errorMessage);
        System.out.println(message);
    }

    public enum Format {
        CARD_DISTRIBUTION("딜러와 %s에게 2장을 나누었습니다."),
        CARDS("%s카드: %s"),
        CARDS_WITH_SCORE(CARDS.format + " - 결과: %d"),
        BUSTED("%s는 버스트 되었습니다."),
        RESULT("%s: %s"),
        DEALER_MORE_CARDS("딜러는 16이하라 %d장의 카드를 더 받았습니다."),
        ERROR("[Error] %s");

        private final String format;

        Format(String format) {
            this.format = format;
        }
    }
}
