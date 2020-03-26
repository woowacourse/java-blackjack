package domain;

import common.PlayersDto;
import domain.user.Player;

import java.util.List;

public interface UserInterface {
    String inputWantToHit(String playerName);
    List<String> inputPlayerNames();
    int inputBettingMoney(String playerName);
    void showCurrentStateOfPlayer(Player player);
}
