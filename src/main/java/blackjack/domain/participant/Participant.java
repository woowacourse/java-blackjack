package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import java.util.List;
import java.util.Objects;

public class Participant {

    private static final int FIRST_DRAW_COUNT = 2;

    private final String nickname;
    private final Hand hand;

    protected Participant(String nickname) {
        validate(nickname);
        this.nickname = nickname;
        this.hand = new Hand();
    }

    private void validate(String nickname) {
        if (nickname.isBlank()) {
            throw new IllegalArgumentException("참가자의 이름은 공백이 될 수 없습니다.");
        }
    }

    public void drawInitialCards(Deck deck) {
        List<Card> initialCards = deck.drawCards(FIRST_DRAW_COUNT);
        hand.addCards(initialCards);
    }

    public void receiveCard(Card card) {
        if (!isDrawable()) {
            throw new IllegalStateException("더이상 카드를 받을 수 없는 참가자입니다.");
        }
        hand.addCard(card);
    }

    public String getNickname() {
        return nickname;
    }

    public int getCardsCount() {
        return hand.getSize();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public boolean isDrawable() {
        return hand.isUnderBlackjackScore();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public int getScore() {
        return hand.getTotalScore();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Participant that = (Participant) o;
        return Objects.equals(nickname, that.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname);
    }
}
