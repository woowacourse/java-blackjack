package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import model.participant.Dealer;
import model.participant.Participant;
import model.participant.Player;
import util.RandomNumberPicker;

public final class BlackJack {
    private static final int STARTING_CARDS = 2;
    private static final String DEALER_NAME = "딜러";

    private final Participants participants;
    private final Set<Card> pickedCards;

    private BlackJack(Participants participants) {
        this.participants = participants;
        this.pickedCards = new HashSet<>();
    }

    public static BlackJack from(Participants participants) {
        return new BlackJack(participants);
    }

    public Map<String, Integer> calculateDealerResult() {
        Map<String, Integer> resultMap = new HashMap<>();

        Participant dealer = participants.getDealer();
        List<Participant> players = participants.getPlayers();

        for (Participant player : players) {

            boolean dealerWins = !player.isBust() && (!dealer.isBust())
                    && dealer.calculateScore() >= player.calculateScore();
            boolean playerBust = player.isBust();
            if (dealerWins || playerBust) {
                resultMap.merge("승", 1, Integer::sum);
                continue;
            }

            resultMap.merge("패", 1, Integer::sum);
        }

        return resultMap;
    }

    public Map<String, Boolean> calculatePlayerResult() {
        Map<String, Boolean> resultMap = new LinkedHashMap<>();

        Participant dealer = participants.getDealer();
        List<Participant> players = participants.getPlayers();

        for (Participant player : players) {
            if (dealer.calculateScore() > player.calculateScore() || player.isBust()) {
                resultMap.put(player.getName(), Boolean.FALSE);
                continue;
            }

            resultMap.put(player.getName(), Boolean.TRUE);
        }

        return resultMap;
    }

    public void dealOut() {
        for (Participant participant : participants) {
            for (int i = 0; i < STARTING_CARDS; i++) {
                Card pick = RandomNumberPicker.pick();
                while (pickedCards.contains(pick)) {
                    pick = RandomNumberPicker.pick();
                }
                pickedCards.add(pick);
                participant.draw(pick);
            }
        }
    }

    public Map<String, Integer> calculateRevenue() {
        Map<String, Integer> calculatedTotalRevenues = new LinkedHashMap<>();
        Map<String, Integer> calculatedPlayerRevenues = new LinkedHashMap<>();

        int dealerRevenue = 0;
        Dealer dealer = ((Dealer) participants.getDealer());

        for (Entry<String, Boolean> entry : calculatePlayerResult().entrySet()) {
            Participant participant = participants.findByName(entry.getKey());

            if (entry.getKey().equals(DEALER_NAME)) {
                dealer = ((Dealer) participant);

                continue;
            }

            if (dealer.isBust() && participant.isBust()) {
                dealerRevenue += ((Player) participant).getBetAmount();
                calculatedPlayerRevenues.merge(entry.getKey(), -((Player) participant).getBetAmount(), Integer::sum);
                continue;
            }

            if (dealer.isBust() && !participant.isBust()) {
                calculatedTotalRevenues.put(participant.getName(), ((Player) participant).getBetAmount());
                dealerRevenue -= ((Player) participant).getBetAmount();
                continue;
            }

            if (participant.isBlackJack() && !dealer.isBlackJack()) {
                int playerBlackJackRevenue = (int) (((Player) participant).getBetAmount() * 1.5);
                extracted(calculatedTotalRevenues, participant, playerBlackJackRevenue);
                dealerRevenue -= playerBlackJackRevenue;
                continue;
            }

            if (isDealerAndPlayerWithBlackJack(entry, dealer, participant, calculatedTotalRevenues)) {
                continue;
            }

            dealerRevenue = calculateTotalRevenue(entry, calculatedPlayerRevenues, (Player) participant, dealerRevenue, dealer);

        }

        calculatedTotalRevenues.put(DEALER_NAME, dealerRevenue);
        calculatedTotalRevenues.putAll(calculatedPlayerRevenues);
        return calculatedTotalRevenues;
    }

    private static void extracted(Map<String, Integer> calculatedTotalRevenues, Participant participant,
                                  int playerBlackJackRevenue) {
        calculatedTotalRevenues.put(participant.getName(), playerBlackJackRevenue);
    }

    private int calculateTotalRevenue(Entry<String, Boolean> entry, Map<String, Integer> calculatedPlayerRevenues,
                                      Player participant, int dealerRevenue, Dealer dealer) {
        if (entry.getValue() || dealer.isBust()) {
            calculatedPlayerRevenues.put(entry.getKey(), participant.getBetAmount());
            dealerRevenue -= participant.getBetAmount();
        }

        if (!entry.getValue()) {
            calculatedPlayerRevenues.put(entry.getKey(), -participant.getBetAmount());
            dealerRevenue += participant.getBetAmount();
        }
        return dealerRevenue;
    }

    private boolean isDealerAndPlayerWithBlackJack(Entry<String, Boolean> entry, Dealer dealer, Participant participant,
                                     Map<String, Integer> calculatedTotalRevenues) {
        if (dealer.isBlackJack() && participant.isBlackJack()) {
            calculatedTotalRevenues.put(entry.getKey(), 0);
            return true;
        }
        return false;
    }
}
