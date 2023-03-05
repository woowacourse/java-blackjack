package domain.game;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.card.Deck;
import domain.card.RandomShuffleStrategy;
import domain.people.Dealer;
import domain.people.Participant;
import domain.people.Participants;
import domain.people.Player;

public class BlackJackGame {
    private static final int DEALER_MINIMUM_VALUE = 17;
    private static final int INITIAL_CARD_COUNT = 2;
    private static final int BUST_BOUNDARY_VALUE = 21;
    private static final int BUST_HAND_VALUE = 0;
    private static final String BUST = "버스트";
    private static final String HIT_REQUEST = "y";
    private static final String DEALER_NAME = new Dealer().getName();

    private final Deck deck;
    private final Participants participants;
    private boolean isOngoing;
    private int currentPlayerIndex;

    private BlackJackGame(List<String> names) {
        this.deck = createDeck();
        this.participants = createParticipants(names);
        this.isOngoing = true;
        this.currentPlayerIndex = 0;
    }

    public static BlackJackGame from(List<String> names) {
        return new BlackJackGame(names);
    }

    public Deck createDeck() {
        return Deck.from(new RandomShuffleStrategy());
    }

    public Participants createParticipants(List<String> names) {
        return Participants.from(names);
    }

    public void dealCardsToParticipants() {
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            Dealer dealer = participants.findDealer();
            dealer.deal(deck, participants.getParticipants());
        }
    }

    public List<String> getPlayerNames() {
        return getPlayers().stream()
            .map(Player::getName)
            .collect(Collectors.toList());
    }

    private List<Player> getPlayers() {
        return participants.findPlayers();
    }

    public Map<String, List<String>> getParticipantsInitHands() {
        Map<String, List<String>> participantsHands = getParticipantsHands();
        List<String> dealerHand = participantsHands.get(DEALER_NAME);
        List<String> dealerFirstCard = dealerHand.subList(0, 1);
        participantsHands.replace(DEALER_NAME, dealerFirstCard);
        return participantsHands;
    }

    public Map<String, List<String>> getParticipantsHands() {
        Map<String, List<String>> participantsHands = new LinkedHashMap<>();
        getDealerHand(participantsHands);
        getPlayersHand(participantsHands);
        return participantsHands;
    }

    private void getDealerHand(Map<String, List<String>> participantsHands) {
        List<String> dealerHand = participants.findDealer().getCardNames();
        participantsHands.put(DEALER_NAME, dealerHand);
    }

    private void getPlayersHand(Map<String, List<String>> participantsHands) {
        for (Participant participant : getPlayers()) {
            String playerName = participant.getName();
            List<String> playerHand = participant.getCardNames();
            participantsHands.put(playerName, playerHand);
        }
    }

    public boolean isOngoing() {
        return isOngoing;
    }

    public String getCurrentPlayer() {
        return getPlayers().get(currentPlayerIndex).getName();
    }

    public void hitOrStay(String hitRequest) {
        if (isHit(hitRequest)) {
            getPlayers().get(currentPlayerIndex).receiveCard(deck.draw());
            return;
        }

        currentPlayerIndex++;
        if (currentPlayerIndex == getPlayers().size()) {
            isOngoing = false;
        }
    }

    private boolean isHit(String drawingInput) {
        return drawingInput.equals(HIT_REQUEST);
    }

    public boolean isDealerHandUnderMinimumValue() {
        int dealerHandValue = participants.findDealer().getHandValue();
        return dealerHandValue < DEALER_MINIMUM_VALUE;
    }

    public void dealerHit() {
        participants.findDealer().receiveCard(deck.draw());
    }

    public Map<Participant, String> getParticipantScores() {
        Map<Participant, String> scores = new LinkedHashMap<>();
        for (Participant participant : participants.getParticipants()) {
            judgeBust(scores, participant);
        }

        return scores;
    }

    private void judgeBust(Map<Participant, String> scores, Participant participant) {
        int handValue = participant.getHandValue();
        if (handValue > BUST_BOUNDARY_VALUE) {
            scores.put(participant, BUST);
            return;
        }
        scores.put(participant, String.valueOf(handValue));
    }

    public Map<String, Result> calculatePlayerResults() {
        Dealer dealer = participants.findDealer();
        Map<String, Result> playerResults = new LinkedHashMap<>();
        for (Player player : getPlayers()) {
            compareHandValue(dealer, playerResults, player);
        }

        return playerResults;
    }

    private void compareHandValue(Dealer dealer, Map<String, Result> playerResults, Player player) {
        int dealerHandValue = getParticipantHandValue(dealer);
        int playerHandValue = getParticipantHandValue(player);

        if (playerHandValue != dealerHandValue) {
            Result result = Result.compareHandValue(playerHandValue, dealerHandValue);
            playerResults.put(player.getName(), result);
            return;
        }
        compareAtTieValue(dealer, playerResults, player, playerHandValue);
    }

    private int getParticipantHandValue(Participant participant) {
        int participantHandValue = participant.getHandValue();
        if (participantHandValue > BUST_BOUNDARY_VALUE) {
            participantHandValue = BUST_HAND_VALUE;
        }
        return participantHandValue;
    }


    private void compareAtTieValue(Dealer dealer, Map<String, Result> playerResults, Player player,
        int playerHandValue) {
        if (playerHandValue == BUST_HAND_VALUE) {
            playerResults.put(player.getName(), Result.TIE);
            return;
        }
        compareHandCount(dealer, playerResults, player);
    }

    private void compareHandCount(Dealer dealer, Map<String, Result> playerResults, Player player) {
        int playerHandCount = player.getCardNames().size();
        int dealerHandCount = dealer.getCardNames().size();
        if (playerHandCount != dealerHandCount) {
            Result result = Result.compareHandCount(playerHandCount, dealerHandCount);
            playerResults.put(player.getName(), result);
            return;
        }
        playerResults.put(player.getName(), Result.TIE);
    }

    public Map<Result, Integer> calculateDealerResults(Map<String, Result> playerResults) {
        return getDealerResults(playerResults);
    }

    public Map<Result, Integer> getDealerResults(Map<String, Result> results) {
        EnumMap<Result, Integer> result = new EnumMap<>(Result.class);

        for (Result playerResult : results.values()) {
            judgeResult(result, playerResult);
        }
        return result;
    }

    private void judgeResult(EnumMap<Result, Integer> result, Result playerResult) {
        if (playerResult.equals(Result.WIN)) {
            result.put(Result.LOSE, result.getOrDefault(Result.LOSE, 0) + 1);
        }
        if (playerResult.equals(Result.TIE)) {
            result.put(Result.TIE, result.getOrDefault(Result.TIE, 0) + 1);
        }
        if (playerResult.equals(Result.LOSE)) {
            result.put(Result.WIN, result.getOrDefault(Result.WIN, 0) + 1);
        }
    }
}
