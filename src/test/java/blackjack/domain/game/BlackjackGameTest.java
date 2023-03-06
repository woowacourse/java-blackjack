package blackjack.domain.game;

import static blackjack.domain.player.Result.WIN;
import static blackjack.util.CardFixtures.EIGHT_SPADE;
import static blackjack.util.CardFixtures.JACK_SPADE;
import static blackjack.util.CardFixtures.KING_SPADE;
import static blackjack.util.CardFixtures.TWO_HEART;
import static blackjack.util.CardFixtures.TWO_SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Deck;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.util.FixedDeck;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class BlackjackGameTest {

    @Test
    void 딜러가_카드를_뽑는다() {
        final Deck deck = new FixedDeck(JACK_SPADE, TWO_SPADE, EIGHT_SPADE, TWO_HEART, KING_SPADE);
        final Players players = Players.from(List.of("허브"));
        final BlackjackGame blackjackGame = new BlackjackGame(players);
        blackjackGame.initialDraw(deck);

        blackjackGame.drawToDealer(deck);

        final Dealer dealer = blackjackGame.getDealer();
        assertThat(dealer.getCardCount()).isEqualTo(3);
    }

    @Test
    void 플레이어에게_카드를_뽑게한다() {
        final Players players = Players.from(List.of("허브"));
        final BlackjackGame blackjackGame = new BlackjackGame(players);
        final Deck deck = new FixedDeck(JACK_SPADE, TWO_SPADE, EIGHT_SPADE, TWO_HEART, KING_SPADE);
        blackjackGame.initialDraw(deck);
        final Player player = players.getPlayers().get(1);

        blackjackGame.drawTo(player, deck);

        assertThat(player.getCardLetters()).containsExactly("8스페이드", "2하트", "K스페이드");
    }

    @Test
    void 플레이어가_카드를_더_뽑을_수_없는_상태로_변경한다() {
        final Players players = Players.from(List.of("허브"));
        final BlackjackGame blackjackGame = new BlackjackGame(players);
        final Player player = blackjackGame.getPlayers().get(1);

        blackjackGame.stay(player);

        assertThat(player.isDrawable()).isFalse();
    }

    @Test
    void 게임_결과를_반환한다() {
        final Deck deck = new FixedDeck(JACK_SPADE, TWO_SPADE, EIGHT_SPADE, TWO_HEART, KING_SPADE);
        final Players players = Players.from(List.of("허브"));
        final BlackjackGame blackjackGame = new BlackjackGame(players);
        blackjackGame.initialDraw(deck);
        blackjackGame.drawToDealer(deck);

        final BlackjackGameResult result = blackjackGame.play();

        assertThat(result.getResult().values()).containsExactly(WIN);
    }

    @Test
    void 플레이어들을_반환한다() {
        final List<String> names = List.of("허브", "후추");
        final BlackjackGame blackjackGame = new BlackjackGame(Players.from(names));

        final List<Player> players = blackjackGame.getPlayers();

        assertThat(players)
                .extracting(Player::getName)
                .containsExactly("딜러", "허브", "후추");
    }

    @Test
    void 딜러를_반환한다() {
        final List<String> names = List.of("후추");
        final BlackjackGame blackjackGame = new BlackjackGame(Players.from(names));

        final Dealer dealer = blackjackGame.getDealer();

        assertThat(dealer.isDealer()).isTrue();
    }
}
