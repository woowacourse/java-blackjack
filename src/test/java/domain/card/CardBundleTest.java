package domain.card;

import domain.dealer.Dealer;
import domain.player.Player;
import domain.player.PlayerName;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CardBundleTest {

    @Test
    void 플레이어의_카드에_에이스가_있는지_확인한다() {
        Card cloverAce = Card.of(CardDenomination.ACE, CardEmblem.CLOVER);
        Card spade2 = Card.of(CardDenomination.TWO, CardEmblem.SPADE);
        Card heart3 = Card.of(CardDenomination.THREE, CardEmblem.HEART);
        List<Card> cards = List.of(cloverAce, spade2, heart3);
        CardBundle cardBundle = CardBundle.of(cards);

        Assertions.assertThat(cardBundle.hasAce()).isTrue();
    }

}
