package view.dto.participant;

import domain.card.Card;
import domain.card.Cards;
import domain.participant.Name;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import view.dto.card.CardsDto;

import java.util.List;

public record ParticipantDto(String name, CardsDto cards) {
    public ParticipantDto(final String name) {
        this(name, new CardsDto(new Cards(), 0));
    }

    public ParticipantDto(final Participant participant) {
        this(participant.name().value(), new CardsDto(participant.hand(), participant.cardSum()));
    }

    public ParticipantDto(final Participant participant, final Card card) {
        this(participant.name().value(), new CardsDto(List.of(card), participant.cardSum()));
    }

    public static List<ParticipantDto> fromPlayers(final Players players) {
        return players.getPlayers()
                      .stream()
                      .map(ParticipantDto::new)
                      .toList();
    }

    public static Players toPlayers(List<ParticipantDto> players) {
        return new Players(players.stream()
                                  .map(player -> new Player(new Name(player.name)))
                                  .toList());
    }

    public int score() {
        return cards.getScore();
    }
}
