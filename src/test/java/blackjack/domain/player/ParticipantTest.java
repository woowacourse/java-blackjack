package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import blackjack.domain.card.Hand;
import blackjack.domain.rule.DealerHitStrategy;
import blackjack.domain.rule.PlayerHitStrategy;
import fixture.HandFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    private Card card;

    @BeforeEach
    void setUp() {
        card = new Card(CardRank.ACE, CardSuit.DIAMOND);
    }

    @DisplayName("플레이어의 히트 조건에 부합하면 핸드에 카드를 추가한다.")
    @Test
    void testPlayerHit() {
        // given
        Hand hand = HandFixture.createHandWithScoreTotal21();

        Participant player = new Participant(hand, new PlayerHitStrategy());

        // when
        player.hit(card);

        // then
        assertThat(hand.getCards()).contains(card);
    }

    @DisplayName("플레이어의 히트 조건에 부합하지 않으면 핸드에 카드를 추가하지 않는다.")
    @Test
    void testPlayerNotHit() {
        // given
        Hand hand = HandFixture.createHandWithScoreTotal21();
        hand.append(new Card(CardRank.ACE, CardSuit.HEART));

        Participant player = new Participant(hand, new PlayerHitStrategy());

        // when
        player.hit(card);

        // then
        assertThat(hand.getCards()).doesNotContain(card);
    }

    @DisplayName("딜러의 히트 조건에 부합하면 핸드에 카드를 추가한다.")
    @Test
    void testDealerHit() {
        // given
        Hand hand = HandFixture.createHandWithScoreTotal16();

        Participant dealer = new Participant(hand, new DealerHitStrategy());

        // when
        dealer.hit(card);

        // then
        assertThat(hand.getCards()).contains(card);
    }

    @DisplayName("딜러의 히트 조건에 부합하지 않으면 핸드에 카드를 추가하지 않는다.")
    @Test
    void testDealerNotHit() {
        // given
        Hand hand = HandFixture.createHandWithScoreTotal16();
        hand.append(new Card(CardRank.ACE, CardSuit.HEART));

        Participant dealer = new Participant(hand, new DealerHitStrategy());

        // when
        dealer.hit(card);

        // then
        assertThat(hand.getCards()).doesNotContain(card);
    }

    @DisplayName("21을 초과하면 버스트다.")
    @Test
    void testIsBust() {
        // given
        Hand hand = HandFixture.createHandWithScoreTotal21();
        hand.append(new Card(CardRank.ACE, CardSuit.HEART));

        Participant player = new Participant(hand, new PlayerHitStrategy());
        Participant dealer = new Participant(hand, new DealerHitStrategy());

        // when & then
        assertAll(
                () -> assertThat(player.isBust()).isTrue(),
                () -> assertThat(dealer.isBust()).isTrue()
        );
    }

    @DisplayName("21을 초과하지 않으면 버스트가 아니다.")
    @Test
    void testIsNotBust() {
        // given
        Hand hand = HandFixture.createHandWithScoreTotal21();

        Participant player = new Participant(hand, new PlayerHitStrategy());
        Participant dealer = new Participant(hand, new DealerHitStrategy());

        // when & then
        assertAll(
                () -> assertThat(player.isBust()).isFalse(),
                () -> assertThat(dealer.isBust()).isFalse()
        );
    }
}
