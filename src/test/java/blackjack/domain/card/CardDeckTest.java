package blackjack.domain.card;

import blackjack.domain.player.Player;
import org.junit.jupiter.api.Test;

import static blackjack.fixture.PlayerFixture.player;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class CardDeckTest {
    @Test
    void 덱에_정확히_52장의_카드가_존재한다() {
        CardDeck deck = CardDeck.createShuffledDeck();
        Player player = player();

        for (int ignored = 0; ignored < 52; ignored++) {
            deck.giveCard(player);
        }

        assertThatThrownBy(() -> deck.giveCard(player))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("카드가 존재하지 않습니다.");
    }

    @Test
    void 카드_한_장을_플레이어에게_지급한다() {
        CardDeck deck = CardDeck.createShuffledDeck();
        Player player = player();

        deck.giveCard(player);

        assertThat(player.getCards().size()).isEqualTo(1);
    }

    @Test
    void 플레이어에게_최초_2개의_카드를_지급한다() {
        CardDeck deck = CardDeck.createShuffledDeck();
        Player player = player();

        deck.giveInitialCards(player);

        assertThat(player.getCards().size()).isEqualTo(2);
    }
}
