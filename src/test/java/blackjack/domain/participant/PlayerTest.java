package blackjack.domain.participant;

import static blackjack.domain.deck.Kind.SPADE;
import static blackjack.domain.deck.Value.ACE;
import static blackjack.domain.deck.Value.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.deck.Card;
import blackjack.domain.deck.Deck;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("플레이어가 카드 추가를 승낙하면, 카드패에 한장이 추가된다")
    @Test
    void should_AddCard_When_PlayerAcceptDraw() {
        Deck deck = Deck.createShuffledDeck();
        Player testPlayer = Player.createPlayer(new Name("pobi"),
                List.of(new Card(SPADE, ACE),
                        new Card(SPADE, TWO)),
                new BetMoney(1));

        testPlayer.draw(() -> Boolean.TRUE, deck);

        assertThat(testPlayer.getHandsCards()).hasSize(3);
    }

    @DisplayName("플레이어가 카드 추가를 거부하면, 카드는 추가되지 않는다")
    @Test
    void should_NotAddCard_When_PlayerRejectDraw() {
        Deck deck = Deck.createShuffledDeck();
        Player testPlayer = Player.createPlayer(new Name("pobi"),
                List.of(new Card(SPADE, ACE),
                        new Card(SPADE, TWO)),
                new BetMoney(1));

        testPlayer.draw(() -> Boolean.FALSE, deck);

        assertThat(testPlayer.getHandsCards()).hasSize(2);
    }
}
