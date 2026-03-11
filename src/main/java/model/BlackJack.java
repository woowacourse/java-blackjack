package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.participant.Participant;
import util.Randoms;

public final class BlackJack {
    private static final int STARTING_CARDS = 2;

    private final Participants participants;
    private final List<Card> pickedCards = new ArrayList<>();
    private final Map<Participant, Integer> betAmount;
    private boolean firstTurn = Boolean.TRUE;

    private BlackJack(Participants participants, Map<Participant, Integer> betAmount) {
        this.participants = participants;
        this.betAmount = betAmount;
    }

    public static BlackJack from(Participants participants, Map<Participant, Integer> betAmount) {
        return new BlackJack(participants, betAmount);
    }

    public Map<String, Integer> calculateDealerResult() {
        Map<String, Integer> resultMap = new HashMap<>();

        Participant dealer = participants.getDealer();
        List<Participant> players = participants.getPlayers();

        for (Participant player : players) {
            if (dealer.calculateScore() > player.calculateScore() || player.isBust()) {
                resultMap.merge("승", 1, Integer::sum);
                continue;
            }

            resultMap.merge("패", 1, Integer::sum);
        }

        return resultMap;
    }

    public Map<String, Boolean> calculatePlayerResult() {
        Map<String, Boolean> resultMap = new HashMap<>();

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
                Card pick = Randoms.pick();
                while (pickedCards.contains(pick)) {
                    pick = Randoms.pick();
                }
                pickedCards.add(pick);
                participant.draw(pick);
            }
        }
    }

    public void setFirstTurn() {
        this.firstTurn = Boolean.FALSE;
    }
}
