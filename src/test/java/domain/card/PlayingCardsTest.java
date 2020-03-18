package domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayingCardsTest {
    private PlayingCards playingCards;

    @BeforeEach
    void setUp() {
        playingCards = PlayingCards.of(new ArrayList<>(Arrays.asList(new Card(Symbol.QUEEN, Type.SPADE), new Card(Symbol.QUEEN, Type.CLOVER))));
    }

    @Test
    @DisplayName("PlayingCards 생성")
    void cards() {
        PlayingCards playingCards = PlayingCards.of(Arrays.asList(new Card(Symbol.TWO, Type.HEART), new Card(Symbol.KING, Type.CLOVER)));
        assertThat(playingCards).isNotNull();
    }

    @Test
    @DisplayName("Cards에 카드 추가")
    void add() {
        Card card = new Card(Symbol.TEN, Type.DIAMOND);
        int defaultCardsSize = playingCards.size();
        playingCards.add(card);
        assertThat(playingCards.size()).isEqualTo(defaultCardsSize + 1);
    }

    @Test
    @DisplayName("카드의 갯수를 구한다")
    void size() {
        assertThat(playingCards.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("#add() : should return playingCards with added cards")
    void addPlayingCards() {
        //given
        int defaultSizeOfPlayingCards = playingCards.size();
        List<Card> cards = Arrays.asList(new Card(Symbol.QUEEN, Type.CLOVER), new Card(Symbol.QUEEN, Type.DIAMOND));
        PlayingCards playingCardsToAdd = PlayingCards.of(cards);

        //when
        PlayingCards playingCards = this.playingCards.add(playingCardsToAdd);

        //then
        assertThat(playingCards.size()).isEqualTo(defaultSizeOfPlayingCards + playingCardsToAdd.size());
    }
}
