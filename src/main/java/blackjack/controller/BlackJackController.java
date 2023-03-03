package blackjack.controller;

import blackjack.view.InputView;
import java.util.List;

public class BlackJackController {

    public void run() {
        List<String> playerNames = InputView.askPlayerNames();
        // TODO outputview 결과 출력
        // TODO 각각 플레이어 처리
    }

    private void hitOrStay(String playerName) {
        InputView.askToTake(playerName);
        // TODO blackjackgame hit or stay
        // TODO outputview 플레이어 보유 카드 출력
    }
}
