package service.dto;

import java.util.List;

public class InitGameDto {
    private final List<ParticipatorDto> participatorDtos;

    public InitGameDto(List<ParticipatorDto> participatorDtos) {
        this.participatorDtos = participatorDtos;
    }

    public List<ParticipatorDto> getParticipatorDtos() {
        return participatorDtos;
    }
}
