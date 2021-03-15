package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;
import blackjack.domain.state.Hit;
import blackjack.domain.state.State;
import java.util.Collections;

public class Player implements Participant {
    private static final String SAMPLE_NAME = "a";
    private final Name name;
    private final BettingMoney bettingMoney;
    private State state;

    public Player() {
        this(SAMPLE_NAME, new Cards(Collections.emptyList()), BettingMoney.ZERO);
    }

    public Player(String name) {
        this(name, new Cards(Collections.emptyList()), BettingMoney.ZERO);
    }

    public Player(String name, String bettingMoney) {
        this(name, new Cards(Collections.emptyList()), new BettingMoney(bettingMoney));
    }

    public Player(long bettingMoney) {
        this(SAMPLE_NAME, new Cards(Collections.emptyList()), new BettingMoney(bettingMoney));
    }

    public Player(String name, Cards cards) {
        this(name, cards, BettingMoney.ZERO);
    }

    public Player(String name, Cards cards, BettingMoney bettingMoney) {
        this.name = new Name(name);
        this.bettingMoney = bettingMoney;
        this.state = new Hit(cards);
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }

    @Override
    public boolean isAbleToTake() {
        return !state.isBurst();
    }

    @Override
    public String getName() {
        return this.name.toString();
    }

    @Override
    public void takeCard(Card card) {
        state = state.takeCard(card);
    }

    @Override
    public boolean isBlackjack() {
        return state.isBlackjack();
    }

    @Override
    public boolean isBurst() {
        return state.isBurst();
    }

    @Override
    public Score finalScore() {
        return state.calculateScore();
    }

    @Override
    public Cards getCards() {
        return state.getCards();
    }

    @Override
    public int sizeOfCards() {
        return state.size();
    }

}
