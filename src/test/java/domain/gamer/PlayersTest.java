package domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.FinalResult;
import domain.deck.Card;
import domain.deck.CardSetGenerator;
import domain.deck.Deck;
import domain.deck.Rank;
import domain.deck.Shape;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayersTest {

    @DisplayName("플레이어는 1명 이상 4명 이하여야 한다.")
    @ParameterizedTest
    @MethodSource("methodSources")
    void 플레이어는_1명_이상_4명_이하여야_한다(final List<Nickname> nicknames) {

        // given
        final List<Player> playerGroup = nicknames.stream()
                .map(nickname -> new Player(nickname, new Betting(1000)))
                .toList();

        // when & then
        assertThatCode(() -> new Players(playerGroup))
                .doesNotThrowAnyException();
    }

    @DisplayName("플레이어는 1명 이상 4명 이하가 아니라면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("methodSources2")
    void 플레이어는_1명_이상_4명_이하가_아니라면_예외가_발생한다(final List<Nickname> nicknames) {

        // given
        final List<Player> playerGroup = nicknames.stream()
                .map(nickname -> new Player(nickname, new Betting(1000)))
                .toList();

        // when & then
        assertThatThrownBy(() -> new Players(playerGroup))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("플레이어의 이름은 중복이 없으면 올바르게 작동한다.")
    @Test
    void 플레이어의_이름은_중복이_없으면_올바르게_작동한다() {
        // given
        final Player player1 = new Player(new Nickname("체체"), new Betting(1000));
        final Player player2 = new Player(new Nickname("체체체"), new Betting(1000));
        final List<Player> playerGroup = List.of(player1, player2);

        // when & then
        assertThatCode(() -> new Players(playerGroup))
                .doesNotThrowAnyException();
    }

    @DisplayName("플레이어의 이름은 중복될 경우 예외가 발생한다.")
    @Test
    void 플레이어의_이름은_중복될_경우_예외가_발생한다() {
        // given
        final Player player1 = new Player(new Nickname("체체"), new Betting(1000));
        final Player player2 = new Player(new Nickname("체체"), new Betting(1000));
        final List<Player> playerGroup = List.of(player1, player2);

        // when & then
        assertThatThrownBy(() -> new Players(playerGroup))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("플레이어들은 초키 카드를 받는다.")
    @Test
    void 플레이어들은_초기_카드를_받는다() {

        // given
        final Player player1 = new Player(new Nickname("체체"), new Betting(1000));
        final Player player2 = new Player(new Nickname("체추"), new Betting(1000));
        final List<Player> playerGroup = List.of(player1, player2);
        final Players players = new Players(playerGroup);
        final CardSetGenerator cardSetGenerator = new CardSetGenerator();
        final Deck deck = new Deck(cardSetGenerator.generate());

        // when & then
        assertThatCode(() -> players.receiveInitialCards(deck))
                .doesNotThrowAnyException();
    }

    @DisplayName("플레이어들의 최종 결과를 반환한다.")
    @Test
    void 플레이어들의_최종_결과를_반환한다() {

        // given
        final Player player1 = new Player(new Nickname("체체"), new Betting(1000));
        final Player player2 = new Player(new Nickname("체추"), new Betting(1000));
        final List<Player> playerGroup = List.of(player1, player2);
        final Players players = new Players(playerGroup);
        final Dealer dealer = new Dealer();

        player1.hit(new Card(Rank.ACE, Shape.SPADE));
        player1.hit(new Card(Rank.JACK, Shape.SPADE));
        player2.hit(new Card(Rank.ACE, Shape.SPADE));
        player2.hit(new Card(Rank.ACE, Shape.SPADE));
        dealer.hit(new Card(Rank.ACE, Shape.SPADE));
        dealer.hit(new Card(Rank.FIVE, Shape.SPADE));

        // when
        final Map<Player, FinalResult> finalResults = players.createFinalResults(dealer);

        // then
        assertThat(finalResults.get(player1)).isEqualTo(FinalResult.WIN);
        assertThat(finalResults.get(player2)).isEqualTo(FinalResult.LOSE);
    }

    private static Stream<Arguments> methodSources() {
        return Stream.of(
                Arguments.arguments(List.of(new Nickname("체체"))),
                Arguments.arguments(List.of(new Nickname("체체"), new Nickname("as"))),
                Arguments.arguments(
                        List.of(new Nickname("체체"), new Nickname("as"), new Nickname("aaa"), new Nickname("bvbb")))
        );
    }

    private static Stream<Arguments> methodSources2() {
        return Stream.of(
                Arguments.arguments(List.of()),
                Arguments.arguments(
                        List.of(new Nickname("체체"), new Nickname("as"), new Nickname("aaa"), new Nickname("bvbb"),
                                new Nickname("qqq")))
        );
    }
}
