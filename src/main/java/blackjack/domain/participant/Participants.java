package blackjack.domain.participant;

import java.util.ArrayList;
import java.util.List;
import blackjack.domain.common.Money;

public class Participants {

    private final Dealer dealer;
    private final List<Player> players;

    public Participants(List<String> playerNames, List<Money> playersMoney) {
        this.dealer = new Dealer();
        this.players = PlayerFactory.createPlayers(playerNames, playersMoney, dealer);
    }

    public List<Participant> getParticipants() {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players);

        return participants;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
