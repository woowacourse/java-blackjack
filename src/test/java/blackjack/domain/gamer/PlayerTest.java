package blackjack.domain.gamer;

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
        PlayerName playerName = new PlayerName("aki");

        assertThatCode(() -> new Player(playerName)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("isValidRange 메서드는 카드의 총합이 21이상인지 검사한다.")
    void validate_range() {
        Gamer gamer = new Player(new PlayerName("aki"));
        gamer.hit(new Card(CardNumber.EIGHT, CardType.CLOVER));
        gamer.hit(new Card(CardNumber.FIVE, CardType.SPADE));
        gamer.hit(new Card(CardNumber.TEN, CardType.DIAMOND));

        assertThat(gamer.isValidRange()).isFalse();
    }

    @Test
    @DisplayName("player의 compare 메서드는 플레이어가 Bust라면 무조건 패배했다고 판단한다.")
    void compare_player_bust() {
        Gamer dealer = new Dealer();
        dealer.hit(new Card(CardNumber.SEVEN, CardType.CLOVER));
        dealer.hit(new Card(CardNumber.TEN, CardType.SPADE));

        Gamer player = new Player(new PlayerName("aki"));
        player.hit(new Card(CardNumber.TEN, CardType.CLOVER));
        player.hit(new Card(CardNumber.TEN, CardType.SPADE));
        player.hit(new Card(CardNumber.TEN, CardType.DIAMOND));

        assertThat(player.compare(dealer)).isNegative();
    }

    @Test
    @DisplayName("player의 compare 메서드는 딜러 Bust라면 무조건 승리했다고 판단한다.")
    void compare_dealer_bust() {
        Gamer dealer = new Dealer();
        dealer.hit(new Card(CardNumber.SEVEN, CardType.CLOVER));
        dealer.hit(new Card(CardNumber.FIVE, CardType.SPADE));
        dealer.hit(new Card(CardNumber.TEN, CardType.DIAMOND));

        Gamer player = new Player(new PlayerName("aki"));
        player.hit(new Card(CardNumber.TEN, CardType.CLOVER));
        player.hit(new Card(CardNumber.TEN, CardType.SPADE));

        assertThat(player.compare(dealer)).isPositive();
    }

    @Test
    @DisplayName("player의 compare 메서드는 딜러가 블랙잭이고 플레이어가 블랙잭이 아니라면 패배했다고 판단한다.")
    void compare_dealer_blackjack() {
        Gamer dealer = new Dealer();
        dealer.hit(new Card(CardNumber.ACE, CardType.CLOVER));
        dealer.hit(new Card(CardNumber.KING, CardType.SPADE));

        Gamer player = new Player(new PlayerName("aki"));
        player.hit(new Card(CardNumber.ACE, CardType.CLOVER));
        player.hit(new Card(CardNumber.TEN, CardType.DIAMOND));

        assertThat(player.compare(dealer)).isNegative();
    }

    @Test
    @DisplayName("player의 compare 메서드는 플레이어가 블랙잭이고 딜러가 블랙잭이 아니라면 이겼다고 판단한다.")
    void compare_player_blackjack() {
        Gamer dealer = new Dealer();
        dealer.hit(new Card(CardNumber.ACE, CardType.CLOVER));
        dealer.hit(new Card(CardNumber.TEN, CardType.SPADE));

        Gamer player = new Player(new PlayerName("aki"));
        player.hit(new Card(CardNumber.ACE, CardType.CLOVER));
        player.hit(new Card(CardNumber.JACK, CardType.DIAMOND));

        assertThat(player.compare(dealer)).isPositive();
    }

    @Test
    @DisplayName("player의 compare 메서드는 딜러와 플레이어 모두 블랙잭이라면 무승부라고 판단한다.")
    void compare_player_and_dealer_blackjack() {
        Gamer dealer = new Dealer();
        dealer.hit(new Card(CardNumber.ACE, CardType.CLOVER));
        dealer.hit(new Card(CardNumber.KING, CardType.SPADE));

        Gamer player = new Player(new PlayerName("aki"));
        player.hit(new Card(CardNumber.ACE, CardType.CLOVER));
        player.hit(new Card(CardNumber.JACK, CardType.DIAMOND));

        assertThat(player.compare(dealer)).isZero();
    }

    @Test
    @DisplayName("player의 compare 메서드는 딜러와 플레이어의 점수를 비교하여 승무패를 판단한다.")
    void compare_player_and_dealer() {
        Gamer dealer = new Dealer();
        dealer.hit(new Card(CardNumber.FIVE, CardType.CLOVER));
        dealer.hit(new Card(CardNumber.TEN, CardType.SPADE));
        dealer.hit(new Card(CardNumber.THREE, CardType.DIAMOND));

        Gamer player = new Player(new PlayerName("aki"));
        player.hit(new Card(CardNumber.SEVEN, CardType.CLOVER));
        player.hit(new Card(CardNumber.TEN, CardType.DIAMOND));

        assertThat(player.compare(dealer)).isNegative();
    }
}
