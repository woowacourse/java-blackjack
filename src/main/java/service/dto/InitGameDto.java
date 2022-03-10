package service.dto;

import java.util.List;
import java.util.Map;
import model.Participator;

public class InitGameDto {
    private final List<ParticipatorDto> participatorDtos;

    public InitGameDto(List<ParticipatorDto> participatorDtos) {
        this.participatorDtos = participatorDtos;
    }

    public List<ParticipatorDto> getParticipatorDtos() {
        return participatorDtos;
    }
}
