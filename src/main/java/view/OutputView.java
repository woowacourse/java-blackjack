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

import static view.message.Message.CARD_MESSAGE;
import static view.message.Message.DEALER_GAME_RESULT;
import static view.message.Message.DRAW_MESSAGE;
import static view.message.Message.FINAL_GAME_RESULT;
import static view.message.Message.PARTICIPANT_CARD_RESULT;
import static view.message.Message.PLAYER_GAME_RESULT;

public class OutputView {

    public static void print(final String message) {
        System.out.println(message);
    }

    public void printParticipantMessage(final Participants participants) {
        final List<String> participantNames = participants.getParticipantNames();
        final String participantNamesMessage = String.join(", ", participantNames)
                .replace(",", "와");
        final String participantNameMessage = String.format(System.lineSeparator() + DRAW_MESSAGE.getMessage(),
                participantNamesMessage);
        print(participantNameMessage);
    }

    public void printDealerCard(final String dealerName, final Card dealerFirstCard) {
        final String dealerCardMessage = String.format(CARD_MESSAGE.getMessage(),
                dealerName, getCardMessage(dealerFirstCard));
        print(dealerCardMessage);
    }

    public void printPlayerCard(final String playerName, final List<Card> playerCards) {
        final String cardsMessage = getCardsMessage(playerCards);
        final String playerCardMessage = String.format(CARD_MESSAGE.getMessage(), playerName, cardsMessage);
        print(playerCardMessage);
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
}
