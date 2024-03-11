package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.cardgame.CardGameResult;
import blackjack.domain.cardgame.WinningStatus;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Dealer extends Player {
    public static final int BUST_THRESHOLD = 21;
    private static final int HIT_THRESHOLD = 16;

    public Dealer() {
        super("딜러");
    }

    public boolean isMoreCardNeeded() {
        return this.hand.getScore() <= HIT_THRESHOLD;
    }

    public Card getFirstCard() {
        try {
            return hand.getAllCards().get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException("[ERROR] 딜러가 카드를 갖고 있지 않습니다.");
        }
    }

    public CardGameResult judgeWithPlayers(final List<Player> players) {
        Map<Player, WinningStatus> result = new LinkedHashMap<>();

        for (final Player player : players) {
            WinningStatus winningStatus = doesPlayerWin(this.getScore(), player.getScore());
            result.put(player, winningStatus);
        }

        return new CardGameResult(result);
    }

    private WinningStatus doesPlayerWin(final int dealerScore, final int playerScore) {
        if (playerScore > BUST_THRESHOLD) {
            return WinningStatus.LOSE;
        }
        if (dealerScore > BUST_THRESHOLD) {
            return WinningStatus.WIN;
        }
        if (dealerScore == playerScore) {
            return WinningStatus.PUSH;
        }
        if (dealerScore < playerScore) {
            return WinningStatus.WIN;
        }
        return WinningStatus.LOSE;
    }
}
