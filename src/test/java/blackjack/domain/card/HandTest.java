package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.game.Score;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.EnumSource.Mode;
import org.junit.jupiter.params.provider.ValueSource;

class HandTest {
    private static final int ACE_ADJUST_AMOUNT = 10;
    private static final int BUST_THRESHOLD = 21;
    private static final int BLACKJACK_SCORE = 21;

    private final Card card = new Card(Rank.ACE, Suit.CLOVER);

    @Test
    void 한장의_카드를_받아서_손패에_추가한다() {
        // given
        Hand hand = new Hand();
        List<Card> existCards = hand.getCards();
        // when
        hand.addCard(card);
        // then
        List<Card> addedCards = hand.getCards();
        assertThat(addedCards.size()).isEqualTo(existCards.size() + 1);
    }

    @ParameterizedTest
    @EnumSource(Rank.class)
    void 에이스를_11로_계산했을_때_버스트가_되지_않는다면_총점에_11로_반영된다(Rank rank) {
        // given
        List<Card> cards = List.of(
            new Card(Rank.ACE, Suit.CLOVER),
            new Card(rank, Suit.DIAMOND)
        );
        int sum = cards.stream().mapToInt(Card::getValue).sum();
        // when
        Hand hand = new Hand(cards);
        Score score = hand.calculateScore();
        // then
        assertThat(score.value()).isEqualTo(sum + ACE_ADJUST_AMOUNT);
    }

    @ParameterizedTest
    @EnumSource(Rank.class)
    void 에이스를_11로_계산했을_때_버스트_되면_총점에_1로_반영된다(Rank rank) {
        // given
        List<Card> cards = List.of(
            new Card(Rank.ACE, Suit.CLOVER),
            new Card(Rank.TEN, Suit.HEART),
            new Card(rank, Suit.DIAMOND)
        );
        int sum = cards.stream().mapToInt(Card::getValue).sum();
        // when
        Hand hand = new Hand(cards);
        Score score = hand.calculateScore();
        // then
        assertThat(score.value()).isEqualTo(sum);
    }

    @ParameterizedTest
    @EnumSource(value = Rank.class, mode = Mode.EXCLUDE, names = "ACE")
    void 에이스가_포함되지_않은_경우_주어진_카드_점수의_합을_최종_점수로_확정한다(Rank rank) {
        // given
        List<Card> cards = List.of(new Card(rank, Suit.CLOVER));
        int sum = rank.getValue();
        // when
        Hand hand = new Hand(cards);
        Score score = hand.calculateScore();
        // then
        assertThat(score.value()).isEqualTo(sum);
    }

    @Test
    void 점수가_임계점을_넘으면_버스트로_판단한다() {
        // given
        List<Card> cards = List.of(
            new Card(Rank.TEN, Suit.HEART),
            new Card(Rank.TEN, Suit.HEART),
            new Card(Rank.TWO, Suit.HEART)
        );
        int sum = cards.stream().mapToInt(Card::getValue).sum();
        Hand hand = new Hand(cards);
        // when & then
        assertThat(sum > BUST_THRESHOLD).isTrue();
        assertThat(hand.isBust()).isTrue();
    }

    @Test
    void 점수가_임계점_이하면_버스트로_판단하지_않는다() {
        // given
        List<Card> cards = List.of(
            new Card(Rank.ACE, Suit.HEART),
            new Card(Rank.TEN, Suit.HEART)
        );
        int sum = cards.stream().mapToInt(Card::getValue).sum();
        Hand hand = new Hand(cards);
        // when & then
        assertThat(sum <= BUST_THRESHOLD).isTrue();
        assertThat(hand.isBust()).isFalse();
    }

    @Test
    void 점수와_카드수_모두_블랙잭_조건이면_블랙잭이다() {
        // given
        List<Card> cards = List.of(
            new Card(Rank.ACE, Suit.HEART),
            new Card(Rank.TEN, Suit.HEART)
        );
        Hand hand = new Hand(cards);
        // when & then
        assertThat(hand.calculateScore().value()).isEqualTo(BLACKJACK_SCORE);
        assertThat(hand.isBlackjack()).isTrue();
    }

    @Test
    void 점수만_블랙잭_조건이면_블랙잭이_아니다() {
        // given
        List<Card> cards = List.of(
            new Card(Rank.ACE, Suit.HEART),
            new Card(Rank.ACE, Suit.HEART),
            new Card(Rank.NINE, Suit.HEART)
        );
        Hand hand = new Hand(cards);
        // when & then
        assertThat(hand.calculateScore().value()).isEqualTo(BLACKJACK_SCORE);
        assertThat(hand.isBlackjack()).isFalse();
    }

    @Test
    void 카드수만_블랙잭_조건이면_블랙잭이_아니다() {
        // given
        List<Card> cards = List.of(
            new Card(Rank.ACE, Suit.HEART),
            new Card(Rank.NINE, Suit.HEART)
        );
        Hand hand = new Hand(cards);
        // when & then
        assertThat(hand.calculateScore().value()).isNotEqualTo(BLACKJACK_SCORE);
        assertThat(hand.isBlackjack()).isFalse();
    }

    @Test
    void 점수와_카드수_모두_블랙잭_조건이_아니면_블랙잭이_아니다() {
        // given
        List<Card> cards = List.of(
            new Card(Rank.ACE, Suit.HEART),
            new Card(Rank.TWO, Suit.HEART),
            new Card(Rank.THREE, Suit.HEART)
        );
        Hand hand = new Hand(cards);
        // when & then
        assertThat(hand.calculateScore().value()).isNotEqualTo(BLACKJACK_SCORE);
        assertThat(hand.isBlackjack()).isFalse();
    }
}
