package blackjack.dto;

import blackjack.domain.game.BlackjackGame;
import blackjack.domain.participant.Participant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class InitialDistributionDto {

    private final List<ParticipantCardsDto> participantsInfo = new ArrayList<>();
    private final boolean isGameOver;

    private InitialDistributionDto(List<Participant> participants, boolean isGameOver) {
        participants.forEach(this::initParticipantInfo);
        this.isGameOver = isGameOver;
    }

    public static InitialDistributionDto of(BlackjackGame game) {
        return new InitialDistributionDto(game.getParticipants(), game.isBlackjackDealer());
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

    public boolean getIsGameOver() {
        return isGameOver;
    }

    @Override
    public String toString() {
        return "InitialDistributionDto{" +
                "participantsInfo=" + participantsInfo +
                ", isGameOver=" + isGameOver +
                '}';
    }
}
