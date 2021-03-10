package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.state.State;
import blackjack.state.StateFactory;
import blackjack.util.BlackJackConstant;
import blackjack.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
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

    public void hit(Card firstCard, Card secondCard) {
        this.state = StateFactory.draw(firstCard, secondCard);
    }

    public void hit(Card card) {
        cards.add(card);
    }

    public boolean isPlayer() {
        return this instanceof Player;
    }

    public boolean isDealer() {
        return this instanceof Dealer;
    }

    public int getScore() {
        if (this.isBlackJack()) {
            return BlackJackConstant.BLACKJACK_SCORE;
        }

        int score = this.cards.stream()
                .mapToInt(Card::getScore)
                .sum();

        score += checkAce(score);

        return checkBust(score);
    }

    public List<Card> getCards() {
        return this.state.cards().getCards();
    }

    public String getName() {
        return this.name;
    }

    public boolean isBlackJack() {
        if (this.cards.stream().anyMatch(Card::isAce) &&
                this.cards.size() == BlackJackConstant.BLACKJACK_SIZE_CONDITION) {
            return cards.stream()
                    .anyMatch(card -> card.getScore() == BlackJackConstant.TEN_SCORE);
        }
        return false;
    }

    public boolean isBust() {
        return this.getScore() == BlackJackConstant.BUST;
    }

    private int checkBust(int score) {
        if (score > BlackJackConstant.BLACKJACK) {
            return BlackJackConstant.BUST;
        }
        return score;
    }

    private int checkAce(int score) {
        if (this.cards.stream()
                .anyMatch(Card::isAce) && score <= BlackJackConstant.ACE_CHECK_SCORE) {
            return BlackJackConstant.TEN_SCORE;
        }
        return 0;
    }

    public void bettingMoney(int bettingMoney){
        this.bettingMoney = new BettingMoney(bettingMoney);
    }

    public int getBettingMoney() {
        return bettingMoney.getBettingMoney();
    }
}
