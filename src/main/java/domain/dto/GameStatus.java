package domain.dto;

import domain.Dealer;
import domain.Player;
import java.util.Collections;
import java.util.List;

public class GameStatus {
    private final List<GamerDto> gamerDtos;
    private final GamerDto dealerDto;

    public GameStatus(Dealer dealer, List<Player> players) {
        this.dealerDto = GamerDto.fromDealer(dealer);
        this.gamerDtos = players.stream()
                .map(GamerDto::fromPlayer)
                .toList();
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
