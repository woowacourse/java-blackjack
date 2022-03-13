package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.PlayerName;
import blackjack.domain.result.Outcome;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OutComeTest {

    @Test
    @DisplayName("Outcome의 compare 메서드는 플레이어가 Bust라면 무조건 패배했다고 판단한다.")
    void compare_player_bust() {
        Gamer dealer = new Dealer();
        dealer.hit(new Card(CardNumber.SEVEN, CardType.CLOVER));
        dealer.hit(new Card(CardNumber.TEN, CardType.SPADE));

        Gamer player = new Player(new PlayerName("aki"));
        player.hit(new Card(CardNumber.TEN, CardType.CLOVER));
        player.hit(new Card(CardNumber.TEN, CardType.SPADE));
        player.hit(new Card(CardNumber.TEN, CardType.DIAMOND));

        assertThat(Outcome.compare(dealer, player)).isEqualTo(Outcome.WIN);
    }

    @Test
    @DisplayName("Outcome의 compare 메서드는 딜러 Bust라면 무조건 패배했다고 판단한다.")
    void compare_dealer_bust() {
        Gamer dealer = new Dealer();
        dealer.hit(new Card(CardNumber.SEVEN, CardType.CLOVER));
        dealer.hit(new Card(CardNumber.FIVE, CardType.SPADE));
        dealer.hit(new Card(CardNumber.TEN, CardType.DIAMOND));

        Gamer player = new Player(new PlayerName("aki"));
        player.hit(new Card(CardNumber.TEN, CardType.CLOVER));
        player.hit(new Card(CardNumber.TEN, CardType.SPADE));

        assertThat(Outcome.compare(dealer, player)).isEqualTo(Outcome.LOSE);
    }

    @Test
    @DisplayName("Outcome의 compare 메서드는 딜러가 블랙잭이고 플레이어가 블랙잭이 아니라면 승리했다고 판단한다.")
    void compare_dealer_blackjack() {
        Gamer dealer = new Dealer();
        dealer.hit(new Card(CardNumber.ACE, CardType.CLOVER));
        dealer.hit(new Card(CardNumber.KING, CardType.SPADE));

        Gamer player = new Player(new PlayerName("aki"));
        player.hit(new Card(CardNumber.ACE, CardType.CLOVER));
        player.hit(new Card(CardNumber.TEN, CardType.DIAMOND));

        assertThat(Outcome.compare(dealer, player)).isEqualTo(Outcome.WIN);
    }

    @Test
    @DisplayName("Outcome의 compare 메서드는 플레이어가 블랙잭이고 딜러가 블랙잭이 아니라면 패배했다고 판단한다.")
    void compare_player_blackjack() {
        Gamer dealer = new Dealer();
        dealer.hit(new Card(CardNumber.ACE, CardType.CLOVER));
        dealer.hit(new Card(CardNumber.TEN, CardType.SPADE));

        Gamer player = new Player(new PlayerName("aki"));
        player.hit(new Card(CardNumber.ACE, CardType.CLOVER));
        player.hit(new Card(CardNumber.JACK, CardType.DIAMOND));

        assertThat(Outcome.compare(dealer, player)).isEqualTo(Outcome.LOSE);
    }

    @Test
    @DisplayName("Outcome의 compare 메서드는 딜러와 플레이어 모두 블랙잭이라면 무승부라고 판단한다.")
    void compare_player_and_dealer_blackjack() {
        Gamer dealer = new Dealer();
        dealer.hit(new Card(CardNumber.ACE, CardType.CLOVER));
        dealer.hit(new Card(CardNumber.KING, CardType.SPADE));

        Gamer player = new Player(new PlayerName("aki"));
        player.hit(new Card(CardNumber.ACE, CardType.CLOVER));
        player.hit(new Card(CardNumber.JACK, CardType.DIAMOND));

        assertThat(Outcome.compare(dealer, player)).isEqualTo(Outcome.DRAW);
    }

    @Test
    @DisplayName("Outcome의 compare 메서드는 딜러와 플레이어의 점수를 비교하여 승무패를 판단한다.")
    void compare_player_and_dealer() {
        Gamer dealer = new Dealer();
        dealer.hit(new Card(CardNumber.FIVE, CardType.CLOVER));
        dealer.hit(new Card(CardNumber.TEN, CardType.SPADE));
        dealer.hit(new Card(CardNumber.THREE, CardType.DIAMOND));

        Gamer player = new Player(new PlayerName("aki"));
        player.hit(new Card(CardNumber.SEVEN, CardType.CLOVER));
        player.hit(new Card(CardNumber.TEN, CardType.DIAMOND));

        assertThat(Outcome.compare(dealer, player)).isEqualTo(Outcome.WIN);
    }
}
