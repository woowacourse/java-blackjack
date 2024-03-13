package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.cardgame.CardGameResult;
import blackjack.domain.cardgame.WinningStatus;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Dealer extends Player {
    private static final int HIT_CONDITION = 16;

    public Dealer() {
        super("딜러");
    }

    public boolean isMoreCardNeeded() {
        return this.getScore() <= HIT_CONDITION;
    }

    public Card getFirstCard() {
        try {
            return hand.getAllCards().get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException("[ERROR] 딜러가 카드를 갖고 있지 않습니다.");
        }
    }

    public CardGameResult judgeWithPlayers(final List<Player> players) {
        final Map<Player, WinningStatus> result = new LinkedHashMap<>();

        for (final Player player : players) {
            WinningStatus winningStatus = WinningStatus.doesPlayerWin(this, player);
            result.put(player, winningStatus);
        }

        return new CardGameResult(result);
    }
}
