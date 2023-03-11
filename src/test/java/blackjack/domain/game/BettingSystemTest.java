package blackjack.domain.game;

import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.util.FixedDeck;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static blackjack.util.CardFixtures.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class BettingSystemTest {

    @Test
    void 겜블러의_수입을_반환한다() {
        final Players players = Players.from(List.of("후추", "허브"));
        Map<Player, Money> betMoneyByPlayer = new HashMap<>();
        for (Player player : players.getPlayers()) {
            betMoneyByPlayer.put(player, Money.createMoneyForBetting(10000));
        }
        final BettingSystem bettingSystem = new BettingSystem(betMoneyByPlayer);

        final BlackjackGame blackjackGame = new BlackjackGame(players, bettingSystem);
        blackjackGame.drawInitialCards(new FixedDeck(List.of(
                ACE_DIAMOND,
                JACK_SPADE,
                EIGHT_SPADE,
                NINE_SPADE,
                KING_SPADE,
                QUEEN_SPADE
        )));

        assertThat(bettingSystem.getProfitResult(blackjackGame.play()).values())
                .extracting(Money::getAmount)
                .containsExactly(-10000, -10000);
    }
}
