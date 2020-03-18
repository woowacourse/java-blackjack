package blackjack.domain.playing.user;

import blackjack.domain.playing.card.Score;
import blackjack.domain.playing.deck.Deck;
import blackjack.domain.playing.user.exception.UserException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class UserTest {
    private Cards cards;
    private User user;

    private User userWithScore(int scoreValue) {
        cards = mock(Cards.class);

        when(cards.calculateScore()).thenReturn(new Score(scoreValue));

        return new User("무늬", cards);
    }

    @Test
    void isBust() {
        user = userWithScore(22);

        assertThat(user.isBust()).isTrue();
        assertThat(user.isNotBust()).isFalse();
    }

    @Test
    void isNotBust() {
        user = userWithScore(21);

        assertThat(user.isNotBust()).isTrue();
        assertThat(user.isBust()).isFalse();
    }

    @Test
    void drawCardInTurn() {
        User user = new User("무늬", Cards.emptyCards());
        Deck deck = Deck.createWithShuffle();

        user.drawCardsInTurn(deck);

        assertThat(user.getCards().size()).isEqualTo(1);
    }

    @Test
    void drawCardInTurn_NotEmptyCards_ShouldThrowException() {
        assertThatThrownBy(() -> {

            user = userWithCards();
            Deck deck = Deck.createWithShuffle();

            user.drawCardsAtFirst(deck);

        }).isInstanceOf(UserException.class);
    }

    private User userWithCards() {
        cards = mock(Cards.class);

        when(cards.isNotEmpty()).thenReturn(true);

        return new User("무늬", cards);
    }

    @Test
    void calculateScore() {
        userWithCards().calculateScore();

        verify(cards).calculateScore();
    }

    @Test
    void isOverScore() {
        user = userWithScore(21);
        User other = userWithScore(20);

        assertThat(user.isOverScore(other)).isTrue();
        assertThat(other.isOverScore(user)).isFalse();
    }

    @Test
    void isUnderScore() {
        user = userWithScore(10);
        User other = userWithScore(11);

        assertThat(user.isUnderScore(other)).isTrue();
        assertThat(other.isUnderScore(user)).isFalse();
    }

    @Test
    void isSameScore() {
        user = userWithScore(1);
        User otherWithSameScore = userWithScore(1);
        User otherWithDifferentScore = userWithScore(2);

        assertThat(user.isSameScore(otherWithSameScore)).isTrue();
        assertThat(user.isSameScore(otherWithDifferentScore)).isFalse();
    }
}
