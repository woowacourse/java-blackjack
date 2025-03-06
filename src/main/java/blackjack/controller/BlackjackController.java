package blackjack.controller;

import blackjack.domain.GameManager;
import blackjack.domain.Players;

public class BlackjackController {

    // 출력
    // 입력
    private final GameManager gameManager;

    public BlackjackController(final GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void start() {
        // todo : 게임 참여자 추가 (기능)
        // todo : 게임 참여 닉네임 입력
        // todo : 닉네임 파싱

        // todo : gameManager.addGamblers(파싱한 데이터);

        Players players = gameManager.getPlayers();

        // todo : 게임 참여자 카드 공개

        // todo : 추가 발급 기능 (기능)

    }
}
