package blackjack.dto;

import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;

import java.util.List;

public class initialParticipantsDto {

    private final String dealerName;
    private final String dealerCardName;
    private final List<String> names;
    private final List<Participant> players;

    private initialParticipantsDto(final String dealerName,
                                   final String dealerCardName,
                                   final List<String> names,
                                   final List<Participant> players) {
        this.dealerName = dealerName;
        this.dealerCardName = dealerCardName;
        this.names = names;
        this.players = players;
    }

    public static initialParticipantsDto from(final Participants participants) {
        return new initialParticipantsDto(
                participants.getDealer().getName(),
                participants.getDealer().getCard(0).getCardName(),
                participants.getPlayerNames(),
                participants.getPlayers());
    }

    public String dealerName() {
        return dealerName;
    }

    public String dealerCardName() {
        return dealerCardName;
    }

    public List<String> names() {
        return names;
    }

    public List<Participant> players() {
        return players;
    }
}
