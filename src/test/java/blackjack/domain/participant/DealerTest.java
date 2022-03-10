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
    void create() {
        Dealer dealer = new Dealer(new Cards(List.of(Card.from(Number.ACE, Kind.SPADE))));

        assertThat(dealer).isNotNull();
    }

    @DisplayName("딜러 최적 점수 계산")
    @Test
    void calculateBestScore() {
        Dealer dealer = new Dealer(new Cards(
                List.of(Card.from(Number.ACE, Kind.SPADE),
                        Card.from(Number.ACE, Kind.HEART))));

        assertThat(dealer.calculateBestScore()).isEqualTo(22);
    }

    @DisplayName("딜러 카드 버스트 시 에이스 1로 계산 테스트")
    @Test
    void calculateBestScore_TwoAces_Is2() {
        Dealer dealer = new Dealer(new Cards(
                List.of(Card.from(Number.ACE, Kind.SPADE),
                        Card.from(Number.ACE, Kind.HEART))));

        assertThat(dealer.calculateBestScore()).isEqualTo(2);
    }

    @DisplayName("딜러 카드 추가 수령 가능 여부 테스트")
    @Test
    void isReceivable_BestScoreAs16_IsTrue() {
        Dealer dealer = new Dealer(new Cards(
                List.of(Card.from(Number.ACE, Kind.SPADE),
                        Card.from(Number.FIVE, Kind.SPADE))));

        assertThat(dealer.isReceivable()).isTrue();
    }

    @DisplayName("딜러 카드 추가 수령 실패 여부 테스트")
    @Test
    void isReceivable_BestScoreAs17_IsFalse() {
        Dealer dealer = new Dealer(new Cards(
                List.of(Card.from(Number.ACE, Kind.SPADE),
                        Card.from(Number.SIX, Kind.SPADE))));

        assertThat(dealer.isReceivable()).isFalse();
    }
}
