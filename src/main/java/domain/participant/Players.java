package domain.participant;

import domain.participant.dto.ParticipantHand;
import domain.participant.dto.ParticipantHandMapper;
import domain.participant.dto.ParticipantResult;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Players {

    private final List<Player> playerList;

    private Players(List<Player> playerList) {
        this.playerList = playerList;
    }

    public static Players from(List<ParticipantName> participantNames) {
        return new Players(participantNames.stream()
                .map(Player::from)
                .toList()
        );
    }

    public Stream<Player> stream() {
        return playerList.stream();
    }

    public Players giveInitialCardBundle(Dealer dealer) {
        playerList.forEach(dealer::handOutInitialCardToPlayer);
        return this;
    }

    public List<String> displayNames() {
        return playerList.stream()
                .map(Player::toDisplayMyName)
                .toList();
    }

    public List<ParticipantHand> toParticipantHand() {
        return playerList.stream()
                .map(ParticipantHandMapper::from)
                .toList();
    }

    public List<ParticipantResult> toParticipantResult() {
        return playerList.stream()
                .map(ParticipantResult::from)
                .toList();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Players players1 = (Players) o;
        return Objects.equals(playerList, players1.playerList);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(playerList);
    }
}
