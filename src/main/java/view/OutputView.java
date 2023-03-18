package view;

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

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Shape;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import view.message.DrawCardCommandSelector;
import view.message.NumberMessage;
import view.message.PatternMessage;

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

    public void printDealerStartCard(final String dealerName, final Card card) {
        final String dealerCardMessage = START_CARD_MESSAGE.format(dealerName, getCardMessage(card));

        print(dealerCardMessage);
        print(LINE_FEED);
    }

    public void printParticipantCards(final String playerName, final List<Card> playerCards) {
        final String cardsMessage = getCardsMessage(playerCards);
        final String playerCardMessage = CARD_MESSAGE.format(playerName, cardsMessage);

        print(playerCardMessage);
    }

    public void printCardsAndScore(final String targetName, final List<Card> cards, final int score) {
        final String cardsMessage = getCardsMessage(cards);
        final String cardsAndScoreMessage = PARTICIPANT_CARD_RESULT.format(targetName, cardsMessage, score);

        print(cardsAndScoreMessage);
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

    public void printBlank() {
        print(LINE_FEED);
    }

    private String mapToParticipantNameMessage(final List<String> participantNames) {
        final String namesMessage = String.join(", ", participantNames)
                .replace(",", "와");

        return DRAW_MESSAGE.format(namesMessage);
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
