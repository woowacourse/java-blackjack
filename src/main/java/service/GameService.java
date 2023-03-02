package service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.Deck;
import domain.Participant;
import domain.Participants;
import domain.Player;
import domain.PrintedHandPool;
import domain.Result;

public class GameService {
    private final Deck deck;
    private final Participants participants;
    private final PrintedHandPool printedHandPool;

    public GameService(Deck deck, Participants participants) {
        this.deck = deck;
        this.participants = participants;
        this.printedHandPool = new PrintedHandPool();
    }

    public void dealCardsToParticipants() {
        participants.deal(deck);
    }

    public List<String> getParticipantsNames() {
        return participants.findPlayers().stream()
            .map(Player::getName)
            .collect(Collectors.toList());
    }

    public Map<String, List<String>> getParticipantsHands() {
        Map<String, List<String>> participantsHands = new LinkedHashMap<>();
        participantsHands.put(participants.findDealer().getName(), participants.findDealer().getCardNames());
        for (Participant participant : participants.findPlayers()) {
            participantsHands.put(participant.getName(), participant.getCardNames());
        }
        return participantsHands;
    }

    public Map<String, List<String>> getParticipantsInitHands() {
        Map<String, List<String>> participantsHands = getParticipantsHands();
        participantsHands.replace("딜러", participantsHands.get("딜러").subList(0, 1));
        return participantsHands;
    }

    public boolean isHit(String drawingInput) {
        return drawingInput.equals("y");
    }

    public void hit(Participant participant) {
        participant.receiveCard(deck.draw());
    }

    public boolean isDealerHandValueUnderStandard() {
        return participants.findDealer().getHandValue() <= 16;
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
