package view;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardPattern;
import domain.game.Result;
import domain.participant.Participant;
import view.message.GameResultMessage;
import view.message.NumberMessage;
import view.message.PatternMessage;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static view.message.Message.CARD_MESSAGE;
import static view.message.Message.DEALER_GAME_RESULT;
import static view.message.Message.DRAW_MESSAGE;
import static view.message.Message.FINAL_GAME_RESULT;
import static view.message.Message.PARTICIPANT_CARD_RESULT;
import static view.message.Message.PLAYER_GAME_RESULT;

public final class OutputView {

    public static void print(final String message) {
        System.out.println(message);
    }

    public void printParticipantMessage(final List<Participant> participants) {
        final String participantNamesMessage = makeParticipantNamesMessage(participants);
        final String drawMessage = String.format(System.lineSeparator() +
                DRAW_MESSAGE.getMessage(), participantNamesMessage);
        print(drawMessage);
    }

    public void printDealerCard(final String dealerName, final Card dealerFirstCard) {
        final String dealerCardMessage = String.format(CARD_MESSAGE.getMessage(),
                dealerName, getCardMessage(dealerFirstCard));
        print(dealerCardMessage);
    }

    public void printParticipantCard(final String playerName, final List<Card> playerCards) {
        final String cardsMessage = getCardsMessage(playerCards);
        final String participantCardMessage = String.format(CARD_MESSAGE.getMessage(), playerName, cardsMessage);
        print(participantCardMessage);
    }

    public void printCardResult(final String participantName,
                                final List<Card> participantCards,
                                final int participantScore) {
        final String cardsResultMessage = String.format(PARTICIPANT_CARD_RESULT.getMessage(),
                participantName, getCardsMessage(participantCards), participantScore);
        print(cardsResultMessage);
    }

    public void printFinalGameResult(final String dealerName, final Map<String, Result> playerGameResults) {
        print(System.lineSeparator() + FINAL_GAME_RESULT.getMessage());
        printDealerGameResult(dealerName, playerGameResults);
        printPlayerGameResult(playerGameResults);
    }

    private String makeParticipantNamesMessage(final List<Participant> participants) {
        return participants.stream()
                .map(Participant::getName)
                .collect(Collectors.joining(", "))
                .replace(",", "와");
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

        final String dealerGameResultMessage = String.format(DEALER_GAME_RESULT.getMessage(),
                dealerName, dealerWinCount, dealerLoseCount, drawCount);
        print(dealerGameResultMessage);
    }

    private void printPlayerGameResult(final Map<String, Result> playerGameResults) {
        playerGameResults.keySet().forEach(playerName -> {
            final Result playerResult = playerGameResults.get(playerName);
            final String playerGameResultMessage = String.format(PLAYER_GAME_RESULT.getMessage(),
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
