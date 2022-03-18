package blackjack.dto;

import blackjack.domain.result.Match;
import blackjack.domain.result.PlayerResult;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

public class PlayerResultDto {
    private final String name;
    private final String result;

    public PlayerResultDto(String name, String result) {
        this.name = name;
        this.result = result;
    }

    public static List<PlayerResultDto> of(PlayerResult playerResult) {
        List<PlayerResultDto> playersResultDto = new ArrayList<>();
        for (Entry<String, Match> playerMatch : playerResult.get().entrySet()) {
            playersResultDto.add(new PlayerResultDto(playerMatch.getKey(), playerMatch.getValue().getResult()));
        }
        return Collections.unmodifiableList(playersResultDto);
    }

    public String getName() {
        return name;
    }

    public String getResult() {
        return result;
    }
}
