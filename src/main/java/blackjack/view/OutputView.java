package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.participant.*;
import blackjack.domain.result.BlackjackResult;
import blackjack.domain.result.HandResult;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DELIMITER = ", ";
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String HAND_OUT_MESSAGE = "%s와 %s에게 2장을 나누었습니다.";
    private static final String PARTICIPANT_HAND = "%s카드: %s";
    private static final String DEALER_HIT_COUNT = "딜러는 16이하라 %d장의 카드를 더 받았습니다.";
    private static final String DEALER_NO_HIT = "딜러는 17이상이라 카드를 더 받지 않았습니다.";
    private static final String HAND_WITH_SCORE_FORMAT = "%s - 결과: %d";
    private static final String GAME_RESULT_PREFIX = "## 최종 승패";
    private static final String DEALER_RESULTS_FORMAT = "딜러: %s";
    private static final String DEALER_RESULT_FORMAT = "%d%s ";
    private static final String PLAYER_RESULT_FORMAT = "%s: %s";

    public void printInitialHand(Participants participants) {
        Dealer dealer = participants.getDealer();
        Players players = participants.getPlayers();
        printHandOutMessage(dealer, players);
        printParticipantsHand(dealer, players);
    }

    public void printPlayerHand(Player player) {
        String playerHand = getParticipantHand(player);
        System.out.println(playerHand);
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

    public void printBlackjackResult(BlackjackResult blackjackResult) {
        printNewLine();
        System.out.println(GAME_RESULT_PREFIX);
        printDealerResults(blackjackResult.getDealerResults());
        printPlayersResult(blackjackResult.getPlayersResult());
    }

    public void printException(IllegalArgumentException e) {
        System.out.println(ERROR_PREFIX + e.getMessage());
    }

    private void printHandOutMessage(Dealer dealer, Players players) {
        String playersName = formatPlayersName(players);
        String handOutMessage = String.format(HAND_OUT_MESSAGE, dealer.getName(), playersName);
        printNewLine();
        System.out.println(handOutMessage);
    }

    private String formatPlayersName(Players players) {
        return players.getValues()
                .stream()
                .map(Participant::getName)
                .collect(Collectors.joining(DELIMITER));
    }

    private void printParticipantsHand(Dealer dealer, Players players) {
        printParticipantsInitialHand(dealer);
        players.getValues()
                .forEach(this::printParticipantsInitialHand);
    }

    private void printParticipantsInitialHand(Participant participant) {
        List<Card> cards = participant.getInitialOpenedCards();
        String cardSignatures = getCardSignatures(cards);
        String participantName = participant.getName();
        String cardsWithName = String.format(PARTICIPANT_HAND, participantName, cardSignatures);
        System.out.println(cardsWithName);
    }

    private String getCardSignatures(List<Card> cards) {
        return cards.stream()
                .map(Card::getSignature)
                .collect(Collectors.joining(DELIMITER));
    }

    private void printParticipantHandWithScore(Participant participant) {
        String participantHand = getParticipantHand(participant);
        String participantHandWithScore = String.format(HAND_WITH_SCORE_FORMAT, participantHand,
                participant.getScore());
        System.out.println(participantHandWithScore);
    }

    private String getParticipantHand(Participant participant) {
        String participantName = participant.getName();
        List<Card> cards = participant.getCards();
        String cardSignatures = getCardSignatures(cards);
        return String.format(PARTICIPANT_HAND, participantName, cardSignatures);
    }

    private void printDealerResults(Map<HandResult, Integer> dealerResults) {
        String formattedDealerResults = dealerResults.entrySet()
                .stream()
                .map(this::getFormattedDealerResult)
                .collect(Collectors.joining());
        System.out.printf(DEALER_RESULTS_FORMAT, formattedDealerResults);
        printNewLine();
    }

    private String getFormattedDealerResult(Map.Entry<HandResult, Integer> dealerResult) {
        HandResult handResult = dealerResult.getKey();
        int resultCount = dealerResult.getValue();
        return String.format(DEALER_RESULT_FORMAT, resultCount, handResult.getName());
    }

    private void printPlayersResult(Map<Player, HandResult> playersResult) {
        for (Player player : playersResult.keySet()) {
            String playerName = player.getName();
            HandResult playerResult = playersResult.get(player);
            String formattedPlayerResult = String.format(PLAYER_RESULT_FORMAT, playerName, playerResult.getName());
            System.out.println(formattedPlayerResult);
        }
    }

    private void printNewLine() {
        System.out.println();
    }
}
