package domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameResultManager {
    public Map<String, List<String>> getParticipantsCard(final Participants participants) {
        final Participant dealer = participants.findDealer();

        Map<String, List<String>> participantsHands = new LinkedHashMap<>();

        participantsHands.put(dealer.getName(), dealer.getCardNames().subList(0, 1));
//        participantsHands.put(participants.findDealer().getName(), participants.findDealer().getCardNames());
        for (Participant participant : participants.findPlayers()) {
            participantsHands.put(participant.getName(), participant.getCardNames());
        }

//        participantsHands.put(participants.findDealer().getName(), participants.findDealer().getCardNames());
//        participantsHands.replace("딜러", participantsHands.get("딜러").subList(0, 1));
//        for (Participant participant : participants.findPlayers()) {
//            participantsHands.put(participant.getName(), participant.getCardNames());
//        }
        return participantsHands;
    }
}
