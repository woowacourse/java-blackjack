package blackjack.domain.user;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.CardFixtures;
import blackjack.domain.card.Card;

class HandTest {

    @Test
    @DisplayName("비어있는 카드와 점수를 갖는 핸드를 생성한다.")
    public void createEmptyHand() {
        // when
        Hand hand = new Hand();

        // then
        assertThat(hand).isNotNull();
    }

    @Test
    @DisplayName("카드를 추가한다.")
    public void testAddCardAsImmutable() {
        // given
        Hand hand = new Hand();
        // when
        Hand newHand = hand.addCard(CardFixtures.ACE);
        // then
        assertThat(newHand).isNotNull();
    }

    @Test
    @DisplayName("카드 목록을 얻을 수 있다.")
    public void testGettingCards() {
        // given
        Hand hand = new Hand();
        hand = hand.addCard(CardFixtures.ACE);
        hand = hand.addCard(CardFixtures.SEVEN);

        // when
        List<Card> cards = hand.getCards();
        // then
        assertThat(cards.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("갖고 있는 카드보다 더 많은 카드를 요구할 수 없다.")
    public void throwsExceptionWithOverCount() {
        // given & when
        Hand hand = new Hand()
            .addCard(CardFixtures.ACE)
            .addCard(CardFixtures.SEVEN);

        // then
        assertThatExceptionOfType(IllegalStateException.class)
            .isThrownBy(() -> hand.getCards(3));
    }

    @Test
    @DisplayName("버스트인지 확인한다.")
    public void testCardsIsBust() {
        // given
        Hand hand = new Hand();
        hand = hand.addCard(CardFixtures.JACK);
        hand = hand.addCard(CardFixtures.SEVEN);
        hand = hand.addCard(CardFixtures.TEN);
        // when
        boolean isBust = hand.isBust();
        // then
        assertThat(isBust).isTrue();
    }

    @Test
    @DisplayName("블랙잭인지 확인한다")
    public void testCardsIsBlackJack() {
        // given
        Hand hand = new Hand();
        hand = hand.addCard(CardFixtures.ACE);
        hand = hand.addCard(CardFixtures.TEN);
        // when
        boolean isBlackJack = hand.isBlackJack();
        // then
        assertThat(isBlackJack).isTrue();
    }

    @Test
    @DisplayName("히트인지 확인한다")
    public void testCardsIsHit() {
        // given
        Hand hand = new Hand();
        hand = hand.addCard(CardFixtures.ACE);
        hand = hand.addCard(CardFixtures.TEN);
        hand = hand.addCard(CardFixtures.JACK);
        // when
        boolean isHit = hand.isHit();
        // then
        assertThat(isHit).isTrue();
    }
}