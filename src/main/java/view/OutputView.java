package view;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardPattern;
import domain.participant.Participant;
import domain.participant.Result;
import view.message.GameResultMessage;
import view.message.NumberMessage;
import view.message.PatternMessage;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static controller.DrawCardCommand.CARD_DRAW_AGAIN;
import static controller.DrawCardCommand.CARD_DRAW_STOP;
import static view.message.MessageFormatter.CARD_MESSAGE;
import static view.message.MessageFormatter.DEALER_DRAW_MESSAGE;
import static view.message.MessageFormatter.DEALER_GAME_RESULT;
import static view.message.MessageFormatter.DRAW_CARD_CARD_MESSAGE;
import static view.message.MessageFormatter.DRAW_MESSAGE;
import static view.message.MessageFormatter.EXCEPTION_MESSAGE;
import static view.message.MessageFormatter.FINAL_GAME_RESULT;
import static view.message.MessageFormatter.PARTICIPANT_CARD_RESULT;
import static view.message.MessageFormatter.PARTICIPANT_NAME_INPUT_MESSAGE;
import static view.message.MessageFormatter.PLAYER_GAME_RESULT;
import static view.message.MessageFormatter.START_CARD_MESSAGE;

public final class OutputView {

    private static final String LINE_FEED = System.lineSeparator();

    public void guideParticipantsName() {
        print(PARTICIPANT_NAME_INPUT_MESSAGE.format());
        print(LINE_FEED);
    }

    public void guideDrawCard(final String name) {
        final String drawCardMessage = DRAW_CARD_CARD_MESSAGE.format(name,
            CARD_DRAW_AGAIN.getCommand(), CARD_DRAW_STOP.getCommand());

        print(drawCardMessage);
        print(LINE_FEED);
    }

    public void guideDealerGivenCard(final String name, final int drawCardCount) {
        int count = 0;

        print(LINE_FEED);
        while (count++ < drawCardCount) {
            print(DEALER_DRAW_MESSAGE.format(name));
            print(LINE_FEED);
        }
        print(LINE_FEED);
    }

    public void guideFinalGameResult() {
        print(LINE_FEED);
        print(FINAL_GAME_RESULT.format());
        print(LINE_FEED);
    }

    public void printGiveParticipantStartCardMessage(final List<String> participantNames) {
        final String participantNameMessage = mapToParticipantNameMessage(participantNames);

        print(LINE_FEED);
        print(participantNameMessage);
        print(LINE_FEED);
    }

    public void printTotalParticipantStartCards(final List<Participant> totalParticipants) {
        final String totalParticipantStartCardsMessage =
            mapToTotalParticipantStartCardsMessage(totalParticipants);

        print(totalParticipantStartCardsMessage);
        print(LINE_FEED);
        print(LINE_FEED);
    }

    public void printParticipantCards(final String playerName, final List<Card> playerCards) {
        final String cardsMessage = getCardsMessage(playerCards);
        final String playerCardMessage = CARD_MESSAGE.format(playerName, cardsMessage);

        print(playerCardMessage);
        print(LINE_FEED);
    }

    public void printCardResult(final List<Participant> totalParticipants) {
        final String cardsResultMessage = totalParticipants.stream()
                .map(participant -> PARTICIPANT_CARD_RESULT
                        .format(participant.getName(), getCardsMessage(participant.getCard()),
                                participant.calculateScore()))
                .collect(Collectors.joining(LINE_FEED));

        print(LINE_FEED);
        print(cardsResultMessage);
        print(LINE_FEED);
    }

    public void printFinalGameResult(final String dealerName, final Map<String, Result> playerGameResults) {
        printDealerGameResult(dealerName, playerGameResults);
        print(LINE_FEED);

        final String playerGameResultMessage = mapToPlayerGameResultMessage(playerGameResults);

        print(playerGameResultMessage);
    }

    public void printExceptionMessage(final String message) {
        print(EXCEPTION_MESSAGE.format(message));
        print(LINE_FEED);
    }

    private String mapToParticipantNameMessage(final List<String> participantNames) {
        final String namesMessage = String.join(", ", participantNames)
                .replace(",", "와");

        return DRAW_MESSAGE.format(namesMessage);
    }

    private String mapToTotalParticipantStartCardsMessage(final List<Participant> players) {
        return players.stream()
                .map(player -> START_CARD_MESSAGE.format(player.getName(),
                        getCardsMessage(player.getStartCard())))
                .collect(Collectors.joining(LINE_FEED));
    }

    private String getCardsMessage(final List<Card> participantCards) {
        return participantCards.stream()
                .map(this::getCardMessage)
                .collect(Collectors.joining(", "));
    }

    private String getCardMessage(final Card participantCard) {
        final CardNumber cardNumber = participantCard.getCardNumber();
        final CardPattern cardPattern = participantCard.getCardPattern();
        final String numberMessage = NumberMessage.findMessage(cardNumber);
        final String patternMessage = PatternMessage.findMessage(cardPattern);

        return numberMessage.concat(patternMessage);
    }

    private void printDealerGameResult(final String dealerName, final Map<String, Result> playerGameResults) {
        final int dealerWinCount = Collections.frequency(playerGameResults.values(), Result.LOSE);
        final int dealerLoseCount = Collections.frequency(playerGameResults.values(), Result.WIN);
        final int drawCount = Collections.frequency(playerGameResults.values(), Result.DRAW);
        final String dealerGameResultMessage =
                DEALER_GAME_RESULT.format(dealerName, dealerWinCount, dealerLoseCount, drawCount);

        print(dealerGameResultMessage);
    }

    private String mapToPlayerGameResultMessage(final Map<String, Result> playerGameResults) {
        return playerGameResults.keySet().stream()
                .map(playerName -> mapToPlayerGameResultMessage(playerName, playerGameResults.get(playerName)))
                .collect(Collectors.joining(LINE_FEED));
    }

    private String mapToPlayerGameResultMessage(final String playerName, final Result playerResult) {
        return PLAYER_GAME_RESULT.format(playerName, GameResultMessage.findMessage(playerResult));
    }

    private void print(final String message) {
        System.out.print(message);
    }
}
