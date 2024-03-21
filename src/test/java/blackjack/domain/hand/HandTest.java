package blackjack.domain.hand;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import fixture.CardFixture;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {

    @DisplayName("카드를 지급 받는다.")
    @Test
    void testAppend() {
        // given
        Hand hand = new Hand(new ArrayList<>());

        // when
        hand.append(CardFixture.createAHeart());

        // then
        assertThat(hand.getCards()).containsExactly(CardFixture.createAHeart());
    }

    @DisplayName("두 장의 카드를 지급 받는다.")
    @Test
    void testAppendInitial() {
        // given
        CardDeck cardDeck = CardDeck.createShuffledFullCardDeck();
        Hand hand = new Hand();

        // when
        hand.appendInitial(cardDeck);

        // then
        assertThat(hand.getCards()).hasSize(2);
    }

    @DisplayName("핸드에 Ace가 있을 때 합이 21을 초과하지 않으면 Ace는 11로 계산한다.")
    @Test
    void testCalculateScoreAcePresentAndNotBust() {
        // given
        List<Card> cards = new ArrayList<>();
        cards.add(CardFixture.createAHeart());
        cards.add(CardFixture.createADiamond());
        cards.add(CardFixture.create9Heart());
        Hand hand = new Hand(cards);

        // when
        Score score = hand.calculateHandScore();

        // then
        assertThat(score).isEqualTo(new Score(21));
    }

    @DisplayName("핸드에 Ace가 있을 때 합이 21을 초과하면 Ace는 11로 계산하지 않는다.")
    @Test
    void testCalculateScoreAcePresentAndBust() {
        // given
        List<Card> cards = new ArrayList<>();
        cards.add(CardFixture.createAHeart());
        cards.add(CardFixture.create2Heart());
        cards.add(CardFixture.create9Heart());
        Hand hand = new Hand(cards);

        // when
        Score score = hand.calculateHandScore();

        // then
        assertThat(score).isEqualTo(new Score(12));
    }

    @DisplayName("핸드에 Ace가 없으면 합을 변경하지 않는다.")
    @Test
    void testCalculateScoreAceAbsent() {
        // given
        List<Card> cards = new ArrayList<>();
        cards.add(CardFixture.create2Heart());
        cards.add(CardFixture.create9Heart());
        Hand hand = new Hand(cards);

        // when
        Score score = hand.calculateHandScore();

        // then
        assertThat(score).isEqualTo(new Score(11));
    }

    @DisplayName("처음 두 장의 카드 합이 21일 경우 블랙잭이다.")
    @Test
    void testIsBlackjack() {
        // given
        Hand hand = new Hand();
        hand.append(CardFixture.createAHeart());
        hand.append(CardFixture.createKHeart());

        // when
        boolean actual = hand.isBlackjack();

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("카드 합이 21이나 처음 두 장이 아니면 블랙잭이 아니다.")
    @Test
    void testIsNotBlackjackWithNonInitialHand() {
        // given
        Hand hand = new Hand();
        hand.append(CardFixture.createAHeart());
        hand.append(CardFixture.create2Heart());
        hand.append(CardFixture.create9Heart());

        // when
        boolean actual = hand.isBlackjack();

        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("카드 합이 21이 아니면 블랙잭이 아니다.")
    @Test
    void testIsNotBlackjackWithDifferentHandScore() {
        // given
        Hand hand = new Hand();
        hand.append(CardFixture.createAHeart());
        hand.append(CardFixture.create9Heart());

        // when
        boolean actual = hand.isBlackjack();

        // then
        assertThat(actual).isFalse();
    }
}
