package blackjack.domain.human;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Symbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("플레이어 생성 테스트")
    public void createPlayer() {
        Player player = Player.of(Name.of("test"));
        assertThat(player.getName()).isEqualTo("test");
    }

    @Test
    @DisplayName("플레이어 카드 추가 테스트")
    public void addCard_Player() {
        Player player = Player.of(Name.of("test"));

        Card card5 = Card.of(Denomination.of("5"), Symbol.of("스페이드"));
        Card card6 = Card.of(Denomination.of("6"), Symbol.of("하트"));
        player.addCard(card5);
        player.addCard(card6);

        assertThat(player.getPoint())
                .isEqualTo(11);
    }
}
