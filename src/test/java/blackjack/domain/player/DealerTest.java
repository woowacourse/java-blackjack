package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Type;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    @DisplayName("Dealer 클래스는 딜러라는 이름을 가지고 정상적으로 생성된다.")
    void create_dealer() {
        String name = "딜러";
        Dealer dealer = new Dealer();

        assertThat(dealer.getName().get()).isEqualTo(name);
    }

    @Test
    @DisplayName("isValidRange 메서드는 카드의 총합이 17이상인지 검사한다.")
    void validate_range() {
        Player dealer = new Dealer();
        dealer.hit(new Card(CardNumber.SEVEN, Type.CLOVER));
        dealer.hit(new Card(CardNumber.TEN, Type.SPADE));

        assertThat(dealer.isValidRange()).isFalse();
    }

    @Test
    @DisplayName("hit 메서드는 입력받은 카드를 카드뭉치에 저장한다.")
    void hit_test() {
        Player player = new Dealer();
        player.hit(new Card(CardNumber.EIGHT, Type.CLOVER));
        player.hit(new Card(CardNumber.FIVE, Type.SPADE));
        player.hit(new Card(CardNumber.TEN, Type.DIAMOND));
        List<Card> cards = player.getCards().get();

        assertThat(cards.get(0)).isEqualTo(new Card(CardNumber.EIGHT, Type.CLOVER));
        assertThat(cards.get(1)).isEqualTo(new Card(CardNumber.FIVE, Type.SPADE));
        assertThat(cards.get(2)).isEqualTo(new Card(CardNumber.TEN, Type.DIAMOND));
    }

    @Test
    @DisplayName("Dealer 클래스가 가진 카드의 점수를 계산하여 반환한다.")
    void get_score() {
        Player player = new Dealer();
        player.hit(new Card(CardNumber.EIGHT, Type.CLOVER));
        player.hit(new Card(CardNumber.THREE, Type.SPADE));

        assertThat(player.getScore()).isEqualTo(11);
    }

    @Test
    @DisplayName("Ace 가진 카드의 총합이 21을 넘지 않으면 Ace는 11로 계산한다.")
    void ace_calculate_11() {
        Player player = new Dealer();
        player.hit(new Card(CardNumber.TEN, Type.CLOVER));
        player.hit(new Card(CardNumber.ACE, Type.SPADE));

        assertThat(player.getScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("Ace 가진 카드의 총합이 21을 넘으면 Ace는 1로 계산한다.")
    void ace_calculate_1() {
        Player player = new Dealer();
        player.hit(new Card(CardNumber.TEN, Type.CLOVER));
        player.hit(new Card(CardNumber.THREE, Type.HEART));
        player.hit(new Card(CardNumber.ACE, Type.SPADE));

        assertThat(player.getScore()).isEqualTo(14);
    }
}
