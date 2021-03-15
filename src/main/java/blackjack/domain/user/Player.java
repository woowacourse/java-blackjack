package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

import java.util.List;

public class Player extends User {

    private final double bettingMoney;

    public Player(String name, double bettingMoney, List<Card> cards) {
        super(name, cards);
        this.bettingMoney = bettingMoney;
    }

    @Override
    public boolean draw(Deck deck) {
        hand.addCard(deck.pickSingleCard());
        return true;
    }


    public BettingResult computeBettingResult(MatchResult matchResult) {
        double earningMoney = matchResult.calculateEarningMoney(bettingMoney);
        return new BettingResult(this.name, earningMoney);
    }
}

