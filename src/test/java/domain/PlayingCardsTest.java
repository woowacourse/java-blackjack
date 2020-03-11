package domain;

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
}
