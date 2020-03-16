package blackjack.domain.user;

import blackjack.domain.card.*;
import blackjack.domain.user.exception.UserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class UserTest {
    @Test
    void isBust() {
        List<Card> cards = Arrays.asList(new Card(Symbol.QUEEN, Type.CLUB),
                new Card(Symbol.KING, Type.HEART),
                new Card(Symbol.TWO, Type.DIAMOND));
        User user = new User("무늬", cards);

        assertThat(user.isBust()).isTrue();
    }

    @Test
    void isNotBust() {
        List<Card> cards = Arrays.asList(new Card(Symbol.QUEEN, Type.CLUB),
                new Card(Symbol.KING, Type.HEART),
                new Card(Symbol.ACE, Type.DIAMOND));
        User user = new User("무늬", cards);

        assertThat(user.isNotBust()).isTrue();
    }

    @Test
    void drawCard() {
        List<Card> cards = new ArrayList<>();
        User user = new User("무늬", cards);
        Deck deck = Deck.create(new ShuffleStrategy());

        user.drawCard(deck);

        assertThat(user.countCards()).isEqualTo(1);
    }

    @Test
    void receiveInitialCards_NotEmptyCards_ShouldThrowException() {
        assertThatThrownBy(() -> {
            List<Card> cards = Arrays.asList(new Card(Symbol.QUEEN, Type.CLUB),
                    new Card(Symbol.KING, Type.HEART));
            User user = new User("무늬", cards);

            Deck deck = Deck.create(new ShuffleStrategy());

            user.receiveInitialCards(deck);
        }).isInstanceOf(UserException.class);
    }

    @Test
    void calculateScore_HasNoAce() {
        List<Card> cards = Arrays.asList(new Card(Symbol.QUEEN, Type.CLUB),
                new Card(Symbol.KING, Type.HEART));
        User user = new User("무늬", cards);

        assertThat(user.calculateScore()).isEqualTo(new Score(20));
    }

    @Test
    void calculateScore_HasAce_ShouldMaximizeAce() {
        List<Card> cards = Arrays.asList(new Card(Symbol.ACE, Type.CLUB),
                new Card(Symbol.KING, Type.HEART));
        User user = new User("무늬", cards);

        assertThat(user.calculateScore()).isEqualTo(new Score(21));
    }

    @Test
    void calculateScore_HasAce_ShouldNotMaximizeAce() {
        List<Card> cards = Arrays.asList(new Card(Symbol.ACE, Type.CLUB),
                new Card(Symbol.KING, Type.HEART),
                new Card(Symbol.QUEEN, Type.CLUB));
        User user = new User("무늬", cards);

        assertThat(user.calculateScore()).isEqualTo(new Score(21));
    }

    @Test
    void isOverScore() {
        List<Card> greaterScoreCards = Arrays.asList(new Card(Symbol.QUEEN, Type.CLUB),
                new Card(Symbol.KING, Type.HEART));
        User greaterScoreUser = new User("무늬", greaterScoreCards);

        List<Card> lessScoreCards = Arrays.asList(new Card(Symbol.QUEEN, Type.CLUB),
                new Card(Symbol.NINE, Type.HEART));
        User lessScoreUser = new User("그니", lessScoreCards);

        assertThat(greaterScoreUser.isOverScore(lessScoreUser)).isTrue();
        assertThat(lessScoreUser.isOverScore(greaterScoreUser)).isFalse();
    }

    @Test
    void isUnderScore() {
        List<Card> lessScoreCards = Arrays.asList(new Card(Symbol.QUEEN, Type.CLUB),
                new Card(Symbol.NINE, Type.HEART));
        User lessScoreUser = new User("무늬", lessScoreCards);

        List<Card> greaterScoreCards = Arrays.asList(new Card(Symbol.QUEEN, Type.CLUB),
                new Card(Symbol.KING, Type.HEART));
        User greaterScoreUser = new User("그니", greaterScoreCards);

        assertThat(lessScoreUser.isUnderScore(greaterScoreUser)).isTrue();
        assertThat(greaterScoreUser.isUnderScore(lessScoreUser)).isFalse();
    }

    @Test
    void isSameScore_ShouldReturnTrue() {
        List<Card> sameCards = Arrays.asList(new Card(Symbol.QUEEN, Type.CLUB),
                new Card(Symbol.KING, Type.HEART));
        User user = new User("무늬", sameCards);
        User other = new User("그니", sameCards);

        assertThat(user.isSameScore(other)).isTrue();
        assertThat(other.isSameScore(user)).isTrue();
    }

    @Test
    void isSameScore_ShouldReturnFalse() {
        List<Card> cards = Arrays.asList(new Card(Symbol.QUEEN, Type.CLUB),
                new Card(Symbol.KING, Type.HEART));
        User user = new User("무늬", cards);
        List<Card> anotherCards = Arrays.asList(new Card(Symbol.QUEEN, Type.CLUB),
                new Card(Symbol.NINE, Type.HEART));
        User other = new User("그니", anotherCards);

        assertThat(user.isSameScore(other)).isFalse();
        assertThat(other.isSameScore(user)).isFalse();
    }

    @Test
    void countCards() {
        List<Card> cards = Arrays.asList(new Card(Symbol.QUEEN, Type.CLUB),
                new Card(Symbol.KING, Type.HEART));
        User user = new User("무늬", cards);

        assertThat(user.countCards()).isEqualTo(2);
    }
}