package dto;

import java.util.List;

public class InitGameDto {
    private final List<ParticipatorDto> playersDto;
    private final ParticipatorDto dealerDto;

    public InitGameDto(List<ParticipatorDto> participatorDtos, ParticipatorDto dealerDto) {
        this.playersDto = participatorDtos;
        this.dealerDto = dealerDto;
    }

    public List<ParticipatorDto> getPlayersDto() {
        return playersDto;
    }

    public ParticipatorDto getDealerDto() {
        return dealerDto;
    }
}
