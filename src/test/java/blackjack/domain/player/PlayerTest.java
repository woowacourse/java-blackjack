package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.CardDeck;
import blackjack.domain.hand.Hand;
import fixture.CardFixture;
import fixture.HandFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("플레이어는 21을 넘지 않을 경우 카드를 뽑는다.")
    @Test
    void testDraw() {
        // given
        Hand hand = HandFixture.createHandWithScoreTotal21();
        int beforeHandSize = hand.getCards().size();

        Player player = new Player(new PlayerName("pobi"), hand);

        CardDeck cardDeck = CardDeck.createShuffledFullCardDeck();

        // when
        player.draw(cardDeck);

        // then
        assertThat(player.getHand().getCards()).hasSize(beforeHandSize + 1);
    }

    @DisplayName("플레이어는 21을 넘을 경우 카드를 뽑지 않는다.")
    @Test
    void testDoNotDraw() {
        // given
        Hand hand = HandFixture.createHandWithScoreTotal21();
        hand.append(CardFixture.createAHeart());
        int beforeHandSize = hand.getCards().size();

        Player player = new Player(new PlayerName("pobi"), hand);

        CardDeck cardDeck = CardDeck.createShuffledFullCardDeck();

        // when
        player.draw(cardDeck);

        // then
        assertThat(player.getHand().getCards()).hasSize(beforeHandSize);
    }
}
