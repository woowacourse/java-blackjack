package blackjack.domain.betting;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BettingManagerTest {

    @Test
    void 이름을_통해_배팅_정보를_찾을_수_있다() {
        final BettingManager bettingManager = new BettingManager();
        bettingManager.registerBetting("kokodak", 10_000);

        final Betting betting = bettingManager.findBettingByName("kokodak");

        Assertions.assertThat(betting.getAmount()).isEqualTo(10_000);
    }
}
