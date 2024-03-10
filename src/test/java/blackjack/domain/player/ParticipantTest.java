package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import blackjack.domain.card.Hand;
import blackjack.domain.rule.DealerHitStrategy;
import blackjack.domain.rule.PlayerHitStrategy;
import java.util.ArrayList;
import java.util.List;
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
        Hand hand = createHandWithScoreTotal21();

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
        Hand hand = createHandWithScoreTotal21();
        hand.append(new Card(CardRank.ACE, CardSuit.HEART));

        Participant player = new Participant(hand, new PlayerHitStrategy());

        // when
        player.hit(card);

        // then
        assertThat(hand.getCards()).doesNotContain(card);
    }

    private Hand createHandWithScoreTotal21() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardRank.TEN, CardSuit.HEART));
        cards.add(new Card(CardRank.NINE, CardSuit.HEART));
        cards.add(new Card(CardRank.TWO, CardSuit.HEART));
        return new Hand(cards);
    }

    @DisplayName("딜러의 히트 조건에 부합하면 핸드에 카드를 추가한다.")
    @Test
    void testDealerHit() {
        // given
        Hand hand = createHandWithScoreTotal16();

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
        Hand hand = createHandWithScoreTotal16();
        hand.append(new Card(CardRank.ACE, CardSuit.HEART));

        Participant dealer = new Participant(hand, new DealerHitStrategy());

        // when
        dealer.hit(card);

        // then
        assertThat(hand.getCards()).doesNotContain(card);
    }

    private Hand createHandWithScoreTotal16() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardRank.TEN, CardSuit.HEART));
        cards.add(new Card(CardRank.SIX, CardSuit.HEART));
        return new Hand(cards);
    }
}
