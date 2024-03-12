package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.participant.*;
import blackjack.domain.result.BlackjackResult;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DELIMITER = ", ";
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String HAND_OUT_MESSAGE = "%s와 %s에게 2장을 나누었습니다.";
    private static final String PARTICIPANT_HAND = "%s카드: %s";
    private static final String DEALER_HIT_COUNT = "딜러는 16이하라 %d장의 카드를 더 받았습니다.";
    private static final String DEALER_NO_HIT = "딜러는 17이상이라 카드를 더 받지 않았습니다.";
    private static final String HAND_WITH_SCORE_FORMAT = "%s - 결과: %d";
    private static final String GAME_RESULT_PREFIX = "## 최종 수익";
    private static final String PROFIT_FORMAT = "%s: %.0f";

    public void printInitialHands(List<InitialHand> initialHands) {
        List<String> names = initialHands.stream()
                .map(InitialHand::getName)
                .toList();
        printHandOutMessage(names);
        initialHands.stream()
                .map(this::getFormattedParticipantHand)
                .forEach(System.out::println);
    }

    private void printHandOutMessage(List<String> names) {
        String firstName = names.get(0);
        String othersName = String.join(DELIMITER, names.subList(1, names.size()));
        String handOutMessage = String.format(HAND_OUT_MESSAGE, firstName, othersName);
        printNewLine();
        System.out.println(handOutMessage);
    }

    private String getFormattedParticipantHand(InitialHand initialHand) {
        String participantName = initialHand.getName();
        String cardSignatures = getCardSignatures(initialHand.getCards());
        return String.format(PARTICIPANT_HAND, participantName, cardSignatures);
    }

    private String getCardSignatures(List<Card> cards) {
        return cards.stream()
                .map(Card::getSignature)
                .collect(Collectors.joining(DELIMITER));
    }

    public void printPlayerHand(Player player) {
        String playerHand = getParticipantHand(player);
        System.out.println(playerHand);
    }

    private String getParticipantHand(Participant participant) {
        String participantName = participant.getName().getValue();
        List<Card> cards = participant.getCards();
        String cardSignatures = getCardSignatures(cards);
        return String.format(PARTICIPANT_HAND, participantName, cardSignatures);
    }

    public void printDealerHitCount(int hitCount) {
        printNewLine();
        if (hitCount == 0) {
            System.out.println(DEALER_NO_HIT);
            return;
        }
        String dealerHitCountMessage = String.format(DEALER_HIT_COUNT, hitCount);
        System.out.println(dealerHitCountMessage);
    }

    public void printParticipantsHandWithScore(Participants participants) {
        printNewLine();
        Dealer dealer = participants.getDealer();
        printParticipantHandWithScore(dealer);
        participants.getPlayers()
                .getValues()
                .forEach(this::printParticipantHandWithScore);
    }

    private void printParticipantHandWithScore(Participant participant) {
        String participantHand = getParticipantHand(participant);
        String participantHandWithScore = String.format(HAND_WITH_SCORE_FORMAT, participantHand,
                participant.getScore());
        System.out.println(participantHandWithScore);
    }

    public void printBlackjackResult(BlackjackResult blackjackResult) {
        printNewLine();
        System.out.println(GAME_RESULT_PREFIX);
        String formattedProfits = blackjackResult.getParticipantProfits()
                .stream()
                .map(participantProfit ->
                        String.format(PROFIT_FORMAT, participantProfit.getParticipantName(), participantProfit.getProfit()))
                .collect(Collectors.joining(System.lineSeparator()));
        System.out.println(formattedProfits);
    }

    public void printException(IllegalArgumentException e) {
        System.out.println(ERROR_PREFIX + e.getMessage());
    }

    private void printNewLine() {
        System.out.println();
    }
}
