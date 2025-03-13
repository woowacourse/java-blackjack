package blackjack.gamer;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.card.Card;
import blackjack.card.Denomination;
import blackjack.card.Shape;
import blackjack.cardMachine.CardMachine;
import blackjack.cardMachine.CardRandomMachine;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("52장이 곂치지 않게 뽑힌다.")
    @Test
    void drawEveryCard() {
        // given
        final Dealer dealer = new Dealer(new CardRandomMachine());

        // when
        dealer.initCardMachine();
        final List<Card> deck = new ArrayList<>();
        for (int i = 0; i < 52; i++) {
            deck.add(dealer.spreadOneCard());
        }
        final Set<Card> uniqueCards = new HashSet<>(deck);

        // then
        assertThat(uniqueCards.size() == deck.size()).isTrue();
    }

    public static class TestCardRandomMachine implements CardMachine {

        @Override
        public void receiveDeck(final List<Card> deck) {

        }

        @Override
        public Card drawOneCard() {
            return new Card(Shape.CLOB, Denomination.ACE);
        }

    }

    @DisplayName("카드 한 장을 랜덤하게 뽑는다.")
    @Test
    void drawRandomCard() {
        // given
        final Dealer dealer = new Dealer(new TestCardRandomMachine());

        // when & then
        assertThat(dealer.spreadOneCard()).isEqualTo(new Card(Shape.CLOB, Denomination.ACE));
    }
}
