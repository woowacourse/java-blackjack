package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Kind;
import blackjack.domain.card.Number;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("플레이어 생성자 테스트")
    @Test
    void constructor_CreatePlayer_HasInstance() {
        Player player = Player.of("쿼리치", 10000);
        player.receive(new Cards(List.of(new Card(Number.ACE, Kind.SPADE))));

        assertThat(player).isNotNull();
    }

    @DisplayName("21점 보유 시 카드 추가 수령 가능")
    @Test
    void isReceivable_BestScore21_IsTrue() {
        Player player = Player.of("쿼리치", 10000);
        player.receive(new Cards(List.of(
                new Card(Number.ACE, Kind.SPADE),
                new Card(Number.KING, Kind.SPADE))));

        assertThat(player.isReceivable()).isTrue();
    }

    @DisplayName("22점 보유 시 카드 추가 수령 불가능")
    @Test
    void isReceivable_BestScore22_IsFalse() {
        Player player = Player.of("쿼리치", 10000);
        player.receive(new Cards(List.of(
                new Card(Number.TEN, Kind.SPADE),
                new Card(Number.TWO, Kind.SPADE),
                new Card(Number.TEN, Kind.HEART))));

        assertThat(player.isReceivable()).isFalse();
    }

    @DisplayName("Ace 4장 보유 시 14점 반환")
    @Test
    void calculateBestScore_FourAces_Returns14() {
        Player player = Player.of("쿼리치", 10000);
        player.receive(new Cards(List.of(
                new Card(Number.ACE, Kind.SPADE),
                new Card(Number.ACE, Kind.DIAMOND),
                new Card(Number.ACE, Kind.CLOVER),
                new Card(Number.ACE, Kind.HEART))));

        assertThat(player.calculateBestScore()).isEqualTo(14);
    }

    @DisplayName("Ace 를 11점으로 판단하여 베스트 점수 계산")
    @Test
    void calculateBestScore_ConsideringAceAsEleven_Returns21() {
        Player player = Player.of("쿼리치", 10000);
        player.receive(new Cards(List.of(
                new Card(Number.ACE, Kind.SPADE),
                new Card(Number.KING, Kind.SPADE))));

        assertThat(player.calculateBestScore()).isEqualTo(21);
    }

    @DisplayName("Ace 를 1점으로 판단하여 베스트 점수 계산")
    @Test
    void calculateBestScore_ConsideringAceAsOne_Returns21() {
        Player player = Player.of("쿼리치", 10000);
        player.receive(new Cards(List.of(
                new Card(Number.ACE, Kind.SPADE),
                new Card(Number.FIVE, Kind.SPADE),
                new Card(Number.SEVEN, Kind.SPADE),
                new Card(Number.EIGHT, Kind.SPADE))));

        assertThat(player.calculateBestScore()).isEqualTo(21);
    }

    @DisplayName("플레이어의 점수가 더 높은 경우 승리")
    @Test
    void isWinner_Player20_isWin() {
        Player player = Player.of("쿼리치", 10000);
        player.receive(new Cards(List.of(
                new Card(Number.ACE, Kind.SPADE),
                new Card(Number.NINE, Kind.SPADE))));

        Dealer dealer = new Dealer();
        dealer.receive(new Cards(List.of(
                new Card(Number.NINE, Kind.HEART),
                new Card(Number.KING, Kind.HEART))));

        assertThat(player.isWinner(dealer)).isEqualTo(player.getBet().getWinningPrize());
    }

    @DisplayName("딜러의 점수가 더 높은 경우 패배")
    @Test
    void isWinner_Player20_isLose() {
        Player player = Player.of("쿼리치", 10000);
        player.receive(new Cards(List.of(
                new Card(Number.ACE, Kind.SPADE),
                new Card(Number.NINE, Kind.SPADE))));

        Dealer dealer = new Dealer();
        dealer.receive(new Cards(List.of(
                new Card(Number.ACE, Kind.HEART),
                new Card(Number.KING, Kind.HEART))));

        assertThat(player.isWinner(dealer)).isEqualTo(player.getBet().getLosingMoney());
    }

    @DisplayName("플레이어가 버스트된 경우 패배")
    @Test
    void isWinner_PlayerBusted_isLose() {
        Player player = Player.of("쿼리치", 10000);
        player.receive(new Cards(List.of(
                new Card(Number.EIGHT, Kind.SPADE),
                new Card(Number.NINE, Kind.SPADE),
                new Card(Number.TEN, Kind.SPADE))));

        Dealer dealer = new Dealer();
        dealer.receive(new Cards(List.of(
                new Card(Number.NINE, Kind.HEART),
                new Card(Number.KING, Kind.HEART))));

        assertThat(player.isWinner(dealer)).isEqualTo(player.getBet().getLosingMoney());
    }

    @DisplayName("딜러가 버스트된 경우 승리")
    @Test
    void isWinner_DealerBusted_isWin() {
        Player player = Player.of("쿼리치", 10000);
        player.receive(new Cards(List.of(
                new Card(Number.ACE, Kind.SPADE),
                new Card(Number.NINE, Kind.SPADE))));

        Dealer dealer = new Dealer();
        dealer.receive(new Cards(List.of(
                new Card(Number.JACK, Kind.HEART),
                new Card(Number.QUEEN, Kind.HEART),
                new Card(Number.KING, Kind.HEART))));

        assertThat(player.isWinner(dealer)).isEqualTo(player.getBet().getWinningPrize());
    }

    @DisplayName("둘 다 버스트된 경우 패배")
    @Test
    void isWinner_BothBusted_isLose() {
        Player player = Player.of("쿼리치", 10000);
        player.receive(new Cards(List.of(
                new Card(Number.EIGHT, Kind.SPADE),
                new Card(Number.NINE, Kind.SPADE),
                new Card(Number.TEN, Kind.SPADE))));

        Dealer dealer = new Dealer();
        dealer.receive(new Cards(List.of(
                new Card(Number.JACK, Kind.HEART),
                new Card(Number.QUEEN, Kind.HEART),
                new Card(Number.KING, Kind.HEART))));

        assertThat(player.isWinner(dealer)).isEqualTo(player.getBet().getLosingMoney());
    }

    @DisplayName("동일한 점수를 가질 경우 무승부")
    @Test
    void isWinner_SameScore_isDraw() {
        Player player = Player.of("쿼리치", 10000);
        player.receive(new Cards(List.of(
                new Card(Number.ACE, Kind.SPADE),
                new Card(Number.KING, Kind.SPADE))));

        Dealer dealer = new Dealer();
        dealer.receive(new Cards(List.of(
                new Card(Number.ACE, Kind.HEART),
                new Card(Number.KING, Kind.HEART))));

        assertThat(player.isWinner(dealer)).isEqualTo(player.getBet().getDrawMoney());
    }

    @DisplayName("동일한 점수를 가지고 버스트된 경우 패배")
    @Test
    void isWinner_SameScoreAndBothBusted_isLose() {
        Player player = Player.of("쿼리치", 10000);
        player.receive(new Cards(List.of(
                new Card(Number.EIGHT, Kind.SPADE),
                new Card(Number.NINE, Kind.SPADE),
                new Card(Number.TEN, Kind.SPADE))));

        Dealer dealer = new Dealer();
        dealer.receive(new Cards(List.of(
                new Card(Number.EIGHT, Kind.HEART),
                new Card(Number.NINE, Kind.HEART),
                new Card(Number.TEN, Kind.HEART))));

        assertThat(player.isWinner(dealer)).isEqualTo(player.getBet().getLosingMoney());
    }

    @DisplayName("플레이어만 블랙잭일 경우 승리")
    @Test
    void isWinner_PlayerIsOnlyBlackJack_isWin() {
        Player player = Player.of("쿼리치", 10000);
        player.receive(new Cards(List.of(
                new Card(Number.ACE, Kind.SPADE),
                new Card(Number.KING, Kind.SPADE))));

        Dealer dealer = new Dealer();
        dealer.receive(new Cards(List.of(
                new Card(Number.ACE, Kind.HEART),
                new Card(Number.JACK, Kind.HEART),
                new Card(Number.KING, Kind.HEART))));

        assertThat(player.isWinner(dealer)).isEqualTo(player.getBet().getBlackJackPrize());
    }

    @DisplayName("둘 다 블랙잭일 경우 무승부")
    @Test
    void isWinner_BothAreBlackJack_isDraw() {
        Player player = Player.of("쿼리치", 10000);
        player.receive(new Cards(List.of(
                new Card(Number.ACE, Kind.SPADE),
                new Card(Number.KING, Kind.SPADE))));

        Dealer dealer = new Dealer();
        dealer.receive(new Cards(List.of(
                new Card(Number.ACE, Kind.HEART),
                new Card(Number.KING, Kind.HEART))));

        assertThat(player.isWinner(dealer)).isEqualTo(player.getBet().getDrawMoney());
    }

    @DisplayName("딜러만 블랙잭일 경우 패배")
    @Test
    void isWinner_dealerIsOnlyBlackJack_isLose() {
        Player player = Player.of("쿼리치", 10000);
        player.receive(new Cards(List.of(
                new Card(Number.ACE, Kind.SPADE),
                new Card(Number.JACK, Kind.SPADE),
                new Card(Number.KING, Kind.SPADE))));

        Dealer dealer = new Dealer();
        dealer.receive(new Cards(List.of(
                new Card(Number.ACE, Kind.HEART),
                new Card(Number.KING, Kind.HEART))));

        assertThat(player.isWinner(dealer)).isEqualTo(player.getBet().getLosingMoney());
    }
}
