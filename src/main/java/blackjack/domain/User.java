package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.state.State;
import blackjack.state.StateFactory;
import blackjack.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public abstract class User {
    protected String name;
    protected List<Card> cards;
    protected BettingMoney bettingMoney;
    protected State state;

    protected User(String name) {
        validate(name);
        this.name = StringUtil.deleteWhiteSpaces(name);
        this.cards = new ArrayList<>();
    }

    private void validate(String name) {
        if (StringUtil.isBlank(name)) {
            throw new IllegalArgumentException("빈 값을 입력하셨습니다. 플레이어의 이름을 입력해주세요.");
        }
    }

    public void initialHit(Card firstCard, Card secondCard) {
        if(this.isDealer()){
            this.state = StateFactory.dealerDraw(firstCard, secondCard);
            return;
        }

        this.state = StateFactory.draw(firstCard, secondCard);
    }

    public void hit(Card card) {
        this.state = state.draw(card);
    }

    public State getState() {
        return state;
    }

    public boolean isPlayer() {
        return this instanceof Player;
    }

    public boolean isDealer() {
        return this instanceof Dealer;
    }

    public int getScore() {
        return this.state.cards().score().getScore();
    }

    public List<Card> getCards() {
        return this.state.cards().list();
    }

    public String getName() {
        return this.name;
    }

    public boolean isBlackJack() {
        return state.cards().score().isBlackJack();
    }

    public boolean isBust() {
        return state.cards().score().isBust();
    }

    public void betting(int bettingMoney) {
        this.bettingMoney = new BettingMoney(bettingMoney);
    }

    public int getBettingMoney() {
        return bettingMoney.getBettingMoney();
    }

    public boolean isFinished() {
        return this.state.isFinished();
    }

    public double profit(int bettingMoney, Dealer dealer) {
        return this.state.profit(bettingMoney, dealer);
    }
}
