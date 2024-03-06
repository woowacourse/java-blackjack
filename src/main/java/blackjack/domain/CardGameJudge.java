package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class CardGameJudge {

    // TODO : 판단의 책임을 dealer가 담당할 수 있지 않을까?
    public CardGameResult judge(Player dealer, Player player) {
        int dealerScore = dealer.getScore();
        int playerScore = player.getScore();

        // TODO : 이름 바꾸기
        WinningStatus status = isPlayerWin(dealerScore, playerScore);

        Map<Player, WinningStatus> result = new LinkedHashMap<>();
        result.put(player, status);

        return new CardGameResult(result);
    }

    // TODO: 이름 바꾸기
    private WinningStatus isPlayerWin(int score1, int score2) {
        if (score1 == score2) {
            return WinningStatus.PUSH;  // TODO: 도메인 지식 없으면 이해하기 힘듦
        }
        if (score1 < score2) {
            return WinningStatus.WIN;
        }
        return WinningStatus.LOSE;
    }
}
