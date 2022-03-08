package blackjack.domain.human;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Card;
import blackjack.domain.Denomination;
import blackjack.domain.Name;
import blackjack.domain.human.Player;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class PlayerTest {

    @Test
    public void 참여자생성() {
        Player player = Player.of(Name.of("test"));
        assertThat(player.getName()).isEqualTo("test");
    }

    @Test
    public void 참여자에_카드_추가() {
        Player player = Player.of(Name.of("test"));

        Card card5 = Card.of(Denomination.of("5"), "spade");
        Card card6 = Card.of(Denomination.of("6"), "heart");

        player.addCard(card5);
        player.addCard(card6);

        assertThat(player.getPoint())
                .isEqualTo(11);
    }
}