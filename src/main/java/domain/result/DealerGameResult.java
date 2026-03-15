package domain.result;

import domain.common.PlayedGameResult;

public class DealerGameResult {

    private PlayedGameResult playedGameResult;

    public void record(PlayedGameResult playedGameResult) {
        this.playedGameResult = playedGameResult;
    }

    public PlayedGameResult playedGameResult() {
        return playedGameResult;
    }
}
