package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    @DisplayName("Player 클래스는 이름을 입력받으면 정상적으로 생성된다.")
    void create_player() {
        UserName userName = new UserName("aki");

        assertThatCode(() -> new Player(userName)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("isValidRange 메서드는 카드의 총합이 20 이하면 true를 반환한다.")
    void validate_range_true() {
        Player player = new Player(new UserName("aki"));
        player.hit(Card.of(CardNumber.TEN, CardType.SPADE));
        player.hit(Card.of(CardNumber.TEN, CardType.DIAMOND));

        assertThat(player.isValidRange()).isTrue();
    }

    @Test
    @DisplayName("isValidRange 메서드는 카드의 총합이 21 이상이면 false를 반환한다.")
    void validate_range_false() {
        Player player = new Player(new UserName("aki"));
        player.hit(Card.of(CardNumber.SEVEN, CardType.CLOVER));
        player.hit(Card.of(CardNumber.FIVE, CardType.SPADE));
        player.hit(Card.of(CardNumber.TEN, CardType.DIAMOND));

        assertThat(player.isValidRange()).isFalse();
    }

}
