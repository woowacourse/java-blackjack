package domain;

import domain.card.Deck;
import domain.participant.BettingMoney;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.LinkedHashMap;
import java.util.Map;

public class BlackJackGame {

    public static final int BLACKJACK_NUMBER = 21;
    public static final int DEALER_REPEAT_NUMBER = 16;

    private final Deck deck;
    private Participants participants;

    public BlackJackGame(Map<String, BettingMoney> playerBettingMoneys) {
        this.deck = new Deck();
        deck.shuffleDeck();
        initializeParticipants(playerBettingMoneys, deck);
    }

    private void initializeParticipants(Map<String, BettingMoney> playerBettingMoneys, Deck deck) {
        try {
            this.participants = new Participants(playerBettingMoneys, deck);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public Map<Player, Integer> calculatePlayersResult() {
        Map<Player, Integer> playersResult = new LinkedHashMap<>();
        for (Player player : participants.getPlayers()) {
            Result playerFinalResult = player.calculateFinalResult(participants.getDealer().getState());
            playersResult.put(player, playerFinalResult.calculateResult(player.getBettingMoney()));
        }
        return playersResult;
    }

    public int calculateDealerResult(Map<Player, Integer> playersResult) {
        int totalPlayerResults = playersResult.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
        return -totalPlayerResults;
    }

    public Participants getParticipants() {
        return participants;
    }

    public Deck getDeck() {
        return deck;
    }
}
