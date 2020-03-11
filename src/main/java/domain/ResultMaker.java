package domain;

import java.util.List;

public interface ResultMaker {
    public List<BlackResult> makeBlackjackResult(List<BlackJackPlayer> players);
}
