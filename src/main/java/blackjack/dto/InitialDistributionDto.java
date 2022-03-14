package blackjack.dto;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class InitialDistributionDto {

    private final List<ParticipantCardsDto> participantsInfo = new ArrayList<>();

    public InitialDistributionDto(Dealer dealer, List<Player> players) {
        initParticipantInfo(dealer);
        players.forEach(this::initParticipantInfo);
    }

    private void initParticipantInfo(Participant participant) {
        ParticipantCardsDto participantCardInfo = ParticipantCardsDto.ofInitial(participant);
        participantsInfo.add(participantCardInfo);
    }

    public List<ParticipantCardsDto> getParticipantsInfo() {
        return Collections.unmodifiableList(participantsInfo);
    }

    public List<String> getPlayerNames() {
        return participantsInfo.stream()
                .filter(ParticipantCardsDto::isPlayer)
                .map(ParticipantCardsDto::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public String toString() {
        return "InitialDistributionDto{" +
                "participantsInfo=" + participantsInfo +
                '}';
    }
}
