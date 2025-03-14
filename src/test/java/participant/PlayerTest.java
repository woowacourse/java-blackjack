package participant;

import card.Card;
import card.CardNumber;
import card.CardType;
import card.Cards;
import deck.Deck;
import deck.DeckCreateStrategy;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import state.finished.Bust;
import state.running.Hit;

class PlayerTest {

    @Test
    @DisplayName("플레이어는 bust면 카드를 받을 수 없다.")
    void test1() {
        // given
        Deck deck = new Deck(new DeckCreateStrategy() {
            @Override
            public List<Card> createAllCards() {
                return List.of(
                    new Card(CardType.DIAMOND, CardNumber.FOUR),
                    new Card(CardType.SPADE, CardNumber.TEN),
                    new Card(CardType.CLOVER, CardNumber.TEN)
                );
            }
        });

        Player player = new Player("율무", new Bust(new Cards()));

        // when
        // then
        Assertions.assertThatThrownBy(() -> player.hit(deck.draw()))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("플레이어는 bust가 아니면 카드를 받을 수 있다.")
    void test2() {
        // given
        Deck deck = new Deck(new DeckCreateStrategy() {
            @Override
            public List<Card> createAllCards() {
                return List.of(
                        new Card(CardType.SPADE, CardNumber.TEN),
                        new Card(CardType.CLOVER, CardNumber.TEN)
                );
            }
        });

        Player player = new Player("율무", new Hit(new Cards()));

        // when
        boolean result = player.canReceiveCard();

        // then
        Assertions.assertThat(result)
                .isTrue();
    }
}
