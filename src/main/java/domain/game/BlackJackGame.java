package domain.game;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.card.Card;
import domain.card.Deck;
import domain.card.ShuffleStrategy;
import domain.people.Dealer;
import domain.people.Participant;
import domain.people.Participants;
import domain.people.Player;

public class BlackJackGame {

    private static final int INITIAL_CARD_COUNT = 2;
    private static final int BUST_BOUNDARY_VALUE = 21;
    private static final int BUST_HAND_VALUE = 0;
    private static final String HIT_REQUEST = "y";
    private static final String DEALER_NAME = new Dealer().getName();

    private final Deck deck;
    private final Participants participants;
    private boolean isPlayerTurnsOngoing;
    private int currentPlayerIndex;

    private BlackJackGame(List<String> names, ShuffleStrategy shuffleStrategy) {
        this.deck = Deck.from(shuffleStrategy);
        this.participants = createParticipants(names);
        this.isPlayerTurnsOngoing = true;
        this.currentPlayerIndex = 0;
    }

    public static BlackJackGame from(List<String> names, ShuffleStrategy shuffleStrategy) {
        return new BlackJackGame(names, shuffleStrategy);
    }

    public Participants createParticipants(List<String> names) {
        return Participants.from(names);
    }

    public void dealCardsToParticipants() {
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            deal(deck, participants.getParticipants());
        }
    }

    public void deal(Deck deck, List<Participant> participants) {
        for (Participant participant : participants) {
            participant.receiveCard(deck.draw());
        }
    }

    public List<String> fetchPlayerNames() {
        return fetchPlayers().stream()
            .map(Player::getName)
            .collect(Collectors.toList());
    }

    private List<Player> fetchPlayers() {
        return participants.findPlayers();
    }

    public Map<String, List<String>> fetchParticipantsInitHands() {
        Map<String, List<String>> participantsHands = fetchParticipantsHands();
        List<String> dealerHand = participantsHands.get(DEALER_NAME);
        List<String> dealerFirstCard = dealerHand.subList(0, 1);
        participantsHands.replace(DEALER_NAME, dealerFirstCard);
        return participantsHands;
    }

    public Map<String, List<String>> fetchParticipantsHands() {
        Map<String, List<String>> participantsHands = new LinkedHashMap<>();
        fetchDealerHand(participantsHands);
        fetchPlayersHand(participantsHands);
        return participantsHands;
    }

    private void fetchDealerHand(Map<String, List<String>> participantsHands) {
        List<Card> dealerHand = participants.findDealer().fetchHand();
        participantsHands.put(DEALER_NAME, dealerHand.stream()
            .map(Card::getName)
            .collect(Collectors.toList()));
    }

    private void fetchPlayersHand(Map<String, List<String>> participantsHands) {
        for (Participant participant : fetchPlayers()) {
            String playerName = participant.getName();
            List<String> playerHand = participant.fetchHand().stream()
                .map(Card::getName)
                .collect(Collectors.toList());
            participantsHands.put(playerName, playerHand);
        }
    }

    public boolean isPlayerTurnsOngoing() {
        return isPlayerTurnsOngoing;
    }

    public String fetchCurrentPlayerName() {
        return fetchPlayers().get(currentPlayerIndex).getName();
    }

    public void hitOrStay(String hitRequest) {
        if (isHit(hitRequest)) {
            fetchPlayers().get(currentPlayerIndex).receiveCard(deck.draw());
            return;
        }

        currentPlayerIndex++;
        if (currentPlayerIndex == fetchPlayers().size()) {
            isPlayerTurnsOngoing = false;
        }
    }

    private boolean isHit(String drawingInput) {
        return drawingInput.equals(HIT_REQUEST);
    }

    public boolean shouldDealerHit() {
        return participants.findDealer().shouldHit();
    }

    public void dealerHit() {
        participants.findDealer().receiveCard(deck.draw());
    }

    public Map<String, String> fetchParticipantScores() {
        Map<String, String> scores = new LinkedHashMap<>();
        for (Participant participant : participants.getParticipants()) {
            judgeBust(scores, participant);
        }

        return scores;
    }

    private void judgeBust(Map<String, String> scores, Participant participant) {
        int handValue = participant.fetchHandValue();
        if (handValue > BUST_BOUNDARY_VALUE) {
            scores.put(participant.getName(), String.valueOf(BUST_HAND_VALUE));
            return;
        }
        scores.put(participant.getName(), String.valueOf(handValue));
    }

    public Map<String, String> calculatePlayerResults() {
        Dealer dealer = participants.findDealer();
        Map<String, String> playerResults = new LinkedHashMap<>();
        for (Player player : fetchPlayers()) {
            compareHandValue(dealer, playerResults, player);
        }

        return playerResults;
    }

    private void compareHandValue(Dealer dealer, Map<String, String> playerResults, Player player) {
        int playerHandValue = player.fetchParticipantHandValue();
        int dealerHandValue = dealer.fetchParticipantHandValue();
        int handValueGap = playerHandValue - dealerHandValue;

        int playerHandCount = player.fetchHand().size();
        int dealerHandCount = dealer.fetchHand().size();
        int handCountGap = playerHandCount - dealerHandCount;

        playerResults.put(player.getName(), Result.calculateResult(handValueGap, handCountGap).getResult());
    }

    public Map<String, Integer> calculateDealerResults(Map<String, String> playerResults) {
        return playerResults.values().stream()
            .collect(Collectors.groupingBy(
                result -> {
                    if (result.equals(Result.LOSE.getResult())) {
                        return Result.WIN.getResult();
                    }
                    if (result.equals(Result.TIE.getResult())) {
                        return Result.TIE.getResult();
                    }
                    return Result.LOSE.getResult();
                },
                LinkedHashMap::new,
                Collectors.summingInt(result -> 1))
            );
    }
}
