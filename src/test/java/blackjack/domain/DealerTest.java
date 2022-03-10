package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

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
        Gamer dealer = new Dealer();
        dealer.hit(new Card(CardNumber.SEVEN, Type.CLOVER));
        dealer.hit(new Card(CardNumber.TEN, Type.SPADE));

        assertThat(dealer.isValidRange()).isFalse();
    }

    @Test
    @DisplayName("hit 메서드는 입력받은 카드를 카드뭉치에 저장한다.")
    void hit_test() {
        Gamer gamer = new Dealer();
        gamer.hit(new Card(CardNumber.EIGHT, Type.CLOVER));
        gamer.hit(new Card(CardNumber.FIVE, Type.SPADE));
        gamer.hit(new Card(CardNumber.TEN, Type.DIAMOND));
        List<Card> cards = gamer.getCards().get();

        assertThat(cards.get(0)).isEqualTo(new Card(CardNumber.EIGHT, Type.CLOVER));
        assertThat(cards.get(1)).isEqualTo(new Card(CardNumber.FIVE, Type.SPADE));
        assertThat(cards.get(2)).isEqualTo(new Card(CardNumber.TEN, Type.DIAMOND));
    }

    @Test
    @DisplayName("Dealer 클래스가 가진 카드의 점수를 계산하여 반환한다.")
    void get_score() {
        Gamer gamer = new Dealer();
        gamer.hit(new Card(CardNumber.EIGHT, Type.CLOVER));
        gamer.hit(new Card(CardNumber.THREE, Type.SPADE));

        assertThat(gamer.getScore()).isEqualTo(11);
    }

    @Test
    @DisplayName("Ace 가진 카드의 총합이 21을 넘지 않으면 Ace는 11로 계산한다.")
    void ace_calculate_11() {
        Gamer gamer = new Dealer();
        gamer.hit(new Card(CardNumber.TEN, Type.CLOVER));
        gamer.hit(new Card(CardNumber.ACE, Type.SPADE));

        assertThat(gamer.getScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("Ace 가진 카드의 총합이 21을 넘으면 Ace는 1로 계산한다.")
    void ace_calculate_1() {
        Gamer gamer = new Dealer();
        gamer.hit(new Card(CardNumber.TEN, Type.CLOVER));
        gamer.hit(new Card(CardNumber.THREE, Type.HEART));
        gamer.hit(new Card(CardNumber.ACE, Type.SPADE));

        assertThat(gamer.getScore()).isEqualTo(14);
    }

    @Test
    @DisplayName("dealer의 compare 메서드는 플레이어가 Bust라면 무조건 딜러가 승리했다고 판단한다.")
    void compare_player_bust() {
        Gamer dealer = new Dealer();
        dealer.hit(new Card(CardNumber.SEVEN, Type.CLOVER));
        dealer.hit(new Card(CardNumber.TEN, Type.SPADE));

        Gamer player = new Player(new Name("aki"));
        player.hit(new Card(CardNumber.TEN, Type.CLOVER));
        player.hit(new Card(CardNumber.TEN, Type.SPADE));
        player.hit(new Card(CardNumber.TEN, Type.DIAMOND));

        assertThat(dealer.compare(player)).isPositive();
    }

    @Test
    @DisplayName("dealer의 compare 메서드는 딜러 Bust라면 딜러가 패배했다고 판단한다.")
    void compare_dealer_bust() {
        Gamer dealer = new Dealer();
        dealer.hit(new Card(CardNumber.SEVEN, Type.CLOVER));
        dealer.hit(new Card(CardNumber.FIVE, Type.SPADE));
        dealer.hit(new Card(CardNumber.TEN, Type.DIAMOND));

        Gamer player = new Player(new Name("aki"));
        player.hit(new Card(CardNumber.TEN, Type.CLOVER));
        player.hit(new Card(CardNumber.TEN, Type.SPADE));

        assertThat(dealer.compare(player)).isNegative();
    }

    @Test
    @DisplayName("dealer의 compare 메서드는 딜러가 블랙잭이고 플레이어가 블랙잭이 아니라면 딜러가 이겼다고 판단한다.")
    void compare_dealer_blackjack() {
        Gamer dealer = new Dealer();
        dealer.hit(new Card(CardNumber.ACE, Type.CLOVER));
        dealer.hit(new Card(CardNumber.KING, Type.SPADE));

        Gamer player = new Player(new Name("aki"));
        player.hit(new Card(CardNumber.ACE, Type.CLOVER));
        player.hit(new Card(CardNumber.TEN, Type.DIAMOND));

        assertThat(dealer.compare(player)).isPositive();
    }

    @Test
    @DisplayName("dealer의 compare 메서드는 플레이어가 블랙잭이고 딜러가 블랙잭이 아니라면 딜러가 졌다고 판단한다.")
    void compare_player_blackjack() {
        Gamer dealer = new Dealer();
        dealer.hit(new Card(CardNumber.ACE, Type.CLOVER));
        dealer.hit(new Card(CardNumber.TEN, Type.SPADE));

        Gamer player = new Player(new Name("aki"));
        player.hit(new Card(CardNumber.ACE, Type.CLOVER));
        player.hit(new Card(CardNumber.JACK, Type.DIAMOND));

        assertThat(dealer.compare(player)).isNegative();
    }

    @Test
    @DisplayName("dealer의 compare 메서드는 딜러와 플레이어 모두 블랙잭이라면 무승부라고 판단한다.")
    void compare_player_and_dealer_blackjack() {
        Gamer dealer = new Dealer();
        dealer.hit(new Card(CardNumber.ACE, Type.CLOVER));
        dealer.hit(new Card(CardNumber.KING, Type.SPADE));

        Gamer player = new Player(new Name("aki"));
        player.hit(new Card(CardNumber.ACE, Type.CLOVER));
        player.hit(new Card(CardNumber.JACK, Type.DIAMOND));

        assertThat(dealer.compare(player)).isZero();
    }

    @Test
    @DisplayName("dealer의 compare 메서드는 딜러와 플레이어의 점수를 비교하여 승무패를 판단한다.")
    void compare_player_and_dealer() {
        Gamer dealer = new Dealer();
        dealer.hit(new Card(CardNumber.FIVE, Type.CLOVER));
        dealer.hit(new Card(CardNumber.TEN, Type.SPADE));
        dealer.hit(new Card(CardNumber.THREE, Type.DIAMOND));

        Gamer player = new Player(new Name("aki"));
        player.hit(new Card(CardNumber.SEVEN, Type.CLOVER));
        player.hit(new Card(CardNumber.TEN, Type.DIAMOND));

        assertThat(dealer.compare(player)).isPositive();
    }
}
