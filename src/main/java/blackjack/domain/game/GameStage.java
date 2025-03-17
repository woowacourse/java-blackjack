package blackjack.domain.game;

import java.util.List;

public enum GameStage {
    PREPARATION(0),
    PLAYING(1),
    RESULT_OUTPUT(2);

    private final int order;

    GameStage(int order) {
        this.order = order;
    }

    public GameStage next() {
        int nextOrder = this.order + 1;
        List<GameStage> states = List.of(GameStage.values());
        return states.stream()
                .filter(state -> state.order == nextOrder)
                .findAny()
                .orElse(RESULT_OUTPUT);
    }
}
