package domain.dto;

import java.util.Collections;
import java.util.List;

public class GameStatusDto {
    private final List<GamerDto> gamerDtos;

    private GameStatusDto(List<GamerDto> gamerDtos) {
        this.gamerDtos = gamerDtos;
    }

    public static GameStatusDto of(List<GamerDto> gamerDtos) {
        return new GameStatusDto(gamerDtos);
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
}
