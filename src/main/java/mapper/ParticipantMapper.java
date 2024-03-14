package mapper;

import domain.card.Card;
import domain.card.Cards;
import domain.participant.Name;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import view.dto.participant.ParticipantDto;
import vo.BettingMoney;

import java.util.List;

public class ParticipantMapper {
    public static ParticipantDto nameToParticipantDto(final String name) {
        return new ParticipantDto(name, CardMapper.cardsToCardsDto(new Cards(), 0));
    }

    public static ParticipantDto participantToParticipantDto(final Participant participant) {
        return new ParticipantDto(participant.name().value(), CardMapper.handToCardsDto(participant.hand(), participant.score()));
    }

    public static ParticipantDto participantAndCardToParticipantDto(final Participant participant, final Card card) {
        return new ParticipantDto(participant.name().value(), CardMapper.cardsToCardsDto(new Cards(List.of(card)), participant.score()));
    }

    public static List<ParticipantDto> playersToParticipantsDto(final Players players) {
        return players.getPlayers()
                      .stream()
                      .map(ParticipantMapper::participantToParticipantDto)
                      .toList();
    }

    public static Players participantsDtoToPlayers(final List<ParticipantDto> players) {
        return new Players(players.stream()
                                  .map(player -> new Player(new Name(player.name()), new BettingMoney(5000)))
                                  .toList());
    }
}
