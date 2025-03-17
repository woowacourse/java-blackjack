package participant;

import card.Card;
import card.CardNumber;
import card.CardType;
import deck.Deck;
import deck.DeckCreateStrategy;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러는 카드 패의 합이 16이 넘으면 카드를 받을 수 없다.")
    void test1() {
        // given
        Deck deck = new Deck(new DeckCreateStrategy() {
            @Override
            public List<Card> createAllCards() {
                return List.of(
                        new Card(CardType.DIAMOND, CardNumber.TEN),
                        new Card(CardType.DIAMOND, CardNumber.SEVEN)
                );
            }
        });

        Dealer dealer = new Dealer();
        dealer.prepareGame(deck.draw(), deck.draw());

        // when
        boolean result = dealer.canReceiveCard();

        // then
        Assertions.assertThat(result)
                .isFalse();
    }

    @Test
    @DisplayName("딜러는 카드 패의 합이 16 넘지않으면 카드를 받을 수 있다.")
    void test2() {
        // given
        Deck deck = new Deck(new DeckCreateStrategy() {
            @Override
            public List<Card> createAllCards() {
                return List.of(
                    new Card(CardType.DIAMOND, CardNumber.THREE),
                    new Card(CardType.SPADE, CardNumber.THREE)
                );
            }
        });

        Dealer dealer = new Dealer();
        dealer.prepareGame(deck.draw(), deck.draw());

        // when
        boolean result = dealer.canReceiveCard();

        // then
        Assertions.assertThat(result)
                .isTrue();
    }
}
