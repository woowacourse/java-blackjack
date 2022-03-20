package blackjack.game.dto;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import java.util.List;

public class ParticipantsDto {
    private final Dealer dealer;
    private final List<Player> players;

    public ParticipantsDto(Participants participants) {
        this.dealer = participants.getDealer();
        this.players = participants.getPlayers();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
