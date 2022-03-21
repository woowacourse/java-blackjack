package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Kind;
import blackjack.domain.card.Number;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러 생성자 테스트")
    @Test
    void constructor_CreateDealer_HasInstance() {
        Dealer dealer = new Dealer();
        dealer.receive(new Cards(List.of(new Card(Number.ACE, Kind.SPADE))));

        assertThat(dealer).isNotNull();
    }

    @DisplayName("16점 이하 보유 시 카드 추가 수령 가능")
    @Test
    void isReceivable_BestScoreAs16_IsTrue() {
        Dealer dealer = new Dealer();
        dealer.receive(new Cards(List.of(
                new Card(Number.ACE, Kind.SPADE),
                new Card(Number.FIVE, Kind.SPADE))));

        assertThat(dealer.isReceivable()).isTrue();
    }

    @DisplayName("17점 이상 보유 시 카드 추가 수령 불가능")
    @Test
    void isReceivable_BestScoreAs17_IsFalse() {
        Dealer dealer = new Dealer();
        dealer.receive(new Cards(List.of(
                new Card(Number.ACE, Kind.SPADE),
                new Card(Number.SIX, Kind.SPADE))));

        assertThat(dealer.isReceivable()).isFalse();
    }

    @DisplayName("Ace 4장 보유 시 14점 반환")
    @Test
    void calculateBestScore_FourAces_Returns14() {
        Dealer dealer = new Dealer();
        dealer.receive(new Cards(List.of(
                new Card(Number.ACE, Kind.SPADE),
                new Card(Number.ACE, Kind.DIAMOND),
                new Card(Number.ACE, Kind.CLOVER),
                new Card(Number.ACE, Kind.HEART))));

        assertThat(dealer.calculateBestScore().getScore()).isEqualTo(14);
    }

    @DisplayName("Ace 를 11점으로 판단하여 베스트 점수 계산")
    @Test
    void calculateBestScore_ConsideringAceAsEleven_Returns21() {
        Dealer dealer = new Dealer();
        dealer.receive(new Cards(List.of(
                new Card(Number.ACE, Kind.SPADE),
                new Card(Number.KING, Kind.SPADE))));

        assertThat(dealer.calculateBestScore().getScore()).isEqualTo(21);
    }

    @DisplayName("Ace 를 1점으로 판단하여 베스트 점수 계산")
    @Test
    void calculateBestScore_ConsideringAceAsOne_Returns21() {
        Dealer dealer = new Dealer();
        dealer.receive(new Cards(List.of(
                new Card(Number.ACE, Kind.SPADE),
                new Card(Number.FIVE, Kind.SPADE),
                new Card(Number.SEVEN, Kind.SPADE),
                new Card(Number.EIGHT, Kind.SPADE))));

        assertThat(dealer.calculateBestScore().getScore()).isEqualTo(21);
    }
}
