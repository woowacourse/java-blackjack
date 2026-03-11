package domain.participant;

import static config.BlackjackGameConstant.*;

import domain.card.CardDeck;
import domain.participant.dto.ParticipantHandDto;
import domain.participant.dto.ParticipantHandDtoMapper;
import domain.result.dto.ParticipantGameResultDto;

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

    public Players giveInitialCardBundle(CardDeck cardDeck) {
        playerList.forEach(player -> player.drawCards(cardDeck, INITIAL_CARD_DRAW_COUNT));
        return this;
    }

    public List<String> displayNames() {
        return playerList.stream()
                .map(Player::toDisplayMyName)
                .toList();
    }

    public List<ParticipantHandDto> toParticipantHandDtos() {
        return playerList.stream()
                .map(ParticipantHandDtoMapper::map)
                .toList();
    }

    public List<ParticipantGameResultDto> toParticipantGameResultDtos() {
        return playerList.stream()
                .map(ParticipantGameResultDto::from)
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
