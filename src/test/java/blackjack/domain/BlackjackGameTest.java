package blackjack.domain;

import static blackjack.domain.Rank.ACE;
import static blackjack.domain.Rank.EIGHT;
import static blackjack.domain.Rank.JACK;
import static blackjack.domain.Rank.KING;
import static blackjack.domain.Rank.TWO;
import static blackjack.domain.Shape.CLOVER;
import static blackjack.domain.Shape.DIAMOND;
import static blackjack.domain.Shape.HEART;
import static blackjack.domain.Shape.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.util.FixedDeck;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class BlackjackGameTest {

    @Test
    void 플레이어들을_반환한다() {
        final BlackjackGame blackjackGame = generateBlackjackGame(List.of("허브", "후추"));

        assertThat(blackjackGame.getPlayers())
                .extracting(Player::getName)
                .containsExactly("딜러", "허브", "후추");
    }

    private BlackjackGame generateBlackjackGame(final List<String> names) {
        final Players players = Players.from(names);
        return new BlackjackGame(players);
    }

    @Test
    void 플레이어들이_게임_시작_시_카드를_뽑는다() {
        final BlackjackGame blackjackGame = generateBlackjackGame(List.of("허브", "후추"));
        final Deck deck = new FixedDeck(List.of(
                new Card(ACE, DIAMOND),
                new Card(JACK, CLOVER),
                new Card(TWO, CLOVER),
                new Card(EIGHT, SPADE),
                new Card(KING, HEART)
        ));

        blackjackGame.initialDraw(deck);

        assertThat(blackjackGame.getPlayers())
                .extracting(Player::calculateScore)
                .containsExactly(11, 12, 18);
    }


}
