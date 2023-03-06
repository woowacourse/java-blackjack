package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.util.FixedDeck;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.domain.card.Rank.*;
import static blackjack.domain.card.Shape.*;
import static blackjack.domain.game.Result.WIN;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class BlackjackGameTest {

    @Test
    void 플레이어들을_반환한다() {
        final BlackjackGame blackjackGame = generateBlackjackGame(List.of("허브", "후추"));

        final List<Player> players = blackjackGame.getPlayers();

        assertThat(players)
                .extracting(Player::getName)
                .containsExactly("딜러", "허브", "후추");
    }

    private BlackjackGame generateBlackjackGame(final List<String> names) {
        final Players players = Players.from(names);
        return new BlackjackGame(players);
    }

    @Test
    void 딜러를_반환한다() {
        final BlackjackGame blackjackGame = generateBlackjackGame(List.of("허브", "후추"));

        final Player dealer = blackjackGame.getDealer();

        assertThat(dealer.getName()).isEqualTo("딜러");
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

        final List<Player> players = blackjackGame.getPlayers();
        assertThat(players)
                .extracting(Player::calculateScore)
                .containsExactly(11, 12, 18);
    }

    @Test
    void 딜러가_카드를_뽑는다() {
        final BlackjackGame blackjackGame = generateBlackjackGame(List.of("허브"));
        final Deck deck = new FixedDeck(List.of(
                new Card(JACK, CLOVER),
                new Card(TWO, CLOVER),
                new Card(EIGHT, SPADE),
                new Card(TWO, DIAMOND),
                new Card(KING, HEART)
        ));
        blackjackGame.initialDraw(deck);

        blackjackGame.drawByDealer(deck);

        final Dealer dealer = blackjackGame.getDealer();
        assertThat(dealer.getCardCount()).isEqualTo(3);
    }

    @Test
    void 게임_결과를_반환한다() {
        final BlackjackGame blackjackGame = generateBlackjackGame(List.of("허브"));
        final Deck deck = new FixedDeck(List.of(
                new Card(JACK, CLOVER),
                new Card(TWO, CLOVER),
                new Card(EIGHT, SPADE),
                new Card(TWO, DIAMOND),
                new Card(KING, HEART)
        ));
        blackjackGame.initialDraw(deck);
        blackjackGame.drawByDealer(deck);

        final BlackjackGameResult result = blackjackGame.play();

        assertThat(result.getResult().values()).containsExactly(WIN);
    }
}
