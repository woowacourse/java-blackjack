package converter;

import domain.Player;
import dto.PlayersDto;
import java.util.List;

public class BlackjackConverter {

    // DTO를 만드는 역할

    public PlayersDto convertPlayersDto(List<Player> players) {
        return new PlayersDto(players);
    }
}
