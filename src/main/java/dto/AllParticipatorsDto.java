package dto;

import java.util.List;

public class AllParticipatorsDto {
    private final List<ParticipatorDto> playersDto;
    private final ParticipatorDto dealerDto;

    public AllParticipatorsDto(List<ParticipatorDto> participatorDtos, ParticipatorDto dealerDto) {
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
