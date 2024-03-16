package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.participant.BetAmount;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.participant.Round;
import blackjack.domain.result.PlayersPots;
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
    private static final String GAME_RESULT_PREFIX = "## 최종 수익";
    private static final String DEALER_RESULTS_FORMAT = "딜러: %,d";
    private static final String PLAYER_RESULT_FORMAT = "%s: %,d";

    public void printInitialHand(Round round) {
        Dealer dealer = round.getDealer();
        Players players = round.getPlayers();
        printHandOutMessage(dealer, players);
        printParticipantsHandWithScore(dealer, players);
    }

    public void printPlayerHand(Player player) {
        String playerHand = getParticipantHand(player);
        System.out.println(playerHand);
    }

    public void printDealerHitCount(int hitCount) {
        System.out.println(System.lineSeparator());
        if (hitCount == 0) {
            System.out.println(DEALER_NO_HIT);
            return;
        }
        String dealerHitCountMessage = String.format(DEALER_HIT_COUNT, hitCount);
        System.out.println(dealerHitCountMessage);
    }

    public void printParticipantsHandWithScore(Round round) {
        System.out.println(System.lineSeparator());
        Dealer dealer = round.getDealer();
        printParticipantHandWithScore(dealer);
        round.getPlayers()
                .getPlayers()
                .forEach(this::printParticipantHandWithScore);
    }

    public void printRoundResult(PlayersPots playersPots) {
        System.out.println(System.lineSeparator() + GAME_RESULT_PREFIX);
        printDealerResults(playersPots);
        printPlayersResult(playersPots);
    }

    public void printException(IllegalArgumentException e) {
        System.out.println(ERROR_PREFIX + e.getMessage());
    }

    private void printHandOutMessage(Dealer dealer, Players players) {
        String playersName = formatPlayersName(players);
        String handOutMessage = String.format(HAND_OUT_MESSAGE, dealer.getName(), playersName);
        System.out.println(System.lineSeparator() + handOutMessage);
    }

    private void printParticipantsHandWithScore(Dealer dealer, Players players) {
        printParticipantsInitialHand(dealer);
        players.getPlayers()
                .forEach(this::printParticipantsInitialHand);
    }

    private String formatPlayersName(Players players) {
        return players.getPlayers()
                .stream()
                .map(Participant::getName)
                .collect(Collectors.joining(DELIMITER));
    }

    private void printParticipantsInitialHand(Participant participant) {
        List<Card> cards = participant.getInitialOpenedCards();
        String cardSignatures = getCardSymbolAndShape(cards);
        String participantName = participant.getName();
        String cardsWithName = String.format(PARTICIPANT_HAND, participantName, cardSignatures);
        System.out.println(cardsWithName);
    }

    private void printParticipantHandWithScore(Participant participant) {
        String participantHand = getParticipantHand(participant);
        String participantHandWithScore = String.format(HAND_WITH_SCORE_FORMAT, participantHand,
                participant.getScore());
        System.out.println(participantHandWithScore);
    }

    private void printDealerResults(PlayersPots playersPots) {
        BetAmount dealerPot = playersPots.calculateDealerBetAmount();
        System.out.printf(DEALER_RESULTS_FORMAT + System.lineSeparator(), dealerPot.amount());
    }

    private void printPlayersResult(PlayersPots playersPots) {
        Map<Player, BetAmount> playersPot = playersPots.getPlayersPots();
        for (Player player : playersPot.keySet()) {
            String playerName = player.getName();
            BetAmount betAmount = playersPot.get(player);
            String formattedPlayerResult = String.format(PLAYER_RESULT_FORMAT, playerName, betAmount.amount());
            System.out.println(formattedPlayerResult);
        }
    }

    private String getParticipantHand(Participant participant) {
        String participantName = participant.getName();
        List<Card> cards = participant.getCards();
        String cardSignatures = getCardSymbolAndShape(cards);
        return String.format(PARTICIPANT_HAND, participantName, cardSignatures);
    }

    private String getCardSymbolAndShape(List<Card> cards) {
        return cards.stream()
                .map(card -> card.getSymbol() + card.getShape())
                .collect(Collectors.joining(DELIMITER));
    }
}
