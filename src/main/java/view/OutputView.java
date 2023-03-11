package view;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardPattern;
import domain.participant.Participant;
import domain.participant.ParticipantMoney;
import view.message.NumberMessage;
import view.message.PatternMessage;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class OutputView {

    private static final String BUST_MESSAGE = "카드의 합이 21을 초과했습니다.";
    private static final String BLACKJACK_MESSAGE = "축하드립니다! 블랙잭입니다!";
    private static final String DEALER_DRAW_MESSAGE_FORMAT = "%s는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String DRAW_MESSAGE_FORMAT = "%s와 %s에게 2장을 나누었습니다.";
    private static final String CARD_MESSAGE_FORMAT = "%s: %s";
    private static final String PARTICIPANT_CARD_RESULT_FORMAT = "%s 카드: %s - 결과: %d";
    private static final String FINAL_GAME_RESULT = "## 최종 수익";
    private static final String PLAYER_GAME_RESULT_FORMAT = "%s: %s";

    public static void print(final String message) {
        System.out.println(message);
    }

    public void printHandMessage(final Participant dealer, final List<Participant> players) {
        final String participantNamesMessage = makeParticipantNamesMessage(players);
        final String drawMessage = String.format(System.lineSeparator() +
                DRAW_MESSAGE_FORMAT, dealer.getName(), participantNamesMessage);
        print(drawMessage);
        printCardInfo(dealer, players);
    }

    private void printCardInfo(final Participant dealer, final List<Participant> players) {
        printDealerCard(dealer.getName(), dealer.getFirstCard());
        players.forEach(player -> printParticipantCard(player.getName(), player.getHand()));
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

    public void printFinalGameResult(final Map<String, ParticipantMoney> playerGameResults) {
        print(System.lineSeparator() + FINAL_GAME_RESULT);
        printPlayerGameResult(playerGameResults);
    }

    public void printBustMessage() {
        System.out.println(BUST_MESSAGE);
    }

    public void printBlackJackMessage() {
        System.out.println(BLACKJACK_MESSAGE);
    }

    public void printDealerDrawMessage(final String dealerName) {
        System.out.println(String.format(DEALER_DRAW_MESSAGE_FORMAT, dealerName) + System.lineSeparator());
    }

    private String makeParticipantNamesMessage(final List<Participant> players) {
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


    private void printPlayerGameResult(final Map<String, ParticipantMoney> playerGameResults) {
        playerGameResults.keySet().forEach(playerName -> {
            final ParticipantMoney participantMoney = playerGameResults.get(playerName);
            final String playerGameResultMessage = String.format(PLAYER_GAME_RESULT_FORMAT,
                    playerName, (int) participantMoney.getMoney());
            print(playerGameResultMessage);
        });
    }
}
