package blackjack.blackjack;

import static blackjack.fixture.TestFixture.provide16Cards;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.blackjack.participant.Dealer;
import blackjack.blackjack.participant.Participants;
import blackjack.blackjack.participant.Player;
import blackjack.blackjack.participant.Players;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    @Test
    void 플레이어가_존재하면_true를_반환한다() {
        // Given
        BlackjackGame blackjackGame = new BlackjackGame(new Participants(new Dealer(), new Players(
                List.of(new Player(provide16Cards(), "밍트", BigDecimal.valueOf(3000))))));

        // When & Then
        assertThat(blackjackGame.isPlaying()).isTrue();
    }

    @Test
    void 플레이어가_존재하지_않으면_false를_반환한다() {
        // Given
        BlackjackGame blackjackGame = new BlackjackGame(new Participants(new Dealer(), new Players(
                List.of(new Player(provide16Cards(), "밍트", BigDecimal.valueOf(3000))))));
        blackjackGame.findCurrentTurnPlayer();

        // When & Then
        assertThat(blackjackGame.isPlaying()).isFalse();
    }

    @Test
    void 현재_턴의_플레이어를_반환한다() {
        // Given
        Player mint = new Player(provide16Cards(), "밍트", BigDecimal.valueOf(3000));
        BlackjackGame blackjackGame = new BlackjackGame(new Participants(new Dealer(), new Players(List.of(mint))));

        // When & Then
        assertThat(blackjackGame.findCurrentTurnPlayer()).isEqualTo(mint);
    }
}
