package view;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Shape;
import domain.participant.Participant;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import view.message.DrawCardCommandSelector;
import view.message.NumberMessage;
import view.message.PatternMessage;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static view.message.MessageFormatter.CARD_MESSAGE;
import static view.message.MessageFormatter.DEALER_DRAW_MESSAGE;
import static view.message.MessageFormatter.DRAW_CARD_CARD_MESSAGE;
import static view.message.MessageFormatter.DRAW_MESSAGE;
import static view.message.MessageFormatter.EXCEPTION_MESSAGE;
import static view.message.MessageFormatter.FINAL_GAME_RESULT;
import static view.message.MessageFormatter.PARTICIPANT_CARD_RESULT;
import static view.message.MessageFormatter.PARTICIPANT_GAME_RESULT;
import static view.message.MessageFormatter.PARTICIPANT_NAME_INPUT_MESSAGE;
import static view.message.MessageFormatter.PLAYER_BETTING_MESSAGE;
import static view.message.MessageFormatter.START_CARD_MESSAGE;

public final class OutputView {

    private static final String LINE_FEED = System.lineSeparator();
    private static final DecimalFormat benefitFormatter = new DecimalFormat("###,###원");

    public void guideParticipantsName() {
        print(PARTICIPANT_NAME_INPUT_MESSAGE.format());
        print(LINE_FEED);
    }

    public void guideBetAmount(final String playerName) {
        print(LINE_FEED);
        print(PLAYER_BETTING_MESSAGE.format(playerName));
        print(LINE_FEED);
    }

    public void guideDrawCard(final String name) {
        final String drawCardMessage = DRAW_CARD_CARD_MESSAGE.format(name,
            DrawCardCommandSelector.CARD_DRAW_AGAIN.command(), DrawCardCommandSelector.CARD_DRAW_STOP.command());

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

        print(cardsResultMessage);
        print(LINE_FEED);
    }

    public void printFinalGameResult(final String dealerName, final Map<String, BigDecimal> totalPlayerGameResult) {
        printDealerGameResult(dealerName, totalPlayerGameResult);
        print(LINE_FEED);

        final String playerGameResultMessage = mapToPlayerGameResultMessage(totalPlayerGameResult);

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
        final Denomination denomination = participantCard.getCardNumber();
        final Shape shape = participantCard.getCardPattern();
        final String numberMessage = NumberMessage.findMessage(denomination);
        final String patternMessage = PatternMessage.findMessage(shape);

        return numberMessage.concat(patternMessage);
    }

    private void printDealerGameResult(final String dealerName, final Map<String, BigDecimal> playerGameResults) {
        final BigDecimal dealerBenefit = playerGameResults.keySet().stream()
                .map(playerGameResults::get)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .negate();
        final String dealerGameResultMessage =
                PARTICIPANT_GAME_RESULT.format(dealerName, benefitFormatter.format(dealerBenefit));

        print(dealerGameResultMessage);
    }

    private String mapToPlayerGameResultMessage(final Map<String, BigDecimal> playerGameResults) {
        return playerGameResults.keySet().stream()
                .map(playerName -> mapToPlayerGameResultMessage(playerName, playerGameResults.get(playerName)))
                .collect(Collectors.joining(LINE_FEED));
    }

    private String mapToPlayerGameResultMessage(final String playerName, final BigDecimal benefit) {
        return PARTICIPANT_GAME_RESULT.format(playerName, benefitFormatter.format(benefit));
    }

    private void print(final String message) {
        System.out.print(message);
    }
}
