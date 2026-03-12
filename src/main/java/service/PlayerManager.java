package service;

import domain.participant.HandCards;
import domain.participant.player.Player;
import domain.participant.player.PlayerGroup;
import domain.vo.Name;
import dto.PlayerInfoDto;

import java.util.ArrayList;
import java.util.List;

public class PlayerManager {
    private PlayerGroup playerGroup;

    public void registerPlayers(List<String> playerNames) {
        List<Player> players = new ArrayList<>();

        for (String playerName : playerNames) {
            Player player = new Player(new Name(playerName), new HandCards());
            players.add(player);
        }

        playerGroup = new PlayerGroup(players);
    }

    public List<Player> getPlayers() {
        return playerGroup.getPlayers();
    }

    public List<PlayerInfoDto> getAllPlayersInfo() {
        List<PlayerInfoDto> playerInfoDtos = new ArrayList<>();

        for (Player player : playerGroup.getPlayers()) {
            playerInfoDtos.add(new PlayerInfoDto(player.getName(), player.getCards(), player.getScore(), player.getWinStatus()));
        }

        return playerInfoDtos;
    }

}
