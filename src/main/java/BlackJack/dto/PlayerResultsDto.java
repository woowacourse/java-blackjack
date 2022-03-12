package BlackJack.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlayerResultsDto {
    private final List<PlayerResultDto> playerResultDtos;

    public PlayerResultsDto(List<PlayerResultDto> playerResultDtos) {
        this.playerResultDtos = new ArrayList<>(playerResultDtos);
    }

    public static PlayerResultsDto from(Map<String, String> result) {
        return result.entrySet().stream()
                .map(entry -> new PlayerResultDto(entry.getKey(), entry.getValue()))
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        PlayerResultsDto::new
                ));
    }

    public List<PlayerResultDto> getPlayerResultDtos() {
        return new ArrayList<>(playerResultDtos);
    }
}
