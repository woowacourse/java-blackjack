package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.withSettings;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import blackjack.domain.card.deck.OnlyTenSpadePickDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

public class UserTest {

    @Test
    @DisplayName("takeInitHand 메서드는 2장의 카드를 뽑는다.")
    void take_init_hand() {
        User user = mock(User.class, withSettings().useConstructor(new UserName("aki")).defaultAnswer(CALLS_REAL_METHODS));
        user.takeInitHand(new OnlyTenSpadePickDeck());

        assertThat(user.getCards().get()).hasSize(2);
    }

    @Test
    @DisplayName("User 클래스가 가진 카드의 점수가 21 이상이면 더이상 hit할 수 없다.")
    void validate_range() {
        User user = mock(User.class, withSettings().useConstructor(new UserName("aki")).defaultAnswer(CALLS_REAL_METHODS));
        user.hit(new Card(CardNumber.EIGHT, CardType.CLOVER));
        user.hit(new Card(CardNumber.FIVE, CardType.SPADE));
        user.hit(new Card(CardNumber.TEN, CardType.SPADE));

        assertThat(user.isValidRange()).isFalse();
    }

    @Test
    @DisplayName("hit 메서드는 입력받은 카드를 카드뭉치에 저장한다.")
    void hit_test() {
        User user = mock(User.class, withSettings().useConstructor(new UserName("aki")).defaultAnswer(CALLS_REAL_METHODS));
        user.hit(new Card(CardNumber.EIGHT, CardType.CLOVER));
        user.hit(new Card(CardNumber.FIVE, CardType.SPADE));
        user.hit(new Card(CardNumber.TEN, CardType.SPADE));
        List<Card> hand = user.getCards().get();

        assertThat(hand.get(0)).isEqualTo(new Card(CardNumber.EIGHT, CardType.CLOVER));
        assertThat(hand.get(1)).isEqualTo(new Card(CardNumber.FIVE, CardType.SPADE));
        assertThat(hand.get(2)).isEqualTo(new Card(CardNumber.TEN, CardType.SPADE));
    }

    @Test
    @DisplayName("Gamer 클래스가 가진 카드의 점수를 계산하여 반환한다.")
    void get_score() {
        User user = mock(User.class, withSettings().useConstructor(new UserName("aki")).defaultAnswer(CALLS_REAL_METHODS));
        user.hit(new Card(CardNumber.EIGHT, CardType.CLOVER));
        user.hit(new Card(CardNumber.THREE, CardType.SPADE));

        assertThat(user.getScore()).isEqualTo(11);
    }

    @Test
    @DisplayName("Ace 가진 카드의 총합이 21을 넘지 않으면 Ace는 11로 계산한다.")
    void ace_calculate_11() {
        User user = mock(User.class, withSettings().useConstructor(new UserName("aki")).defaultAnswer(CALLS_REAL_METHODS));
        user.hit(new Card(CardNumber.TEN, CardType.CLOVER));
        user.hit(new Card(CardNumber.ACE, CardType.SPADE));

        assertThat(user.getScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("Ace 가진 카드의 총합이 21을 넘으면 Ace는 1로 계산한다.")
    void ace_calculate_1() {
        User user = mock(User.class, withSettings().useConstructor(new UserName("aki")).defaultAnswer(CALLS_REAL_METHODS));
        user.hit(new Card(CardNumber.TEN, CardType.CLOVER));
        user.hit(new Card(CardNumber.THREE, CardType.HEART));
        user.hit(new Card(CardNumber.ACE, CardType.SPADE));

        assertThat(user.getScore()).isEqualTo(14);
    }
}
