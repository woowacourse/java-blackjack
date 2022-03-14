package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {

    private static final int MINIMUM_NUMBER_OF_PEOPLE = 1;
    private static final int MAXIMUM_NUMBER_OF_PEOPLE = 8;

    private static final String PLAYER_NUMBER_ERROR_MESSAGE = "[ERROR] 플레이어 수는 1~8명 사이여야 합니다.";


    private final Dealer dealer = new Dealer();
    private final List<Player> players;

    public Participants(List<Player> players) {
        validatePlayerNumber(players);
        this.players = new ArrayList<>(players);
    }

    public static Participants from(List<String> playerNames) {
        return new Participants(playerNames.stream()
            .map(Player::new)
            .collect(Collectors.toList()));
    }

    public void handOutInitialCards(Deck deck) {
        for (Participant participant : getParticipants()) {
            List<Card> cards = List.of(deck.pickCard(), deck.pickCard());
            participant.receiveInitCards(cards);
        }
    }


    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Participant> getParticipants() {
        List<Participant> participants = new ArrayList<>();

        participants.add(dealer);
        participants.addAll(players);

        return Collections.unmodifiableList(participants);
    }

    private void validatePlayerNumber(List<Player> players) {
        if (players.size() < MINIMUM_NUMBER_OF_PEOPLE || players.size() > MAXIMUM_NUMBER_OF_PEOPLE) {
            throw new IllegalArgumentException(PLAYER_NUMBER_ERROR_MESSAGE);
        }
    }
}
