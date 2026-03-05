package player;

import domain.card.*;
import domain.dealer.Dealer;
import domain.player.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    void 플레이어가_카드를_1장_받는다() {
        CardDeck cardDeck = new CardDeckBuilder()
                .cards(Card.of(CardEmblem.EIGHT, CardDenomination.CLOVER), Card.of(CardEmblem.NINE, CardDenomination.SPADE),
                        Card.of(CardEmblem.TWO, CardDenomination.SPADE), Card.of(CardEmblem.THREE, CardDenomination.HEART))
                .build();
        Dealer dealer = Dealer.of(cardDeck);
        Player player = Player.from();

        dealer.handOutCardToPlayer(player, 1);
        Card card = Card.of(CardEmblem.EIGHT, CardDenomination.CLOVER);

        Assertions.assertThat(player.hasCard(card)).isTrue();
    }

    @Test
    void 덱에_카드가_없으면_플레이어는_카드를_받을_수_없다() {
        CardDeck cardDeck = new CardDeckBuilder()
                .build();

        Dealer dealer = Dealer.of(cardDeck);
        Player player = Player.from();

        Assertions.assertThatThrownBy(() -> {
            dealer.handOutCardToPlayer(player, 1);
        }).isInstanceOf(IllegalArgumentException.class);
    }

}
