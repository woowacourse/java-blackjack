package domain.dto;

import domain.Dealer;
import domain.Player;
import java.util.Collections;
import java.util.List;

public class GameStatus {
    private final List<PlayerDto> playerDtos;
    private final DealerDto dealerDto;

    public GameStatus(Dealer dealer, List<Player> players) {
        this.dealerDto = DealerDto.from(dealer);
        this.playerDtos = players.stream()
                .map(PlayerDto::from)
                .toList();
    }

    public List<PlayerDto> getGamerDtos() {
        return Collections.unmodifiableList(playerDtos);
    }

    public PlayerDto getGamerDtoFromName(String name) {
        return playerDtos.stream()
                .filter(gamerDto -> gamerDto.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 참여자 입니다."));
    }

    public DealerDto getDealerDto() {
        return dealerDto;
    }
}
