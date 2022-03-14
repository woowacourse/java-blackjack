package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
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
        Dealer dealer = Dealer.createDefaultNameDealer();
        dealer.receive(new CardDeck(List.of(Card.from(Number.ACE, Kind.SPADE))), 1);

        assertThat(dealer).isNotNull();
    }

    @DisplayName("딜러 최적 점수 계산")
    @Test
    void calculateBestScore() {
        Dealer dealer = Dealer.createDefaultNameDealer();
        dealer.receive(new CardDeck(
                List.of(Card.from(Number.ACE, Kind.SPADE),
                        Card.from(Number.EIGHT, Kind.HEART))), 2);

        assertThat(dealer.calculateBestScore()).isEqualTo(19);
    }

    @DisplayName("딜러 카드 추가 수령 가능 여부 테스트")
    @Test
    void isReceivable_BestScoreAs16_IsTrue() {
        Dealer dealer = Dealer.createDefaultNameDealer();
        dealer.receive(new CardDeck(
                List.of(Card.from(Number.ACE, Kind.SPADE),
                        Card.from(Number.FIVE, Kind.SPADE))), 1);

        assertThat(dealer.isReceivable()).isTrue();
    }

    @DisplayName("딜러 카드 추가 수령 실패 여부 테스트")
    @Test
    void isReceivable_BestScoreAs17_IsFalse() {
        Dealer dealer = Dealer.createDefaultNameDealer();
        dealer.receive(new CardDeck(
                List.of(Card.from(Number.ACE, Kind.SPADE),
                        Card.from(Number.SIX, Kind.SPADE))), 2);

        assertThat(dealer.isReceivable()).isFalse();
    }
}
