package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.result.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserCardsTest {

    private UserCards userCards;

    @BeforeEach
    void resetVariable() {
        userCards = new UserCards();
    }

    @DisplayName("같은 카드 추가시 예외 발생")
    @Test
    void add() {
        Card card = new Card(Type.DIAMOND, Symbol.ACE);
        userCards.add(card);
        assertThatThrownBy(() -> userCards.add(card)).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("중복");
    }

    @DisplayName("카드 숫자 합계")
    @Test
    void getTotalSum() {
        userCards.add(new Card(Type.DIAMOND, Symbol.ACE));
        userCards.add(new Card(Type.SPADE, Symbol.ACE));
        userCards.add(new Card(Type.HEART, Symbol.ACE));
        userCards.add(new Card(Type.CLUB, Symbol.ACE));
        userCards.add(new Card(Type.CLUB, Symbol.JACK));
        assertThat(userCards.getScore()).isEqualTo(new Score(14, true, 1));

        userCards = new UserCards();
        userCards.add(new Card(Type.DIAMOND, Symbol.ACE));
        userCards.add(new Card(Type.CLUB, Symbol.ACE));
        userCards.add(new Card(Type.CLUB, Symbol.TWO));
        assertThat(userCards.getScore()).isEqualTo(new Score(4, true, 1));
    }
}

