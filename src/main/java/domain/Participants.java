package domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Participants {
    private final List<Participant> participants;

    private Participants(List<Participant> participants) {
        this.participants = participants;
    }

    public static Participants of(List<String> playerNames, Deck totalDeck) {
        return new Participants(createParticipants(playerNames, totalDeck));
    }

    private static List<Participant> createParticipants(List<String> playerNames, Deck totalDeck) {
        List<Participant> participants = new ArrayList<>();
        participants.add(createDealer(totalDeck));
        participants.addAll(createPlayer(playerNames, totalDeck));
        return participants;
    }

    private static Dealer createDealer(Deck totalDeck) {
        return new Dealer(
                Deck.createParticipantDeck(totalDeck)
        );
    }

    private static List<Player> createPlayer(List<String> playerNames, Deck totalDeck) {
        List<Player> players = new ArrayList<>();

        playerNames.forEach(
                name -> players.add(
                        new Player(Deck.createParticipantDeck(totalDeck), name)
                )
        );

        return players;
    }

    public Map<String, Deck> getDecksPerUser() {
        Map<String, Deck> decksPerUser = new LinkedHashMap<>();
        for (Participant participant : participants) {
            decksPerUser.put(participant.getName(), participant.getDeck());
        }
        return decksPerUser;
    }
}
