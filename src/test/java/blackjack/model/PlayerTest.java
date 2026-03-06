package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    PickStrategy mockStrategy = cards -> Card.opened(Rank.TEN, Suit.CLOVER);

    @Test
    @DisplayName("카드 덱에서 카드를 뽑아서 핸즈에 추가한다.")
    void pickACard() {
        // given
        Player player = Player.of("player1");
        CardDeck cardDeck = CardDeck.of(mockStrategy);

        // when & then
        assertThatCode(() -> player.pickInitCards(cardDeck))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("플레이어의 총 점수가 21 초과이면 true를 반환한다.")
    void isBust() {
        // given
        CardDeck cardDeck = CardDeck.of(mockStrategy);

        Player player = Player.of("player1");

        player.pickInitCards(cardDeck);
        player.pickAdditionalCard(cardDeck);

        // when & then
        assertThat(player.isBust()).isTrue();
    }
}