package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.Randoms;

public class BlackJack {
    private Participants participants;
    private int round;
    private List<Card> pickedCards = new ArrayList<>();

    private BlackJack(Participants participants) {
        this.participants = participants;
    }

    public static BlackJack from(Participants participants) {
        return new BlackJack(participants);
    }

    public Map<String, Integer> calculateDealerResult() {
        Map<String, Integer> resultMap = new HashMap<>();

        Participant dealer = participants.getDealer();
        List<Participant> players = participants.getPlayers();

        for (Participant player : players) {
            if (dealer.calculateScore() > player.calculateScore()) {
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
            if (dealer.calculateScore() > player.calculateScore()) {
                resultMap.put(player.getName(), Boolean.FALSE);
                continue;
            }

            resultMap.put(player.getName(), Boolean.TRUE);
        }

        return resultMap;
    }

    public void dealOut() {
        for (Participant participant : participants) {
            for (int i = 0; i < 2; i++) {
                Card pick = Randoms.pick();
                while (pickedCards.contains(pick)) {
                    pick = Randoms.pick();
                }
                pickedCards.add(pick);
                participant.draw(pick);
            }
        }
    }
}
