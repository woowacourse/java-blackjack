package domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class PlayingCardsTest {
    private PlayingCards playingCards;

    @BeforeEach
    void setUp() {
        playingCards = new PlayingCards(new ArrayList<>());
    }

    @Test
    @DisplayName("PlayingCards 생성")
    void cards() {
        PlayingCards playingCards = new PlayingCards(Collections.singletonList(new Card(Symbol.TWO, Type.HEART)));
        assertThat(playingCards).isNotNull();
    }

    @Test
    @DisplayName("Cards에 카드 추가")
    void add() {
        Card card = new Card(Symbol.TEN, Type.DIAMOND);
        playingCards.add(card);
        assertThat(playingCards.getCards()).isEqualTo(Collections.singletonList(card));
    }

    @Test
    @DisplayName("Cards의 Score를 계산")
    void calculateScore() {
        playingCards.add(new Card(Symbol.TEN, Type.DIAMOND));
        playingCards.add(new Card(Symbol.FIVE, Type.DIAMOND));
        assertThat(playingCards.calculateScore()).isEqualTo(15);
    }

    @Test
    @DisplayName("버스트에 따라서 ACE값이 결정되서 계산한다")
    void calculateWithAceNotBurst() {
        playingCards.add(new Card(Symbol.TEN, Type.DIAMOND));
        playingCards.add(new Card(Symbol.ACE, Type.DIAMOND));
        assertThat(playingCards.calculateScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("버스트에 따라서 ACE값이 결정되서 계산한다")
    void calculateWithAceOnBurst() {
        playingCards.add(new Card(Symbol.TEN, Type.DIAMOND));
        playingCards.add(new Card(Symbol.NINE, Type.CLOVER));
        playingCards.add(new Card(Symbol.ACE, Type.SPADE));
        assertThat(playingCards.calculateScore()).isEqualTo(20);
    }

    @Test
    @DisplayName("버스트에 따라서 ACE값이 결정되서 계산한다")
    void calculateWithAceOnBurst1() {
        playingCards.add(new Card(Symbol.NINE, Type.CLOVER));
        playingCards.add(new Card(Symbol.ACE, Type.SPADE));
        playingCards.add(new Card(Symbol.ACE, Type.SPADE));
        assertThat(playingCards.calculateScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("카드의 갯수를 구한다")
    void size() {
        playingCards.add(new Card(Symbol.NINE, Type.CLOVER));
        playingCards.add(new Card(Symbol.ACE, Type.SPADE));
        assertThat(playingCards.countCards()).isEqualTo(2);
    }

    @Test
    @DisplayName("버스트라는 걸 확인한다")
    void isBust() {
        playingCards.add(new Card(Symbol.NINE, Type.CLOVER));
        playingCards.add(new Card(Symbol.TEN, Type.SPADE));
        playingCards.add(new Card(Symbol.EIGHT, Type.SPADE));

        assertThat(playingCards.isBust()).isTrue();
    }

    @Test
    @DisplayName("버스트가 아닌 걸 확인한다")
    void isNotBust() {
        playingCards.add(new Card(Symbol.NINE, Type.CLOVER));
        playingCards.add(new Card(Symbol.TEN, Type.SPADE));

        assertThat(playingCards.isNotBust()).isTrue();
    }

    @Test
    @DisplayName("블랙잭인 것을 확인한다")
    void isBlackJack() {
        playingCards.add(new Card(Symbol.ACE, Type.CLOVER));
        playingCards.add(new Card(Symbol.TEN, Type.SPADE));

        assertThat(playingCards.isBlackJack()).isTrue();
    }

    @Test
    @DisplayName("카드 장수가 2장이 아닐 경우 블랙잭이 아닌 것을 확인한다")
    void isNotBlackJack1() {
        playingCards.add(new Card(Symbol.TWO, Type.CLOVER));
        playingCards.add(new Card(Symbol.TEN, Type.SPADE));
        playingCards.add(new Card(Symbol.NINE, Type.SPADE));

        assertThat(playingCards.isNotBlackJack()).isTrue();
    }

    @Test
    @DisplayName("점수가 21점이 아닐 경우 블랙잭이 아닌 것을 확인한다")
    void isNotBlackJack2() {
        playingCards.add(new Card(Symbol.TWO, Type.CLOVER));
        playingCards.add(new Card(Symbol.NINE, Type.SPADE));

        assertThat(playingCards.isNotBlackJack()).isTrue();
    }
}
