package service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.card.Deck;
import domain.people.Participant;
import domain.people.Participants;
import domain.people.Player;
import domain.game.PrintedHandPool;
import domain.game.Result;

public class GameService {
    private static final int DEALER_MINIMUM_VALUE= 17;
    private static final String HIT_REQUEST = "y";
    private final Deck deck;
    private final Participants participants;
    private final PrintedHandPool printedHandPool;

    public GameService(Deck deck, Participants participants) {
        this.deck = deck;
        this.participants = participants;
        this.printedHandPool = new PrintedHandPool();
    }

    public List<String> getParticipantsNames() {
        return participants.findPlayers().stream()
            .map(Player::getName)
            .collect(Collectors.toList());
    }

    public void dealCardsToParticipants() {
        participants.deal(deck);
    }

    public Map<String, List<String>> getParticipantsInitHands() {
        Map<String, List<String>> participantsHands = getParticipantsHands();
        List<String> dealerFirstCard = participantsHands.get("딜러").subList(0, 1);
        participantsHands.replace("딜러", dealerFirstCard);
        return participantsHands;
    }

    public Map<String, List<String>> getParticipantsHands() {
        Map<String, List<String>> participantsHands = new LinkedHashMap<>();
        getDealerHand(participantsHands);
        getPlayersHand(participantsHands);
        return participantsHands;
    }

    private void getPlayersHand(Map<String, List<String>> participantsHands) {
        for (Participant participant : participants.findPlayers()) {
            String playerName = participant.getName();
            List<String> playerHand = participant.getCardNames();
            participantsHands.put(playerName, playerHand);
        }
    }

    private void getDealerHand(Map<String, List<String>> participantsHands) {
        String dealerName = participants.findDealer().getName();
        List<String> dealerHand = participants.findDealer().getCardNames();
        participantsHands.put(dealerName, dealerHand);
    }

    public boolean isHit(String drawingInput) {
        return drawingInput.equals(HIT_REQUEST);
    }

    public void hit(Participant participant) {
        participant.receiveCard(deck.draw());
    }

    public boolean isDealerHandValueUnderStandard() {
        int dealerHandValue = participants.findDealer().getHandValue();
        return dealerHandValue < DEALER_MINIMUM_VALUE;
    }

    public void dealerHit() {
        hit(participants.findDealer());
    }

    public Map<Participant, String> getParticipantScores() {
        return participants.getScores();
    }

    public Map<String, Result> calculatePlayerResults() {
        return participants.getPlayerResults();
    }

    public Map<Result, Integer> calculateDealerResults(Map<String, Result> playerResults) {
        return participants.getDealerResults(playerResults);
    }

    public boolean existHandInPool(List<String> cardNames) {
        return printedHandPool.exist(cardNames);
    }

    public void addHandToPool(List<String> cardNames) {
        printedHandPool.add(cardNames);
    }
}
