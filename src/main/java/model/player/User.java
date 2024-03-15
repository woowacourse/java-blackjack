package model.player;

import java.util.List;
import java.util.Objects;
import model.Outcome;
import model.card.Card;
import model.card.Cards;

public abstract class User {

    protected final Name name;
    protected Cards cards;

    public User(Name name, List<Card> cards) {
        this.name = name;
        this.cards = new Cards(cards);
    }

    public void addCards(List<Card> card) { //TODO Card...를 사용해서 리팩토링 해보기
        cards.addCards(card);
    }

    public void addCard(Card card) {
        cards.addCard(card);
    }

    public int calculateScore() {
        return cards.calculateScore();
    }

    public boolean isBlackJack() {
        return cards.isBlackJack();
    }

    public boolean isNotBlackJack() {
        return !cards.isBlackJack();
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public boolean isNotBust() {
        return cards.isNotBust();
    }

    public int findPlayerDifference() {
        return cards.findPlayerDifference();
    }

    // todo 21과 어떤 것이 더 가까운지 확인하는 부분은 User에 두고 버스트 여부로 파악하는 건 Participant에 둔 것이 이상함
    protected Outcome findPlayerOutcome(int otherDifference) {
        int difference = findPlayerDifference();
        if (otherDifference > difference) {
            return Outcome.WIN;
        }
        if (otherDifference < difference) {
            return Outcome.LOSE;
        }
        return Outcome.DRAW;
    }

    public Name getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
