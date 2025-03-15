package participant;

import card.Card;
import card.Cards;
import score.Score;

import java.util.List;
import java.util.Objects;

public class Player {

    private static final int HIT_CONDITION = 21;

    private final Cards cards;
    private final Nickname nickname;

    public Player(String nickname) {
        this.cards = new Cards();
        this.nickname = new Nickname(nickname);
    }

    public void addCard(final Card findCard) {
        cards.add(findCard);
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public void applyAceRule() {
        cards.applyAceRule();
    }

    public Score getScore() {
        return cards.calculateScore();
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public boolean canHit() {
        return cards.isHit(HIT_CONDITION);
    }

    public String getNickname() {
        return nickname.getValue();
    }

    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(nickname, player.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nickname);
    }
}
