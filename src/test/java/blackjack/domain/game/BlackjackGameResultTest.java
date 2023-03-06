package blackjack.domain.game;

import static blackjack.domain.player.Result.LOSE;
import static blackjack.domain.player.Result.PUSH;
import static blackjack.domain.player.Result.WIN;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Deck;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Player;
import blackjack.domain.player.Result;
import blackjack.util.FixedDeck;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class BlackjackGameResultTest {

    @Test
    void 게임_결과를_반환한다() {
        final Deck deck = FixedDeck.getFullDeck();
        final LinkedHashMap<Player, Result> map = new LinkedHashMap<>();
        map.put(Gambler.create("허브", deck), WIN);
        map.put(Gambler.create("후추", deck), LOSE);
        final BlackjackGameResult blackjackGameResult = new BlackjackGameResult(map);

        final Map<Player, Result> result = blackjackGameResult.getResult();

        assertThat(result.values()).containsExactly(WIN, LOSE);
    }

    @Test
    void 딜러_승리_수를_반환한다() {
        final Deck deck = FixedDeck.getFullDeck();
        final BlackjackGameResult blackjackGameResult = new BlackjackGameResult(Map.of(
                Gambler.create("허브", deck), LOSE,
                Gambler.create("후추", deck), LOSE
        ));

        final int result = blackjackGameResult.getDealerWinCount();

        assertThat(result).isEqualTo(2);
    }

    @Test
    void 딜러_무승부_수를_반환한다() {
        final Deck deck = FixedDeck.getFullDeck();
        final BlackjackGameResult blackjackGameResult = new BlackjackGameResult(Map.of(
                Gambler.create("허브", deck), PUSH,
                Gambler.create("후추", deck), PUSH
        ));

        final int result = blackjackGameResult.getDealerPushCount();

        assertThat(result).isEqualTo(2);
    }

    @Test
    void 딜러_패배_수를_반환한다() {
        final Deck deck = FixedDeck.getFullDeck();
        final BlackjackGameResult blackjackGameResult = new BlackjackGameResult(Map.of(
                Gambler.create("허브", deck), WIN,
                Gambler.create("후추", deck), WIN
        ));

        final int result = blackjackGameResult.getDealerLoseCount();

        assertThat(result).isEqualTo(2);
    }
}
