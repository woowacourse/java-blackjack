package blackjack.domain;

import static blackjack.domain.Players.from;
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
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.util.FixedDeck;
import java.util.List;
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

        assertThat(players.getNames()).containsExactly("딜러", "후추", "허브");
    }

    @Test
    void 플레이어들이_게임_시작_시_카드를_뽑는다() {
        final List<String> names = List.of("후추", "허브");
        final Players players = from(names);
        final Deck deck = new FixedDeck(List.of(
                new Card(ACE, DIAMOND),
                new Card(JACK, CLOVER),
                new Card(TWO, CLOVER),
                new Card(EIGHT, SPADE),
                new Card(KING, HEART)
        ));

        players.initialDraw(deck);

        assertThat(players.getPlayers())
                .extracting(Player::calculateScore)
                .containsExactly(11, 12, 18);
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
    void 딜러를_반환한다() {
        final List<String> names = List.of("후추", "허브");
        final Players players = from(names);

        final Player player = players.getDealer();

        assertThat(player.getName()).isEqualTo("딜러");
    }
}
