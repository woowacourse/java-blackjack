package blackJack.domain.participant;

import blackJack.domain.card.Card;
import blackJack.domain.card.Denomination;
import blackJack.domain.card.Suit;
import blackJack.domain.result.WinDrawLose;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {

    @Test
    @DisplayName("Dealer 생성 테스트")
    void createValidDealer() {
        assertThat(new Dealer()).isNotNull();
    }

    @Test
    @DisplayName("딜러의 카드 합계 계산 테스트")
    void calculateScore() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(Card.from(Suit.CLOVER, Denomination.EIGHT));

        assertThat(dealer.getScore()).isEqualTo(8);
    }

    @Test
    @DisplayName("딜러가 플레이어를 이기는 상황 검증 테스트")
    void isWin() {
        Dealer dealer = new Dealer();
        Player player = new Player("kth990303");
        dealer.receiveCard(Card.from(Suit.SPADE, Denomination.JACK));
        dealer.receiveCard(Card.from(Suit.CLOVER, Denomination.NINE));
        dealer.receiveCard(Card.from(Suit.CLOVER, Denomination.THREE));
        player.receiveCard(Card.from(Suit.SPADE, Denomination.JACK));
        player.receiveCard(Card.from(Suit.CLOVER, Denomination.NINE));
        player.receiveCard(Card.from(Suit.CLOVER, Denomination.THREE));

        assertThat(dealer.isWin(player)).isEqualTo(WinDrawLose.WIN);
    }

    @Test
    @DisplayName("딜러가 플레이어와 무승부인 상황 검증 테스트")
    void isDraw() {
        Dealer dealer = new Dealer();
        Player player = new Player("kth990303");
        dealer.receiveCard(Card.from(Suit.SPADE, Denomination.JACK));
        dealer.receiveCard(Card.from(Suit.CLOVER, Denomination.NINE));
        dealer.receiveCard(Card.from(Suit.CLOVER, Denomination.TWO));
        player.receiveCard(Card.from(Suit.SPADE, Denomination.JACK));
        player.receiveCard(Card.from(Suit.CLOVER, Denomination.NINE));
        player.receiveCard(Card.from(Suit.CLOVER, Denomination.TWO));

        assertThat(dealer.isWin(player)).isEqualTo(WinDrawLose.DRAW);
    }

    @Test
    @DisplayName("딜러가 플레이어에게 지는 상황 검증 테스트")
    void isLose() {
        Dealer dealer = new Dealer();
        Player player = new Player("kth990303");
        dealer.receiveCard(Card.from(Suit.SPADE, Denomination.JACK));
        dealer.receiveCard(Card.from(Suit.CLOVER, Denomination.NINE));
        dealer.receiveCard(Card.from(Suit.CLOVER, Denomination.TWO));
        player.receiveCard(Card.from(Suit.CLOVER, Denomination.JACK));
        player.receiveCard(Card.from(Suit.CLOVER, Denomination.ACE));

        assertThat(dealer.isWin(player)).isEqualTo(WinDrawLose.LOSE);
    }

    @Test
    @DisplayName("딜러의 카드 추가 분배가 불가능한 경우 테스트")
    void hasFalseDealerNextTurn() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(Card.from(Suit.SPADE, Denomination.SEVEN));
        dealer.receiveCard(Card.from(Suit.HEART, Denomination.JACK));

        assertThat(dealer.hasNextTurn()).isFalse();
    }

    @Test
    @DisplayName("딜러의 카드 추가 분배가 가능한 경우 테스트")
    void hasTrueDealerNextTurn() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(Card.from(Suit.SPADE, Denomination.SIX));
        dealer.receiveCard(Card.from(Suit.HEART, Denomination.JACK));

        assertThat(dealer.hasNextTurn()).isTrue();
    }
}
