package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CardGameJudge {

    public static final int BUST_CONDITION = 21;

    public CardGameResult judge(final Player dealer, final List<Player> players) {
        Map<Player, WinningStatus> result = new LinkedHashMap<>();

        for (final Player player : players) {
            WinningStatus winningStatus = judge(dealer, player);
            result.put(player, winningStatus);
        }

        return new CardGameResult(result);
    }

    // TODO : 판단의 책임을 dealer가 담당할 수 있지 않을까?
    private WinningStatus judge(final Player dealer, final Player player) {
        int dealerScore = dealer.getScore();
        int playerScore = player.getScore();

        return doesPlayerWin(dealerScore, playerScore);
    }

    // TODO: 이름 바꾸기
    // TODO: 정리
    private WinningStatus doesPlayerWin(final int dealerScore, final int playerScore) {
        if (playerScore > BUST_CONDITION) {
            return WinningStatus.LOSE;
        }
        if (dealerScore > BUST_CONDITION) {
            return WinningStatus.WIN;
        }
        if (dealerScore == playerScore) {
            return WinningStatus.PUSH;  // TODO: 도메인 지식 없으면 이해하기 힘듦
        }
        if (dealerScore < playerScore) {
            return WinningStatus.WIN;
        }
        return WinningStatus.LOSE;
    }
}
