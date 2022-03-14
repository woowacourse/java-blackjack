package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import blackjack.domain.result.Outcome;
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
        User user = new Player(new UserName("aki"));
        user.hit(new Card(CardNumber.TEN, CardType.SPADE));
        user.hit(new Card(CardNumber.TEN, CardType.DIAMOND));

        assertThat(user.isValidRange()).isTrue();
    }

    @Test
    @DisplayName("isValidRange 메서드는 카드의 총합이 21 이상이면 false를 반환한다.")
    void validate_range_false() {
        User user = new Player(new UserName("aki"));
        user.hit(new Card(CardNumber.SEVEN, CardType.CLOVER));
        user.hit(new Card(CardNumber.FIVE, CardType.SPADE));
        user.hit(new Card(CardNumber.TEN, CardType.DIAMOND));

        assertThat(user.isValidRange()).isFalse();
    }

    @Test
    @DisplayName("player의 determineWinner 메서드는 딜러가 Bust가 아니고, 플레이어가 Bust라면 패배했다고 판단한다.")
    void if_only_player_bust() {
        User dealer = new Dealer();
        dealer.hit(new Card(CardNumber.SEVEN, CardType.CLOVER));
        dealer.hit(new Card(CardNumber.TEN, CardType.SPADE));

        User player = new Player(new UserName("aki"));
        player.hit(new Card(CardNumber.TEN, CardType.CLOVER));
        player.hit(new Card(CardNumber.TEN, CardType.SPADE));
        player.hit(new Card(CardNumber.TEN, CardType.DIAMOND));

        assertThat(player.determineWinner(dealer)).isEqualTo(Outcome.LOSE);
    }

    @Test
    @DisplayName("player의 determineWinner 메서드는 플레이어가 Bust가 아니고, 딜러 Bust라면 승리했다고 판단한다.")
    void if_only_dealer_bust() {
        User dealer = new Dealer();
        dealer.hit(new Card(CardNumber.SEVEN, CardType.CLOVER));
        dealer.hit(new Card(CardNumber.FIVE, CardType.SPADE));
        dealer.hit(new Card(CardNumber.TEN, CardType.DIAMOND));

        User player = new Player(new UserName("aki"));
        player.hit(new Card(CardNumber.TEN, CardType.CLOVER));
        player.hit(new Card(CardNumber.TEN, CardType.SPADE));

        assertThat(player.determineWinner(dealer)).isEqualTo(Outcome.WIN);
    }

    @Test
    @DisplayName("player의 determineWinner 메서드는 플레이어, 딜러 모두 Bust라면 무승부라고 판단한다.")
    void if_dealer_and_player_bust() {
        User dealer = new Dealer();
        dealer.hit(new Card(CardNumber.SEVEN, CardType.CLOVER));
        dealer.hit(new Card(CardNumber.FIVE, CardType.SPADE));
        dealer.hit(new Card(CardNumber.TEN, CardType.DIAMOND));

        User player = new Player(new UserName("aki"));
        player.hit(new Card(CardNumber.TEN, CardType.CLOVER));
        player.hit(new Card(CardNumber.TEN, CardType.DIAMOND));
        player.hit(new Card(CardNumber.TEN, CardType.SPADE));

        assertThat(player.determineWinner(dealer)).isEqualTo(Outcome.DRAW);
    }

    @Test
    @DisplayName("player의 determineWinner 메서드는 딜러가 블랙잭이고, 플레이어가 블랙잭이 아니라면 패배했다고 판단한다.")
    void if_only_dealer_blackjack() {
        User dealer = new Dealer();
        dealer.hit(new Card(CardNumber.ACE, CardType.CLOVER));
        dealer.hit(new Card(CardNumber.KING, CardType.SPADE));

        User player = new Player(new UserName("aki"));
        player.hit(new Card(CardNumber.TEN, CardType.CLOVER));
        player.hit(new Card(CardNumber.TEN, CardType.DIAMOND));

        assertThat(player.determineWinner(dealer)).isEqualTo(Outcome.LOSE);
    }

    @Test
    @DisplayName("player의 determineWinner 메서드는 플레이어가 블랙잭이고 딜러가 블랙잭이 아니라면 이겼다고 판단한다.")
    void if_only_player_blackjack() {
        User dealer = new Dealer();
        dealer.hit(new Card(CardNumber.TEN, CardType.CLOVER));
        dealer.hit(new Card(CardNumber.TEN, CardType.SPADE));

        User player = new Player(new UserName("aki"));
        player.hit(new Card(CardNumber.ACE, CardType.CLOVER));
        player.hit(new Card(CardNumber.JACK, CardType.DIAMOND));

        assertThat(player.determineWinner(dealer)).isEqualTo(Outcome.WIN);
    }

    @Test
    @DisplayName("player의 determineWinner 메서드는 딜러와 플레이어 모두 블랙잭이라면 무승부라고 판단한다.")
    void if_dealer_and_player_blackjack() {
        User dealer = new Dealer();
        dealer.hit(new Card(CardNumber.ACE, CardType.CLOVER));
        dealer.hit(new Card(CardNumber.KING, CardType.SPADE));

        User player = new Player(new UserName("aki"));
        player.hit(new Card(CardNumber.ACE, CardType.CLOVER));
        player.hit(new Card(CardNumber.JACK, CardType.DIAMOND));

        assertThat(player.determineWinner(dealer)).isEqualTo(Outcome.DRAW);
    }

    @Test
    @DisplayName("player의 determineWinner 메서드는 딜러와 플레이어 모두 Bust, 블랙잭이 아닐경우 점수를 비교하여 승무패를 판단한다.")
    void compare_score() {
        User dealer = new Dealer();
        dealer.hit(new Card(CardNumber.FIVE, CardType.CLOVER));
        dealer.hit(new Card(CardNumber.TEN, CardType.SPADE));
        dealer.hit(new Card(CardNumber.THREE, CardType.DIAMOND));

        User player = new Player(new UserName("aki"));
        player.hit(new Card(CardNumber.SEVEN, CardType.CLOVER));
        player.hit(new Card(CardNumber.TEN, CardType.DIAMOND));

        assertThat(player.determineWinner(dealer)).isEqualTo(Outcome.LOSE);
    }
}
