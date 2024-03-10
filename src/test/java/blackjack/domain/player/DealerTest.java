package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import blackjack.domain.card.Hand;
import fixture.HandFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    private Card card;

    @BeforeEach
    void setUp() {
        card = new Card(CardRank.ACE, CardSuit.DIAMOND);
    }

    @DisplayName("딜러는 16 이하이면 핸드에 카드를 추가한다.")
    @Test
    void testDealerHit() {
        // given
        Hand hand = HandFixture.createHandWithScoreTotal16();

        Dealer dealer = new Dealer(hand);

        // when
        dealer.hit(card);

        // then
        assertThat(hand.getCards()).contains(card);
    }

    @DisplayName("딜러는 17점 이상이면 핸드에 카드를 추가하지 않는다.")
    @Test
    void testDealerNotHit() {
        // given
        Hand hand = HandFixture.createHandWithScoreTotal16();
        hand.append(new Card(CardRank.ACE, CardSuit.HEART));

        Dealer dealer = new Dealer(hand);

        // when
        dealer.hit(card);

        // then
        assertThat(hand.getCards()).doesNotContain(card);
    }
}
