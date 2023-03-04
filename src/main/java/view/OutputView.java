package view;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardPattern;
import domain.participant.Result;
import domain.participant.Participants;
import view.message.GameResultMessage;
import view.message.NumberMessage;
import view.message.PatternMessage;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static view.message.MessageFormatter.CARD_MESSAGE;
import static view.message.MessageFormatter.DEALER_GAME_RESULT;
import static view.message.MessageFormatter.DRAW_MESSAGE;
import static view.message.MessageFormatter.EXCEPTION_MESSAGE;
import static view.message.MessageFormatter.FINAL_GAME_RESULT;
import static view.message.MessageFormatter.PARTICIPANT_CARD_RESULT;
import static view.message.MessageFormatter.PLAYER_GAME_RESULT;

public class OutputView {

    public static void print(final String message) {
        System.out.println(message);
    }

    public void printParticipantMessage(final Participants participants) {
        final List<String> participantNames = participants.getParticipantNames();
        final String participantNameMessage = mapToParticipantNameMessage(participantNames);

        print(participantNameMessage);
    }

    public void printParticipantCards(final String playerName, final List<Card> playerCards) {
        final String cardsMessage = getCardsMessage(playerCards);
        final String playerCardMessage = CARD_MESSAGE.format(playerName, cardsMessage);

        print(playerCardMessage);
    }

    public void printCardResult(final String participantName,
                                final List<Card> participantCards,
                                final int participantScore) {
        final String cardsResultMessage = PARTICIPANT_CARD_RESULT
                .format(participantName, getCardsMessage(participantCards), participantScore);

        print(cardsResultMessage);
    }

    public void printFinalGameResult(final String dealerName, final Map<String, Result> playerGameResults) {
        print(System.lineSeparator() + FINAL_GAME_RESULT.format());
        printDealerGameResult(dealerName, playerGameResults);
        printPlayerGameResult(playerGameResults);
    }

    public void printExceptionMessage(final String message) {
        print(EXCEPTION_MESSAGE.format(message));
    }

    private String mapToParticipantNameMessage(final List<String> participantNames) {
        final String namesMessage = String.join(", ", participantNames).replace(",", "와");

        return System.lineSeparator() + DRAW_MESSAGE.format(namesMessage);
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
        final int dealerWinCount = Collections.frequency(playerGameResults.values(), Result.LOSE);
        final int dealerLoseCount = Collections.frequency(playerGameResults.values(), Result.WIN);
        final int drawCount = Collections.frequency(playerGameResults.values(), Result.DRAW);
        final String dealerGameResultMessage =
                DEALER_GAME_RESULT.format(dealerName, dealerWinCount, dealerLoseCount, drawCount);

        print(dealerGameResultMessage);
    }

    private void printPlayerGameResult(final Map<String, Result> playerGameResults) {
        playerGameResults.keySet().forEach(playerName ->
                print(mapToPlayerGameResultMessage(playerName, playerGameResults.get(playerName))));
    }

    private String mapToPlayerGameResultMessage(final String playerName, final Result playerResult) {
        return PLAYER_GAME_RESULT.format(playerName, GameResultMessage.findMessage(playerResult));
    }
}
