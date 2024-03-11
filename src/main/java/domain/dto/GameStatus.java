package domain.dto;

import domain.BlackJackGame;
import java.util.Collections;
import java.util.List;

public class GameStatus {
    private final List<GamerDto> gamerDtos;
    private final GamerDto dealerDto;

    public GameStatus(BlackJackGame blackJackGame) {
        this.gamerDtos = blackJackGame.getPlayers().stream()
                .map(GamerDto::fromPlayer)
                .toList();
        this.dealerDto = GamerDto.fromDealer(blackJackGame.getDealer());
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
