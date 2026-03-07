package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import blackjack.model.cardDeck.CardDeck;
import blackjack.model.cardDeck.PickStrategy;
import blackjack.model.participant.Player;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    PickStrategy mockStrategy = cards -> Card.opened(Rank.TEN, Suit.CLOVER);
    
    @Test
    @DisplayName("플레이어는 2 장의 오픈된 카드를 뽑는다.")
    void pickInitCards() {
        // given
        Player player = Player.of("player1");
        CardDeck cardDeck = CardDeck.of(mockStrategy);
    
        // when
        player.pickInitCards(cardDeck);

        //then
        List<Card> cards = player.getOpenedCards();
        assertThat(cards.size()).isEqualTo(2);
    }
}