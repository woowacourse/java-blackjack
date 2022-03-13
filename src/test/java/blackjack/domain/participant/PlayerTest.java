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
        Player player = Player.of("쿼리치");
        player.receive(new Cards(List.of(Card.from(Number.ACE, Kind.SPADE))));

        assertThat(player).isNotNull();
    }

    @DisplayName("21점 보유 시 카드 추가 수령 가능")
    @Test
    void isReceivable_BestScore21_IsTrue() {
        Player player = Player.of("쿼리치");
        player.receive(new Cards(List.of(
                Card.from(Number.ACE, Kind.SPADE),
                Card.from(Number.KING, Kind.SPADE))));

        assertThat(player.isReceivable()).isTrue();
    }

    @DisplayName("22점 보유 시 카드 추가 수령 불가능")
    @Test
    void isReceivable_BestScore22_IsFalse() {
        Player player = Player.of("쿼리치");
        player.receive(new Cards(List.of(
                Card.from(Number.TEN, Kind.SPADE),
                Card.from(Number.TWO, Kind.SPADE),
                Card.from(Number.TEN, Kind.HEART))));

        assertThat(player.isReceivable()).isFalse();
    }

    @DisplayName("Ace 4장 보유 시 14점 반환")
    @Test
    void calculateBestScore_FourAces_Returns14() {
        Player player = Player.of("쿼리치");
        player.receive(new Cards(List.of(
                Card.from(Number.ACE, Kind.SPADE),
                Card.from(Number.ACE, Kind.DIAMOND),
                Card.from(Number.ACE, Kind.CLOVER),
                Card.from(Number.ACE, Kind.HEART))));

        assertThat(player.calculateBestScore()).isEqualTo(14);
    }

    @DisplayName("Ace 를 11점으로 판단하여 베스트 점수 계산")
    @Test
    void calculateBestScore_ConsideringAceAsEleven_Returns21() {
        Player player = Player.of("쿼리치");
        player.receive(new Cards(List.of(
                Card.from(Number.ACE, Kind.SPADE),
                Card.from(Number.KING, Kind.SPADE))));

        assertThat(player.calculateBestScore()).isEqualTo(21);
    }

    @DisplayName("Ace 를 1점으로 판단하여 베스트 점수 계산")
    @Test
    void calculateBestScore_ConsideringAceAsOne_Returns21() {
        Player player = Player.of("쿼리치");
        player.receive(new Cards(List.of(
                Card.from(Number.ACE, Kind.SPADE),
                Card.from(Number.FIVE, Kind.SPADE),
                Card.from(Number.SEVEN, Kind.SPADE),
                Card.from(Number.EIGHT, Kind.SPADE))));

        assertThat(player.calculateBestScore()).isEqualTo(21);
    }

    @DisplayName("플레이어의 점수가 더 높은 경우 승리 테스트")
    @Test
    void isWinner_Player20_isWin() {
        Player player = Player.of("쿼리치");
        player.receive(new Cards(List.of(
                Card.from(Number.ACE, Kind.SPADE),
                Card.from(Number.NINE, Kind.SPADE))));

        assertThat(player.isWinner(19)).isTrue();
    }

    @DisplayName("딜러의 점수가 더 높은 경우 패배 테스트")
    @Test
    void isWinner_Player20_isLose() {
        Player player = Player.of("쿼리치");
        player.receive(new Cards(List.of(
                Card.from(Number.ACE, Kind.SPADE),
                Card.from(Number.NINE, Kind.SPADE))));

        assertThat(player.isWinner(21)).isFalse();
    }

    @DisplayName("플레이어가 버스트된 경우 패배 테스트")
    @Test
    void isWinner_PlayerBusted_isLose() {
        Player player = Player.of("쿼리치");
        player.receive(new Cards(List.of(
                Card.from(Number.NINE, Kind.SPADE),
                Card.from(Number.NINE, Kind.CLOVER),
                Card.from(Number.NINE, Kind.HEART))));

        assertThat(player.isWinner(19)).isFalse();
    }

    @DisplayName("딜러가 버스트된 경우 승리 테스트")
    @Test
    void isWinner_DealerBusted_isWin() {
        Player player = Player.of("쿼리치");
        player.receive(new Cards(List.of(
                Card.from(Number.ACE, Kind.SPADE),
                Card.from(Number.NINE, Kind.SPADE))));

        assertThat(player.isWinner(22)).isTrue();
    }

    @DisplayName("둘 다 버스트된 경우 패배 테스트")
    @Test
    void isWinner_BothBusted_isLose() {
        Player player = Player.of("쿼리치");
        player.receive(new Cards(List.of(
                Card.from(Number.NINE, Kind.SPADE),
                Card.from(Number.NINE, Kind.CLOVER),
                Card.from(Number.NINE, Kind.HEART))));

        assertThat(player.isWinner(22)).isFalse();
    }
}
