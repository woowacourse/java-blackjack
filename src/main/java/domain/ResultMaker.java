package domain;

import domain.player.BlackJackPlayer;
import domain.result.WinLose;

import java.util.List;

public interface ResultMaker {
    public List<WinLose> makeBlackjackResult(List<BlackJackPlayer> players);
}
