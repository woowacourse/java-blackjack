package blackjack.domain.participant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

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

    public void dealInitialCards(Deck deck) {
        for (Participant participant : getParticipants()) {
            List<Card> cards = List.of(deck.pickCard(), deck.pickCard());
            participant.initCards(cards);
        }
    }

    public List<Participant> getParticipants() {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players);
        return Collections.unmodifiableList(participants);
    }

    private void validatePlayerNumber(List<Player> players) {
        if (players.size() < 1 || players.size() > 8) {
            throw new IllegalArgumentException(PLAYER_NUMBER_ERROR_MESSAGE);
        }
    }

    public Dealer getDealer() {
        return dealer;
    }
}
