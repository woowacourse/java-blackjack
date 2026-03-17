package controller.phase;

import controller.GameContext;
import java.util.List;

/**
 * 블랙잭 게임의 준비 단계를 담당한다. 플레이어들과 베팅 금액을 입력 받고, 참가자들에게 카드를 분배한다.
 */
public class PrepareBlackJackPhase implements GamePhase {

    private final List<GamePhase> phases;

    public PrepareBlackJackPhase() {
        phases = List.of(
                new EnterPlayerPhase(),
                new BettingPhase(),
                new InitialDealPhase()
        );
    }

    @Override
    public void execute(GameContext gameContext) {
        for (GamePhase phase : phases) {
            phase.execute(gameContext);
        }
    }
}
