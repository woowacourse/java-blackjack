package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;
import java.util.Collections;
import java.util.List;

public class Player implements Participant {
    private final Name name;
    private final Cards cards;
    private final BettingMoney bettingMoney;

    public Player(Name name) {
        this(name, new Cards(Collections.emptyList()), new BettingMoney(0));
    }

    public Player(Name name, BettingMoney bettingMoney) {
        this(name, new Cards(Collections.emptyList()), bettingMoney);
    }

    public Player(Name name, Cards cards) {
        this(name, cards, new BettingMoney(0));
    }

    public Player(Name name, Cards cards, BettingMoney bettingMoney) {
        this.name = name;
        this.cards = cards;
        this.bettingMoney = bettingMoney;
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }

    @Override
    public boolean isNotAbleToTake() {
        return sumCards().isBurst();
    }

    @Override
    public void takeCard(Card card) {
        cards.takeCard(card);
    }

    @Override
    public Name getName() {
        return this.name;
    }

    @Override
    public Score sumCards() {
        return cards.sumCards();
    }

    @Override
    public Score sumCardsForResult() {
        return cards.sumCardsForResult();
    }

    @Override
    public Cards getCards() {
        return cards;
    }

    @Override
    public List<Card> getUnmodifiableCards() {
        return cards.getUnmodifiableList();
    }

}
