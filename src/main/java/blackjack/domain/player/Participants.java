package blackjack.domain.player;

import blackjack.domain.card.CardDeck;
import java.util.ArrayList;
import java.util.List;

public class Participants {

    private final Players players;
    private final Dealer dealer;

    public Participants(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public static Participants create(List<String> playerNames) {
        Players players = Players.create(playerNames);
        Dealer dealer = new Dealer();
        return new Participants(players, dealer);
    }

    public void deal(CardDeck cardDeck) {
        for (Participant participant : getParticipants()) {
            participant.hit(cardDeck.popCard());
            participant.hit(cardDeck.popCard());
        }
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }

    public List<Participant> getParticipants() {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players.getPlayers());
        return participants;
    }
}
