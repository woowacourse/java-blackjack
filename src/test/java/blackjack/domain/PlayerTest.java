package blackjack.domain;

import blackjack.domain.card.CardDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {
    @DisplayName("플레이어는 이름을 받아 생성된다.")
    @Test
    void playerCreationTest() {
        Player player = new Player("youngE");
        assertThat(player).isInstanceOf(Player.class);
    }

    @DisplayName("플레이어 이름에 공백이 입력되는 경우 예외를 발생시킨다.")
    @Test
    void playerCreationErrorTest() {
        assertThatThrownBy(() -> {
            new Player(" ");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("히트 - 플레이어는 카드를 받는다.")
    @Test
    void hitCard() {
        CardDeck cardDeck = CardDeck.createDeck();
        Player player = new Player("Player");
        player.hit(cardDeck.drawCard(), cardDeck.drawCard());
        player.hit(cardDeck.drawCard());

        assertThat(player.getCards()).hasSize(3);
    }
}