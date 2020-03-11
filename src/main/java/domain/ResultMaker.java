package domain;

import domain.player.BlackJackPlayer;

import java.util.List;

public interface ResultMaker {
    public List<BlackResult> makeBlackjackResult(List<BlackJackPlayer> players);
}
