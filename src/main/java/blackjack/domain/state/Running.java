package blackjack.domain.state;

import blackjack.domain.card.PlayingCards;
import blackjack.domain.player.Player;
import blackjack.domain.result.Match;

public abstract class Running extends Started {

    private static final String PROFIT_ERROR_GUID_MESSAGE = "[Error]: 수익 결과는 게임이 종료된 후에 안내됩니다.";

    public Running(PlayingCards playingCards) {
        super(playingCards);
    }

    @Override
    public final boolean isFinished() {
        return false;
    }

    @Override
    public final Match matchResult(Player player) {
        throw new IllegalStateException(PROFIT_ERROR_GUID_MESSAGE);
    }
}
