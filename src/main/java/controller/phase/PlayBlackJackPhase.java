package controller.phase;

import controller.GameContext;
import java.util.List;

/**
 * 블랙잭 게임의 진행 단계를 담당한다. 현재 카드를 출력하며, 플레이어의 행동과 딜러의 행동을 실행한다.
 */
public class PlayBlackJackPhase implements GamePhase {

    private final List<GamePhase> phases;

    public PlayBlackJackPhase() {
        phases = List.of(
                new PrintParticipantsCardPhase(),
                new PlayerTurnPhase(),
                new DealerTurnPhase()
        );
    }

    @Override
    public void execute(GameContext gameContext) {
        for (GamePhase phase : phases) {
            phase.execute(gameContext);
        }
    }
}
