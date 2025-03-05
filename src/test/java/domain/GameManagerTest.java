package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import org.junit.jupiter.api.Test;

class GameManagerTest {

    @Test
    void 게임_메니저를_생성한다() {
        // given
        CardDeck cardDeck = CardDeck.of();
        Participants participants = Participants.of(
                Dealer.of(), List.of(
                        Player.of("pobi1"),
                        Player.of("pobi2"),
                        Player.of("pobi3")
                )
        );

        // when & then
        assertThatCode(() -> GameManager.of(cardDeck, participants))
                .doesNotThrowAnyException();
    }

    @Test
    void 게임을_시작하고_카드를_두_장씩_배부한다() {
        // given
        CardDeck cardDeck = CardDeck.of();
        Participants participants = Participants.of(
                Dealer.of(), List.of(
                        Player.of("pobi1"),
                        Player.of("pobi2"),
                        Player.of("pobi3")
                )
        );
        GameManager gameManager = GameManager.of(cardDeck, participants);

        // when
        gameManager.distributeCards();

        // then
        assertThat(cardDeck.getCards()).hasSize(44);
    }
}