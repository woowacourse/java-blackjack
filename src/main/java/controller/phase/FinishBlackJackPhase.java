package controller.phase;

import controller.GameContext;
import java.util.List;

/**
 * 블랙잭 게임의 마지막 단계를 담당한다. 내부적으로 점수 계산과 베팅 결과 출력 단계를 순차적으로 실행한다.
 */
public class FinishBlackJackPhase implements GamePhase {

    private final List<GamePhase> phases;

    public FinishBlackJackPhase() {
        phases = List.of(
                new CalculateScorePhase(),
                new ShowBettingResultPhase()
        );
    }

    @Override
    public void execute(GameContext gameContext) {
        for (GamePhase phase : phases) {
            phase.execute(gameContext);
        }
    }
}
