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

class AbstractUserTest {
    private Cards cards;
    private AbstractUser abstractUser;

    private AbstractUser userWithScore(int scoreValue) {
        cards = mock(Cards.class);

        when(cards.calculateScore()).thenReturn(new Score(scoreValue));
        when(cards.count()).thenReturn(2);

        return new AbstractUser("무늬", cards);
    }

    @ParameterizedTest
    @CsvSource(value = {"21:true", "22:false"}, delimiter = ':')
    void isBlackjack(int number, boolean result) {
        abstractUser = userWithScore(number);

        assertThat(abstractUser.isBlackjack()).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource(value = {"22:true", "21:false"}, delimiter = ':')
    void isBust(int number, boolean result) {
        abstractUser = userWithScore(number);

        assertThat(abstractUser.isBust()).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource(value = {"21:true", "22:false"}, delimiter = ':')
    void isNotBust(int number, boolean result) {
        abstractUser = userWithScore(number);

        assertThat(abstractUser.isNotBust()).isEqualTo(result);
    }

    @Test
    void drawCardInTurn() {
        AbstractUser abstractUser = new AbstractUser("무늬", Cards.emptyCards());
        Deck deck = Deck.createWithShuffle();

        abstractUser.drawCardsInTurn(deck);

        assertThat(abstractUser.getCards().size()).isEqualTo(1);
    }

    @Test
    void drawCardInTurn_NotEmptyCards_ShouldThrowException() {
        assertThatThrownBy(() -> {

            abstractUser = userWithCards();
            Deck deck = Deck.createWithShuffle();

            abstractUser.drawCardsAtFirst(deck);

        }).isInstanceOf(UserException.class);
    }

    private AbstractUser userWithCards() {
        cards = mock(Cards.class);

        when(cards.isNotEmpty()).thenReturn(true);

        return new AbstractUser("무늬", cards);
    }

    @Test
    void calculateScore() {
        userWithCards().calculateScore();

        verify(cards).calculateScore();
    }

    @ParameterizedTest
    @CsvSource(value = {"20:true", "22:false"}, delimiter = ':')
    void isOverScore(int number, boolean result) {
        abstractUser = userWithScore(21);
        AbstractUser other = userWithScore(number);

        assertThat(abstractUser.isOverScore(other)).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource(value = {"22:true", "20:false"}, delimiter = ':')
    void isUnderScore(int number, boolean result) {
        abstractUser = userWithScore(21);
        AbstractUser other = userWithScore(number);

        assertThat(abstractUser.isUnderScore(other)).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource(value = {"21:true", "22:false"}, delimiter = ':')
    void isSameScore(int number, boolean result) {
        abstractUser = userWithScore(21);
        AbstractUser other = userWithScore(number);

        assertThat(abstractUser.isSameScore(other)).isEqualTo(result);
    }
}
