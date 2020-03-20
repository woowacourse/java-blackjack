package domain;

import common.PlayersDto;

import java.util.List;

public interface UserInterface {
    String inputWantToHit(String playerName);
    PlayersDto inputPlayers();
    List<String> inputPlayerNames();
    int inputBettingMoney(String playerName);
}
