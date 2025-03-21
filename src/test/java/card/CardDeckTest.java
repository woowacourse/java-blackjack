package card;

import card.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

class CardDeckTest {

    DeckGenerator testDeckGenerator = new DeckGenerator() {
        @Override
        public List<Card> generateDeck() {
            return new ArrayList<>(
                    List.of(
                            new Card(Suit.DIAMONDS, NormalRank.TEN),
                            new Card(Suit.HEARTS, NormalRank.TEN),
                            new Card(Suit.SPADES, NormalRank.TEN),
                            new Card(Suit.CLUBS, NormalRank.TEN)
                    )
            );
        }
    };

    private final CardDeck shuffledDeck = new CardDeck(new ShuffledDeckGenerator().generateDeck());
    private final CardDeck customDeck = new CardDeck(testDeckGenerator.generateDeck());

    @Test
    @DisplayName("카드덱 객체가 잘 생성되는 지")
    void newCardDeck() {
        // given
        // when
        // then
        Assertions.assertThat(shuffledDeck.getDeck().size()).isEqualTo(52);
    }

    @Test
    @DisplayName("카드 덱에서 카드가 잘 추출 되는지")
    void getRandomCard() {
        //given
        List<Card> cards = List.of(
                new Card(Suit.DIAMONDS, NormalRank.TEN),
                new Card(Suit.HEARTS, NormalRank.TEN),
                new Card(Suit.SPADES, NormalRank.TEN),
                new Card(Suit.CLUBS, NormalRank.TEN)
        );
        //when
        //then
        for (Card card : cards) {
            Assertions.assertThat(customDeck.pickCard()).isEqualTo(card);
        }
    }
}
