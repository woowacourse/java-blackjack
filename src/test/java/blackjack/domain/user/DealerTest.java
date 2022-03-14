package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import blackjack.domain.result.Outcome;
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
    @DisplayName("isValidRange 메서드는 카드의 총합이 16 이하면 true를 반환한다.")
    void validate_range_true() {
        User dealer = new Dealer();
        dealer.hit(new Card(CardNumber.SIX, CardType.CLOVER));
        dealer.hit(new Card(CardNumber.TEN, CardType.SPADE));

        assertThat(dealer.isValidRange()).isTrue();
    }

    @Test
    @DisplayName("isValidRange 메서드는 카드의 총합이 17 이상이면 false를 반환한다.")
    void validate_range_false() {
        User dealer = new Dealer();
        dealer.hit(new Card(CardNumber.SEVEN, CardType.CLOVER));
        dealer.hit(new Card(CardNumber.TEN, CardType.SPADE));

        assertThat(dealer.isValidRange()).isFalse();
    }

    @Test
    @DisplayName("dealer의 determineWinner 메서드는 플레이어가 Bust라면 무조건 딜러가 승리했다고 판단한다.")
    void if_only_player_bust() {
        User dealer = new Dealer();
        dealer.hit(new Card(CardNumber.SEVEN, CardType.CLOVER));
        dealer.hit(new Card(CardNumber.TEN, CardType.SPADE));

        User player = new Player(new UserName("aki"));
        player.hit(new Card(CardNumber.TEN, CardType.CLOVER));
        player.hit(new Card(CardNumber.TEN, CardType.SPADE));
        player.hit(new Card(CardNumber.TEN, CardType.DIAMOND));

        assertThat(dealer.determineWinner(player)).isEqualTo(Outcome.WIN);
    }

    @Test
    @DisplayName("dealer의 determineWinner 메서드는 플레이어가 Bust가 아니고, 딜러 Bust라면 딜러가 패배했다고 판단한다.")
    void if_only_dealer_bust() {
        User dealer = new Dealer();
        dealer.hit(new Card(CardNumber.SEVEN, CardType.CLOVER));
        dealer.hit(new Card(CardNumber.FIVE, CardType.SPADE));
        dealer.hit(new Card(CardNumber.TEN, CardType.DIAMOND));

        User player = new Player(new UserName("aki"));
        player.hit(new Card(CardNumber.TEN, CardType.CLOVER));
        player.hit(new Card(CardNumber.TEN, CardType.SPADE));

        assertThat(dealer.determineWinner(player)).isEqualTo(Outcome.LOSE);
    }

    @Test
    @DisplayName("dealer의 determineWinner 메서드는 딜러가 블랙잭이고 플레이어가 블랙잭이 아니라면 딜러가 이겼다고 판단한다.")
    void if_only_dealer_blackjack() {
        User dealer = new Dealer();
        dealer.hit(new Card(CardNumber.ACE, CardType.CLOVER));
        dealer.hit(new Card(CardNumber.KING, CardType.SPADE));

        User player = new Player(new UserName("aki"));
        player.hit(new Card(CardNumber.TEN, CardType.CLOVER));
        player.hit(new Card(CardNumber.TEN, CardType.DIAMOND));

        assertThat(dealer.determineWinner(player)).isEqualTo(Outcome.WIN);
    }

    @Test
    @DisplayName("dealer의 determineWinner 메서드는 플레이어가 블랙잭이고 딜러가 블랙잭이 아니라면 딜러가 졌다고 판단한다.")
    void if_only_player_blackjack() {
        User dealer = new Dealer();
        dealer.hit(new Card(CardNumber.TEN, CardType.CLOVER));
        dealer.hit(new Card(CardNumber.TEN, CardType.SPADE));

        User player = new Player(new UserName("aki"));
        player.hit(new Card(CardNumber.ACE, CardType.CLOVER));
        player.hit(new Card(CardNumber.JACK, CardType.DIAMOND));

        assertThat(dealer.determineWinner(player)).isEqualTo(Outcome.LOSE);
    }

    @Test
    @DisplayName("dealer의 determineWinner 메서드는 딜러와 플레이어 모두 블랙잭이라면 무승부라고 판단한다.")
    void if_dealer_and_player_blackjack() {
        User dealer = new Dealer();
        dealer.hit(new Card(CardNumber.ACE, CardType.CLOVER));
        dealer.hit(new Card(CardNumber.KING, CardType.SPADE));

        User player = new Player(new UserName("aki"));
        player.hit(new Card(CardNumber.ACE, CardType.CLOVER));
        player.hit(new Card(CardNumber.JACK, CardType.DIAMOND));

        assertThat(dealer.determineWinner(player)).isEqualTo(Outcome.DRAW);
    }

    @Test
    @DisplayName("dealer의 determineWinner 메서드는 딜러와 플레이어 모두 Bust, 블랙잭이 아닐경우 점수를 비교하여 승무패를 판단한다.")
    void compare_score() {
        User dealer = new Dealer();
        dealer.hit(new Card(CardNumber.FIVE, CardType.CLOVER));
        dealer.hit(new Card(CardNumber.TEN, CardType.SPADE));
        dealer.hit(new Card(CardNumber.THREE, CardType.DIAMOND));

        User player = new Player(new UserName("aki"));
        player.hit(new Card(CardNumber.SEVEN, CardType.CLOVER));
        player.hit(new Card(CardNumber.TEN, CardType.DIAMOND));

        assertThat(dealer.determineWinner(player)).isEqualTo(Outcome.WIN);
    }
}
