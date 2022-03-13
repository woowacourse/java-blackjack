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
        Dealer dealer = new Dealer(new Name("딜러"));
        dealer.receive(new Cards(List.of(Card.from(Number.ACE, Kind.SPADE))));

        assertThat(dealer).isNotNull();
    }

    @DisplayName("16점 이하 보유 시 카드 추가 수령 가능")
    @Test
    void isReceivable_BestScoreAs16_IsTrue() {
        Dealer dealer = new Dealer(new Name("딜러"));
        dealer.receive(new Cards(List.of(
                Card.from(Number.ACE, Kind.SPADE),
                Card.from(Number.FIVE, Kind.SPADE))));

        assertThat(dealer.isReceivable()).isTrue();
    }

    @DisplayName("17점 이상 보유 시 카드 추가 수령 불가능")
    @Test
    void isReceivable_BestScoreAs17_IsFalse() {
        Dealer dealer = new Dealer(new Name("딜러"));
        dealer.receive(new Cards(List.of(
                Card.from(Number.ACE, Kind.SPADE),
                Card.from(Number.SIX, Kind.SPADE))));

        assertThat(dealer.isReceivable()).isFalse();
    }

    @DisplayName("Ace 4장 보유 시 4점 반환")
    @Test
    void calculateBestScore_FourAces_Returns4() {
        Dealer dealer = new Dealer(new Name("딜러"));
        dealer.receive(new Cards(List.of(
                Card.from(Number.ACE, Kind.SPADE),
                Card.from(Number.ACE, Kind.DIAMOND),
                Card.from(Number.ACE, Kind.CLOVER),
                Card.from(Number.ACE, Kind.HEART))));

        assertThat(dealer.calculateBestScore()).isEqualTo(4);
    }

    @DisplayName("21점 이하일 경우 Ace 를 11점으로 판단하여 계산")
    @Test
    void calculateBestScore_ConsideringAceAsElevenWhenUnder21_Returns19() {
        Dealer dealer = new Dealer(new Name("딜러"));
        dealer.receive(new Cards(List.of(
                Card.from(Number.ACE, Kind.SPADE),
                Card.from(Number.EIGHT, Kind.HEART))));

        assertThat(dealer.calculateBestScore()).isEqualTo(19);
    }

    @DisplayName("21점 초과 시 Ace 를 1점으로 판단하여 계산")
    @Test
    void calculateBestScore_ConsideringAceAsOneWhenExceeds21_Returns2() {
        Dealer dealer = new Dealer(new Name("딜러"));
        dealer.receive(new Cards(List.of(
                Card.from(Number.ACE, Kind.SPADE),
                Card.from(Number.ACE, Kind.HEART))));

        assertThat(dealer.calculateBestScore()).isEqualTo(2);
    }
}
