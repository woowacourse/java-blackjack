package domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("블랙잭 게임 ")
class GameDeckTest {
    @Test
    @DisplayName("카드는 52장까지 뽑을 수 있다.")
    void drawCardTest() {
        //given
        GameDeck gameDeck = new GameDeck(new ShuffleDeckGenerator());

        //when
        IntStream.range(0,52).forEach(index -> gameDeck.drawCard());

        //then
        assertThrows(IllegalStateException.class, gameDeck::drawCard);
    }

    @Test
    @DisplayName("시작 직후 카드를 두 장씩 분배한다.")
    void drawForFirstTurnTest() {
        GameDeck gameDeck = new GameDeck(new ShuffleDeckGenerator());

        List<Card> firstTurnCards = gameDeck.drawForFirstTurn();

        assertThat(firstTurnCards.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("카드는 덱에서 순서대로 뽑아간다.")
    void drawCard() {
        //given
        List<Card> cards = new ArrayList<>(List.of(CloverCard.ACE, CloverCard.THREE,
                CloverCard.FIVE));
        GameDeck gameDeck = new GameDeck(new TestDeckGenerator(cards));
        List<Card> drawCards = new ArrayList<>();

        //when
        IntStream.range(0, cards.size()).forEach(trial -> drawCards.add(gameDeck.drawCard()));

        //then
        assertThat(drawCards).isEqualTo(List.of(CloverCard.ACE,
                CloverCard.THREE, CloverCard.FIVE));
    }

    static class TestDeckGenerator implements DeckGenerator {
        private final List<Card> cards;

        public TestDeckGenerator(List<Card> cards) {
            this.cards = cards;
        }

        @Override
        public List<Card> generate() {
            return cards;
        }
    }
}
