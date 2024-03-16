package model.game.action;

import model.game.GameResult;
import model.participant.Player;

public interface JudgeAction {

    GameResult judge(Player player);
}
