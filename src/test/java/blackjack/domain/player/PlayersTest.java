package blackjack.domain.player;

import blackjack.domain.card.Deck;
import blackjack.util.FixedDeck;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static blackjack.domain.player.Players.from;
import static blackjack.domain.player.Result.PUSH;
import static blackjack.domain.player.Result.WIN;
import static blackjack.util.CardFixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class PlayersTest {

    @Test
    void 입력받은_플레이어의_이름이_중복되는_경우_예외를_던진다() {
        final List<String> names = List.of("name", "name");

        assertThatThrownBy(() -> from(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Players.DUPLICATE_NAMES_MESSAGE + names);
    }

    @ParameterizedTest(name = "입력받은 플레이어가 {0}명인 경우 예외를 던진다.")
    @ValueSource(ints = {0, 7})
    void 입력받은_플레이어가_1명_미만_6명_초과인_경우_예외를_던진다(final int count) {
        final List<String> names = IntStream.range(0, count)
                .mapToObj(String::valueOf)
                .collect(Collectors.toList());

        assertThatThrownBy(() -> from(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Players.INVALID_NAME_COUNT + count);
    }

    @Test
    void 플레이어들이_정상_생성된다() {
        final List<String> names = List.of("후추", "허브");

        final Players players = from(names);

        assertThat(getPlayerNames(players)).containsExactly("딜러", "후추", "허브");
    }

    private List<String> getPlayerNames(final Players players) {
        return players.getPlayers()
                .stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    @Test
    void 플레이어들이_게임_시작_시_카드를_뽑는다() {
        final List<String> names = List.of("후추", "허브");
        final Players players = from(names);
        final Deck deck = new FixedDeck(List.of(
                ACE_DIAMOND,
                FIVE_SPADE,
                JACK_SPADE,
                TWO_SPADE,
                EIGHT_SPADE,
                KING_SPADE
        ));

        players.drawInitialCards(deck);

        assertThat(players.getPlayers())
                .extracting(Player::calculateScore)
                .containsExactly(16, 12, 18);
    }

    @Test
    void 플레이어들을_반환한다() {
        final List<String> names = List.of("후추", "허브");
        final Players players = from(names);

        List<Player> result = players.getPlayers();

        assertThat(result).extracting(Player::getName)
                .containsExactly("딜러", "후추", "허브");
    }

    @Test
    void 겜블러들을_반환한다() {
        final List<String> names = List.of("후추", "허브");
        final Players players = from(names);

        List<Player> result = players.getGamblers();

        assertThat(result).extracting(Player::getName)
                .containsExactly("후추", "허브");
    }

    @Test
    void 딜러를_반환한다() {
        final List<String> names = List.of("후추", "허브");
        final Players players = from(names);

        final Player player = players.getDealer();

        assertThat(player.getName()).isEqualTo("딜러");
    }

    @Test
    void 딜러가_카드를_가능할_때_까지_뽑는다() {
        final List<String> names = List.of("후추");
        final Players players = from(names);
        final Deck deck = new FixedDeck(List.of(
                ACE_DIAMOND,
                FIVE_SPADE,
                TWO_SPADE,
                THREE_SPADE,
                FOUR_SPADE,
                ACE_SPADE
        ));
        players.drawInitialCards(deck);

        players.drawByDealer(deck);

        assertThat(players.getDealer().calculateScore()).isEqualTo(20);
    }

    @Test
    void 게임_결과를_반환환다() {
        final List<String> names = List.of("후추", "허브");
        final Players players = from(names);
        final Deck deck = new FixedDeck(List.of(
                SEVEN_SPADE,
                KING_SPADE,
                NINE_SPADE,
                TEN_SPADE,
                JACK_SPADE,
                SEVEN_SPADE
        ));
        players.drawInitialCards(deck);
        players.drawByDealer(deck);

        Map<Player, Result> result = players.compareHands();

        assertThat(result.values()).containsExactly(WIN, PUSH);
    }
}
