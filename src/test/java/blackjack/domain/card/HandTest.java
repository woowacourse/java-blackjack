package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.game.Score;
import fixture.CardFixture;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    @DisplayName("지정한 개수만큼 지급받은 카드를 공개한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void testRevealHand(int count) {
        // given
        Hand hand = new Hand();
        hand.append(new Card(CardRank.ACE, CardSuit.HEART));
        hand.append(new Card(CardRank.TWO, CardSuit.HEART));
        hand.append(new Card(CardRank.THREE, CardSuit.HEART));

        // when
        List<Card> cards = hand.revealHand(count);

        // then
        assertThat(cards).hasSize(count);
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
        assertThat(score).extracting("value").isEqualTo(21);
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
        assertThat(score).extracting("value").isEqualTo(12);
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
        assertThat(score).extracting("value").isEqualTo(11);
    }
}
