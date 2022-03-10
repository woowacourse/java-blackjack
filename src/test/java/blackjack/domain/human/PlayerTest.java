package blackjack.domain.human;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class PlayerTest {

    @Test
    public void 참여자생성() {
        Player player = Player.of("test");
        assertThat(player.getName()).isEqualTo("test");
    }

    @Test
    public void 참여자에_카드_추가() {
        Player player = Player.of("test");

        Card card5 = Card.of(Denomination.of("5"), Suit.of("스페이드"));
        Card card6 = Card.of(Denomination.of("6"), Suit.of("하트"));
        player.addCard(card5);
        player.addCard(card6);

        assertThat(player.getPoint())
                .isEqualTo(11);
    }
}