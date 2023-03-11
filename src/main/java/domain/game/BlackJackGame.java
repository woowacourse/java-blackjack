package domain.game;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.card.Deck;
import domain.card.ShuffleStrategy;
import domain.people.Participants;
import domain.people.Player;

public class BlackJackGame {

    private static final int INIT_HAND_COUNT = 2;
    private static final String HIT_REQUEST = "y";

    private final Deck deck;
    private final Participants participants;

    private BlackJackGame(List<String> names, ShuffleStrategy shuffleStrategy) {
        this.deck = Deck.from(shuffleStrategy);
        this.participants = createParticipants(names);
    }

    public static BlackJackGame from(List<String> names, ShuffleStrategy shuffleStrategy) {
        return new BlackJackGame(names, shuffleStrategy);
    }

    public Participants createParticipants(List<String> names) {
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

    public List<String> fetchParticipantInitHand(String participantName) {
        List<String> participantHand = fetchParticipantHand(participantName);
        if (participantName.equals(participants.getDealer().getName())) {
            participantHand = participantHand.subList(0, 1);
        }
        return new ArrayList<>(participantHand);
    }

    public List<String> fetchParticipantHand(String participantName) {
        if (participantName.equals(participants.getDealer().getName())) {
            return participants.getDealer().fetchHand().stream().map(Card::toString).collect(toList());
        }

        List<Card> participantHand = participants.findPlayer(participantName).orElseThrow().fetchHand();
        return participantHand.stream().
            map(Card::toString).
            collect(toList());
    }

    public void hitOrStay(String hitRequest, String playerName) {
        if (isHit(hitRequest)) {
            participants.findPlayer(playerName).
                orElseThrow().
                receiveCard(deck.draw());
        }
    }

    private boolean isHit(String drawingInput) {
        return drawingInput.equals(HIT_REQUEST);
    }

    public boolean shouldDealerHit() {
        return participants.getDealer().shouldHit();
    }

    public void dealerHit() {
        participants.getDealer().receiveCard(deck.draw());
    }

    public int fetchParticipantScore(String name) {
        if (name.equals(participants.getDealer().getName())) {
            return participants.getDealer().fetchHandValue();
        }
        return participants.findPlayer(name).orElseThrow().fetchHandValue(INIT_HAND_COUNT);
    }

    public int getInitHandCount() {
        return INIT_HAND_COUNT;
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
    // public Map<String, String> calculatePlayerResults() {
    //     Dealer dealer = participants.getDealer();
    //     Map<String, String> playerResults = new LinkedHashMap<>();
    //     for (Player player : fetchPlayers()) {
    //         compareHandValue(dealer, playerResults, player);
    //     }
    //
    //     return playerResults;
    // }

    // private void compareHandValue(Dealer dealer, Map<String, String> playerResults, Player player) {
    //     int playerHandValue = player.fetchHandValue();
    //     int dealerHandValue = dealer.fetchHandValue();
    //     int handValueGap = playerHandValue - dealerHandValue;
    //
    //     int playerHandCount = player.fetchHand().size();
    //     int dealerHandCount = dealer.fetchHand().size();
    //     int handCountGap = playerHandCount - dealerHandCount;
    //
    //     playerResults.put(player.getName(), Result.calculateResult(handValueGap, handCountGap).getResult());
    // }
}
