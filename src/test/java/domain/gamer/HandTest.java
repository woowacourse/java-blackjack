package domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import domain.calculatestrategy.PlayerStrategy;
import domain.deck.Card;
import domain.deck.Rank;
import domain.deck.Shape;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {

    private List<Card> cards;
    private Card card1;
    private Card card2;
    private Card card3;
    private Card card4;
    private Card card5;
    private Card card6;
    private Card card7;
    private Card card8;
    private Card card9;
    private Card card10;

    private Hand hand;

    @BeforeEach
    void setUp() {
        cards = new ArrayList<>();
        card1 = new Card(Rank.ACE, Shape.CLOVER);
        card2 = new Card(Rank.TWO, Shape.CLOVER);
        card3 = new Card(Rank.THREE, Shape.CLOVER);
        card4 = new Card(Rank.FOUR, Shape.CLOVER);
        card5 = new Card(Rank.FIVE, Shape.CLOVER);
        card6 = new Card(Rank.SIX, Shape.CLOVER);
        card7 = new Card(Rank.SEVEN, Shape.CLOVER);
        card8 = new Card(Rank.EIGHT, Shape.CLOVER);
        card9 = new Card(Rank.NINE, Shape.CLOVER);
        card10 = new Card(Rank.JACK, Shape.CLOVER);
    }

    @DisplayName("카드를 손에 추가한다.")
    @Test
    void 카드를_손에_추가한다() {

        // given
        final Hand hand = new Hand(new PlayerStrategy());

        // when & then
        assertThatCode(() -> {
            hand.add(card1);
            hand.add(card2);
        }).doesNotThrowAnyException();
    }

    @DisplayName("손에 있는 카드의 합을 가져온다.")
    @Test
    void 손에_있는_카드의_합을_가져온다() {

        // given
        final Hand hand = new Hand(new PlayerStrategy());

        // when
        hand.add(card1);
        hand.add(card2);

        // then
        assertThat(hand.getSumOfRank()).isEqualTo(13);
    }
}
