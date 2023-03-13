package blackjack.domain.participant.comparator;

import blackjack.domain.game.WinTieLose;
import blackjack.domain.participant.Player;

public class NoCaseComparator implements Comparator{

    private static final String NO_CASE_MASSAGE = "더이상의 경우의 수가 없습니다";

    @Override
    public void setNext() {
    }

    @Override
    public WinTieLose compareWithPlayer(Player player) {
        throw new NullPointerException(NO_CASE_MASSAGE);
    }
}
