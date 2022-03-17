package domain.player;

import domain.MatchResult;
import domain.card.PlayingCard;
import java.util.ArrayList;
import java.util.List;
import vo.Wallet;

public class Gambler extends Player {
    private static final int MAXIMUM_VALID_SCORE = 21;
    private static final int EMPTY_MONEY = 0;

    public Gambler(Wallet wallet) {
        super(wallet);
    }

    public Gambler(String name) {
        this(Wallet.of(name, EMPTY_MONEY));
    }

    @Override
    public boolean isHittable() {
        return !isBust() && getScore() < MAXIMUM_VALID_SCORE;
    }

    @Override
    public List<PlayingCard> getOpenCards() {
        return new ArrayList<>(playingCards.getCards());
    }

    @Override
    public MatchResult match(Player dealer) {
        if (this.isBust()) {
            return MatchResult.LOSE;
        }
        if (dealer.isBust()) {
            return MatchResult.WIN;
        }
        return getMatchResultAfterBustCheck(dealer);
    }

    @Override
    public boolean isDealer() {
        return false;
    }
}
