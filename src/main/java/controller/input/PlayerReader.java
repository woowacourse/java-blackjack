package controller.input;

import model.paticipant.Player;
import model.paticipant.Players;

public interface PlayerReader<T extends Player> {

    Players<T> readPlayers();

}
