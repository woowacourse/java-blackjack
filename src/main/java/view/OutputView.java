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
    private static final String DELIMITER = ", ";
    private static final String DEALER_NAME = "딜러";
    private static final String DEALER_NO_MORE_CARD_MESSAGE = "딜러의 카드합이 17이상이라 카드를 더 받지 않았습니다.";
    private static final String FINAL_RESULT_MESSAGE = "## 최종 승패";

    public void printInitialCards(Dealer dealer, Players players) {
        String namesFormat = players.getPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.joining(DELIMITER));

        breakLine();
        String format = String.format(Format.CARD_DISTRIBUTION.format, namesFormat);
        System.out.println(format);
        printParticipantsInitialCards(dealer);
        printParticipantsInitialCards(players);
        breakLine();
    }

    private void printParticipantsInitialCards(Participant participant) {
        List<Card> initialCards = participant.getInitialCards();
        String format = String.format(Format.CARDS.format,
                participant.getName(), getCardsFormat(initialCards));
        System.out.println(format);
    }

    private void printParticipantsInitialCards(Players players) {
        for (Player player : players.getPlayers()) {
            printParticipantsInitialCards(player);
        }
    }

    private String getCardsFormat(List<Card> cards) {
        return cards.stream()
                .map(this::getCardFormat)
                .collect(Collectors.joining(DELIMITER));
    }

    private String getCardFormat(Card card) {
        return card.getNumberSignature() + card.getTypeName();
    }

    public void printPlayerCards(Player player) {
        String format = String.format(Format.CARDS.format,
                player.getName(), getCardsFormat(player.getCards()));
        System.out.println(format);
    }

    public void printBusted(String name) {
        String format = String.format(Format.BUSTED.format, name);
        System.out.println(format);
    }

    public void printDealerHitCount(int hitCardCount) {
        if (hitCardCount == 0) {
            System.out.println(DEALER_NO_MORE_CARD_MESSAGE);
            return;
        }

        breakLine();
        String format = String.format(Format.DEALER_MORE_CARDS.format, hitCardCount);
        System.out.println(format);
    }

    public void printCardsWithScore(Dealer dealer, Players players) {
        breakLine();
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
        return String.format(Format.CARDS_WITH_SCORE.format,
                name, getCardsFormat(participant.getCards()), participant.calculateBlackjackScore());
    }

    public void printFinalResult(Dealer dealer) {
        breakLine();
        System.out.println(FINAL_RESULT_MESSAGE);
        System.out.println(getDealerResultFormat(dealer));

        dealer.getPlayerResultMap().forEach(
                (key, value) -> printPlayerResult(key, value.convertToOpposite())
        );
    }

    private String getDealerResultFormat(Dealer dealer) {
        StringBuilder dealerResultsFormat = new StringBuilder();
        for (Result result : Result.values()) {
            dealerResultsFormat.append(getResultFormat(result, dealer.getResultCount(result)));
        }

        return String.format(Format.RESULT.format, DEALER_NAME, dealerResultsFormat);
    }

    private String getResultFormat(Result result, int resultCount) {
        if (resultCount == 0) {
            return "";
        }

        return resultCount + result.getValue();
    }

    private void printPlayerResult(Player player, Result result) {
        String format = String.format(Format.RESULT.format,
                player.getName(), result.getValue());
        System.out.println(format);
    }

    public void printErrorMessage(String message) {
        String format = String.format(Format.ERROR.format, message);
        System.out.println(format);
    }

    private void breakLine() {
        System.out.print(System.lineSeparator());
    }

    public enum Format {
        CARD_DISTRIBUTION("딜러와 %s에게 2장을 나누었습니다."),
        CARDS("%s카드: %s"),
        CARDS_WITH_SCORE(CARDS.format + " - 결과: %d"),
        BUSTED("%s는 버스트 되었습니다."),
        RESULT("%s: %s"),
        DEALER_MORE_CARDS("딜러는 16이하라 %d장의 카드를 더 받았습니다."),
        ERROR("[Error] %s")
        ;

        private final String format;

        Format(String format) {
            this.format = format;
        }
    }
}
