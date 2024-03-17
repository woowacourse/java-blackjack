package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import domain.constants.CardCommand;
import domain.game.deck.Deck;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PlayerTest {
    @DisplayName("카드를 저장한다.")
    @Test
    void saveCard() {
        Player player = new Player("pobi", 1);
        player.pickCard(new Deck(List.of(new Card(Denomination.ACE, Suit.CLOVER))), 1);
        int totalSize = player.cardSize();
        assertThat(totalSize).isEqualTo(1);
    }

    @DisplayName("카드를 받을 수 있는 지 테스트")
    @Nested
    class canPickCard {
        @DisplayName("플레이어의 카드가 이미 21을 초과하였고, 더 받으려 하는 경우 false를 반환한다.")
        @Test
        void hitWhenAlreadyBusted() {
            Player player = new Player("pobi", 1);
            Deck deck = new Deck(List.of(
                    new Card(Denomination.TEN, Suit.CLOVER),
                    new Card(Denomination.TEN, Suit.DIAMOND),
                    new Card(Denomination.THREE, Suit.CLOVER)
            ));
            player.pickCard(deck, 3);

            boolean ableToDrawCard = player.canPickCard(() -> CardCommand.HIT);
            assertThat(ableToDrawCard).isFalse();
        }

        @DisplayName("플레이어의 카드가 이미 21을 초과하였고, 더 받지 않겠다고 한 경우 false를 반환한다.")
        @Test
        void standWhenAlreadyBusted() {
            Player player = new Player("pobi", 1);
            Deck deck = new Deck(List.of(
                    new Card(Denomination.TEN, Suit.CLOVER),
                    new Card(Denomination.TEN, Suit.DIAMOND),
                    new Card(Denomination.THREE, Suit.CLOVER)
            ));
            player.pickCard(deck, 3);

            boolean ableToDrawCard = player.canPickCard(() -> CardCommand.STAND);
            assertThat(ableToDrawCard).isFalse();
        }

        @DisplayName("플레이어의 카드가 21을 초과하지 않았고, 더 받으려 하는 경우 true를 반환한다.")
        @Test
        void hitWhenDoesNotBusted() {
            Player player = new Player("pobi", 1);
            Deck deck = new Deck(List.of(
                    new Card(Denomination.TEN, Suit.CLOVER),
                    new Card(Denomination.TEN, Suit.DIAMOND)
            ));
            player.pickCard(deck, 2);

            boolean ableToDrawCard = player.canPickCard(() -> CardCommand.HIT);
            assertThat(ableToDrawCard).isTrue();
        }

        @DisplayName("플레이어의 카드가 21을 초과하지 않았고, 더 받으려 하는 경우 true를 반환한다.")
        @Test
        void standWhenDoesNotBusted() {
            Player player = new Player("pobi", 1);
            Deck deck = new Deck(List.of(
                    new Card(Denomination.TEN, Suit.CLOVER),
                    new Card(Denomination.TEN, Suit.DIAMOND)
            ));
            player.pickCard(deck, 2);

            boolean ableToDrawCard = player.canPickCard(() -> CardCommand.STAND);
            assertThat(ableToDrawCard).isFalse();
        }
    }
}
