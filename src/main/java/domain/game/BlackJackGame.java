package domain.game;


import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.card.Deck;
import domain.card.ShuffleStrategy;
import domain.people.Participants;
import domain.people.Player;

public final class BlackJackGame {

    public static final int INIT_HAND_COUNT = 2;
    private static final String HIT_REQUEST = "y";

    private final Deck deck;
    private final Participants participants;

    private BlackJackGame(final List<String> names, final ShuffleStrategy shuffleStrategy) {
        this.deck = Deck.from(shuffleStrategy);
        this.participants = createParticipants(names);
    }

    public static BlackJackGame from(final List<String> names, final ShuffleStrategy shuffleStrategy) {
        return new BlackJackGame(names, shuffleStrategy);
    }

    private Participants createParticipants(final List<String> names) {

        return Participants.from(names);
    }

    public void dealCardsToParticipants() {
        for (int i = 0; i < INIT_HAND_COUNT; i++) {
            deal();
        }
    }

    private void deal() {
        participants.getDealer().receiveCard(deck.draw());
        for (Player player : participants.getPlayers()) {
            player.receiveCard(deck.draw());
        }
    }

    public List<String> fetchParticipantNames() {
        List<String> participantNames = new ArrayList<>();
        participantNames.add(participants.getDealer().getName());
        participantNames.addAll(fetchPlayerNames());
        return participantNames;
    }

    public List<String> fetchPlayerNames() {
        return participants.getPlayers().stream()
            .map(Player::fetchPlayerName)
            .collect(toList());
    }

    public String fetchDealerName() {
        return participants.getDealer().getName();
    }

    public List<String> fetchParticipantInitHand(final String participantName) {
        List<String> participantHand = fetchParticipantHand(participantName);

        if (participantName.equals(participants.getDealer().getName())) {
            participantHand = participantHand.subList(0, 1);
        }

        return new ArrayList<>(participantHand);
    }

    public List<String> fetchParticipantHand(final String participantName) {
        if (participantName.equals(participants.getDealer().getName())) {
            return participants.getDealer().fetchHand().stream().map(Card::toString).collect(toList());
        }

        List<Card> participantHand = participants.findPlayer(participantName).orElseThrow().fetchHand();
        return participantHand.stream().
            map(Card::toString).
            collect(toList());
    }

    public void hitOrStay(final String hitRequest, final String playerName) {
        if (isHit(hitRequest)) {
            participants.findPlayer(playerName).
                orElseThrow().
                receiveCard(deck.draw());
        }
    }

    private boolean isHit(final String drawingInput) {
        return drawingInput.equals(HIT_REQUEST);
    }

    public boolean shouldDealerHit() {
        return participants.getDealer().shouldHit();
    }

    public void dealerHit() {
        participants.getDealer().receiveCard(deck.draw());
    }

    public int fetchParticipantScore(String name) {
        if (name.equals(fetchDealerName())) {
            return participants.getDealer().fetchHandValue();
        }

        return participants.findPlayer(name).orElseThrow().fetchHandValue(INIT_HAND_COUNT);
    }

    public boolean hasBlackJack(String playerName) {
        Player player = participants.findPlayer(playerName).orElseThrow();
        return player.hasBlackJack(INIT_HAND_COUNT);
    }

    public List<String> fetchNoBlackJackPlayerNames() {
        return participants.getPlayers().stream().
            filter(player -> !player.hasBlackJack(INIT_HAND_COUNT)).
            map(Player::fetchPlayerName).
            collect(toList());
    }

    public boolean isBust(String playerName) {
        return participants.findPlayer(playerName).orElseThrow().isBust();
    }

    public boolean isTurnOver(String hitRequest) {
        return !hitRequest.equals(HIT_REQUEST);
    }

    public void assignBetAmount(String name, int betAmount) {
        participants.findPlayer(name).orElseThrow().assignBetAmount(betAmount);
    }

    public Integer fetchPlayerProfit(String name) {
        return participants.findPlayer(name)
            .orElseThrow()
            .fetchProfit(INIT_HAND_COUNT, participants.getDealer().fetchHandValue());
    }
}
