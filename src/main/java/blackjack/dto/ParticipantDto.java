package blackjack.dto;

import blackjack.domain.participant.Participant;

public class ParticipantDto {
    private final String name;
    private final HandDto handDto;

    private ParticipantDto(String name, HandDto handDto) {
        this.name = name;
        this.handDto = handDto;
    }

    public static ParticipantDto from(Participant participant) {
        return new ParticipantDto(participant.getName(), HandDto.of(participant));
    }

    public String getName() {
        return name;
    }

    public HandDto getHandDto() {
        return handDto;
    }

    @Override
    public String toString() {
        return "ParticipantDto{" +
                "name='" + name + '\'' +
                ", handDto=" + handDto +
                '}';
    }
}
