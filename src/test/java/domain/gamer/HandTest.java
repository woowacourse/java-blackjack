package domain.gamer;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Symbol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class HandTest {
    Stack<Card> cards;
    Hand hand;

    @BeforeEach
    void init() {
        Card card1 = new Card(Symbol.SPADE, Rank.KING);
        Card card2 = new Card(Symbol.HEART, Rank.THREE);
        Card card3 = new Card(Symbol.CLOVER, Rank.NINE);

        cards = Stream.of(card1, card2, card3)
                .collect(Collectors.toCollection(Stack::new));
        hand = new Hand();
    }

    @DisplayName("뽑은 카드를 저장한다.")
    @Test
    void addCardTest() {
        // given
        Card expectedCard = new Card(Symbol.CLOVER, Rank.NINE);

        // when
        hand.add(cards.pop());
        List<Card> result = hand.getCards();

        // then
        assertAll(
                () -> assertThat(result).hasSize(1),
                () -> assertThat(result.get(0)).isEqualTo(expectedCard)
        );
    }

    @DisplayName("가지고 있는 카드의 총 합계 점수를 구한다.")
    @Test
    void sumScoreTest() {
        // given
        Card aceCard = new Card(Symbol.CLOVER, Rank.BIG_ACE);
        hand.add(aceCard);
        hand.add(cards.pop());
        hand.add(cards.pop());

        int expectedScore = 13;

        // when
        int result = hand.sum();

        // then
        assertThat(result).isEqualTo(expectedScore);
    }

    @DisplayName("점수 합계가 21이하면 버스트가 아니다.")
    @Test
    void isNotBustTest() {
        // given
        hand.add(cards.pop());
        hand.add(cards.pop());

        // when
        boolean result = hand.isOverBlackJack();

        // then
        assertThat(result).isFalse();
    }

    @DisplayName("점수 합계가 21이상이면 버스트다.")
    @Test
    void isBustTest() {
        // given
        hand.add(cards.pop());
        hand.add(cards.pop());
        hand.add(cards.pop());

        // when
        boolean result = hand.isOverBlackJack();

        // then
        assertThat(result).isTrue();
    }
}
