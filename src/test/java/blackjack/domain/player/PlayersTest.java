package blackjack.domain.player;

import static blackjack.domain.player.Result.PUSH;
import static blackjack.domain.player.Result.WIN;
import static blackjack.util.CardFixture.ACE_DIAMOND;
import static blackjack.util.CardFixture.ACE_SPADE;
import static blackjack.util.CardFixture.EIGHT_SPADE;
import static blackjack.util.CardFixture.JACK_CLOVER;
import static blackjack.util.CardFixture.JACK_SPADE;
import static blackjack.util.CardFixture.KING_HEART;
import static blackjack.util.CardFixture.NINE_CLOVER;
import static blackjack.util.CardFixture.NINE_HEART;
import static blackjack.util.CardFixture.NINE_SPADE;
import static blackjack.util.CardFixture.SEVEN_SPADE;
import static blackjack.util.CardFixture.TWO_SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Deck;
import blackjack.util.FixedDeck;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class PlayersTest {

    @Test
    void 입력받은_플레이어의_이름이_중복되는_경우_예외를_던진다() {
        final Players players = Players.create();
        final List<String> names = List.of("name", "name");

        assertThatThrownBy(() -> players.addPlayers(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 이름이 중복될 수 없습니다. 입력값:" + names);
    }

    @ParameterizedTest(name = "입력받은 플레이어가 {0}명인 경우 예외를 던진다.")
    @ValueSource(ints = {0, 7})
    void 입력받은_플레이어가_1명_미만_6명_초과인_경우_예외를_던진다(final int count) {
        final Players players = Players.create();
        final List<String> names = IntStream.range(0, count)
                .mapToObj(String::valueOf)
                .collect(Collectors.toList());

        assertThatThrownBy(() -> players.addPlayers(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어는 최소 1명 이상, 최대 6명 이하여야 합니다. 입력값:" + count);
    }

    @Test
    void 플레이어들이_정상적으로_추가된다() {
        final Players players = Players.create();
        final List<String> names = List.of("후추", "허브");

        players.addPlayers(names);

        assertThat(players.getPlayers()).hasSize(3);
    }

    @Test
    void 플레이어들이_게임_시작_시_카드를_뽑는다() {
        final Players players = Players.create();
        players.addPlayers(List.of("후추", "허브"));
        final Deck deck = new FixedDeck(ACE_SPADE, KING_HEART, JACK_SPADE, TWO_SPADE, EIGHT_SPADE, JACK_SPADE);

        players.initialDraw(deck);

        assertThat(players.getPlayers())
                .extracting(Player::score)
                .containsExactly(21, 12, 18);
    }

    @Test
    void 딜러가_카드를_가능할_때_까지_뽑는다() {
        final Players players = Players.create();
        final Deck deck = new FixedDeck(ACE_DIAMOND, TWO_SPADE, EIGHT_SPADE);
        players.initialDraw(deck);

        players.drawToDealer(deck);

        final Dealer dealer = players.getDealer();
        assertThat(dealer.score()).isEqualTo(21);
    }

    @Test
    void 입력한_플레이어의_카드를_추가한다() {
        final Players players = Players.create();
        players.addPlayers(List.of("후추"));
        final Deck deck = new FixedDeck(JACK_SPADE);

        players.drawTo(Name.from("후추"), deck);

        final Player player = players.findDrawablePlayer();
        assertThat(player.getSymbols()).containsExactly("J스페이드");
    }

    @Test
    void 카드를_뽑을_수_있는_플레이어가_없다면_예외를_던진다() {
        final Players players = Players.create();

        assertThatThrownBy(players::findDrawablePlayer)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드를 뽑을 수 있는 플레이어가 존재하지 않습니다.");
    }

    @Test
    void 입력한_플레이어의_상태를_카드를_더_뽑을_수_없는_상태로_변경한다() {
        final Players players = Players.create();
        players.addPlayers(List.of("후추"));

        players.stay(Name.from("후추"));

        assertThat(players.isDrawable()).isFalse();
    }

    @Test
    void 카드를_뽑을_수_있는_플레이어들이_존재하는지_확인한다() {
        final Players players = Players.create();
        players.addPlayers(List.of("후추"));

        final boolean result = players.isDrawable();

        assertThat(result).isTrue();
    }

    @Test
    void 카드를_뽑을_수_있는_플레이어들이_없다면_거짓을_반환한다() {
        final Players players = Players.create();

        final boolean result = players.isDrawable();

        assertThat(result).isFalse();
    }

    @Test
    void 카드를_뽑을_수_있는_플레이어를_반환한다() {
        final Players players = Players.create();
        players.addPlayers(List.of("후추"));

        final Player player = players.findDrawablePlayer();

        assertThat(player.getNameValue()).isEqualTo("후추");
    }

    @Test
    void 게임_결과를_반환한다() {
        final Players players = Players.create();
        players.addPlayers(List.of("후추", "허브"));
        final Deck deck = new FixedDeck(ACE_DIAMOND, SEVEN_SPADE, JACK_CLOVER, NINE_SPADE, NINE_HEART, NINE_CLOVER);
        players.initialDraw(deck);

        Map<Name, Result> result = players.play();

        assertThat(result.values()).containsExactly(WIN, PUSH);
    }

    @Test
    void 플레이어들을_반환한다() {
        final Players players = Players.create();
        players.addPlayers(List.of("후추", "허브"));

        final List<Player> result = players.getPlayers();

        assertThat(result).extracting(Player::getNameValue)
                .containsExactly("딜러", "후추", "허브");
    }

    @Test
    void 딜러를_반환한다() {
        final Players players = Players.create();

        final Player player = players.getDealer();

        assertThat(player.getNameValue()).isEqualTo("딜러");
    }

    @Test
    void 겜블러의_이름을_반환한다() {
        final Players players = Players.create();
        players.addPlayers(List.of("후추", "허브"));

        final List<Name> result = players.getGamblerNames();

        assertThat(result).containsExactly(Name.from("후추"), Name.from("허브"));
    }
}
