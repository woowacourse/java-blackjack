package domain.card;


import domain.Rank;
import domain.Score;
import domain.Suit;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HandTest {
    @Test
    void 카드_더미가_정상적으로_생성되어야_한다() {
        Assertions.assertThatNoException().isThrownBy(Hand::createEmpty);
    }

    @Test
    void 카드가_정상적으로_추가되어야_한다() {
        //given
        Card card = Card.of(Suit.SPADE, Rank.ACE);
        Hand hand = Hand.createEmpty();

        //when
        hand.add(card);

        //then
        Assertions.assertThat(hand.size()).isEqualTo(1);
    }

    @Test
    void 여러_개의_카드가_정상적으로_추가되어야_한다() {
        //given
        List<Card> threeCards = List.of(Card.of(Suit.SPADE, Rank.ACE), Card.of(Suit.SPADE, Rank.Q),
                Card.of(Suit.SPADE, Rank.K));
        Hand hand = Hand.createEmpty();

        // when
        hand.addAll(threeCards);

        //then
        Assertions.assertThat(hand.size()).isEqualTo(3);
    }

    @Test
    void getCards_수행_시_방어적_복사가_수행되어야_한다() {
        //given
        List<Card> threeCards = List.of(Card.of(Suit.SPADE, Rank.ACE), Card.of(Suit.SPADE, Rank.Q),
                Card.of(Suit.SPADE, Rank.K));

        Hand original = Hand.createEmpty();
        original.addAll(threeCards);
        List<Card> copied = original.getCards();

        // when
        original.add(Card.of(Suit.SPADE, Rank.TWO));

        // then
        Assertions.assertThat(copied.size()).isEqualTo(3);
    }

    @Test
    void 가장_처음에_받은_카드를_확인할_수_있다() {
        //given
        List<Card> threeCards = List.of(Card.of(Suit.SPADE, Rank.ACE), Card.of(Suit.SPADE, Rank.Q),
                Card.of(Suit.SPADE, Rank.K));

        Hand hand = Hand.createEmpty();
        hand.addAll(threeCards);

        // when
        Card peek = hand.peek();

        // then
        Assertions.assertThat(peek).isEqualTo(Card.of(Suit.SPADE, Rank.ACE));
    }

    @Test
    void 블랙잭인_경우_참을_반환해야_한다() {
        //given
        List<Card> blackjackCards = List.of(Card.of(Suit.SPADE, Rank.ACE), Card.of(Suit.SPADE, Rank.Q));

        Hand hand = Hand.createEmpty();
        hand.addAll(blackjackCards);

        // when & then
        Assertions.assertThat(hand.isBlackjack()).isEqualTo(true);
    }


    @Test
    void 블랙잭이_아닌_경우_거짓을_반환해야_한다() {
        //given
        List<Card> notBlackjackCards = List.of(Card.of(Suit.SPADE, Rank.ACE), Card.of(Suit.SPADE, Rank.Q),
                Card.of(Suit.SPADE, Rank.THREE));

        Hand hand = Hand.createEmpty();
        hand.addAll(notBlackjackCards);

        // when & then
        Assertions.assertThat(hand.isBlackjack()).isEqualTo(false);
    }

    @Test
    void 손패의_합계를_구할_수_있다_성공() {
        //given
        List<Card> sum14Cards = List.of(Card.of(Suit.SPADE, Rank.ACE), Card.of(Suit.SPADE, Rank.Q),
                Card.of(Suit.SPADE, Rank.THREE));

        Hand hand = Hand.createEmpty();
        hand.addAll(sum14Cards);

        // when & then
        Assertions.assertThat(hand.totalSum()).isEqualTo(new Score(14));
    }

    @Test
    void 손패의_합계를_구할_수_있다_실패() {
        //given
        List<Card> sum14Cards = List.of(Card.of(Suit.SPADE, Rank.ACE), Card.of(Suit.SPADE, Rank.Q),
                Card.of(Suit.SPADE, Rank.THREE));

        Hand hand = Hand.createEmpty();
        hand.addAll(sum14Cards);

        // when & then
        Assertions.assertThat(hand.totalSum()).isNotEqualTo(new Score(17));
    }

    @Test
    void 버스트인_경우_참을_반환해야_한다() {
        //given
        List<Card> bustCards = List.of(Card.of(Suit.SPADE, Rank.J), Card.of(Suit.SPADE, Rank.Q),
                Card.of(Suit.SPADE, Rank.K));

        Hand hand = Hand.createEmpty();
        hand.addAll(bustCards);

        // when & then
        Assertions.assertThat(hand.isBust()).isEqualTo(true);
    }

    @Test
    void 버스트가_아니면_거짓을_반환해야_한다() {
        //given
        List<Card> notBustCards = List.of(Card.of(Suit.SPADE, Rank.ACE), Card.of(Suit.SPADE, Rank.Q),
                Card.of(Suit.SPADE, Rank.K));

        Hand hand = Hand.createEmpty();
        hand.addAll(notBustCards);

        // when & then
        Assertions.assertThat(hand.isBust()).isEqualTo(false);
    }
}
