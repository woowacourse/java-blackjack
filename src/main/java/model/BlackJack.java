package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.participant.Participant;
import util.Randoms;

public class BlackJack {
    private final Participants participants;
    private boolean firstTurn;
    private final List<Card> pickedCards = new ArrayList<>();

    private BlackJack(Participants participants) {
        this.participants = participants;
        this.firstTurn = true;
    }

    public static BlackJack from(Participants participants) {
        return new BlackJack(participants);
    }

    public Map<String, List<String>> dealOut() {
        Map<String, List<String>> dealOutResult = new LinkedHashMap<>();
        for (Participant participant : participants) {
            for (int i = 0; i < 2; i++) {
                Card pick = Randoms.pick();
                while (pickedCards.contains(pick)) {
                    pick = Randoms.pick();
                }
                pickedCards.add(pick);
                participant.draw(pick);
            }
            dealOutResult.put(participant.getName(), participant.open());
        }

        firstTurn = false;

        return dealOutResult;
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

    public void setFirstTurn() {
        this.firstTurn = Boolean.FALSE;
    }
}
