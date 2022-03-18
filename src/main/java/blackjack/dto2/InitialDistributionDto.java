package blackjack.dto2;

import blackjack.domain.BlackjackGame2;
import blackjack.domain.participant2.Participants;
import blackjack.domain.participant2.Participant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class InitialDistributionDto {

    private final List<ParticipantCardsDto> participantsInfo = new ArrayList<>();

    private InitialDistributionDto(final Participants participants) {
        participants.getValue()
                .forEach(this::initParticipantInfo);
    }

    public static InitialDistributionDto of(final BlackjackGame2 game) {
        return new InitialDistributionDto(game.getParticipants());
    }

    private void initParticipantInfo(final Participant participant) {
        ParticipantCardsDto participantCardInfo = ParticipantCardsDto.ofInitial(participant);
        participantsInfo.add(participantCardInfo);
    }

    public List<ParticipantCardsDto> getParticipantsInfo() {
        return Collections.unmodifiableList(participantsInfo);
    }

    public List<String> getAllParticipantNames() {
        return participantsInfo.stream()
                .map(ParticipantCardsDto::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public String toString() {
        return "InitialDistributionDto{" + "participantsInfo=" + participantsInfo + '}';
    }
}
