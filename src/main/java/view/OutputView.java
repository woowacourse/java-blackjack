package view;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardPattern;
import domain.game.Result;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import view.message.GameResultMessage;
import view.message.NumberMessage;
import view.message.PatternMessage;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class OutputView {

    private static final String BUST_MESSAGE = "카드의 합이 21을 초과했습니다.";
    private static final String BLACKJACK_MESSAGE = "축하드립니다! 블랙잭입니다!";
    private static final String DEALER_DRAW_MESSAGE_FORMAT = "%s는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String DRAW_MESSAGE_FORMAT = "%s와 %s에게 2장을 나누었습니다.";
    private static final String CARD_MESSAGE_FORMAT = "%s: %s";
    private static final String PARTICIPANT_CARD_RESULT_FORMAT = "%s 카드: %s - 결과: %d";
    private static final String FINAL_GAME_RESULT = "## 최종 승패";
    private static final String DEALER_GAME_RESULT_FORMAT = "%s: %d승 %d패 %d무";
    private static final String PLAYER_GAME_RESULT_FORMAT = "%s: %s";

    public static void print(final String message) {
        System.out.println(message);
    }

    public void printParticipantMessage(final Dealer dealer, final List<Player> players) {
        final String participantNamesMessage = makeParticipantNamesMessage(players);
        final String drawMessage = String.format(System.lineSeparator() +
                DRAW_MESSAGE_FORMAT, dealer.getName(), participantNamesMessage);
        print(drawMessage);
    }

    public void printDealerCard(final String dealerName, final Card dealerFirstCard) {
        final String dealerCardMessage = String.format(CARD_MESSAGE_FORMAT,
                dealerName, getCardMessage(dealerFirstCard));
        print(dealerCardMessage);
    }

    public void printParticipantCard(final String playerName, final List<Card> playerCards) {
        final String cardsMessage = getCardsMessage(playerCards);
        final String participantCardMessage = String.format(CARD_MESSAGE_FORMAT, playerName, cardsMessage);
        print(participantCardMessage);
    }

    public void printCardResult(final String participantName,
                                final List<Card> participantCards,
                                final int participantScore) {
        final String cardsResultMessage = String.format(PARTICIPANT_CARD_RESULT_FORMAT,
                participantName, getCardsMessage(participantCards), participantScore);
        print(cardsResultMessage);
    }

    public void printFinalGameResult(final String dealerName, final Map<String, Result> playerGameResults) {
        print(System.lineSeparator() + FINAL_GAME_RESULT);
        printDealerGameResult(dealerName, playerGameResults);
        printPlayerGameResult(playerGameResults);
    }

    public void printBustMessage() {
        System.out.println(BUST_MESSAGE);
    }

    public void printBlackJackMessage() {
        System.out.println(BLACKJACK_MESSAGE);
    }

    public void printDrawMessage(final String dealerName) {
        System.out.println(String.format(DEALER_DRAW_MESSAGE_FORMAT, dealerName) + System.lineSeparator());
    }

    private String makeParticipantNamesMessage(final List<Player> players) {
        return players.stream()
                .map(Participant::getName)
                .collect(Collectors.joining(", "));
    }

    private String getCardMessage(final Card participantCard) {
        final CardNumber cardNumber = participantCard.getCardNumber();
        final CardPattern cardPattern = participantCard.getCardPattern();

        final String numberMessage = NumberMessage.findMessage(cardNumber);
        final String patternMessage = PatternMessage.findMessage(cardPattern);

        return numberMessage + patternMessage;
    }

    private String getCardsMessage(final List<Card> participantCards) {
        return participantCards.stream().map(this::getCardMessage)
                .collect(Collectors.joining(", "));
    }

    private void printDealerGameResult(final String dealerName, final Map<String, Result> playerGameResults) {
        final Map<Result, Long> resultCounts = getResultCounts(playerGameResults);
        final int dealerWinCount = getDealerResultCountByType(resultCounts, Result.LOSE);
        final int dealerLoseCount = getDealerResultCountByType(resultCounts, Result.WIN);
        final int drawCount = getDealerResultCountByType(resultCounts, Result.DRAW);

        final String dealerGameResultMessage = String.format(DEALER_GAME_RESULT_FORMAT,
                dealerName, dealerWinCount, dealerLoseCount, drawCount);
        print(dealerGameResultMessage);
    }

    private void printPlayerGameResult(final Map<String, Result> playerGameResults) {
        playerGameResults.keySet().forEach(playerName -> {
            final Result playerResult = playerGameResults.get(playerName);
            final String playerGameResultMessage = String.format(PLAYER_GAME_RESULT_FORMAT,
                    playerName, GameResultMessage.findMessage(playerResult));
            print(playerGameResultMessage);
        });
    }

    private Map<Result, Long> getResultCounts(final Map<String, Result> playerGameResults) {
        return playerGameResults.values().stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private int getDealerResultCountByType(final Map<Result, Long> resultCounts, final Result resultType) {
        return resultCounts.getOrDefault(resultType, 0L).intValue();
    }
}
