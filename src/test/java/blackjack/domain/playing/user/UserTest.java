package blackjack.domain.playing.user;

import blackjack.domain.playing.card.Score;
import blackjack.domain.playing.deck.Deck;
import blackjack.domain.playing.user.exception.UserException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @ParameterizedTest
    @CsvSource(value = {"21:true", "22:false"}, delimiter = ':')
    void isBlackjack(int number, boolean result) {
        user = userWithScore(number);

        assertThat(user.isBlackjack()).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource(value = {"22:true", "21:false"}, delimiter = ':')
    void isBust(int number, boolean result) {
        user = userWithScore(number);

        assertThat(user.isBust()).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource(value = {"21:true", "22:false"}, delimiter = ':')
    void isNotBust(int number, boolean result) {
        user = userWithScore(number);

        assertThat(user.isNotBust()).isEqualTo(result);
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

    @ParameterizedTest
    @CsvSource(value = {"20:true", "22:false"}, delimiter = ':')
    void isOverScore(int number, boolean result) {
        user = userWithScore(21);
        User other = userWithScore(number);

        assertThat(user.isOverScore(other)).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource(value = {"22:true", "20:false"}, delimiter = ':')
    void isUnderScore(int number, boolean result) {
        user = userWithScore(21);
        User other = userWithScore(number);

        assertThat(user.isUnderScore(other)).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource(value = {"21:true", "22:false"}, delimiter = ':')
    void isSameScore(int number, boolean result) {
        user = userWithScore(21);
        User other = userWithScore(number);

        assertThat(user.isSameScore(other)).isEqualTo(result);
    }
}
