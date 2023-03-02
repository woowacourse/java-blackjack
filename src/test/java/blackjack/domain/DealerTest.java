package blackjack.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DealerTest {

    static Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer(new ArrayList<>(List.of(new Card(TrumpShape.DIAMOND, TrumpNumber.FIVE), new Card(TrumpShape.HEART, TrumpNumber.JACK))));
    }

    @Test
    @DisplayName("카드 한장씩 잘 받는지 테스트")
    void receiveCardSuccess() {
        Card card = new Card(TrumpShape.CLOVER, TrumpNumber.FIVE);
        dealer.receiveCard(card);
        List<Card> cards = dealer.getCards();
        assertThat(cards.get(cards.size() - 1)).isEqualTo(card);
    }

    @Test
    @DisplayName("카드 새로 뽑을 수 있는지 판단 - 성공")
    void canReceiveNewCard() {
        assertThat(dealer.isAbleToReceive()).isTrue();
    }

    @Test
    @DisplayName("카드 새로 뽑을 수 있는지 판단 - 실패")
    void cannotReceiveNewCard() {
        dealer.receiveCard(new Card(TrumpShape.HEART, TrumpNumber.THREE));
        assertThat(dealer.isAbleToReceive()).isFalse();
    }

}
