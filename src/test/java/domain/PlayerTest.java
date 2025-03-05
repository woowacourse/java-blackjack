package domain;

import static org.assertj.core.api.Assertions.assertThat;

import config.CardDeckFactory;
import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    @DisplayName("카드 두 장 뽑기 테스트")
    void hitCardsTest() {
        // given
        Player player = new Player("pobi");
        CardDeckFactory cardDeckFactory = new CardDeckFactory();
        CardDeck cardDeck = cardDeckFactory.create();
        Dealer dealer = new Dealer(cardDeck);

        // when
        player.hitCards(dealer);

        // then
        assertThat(player.getCardDeck().getCardsSize()).isEqualTo(2);
    }
}
