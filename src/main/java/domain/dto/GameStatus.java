package domain.dto;

import java.util.Collections;
import java.util.List;

public class GameStatus {
    private final List<GamerDto> gamerDtos;
    private final GamerDto dealerDto;

    private GameStatus(GamerDto dealerDto, List<GamerDto> gamerDtos) {
        this.gamerDtos = gamerDtos;
        this.dealerDto = dealerDto;
    }

    public static GameStatus of(GamerDto dealerDto, List<GamerDto> gamerDtos) {
        return new GameStatus(dealerDto, gamerDtos);
    }

    public List<GamerDto> getGamerDtos() {
        return Collections.unmodifiableList(gamerDtos);
    }

    public GamerDto getGamerDtoFromName(String name) {
        return gamerDtos.stream()
                .filter(gamerDto -> gamerDto.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 참여자 입니다."));
    }

    public GamerDto getDealerDto() {
        return dealerDto;
    }
}
