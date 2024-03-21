package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.domain.card.CardScore.*;
import static blackjack.domain.card.CardSuit.CLUB;
import static blackjack.domain.card.CardSuit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("핸드")
public class HandTest {
    @Test
    @DisplayName("는 덱에서 카드 한 장을 뽑는다.")
    void draw() {
        // given
        Hand hand = new Hand(new Deck(cards -> List.of(new Card(SPADE, ACE))));

        // when
        hand.draw();

        // then
        assertThat(hand.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("는 덱에서 카드 두 장을 뽑는다.")
    void drawCount() {
        // given
        Hand hand = new Hand(new Deck(cards -> List.of(new Card(SPADE, ACE), new Card(SPADE, FIVE))));

        // when
        hand.draw(2);

        // then
        assertThat(hand.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("는 덱에서 뽑은 카드 J, Q이면 점수가 20이다.")
    void totalScoreNormal() {
        // given
        Hand hand = new Hand(new Deck(cards -> List.of(new Card(CLUB, JACK), new Card(SPADE, QUEEN))));

        // when
        hand.draw(2);

        // then
        assertThat(hand.totalScore()).isEqualTo(20);
    }

    @Test
    @DisplayName("는 덱에서 뽑은 카드 J, A이면 점수가 21이다.")
    void scoreWithAceNotExceed() {
        // given
        Hand hand = new Hand(new Deck(cards -> List.of(new Card(CLUB, JACK), new Card(SPADE, ACE))));

        // when
        hand.draw(2);

        // then
        assertThat(hand.totalScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("는 덱에서 뽑은 카드 4, J, A이면 점수가 15이다.")
    void scoreWithAceExceed() {
        // given
        Hand hand = new Hand(new Deck(cards ->
                List.of(new Card(CLUB, FOUR), new Card(CLUB, JACK), new Card(SPADE, ACE))));

        // when
        hand.draw(3);

        // then
        assertThat(hand.totalScore()).isEqualTo(15);
    }

    @Test
    @DisplayName("는 덱에서 뽑은 카드 5, A, A이면 점수가 17이다.")
    void scoreWithManyAce() {
        // given
        Hand hand = new Hand(new Deck(cards ->
                List.of(new Card(CLUB, FIVE), new Card(CLUB, ACE), new Card(SPADE, ACE))));

        // when
        hand.draw(3);

        // then
        assertThat(hand.totalScore()).isEqualTo(17);
    }
}
