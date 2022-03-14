package domain.player;

import domain.MatchResult;
import domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class Gambler extends Player {
    public Gambler(String name) {
        super(name);
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    @Override
    public boolean canGetMoreCard() {
        return !isBust() && !isBlackJack();
    }

    @Override
    public List<Card> getOpenCards() {
        return new ArrayList<>(cards.getCards());
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
}
