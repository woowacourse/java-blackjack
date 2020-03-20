package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.gambler.Dealer;
import blackjack.domain.result.CardsResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserCardsTest {

    private UserCards userCards;

    @BeforeEach
    void resetVariable() {
        userCards = new UserCards();
    }

    @DisplayName("Null 추가시 예외 발생")
    @Test
    void add_AddNull_ThrowException() {
        assertThatThrownBy(() -> userCards.add(null)).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Null");
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
        assertThat(userCards.getResult()).isEqualTo(new CardsResult(14, true, 1));

        userCards = new UserCards();
        userCards.add(new Card(Type.DIAMOND, Symbol.ACE));
        userCards.add(new Card(Type.CLUB, Symbol.ACE));
        userCards.add(new Card(Type.CLUB, Symbol.TWO));
        assertThat(userCards.getResult()).isEqualTo(new CardsResult(4, true, 1));
    }

    @DisplayName("카드 없을경우 예외")
    @Test
    void validNoCard() {
        Dealer dealer = new Dealer();
        assertThatThrownBy(dealer::getFirstCardInfo).isInstanceOf(NullPointerException.class)
            .hasMessageContaining("없");
        assertThatThrownBy(dealer::getCardsInfos).isInstanceOf(NullPointerException.class)
            .hasMessageContaining("없");
    }
}

