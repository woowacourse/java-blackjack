package blackjack.domain.user;

import blackjack.domain.card.*;
import blackjack.domain.user.exception.UserException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserTest {
    @Test
    void isBust() {
        Cards cards = Cards.from(Arrays.asList(new Card(Symbol.QUEEN, Type.CLUB),
                new Card(Symbol.KING, Type.HEART),
                new Card(Symbol.TWO, Type.DIAMOND)));
        User user = new User("무늬", cards);

        assertThat(user.isBust()).isTrue();
        assertThat(user.isNotBust()).isFalse();
    }

    @Test
    void isNotBust() {
        Cards cards = Cards.from(Arrays.asList(new Card(Symbol.QUEEN, Type.CLUB),
                new Card(Symbol.KING, Type.HEART),
                new Card(Symbol.ACE, Type.DIAMOND)));
        User user = new User("무늬", cards);

        assertThat(user.isNotBust()).isTrue();
        assertThat(user.isBust()).isFalse();
    }

    @Test
    void drawCard() {
        User user = new User("무늬", Cards.emptyCards());
        Deck deck = Deck.createWithShuffle();

        user.drawCardsInTurn(deck);

        assertThat(user.getCards().size()).isEqualTo(1);
    }

    @Test
    void receiveInitialCards_NotEmptyCards_ShouldThrowException() {
        assertThatThrownBy(() -> {
            Cards cards = Cards.from(Arrays.asList(new Card(Symbol.QUEEN, Type.CLUB),
                    new Card(Symbol.KING, Type.HEART)));
            User user = new User("무늬", cards);

            Deck deck = Deck.createWithShuffle();

            user.drawCardsAtFirst(deck);
        }).isInstanceOf(UserException.class);
    }

    @Test
    void calculateScore_HasNoAce() {
        Cards cards = Cards.from(Arrays.asList(new Card(Symbol.QUEEN, Type.CLUB),
                new Card(Symbol.KING, Type.HEART)));
        User user = new User("무늬", cards);

        assertThat(user.calculateScore()).isEqualTo(new Score(20));
    }

    @Test
    void calculateScore_HasAce_ShouldMaximizeAce() {
        Cards cards = Cards.from(Arrays.asList(new Card(Symbol.ACE, Type.CLUB),
                new Card(Symbol.KING, Type.HEART)));
        User user = new User("무늬", cards);

        assertThat(user.calculateScore()).isEqualTo(new Score(21));
    }

    @Test
    void calculateScore_HasAce_ShouldNotMaximizeAce() {
        Cards cards = Cards.from(Arrays.asList(new Card(Symbol.ACE, Type.CLUB),
                new Card(Symbol.KING, Type.HEART),
                new Card(Symbol.QUEEN, Type.CLUB)));
        User user = new User("무늬", cards);

        assertThat(user.calculateScore()).isEqualTo(new Score(21));
    }

    @Test
    void isOverScore() {
        Cards greaterScoreCards = Cards.from(Arrays.asList(new Card(Symbol.QUEEN, Type.CLUB),
                new Card(Symbol.KING, Type.HEART)));
        User greaterScoreUser = new User("무늬", greaterScoreCards);

        Cards lessScoreCards = Cards.from(Arrays.asList(new Card(Symbol.QUEEN, Type.CLUB),
                new Card(Symbol.NINE, Type.HEART)));
        User lessScoreUser = new User("그니", lessScoreCards);

        assertThat(greaterScoreUser.isOverScore(lessScoreUser)).isTrue();
        assertThat(lessScoreUser.isOverScore(greaterScoreUser)).isFalse();
    }

    @Test
    void isUnderScore() {
        Cards lessScoreCards = Cards.from(Arrays.asList(new Card(Symbol.QUEEN, Type.CLUB),
                new Card(Symbol.NINE, Type.HEART)));
        User lessScoreUser = new User("무늬", lessScoreCards);

        Cards greaterScoreCards = Cards.from(Arrays.asList(new Card(Symbol.QUEEN, Type.CLUB),
                new Card(Symbol.KING, Type.HEART)));
        User greaterScoreUser = new User("그니", greaterScoreCards);

        assertThat(lessScoreUser.isUnderScore(greaterScoreUser)).isTrue();
        assertThat(greaterScoreUser.isUnderScore(lessScoreUser)).isFalse();
    }

    @Test
    void isSameScore_ShouldReturnTrue() {
        Cards sameCards = Cards.from(Arrays.asList(new Card(Symbol.QUEEN, Type.CLUB),
                new Card(Symbol.KING, Type.HEART)));
        User user = new User("무늬", sameCards);
        User other = new User("그니", sameCards);

        assertThat(user.isSameScore(other)).isTrue();
        assertThat(other.isSameScore(user)).isTrue();
    }

    @Test
    void isSameScore_ShouldReturnFalse() {
        Cards cards = Cards.from(Arrays.asList(new Card(Symbol.QUEEN, Type.CLUB),
                new Card(Symbol.KING, Type.HEART)));
        User user = new User("무늬", cards);
        Cards anotherCards = Cards.from(Arrays.asList(new Card(Symbol.QUEEN, Type.CLUB),
                new Card(Symbol.NINE, Type.HEART)));
        User other = new User("그니", anotherCards);

        assertThat(user.isSameScore(other)).isFalse();
        assertThat(other.isSameScore(user)).isFalse();
    }

    @Test
    void countCards() {
        Cards cards = Cards.from(Arrays.asList(new Card(Symbol.QUEEN, Type.CLUB),
                new Card(Symbol.KING, Type.HEART)));
        User user = new User("무늬", cards);

        assertThat(user.getCards().size()).isEqualTo(2);
    }
}