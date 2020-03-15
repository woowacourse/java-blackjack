package blackjack.controller.dto.response;

import java.util.List;

public class HandResponseDtos {

    private List<HandResponseDto> handResponseDtos;

    public List<HandResponseDto> getHandResponseDtos() {
        return handResponseDtos;
    }

    public HandResponseDtos(List<HandResponseDto> handResponseDtos) {
        this.handResponseDtos = handResponseDtos;
    }
}
