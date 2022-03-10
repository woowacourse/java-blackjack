package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Participants {

    private static final String PLAYER_NUMBER_ERROR_MESSAGE = "[ERROR] 플레이어 수는 1~8명 사이여야 합니다.";

    private final Dealer dealer = new Dealer();
    private final List<Player> players;

    public Participants(List<Player> players) {
        validatePlayerNumber(players);
        this.players = new ArrayList<>(players);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    private void validatePlayerNumber(List<Player> players) {
        if (players.size() < 1 || players.size() > 8) {
            throw new IllegalArgumentException(PLAYER_NUMBER_ERROR_MESSAGE);
        }
    }

    public void handOutInitialCards(Deck deck) {
        for (Participant participant : getParticipants()) {
            List<Card> cards = List.of(deck.pickCard(), deck.pickCard());
            participant.receiveInitCards(cards);
        }
    }

    public List<Participant> getParticipants() {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players);
        return Collections.unmodifiableList(participants);
    }

    public Dealer getDealer() {
        return dealer;
    }
}
