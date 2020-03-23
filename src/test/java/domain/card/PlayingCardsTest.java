package domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

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
        Cards cardsToAdd = Cards.of(cards);

        //when
        PlayingCards playingCards = this.playingCards.add(cardsToAdd);

        //then
        assertThat(playingCards.size()).isEqualTo(defaultSizeOfPlayingCards + cardsToAdd.size());
    }

    @ParameterizedTest
    @MethodSource({"getCasesForTestingCalculate"})
    @DisplayName("#calculate : should return sum input card scores")
    void calculate(List<Card> cards, int expected) {
        PlayingCards playingCards = PlayingCards.of(cards);
        int sum = playingCards.calculate();
        assertThat(sum).isEqualTo(expected);

    }

    private static Stream<Arguments> getCasesForTestingCalculate() {
        int jokerValue = 11;
        return Stream.of(
                Arguments.of(Arrays.asList(new Card(Symbol.QUEEN, Type.SPADE), new Card(Symbol.NINE, Type.SPADE)),
                        Symbol.QUEEN.getValue() + Symbol.NINE.getValue(), "without ace, not bust"),
                Arguments.of(Arrays.asList(new Card(Symbol.QUEEN, Type.SPADE), new Card(Symbol.NINE, Type.SPADE), new Card(Symbol.THREE, Type.SPADE)),
                        Symbol.QUEEN.getValue() + Symbol.NINE.getValue() + Symbol.THREE.getValue(), "without ace, bust"),
                Arguments.of(Arrays.asList(new Card(Symbol.QUEEN, Type.SPADE), new Card(Symbol.ACE, Type.SPADE)),
                        Symbol.QUEEN.getValue() + jokerValue, "with ace, not bust, joker"),
                Arguments.of(Arrays.asList(new Card(Symbol.QUEEN, Type.SPADE), new Card(Symbol.TWO, Type.SPADE), new Card(Symbol.ACE, Type.SPADE)),
                        Symbol.QUEEN.getValue() + Symbol.TWO.getValue() + Symbol.ACE.getValue(), "with ace, not bust, not joker"),
                Arguments.of(Arrays.asList(new Card(Symbol.QUEEN, Type.SPADE), new Card(Symbol.ACE, Type.SPADE), new Card(Symbol.ACE, Type.DIAMOND)),
                        Symbol.QUEEN.getValue() + jokerValue + Symbol.ACE.getValue(), "with ace, bust, joker and not joker")
        );
    }
}
