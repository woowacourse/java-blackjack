package blackjack.domain.game;

import blackjack.domain.card.Deck;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.domain.player.Result;
import blackjack.util.FixedDeck;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static blackjack.domain.player.Result.WIN;
import static blackjack.util.CardFixtures.*;
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
        return new BlackjackGame(players, new BettingSystem(new HashMap<>()));
    }

    @Test
    void 딜러를_반환한다() {
        final BlackjackGame blackjackGame = generateBlackjackGame(List.of("허브", "후추"));

        final Player dealer = blackjackGame.getDealer();

        assertThat(dealer.getName()).isEqualTo("딜러");
    }

    @Test
    void 플레이어들이_게임_시작_시_카드를_뽑는다() {
        final Deck deck = new FixedDeck(List.of(
                ACE_DIAMOND,
                JACK_SPADE,
                EIGHT_SPADE,
                NINE_SPADE,
                KING_SPADE,
                QUEEN_SPADE
        ));
        final BlackjackGame blackjackGame = generateBlackjackGame(List.of("허브", "후추"));

        blackjackGame.drawInitialCards(deck);

        final List<Player> players = blackjackGame.getPlayers();
        assertThat(players)
                .extracting(Player::calculateScore)
                .containsExactly(21, 17, 20);
    }

    @Test
    void 딜러가_카드를_뽑는다() {
        final Deck deck = new FixedDeck(List.of(
                JACK_SPADE,
                TWO_SPADE,
                EIGHT_SPADE,
                ACE_DIAMOND,
                KING_SPADE
        ));
        final BlackjackGame blackjackGame = generateBlackjackGame(List.of("허브"));
        blackjackGame.drawInitialCards(deck);

        blackjackGame.drawByDealer(deck);

        final Dealer dealer = blackjackGame.getDealer();
        assertThat(dealer.getCardCount()).isEqualTo(3);
    }

    @Test
    void 게임_결과를_반환한다() {
        final Deck deck = new FixedDeck(List.of(
                JACK_SPADE,
                TWO_SPADE,
                EIGHT_SPADE,
                ACE_DIAMOND,
                KING_SPADE
        ));
        final BlackjackGame blackjackGame = generateBlackjackGame(List.of("허브"));
        blackjackGame.drawInitialCards(deck);
        blackjackGame.drawByDealer(deck);

        final Map<Player, Result> result = blackjackGame.play();

        assertThat(result.values()).containsExactly(WIN);
    }
}
