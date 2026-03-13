package mapper;

import domain.participant.Player;
import domain.participant.Players;
import dto.PlayerDto;
import dto.PlayersDto;

public class PlayersMapper {

    public static PlayersDto toPlayersDto(Players players) {
        return new PlayersDto(players.getPlayersHand(), players.getPlayersScore());
    }

    public static PlayerDto toPlayerDto(Player player) {
        return new PlayerDto(player.getName(), player.getCards());
    }
}
