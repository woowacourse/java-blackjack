package domain;

import static domain.HandsTestFixture.bustHands;
import static domain.HandsTestFixture.noBustHands;
import static domain.HandsTestFixture.sum18Size2;
import static domain.HandsTestFixture.sum20Size2;
import static domain.HandsTestFixture.sum20Size3;
import static domain.HandsTestFixture.sum21Size2;
import static domain.Result.LOSE;
import static domain.Result.TIE;
import static domain.Result.WIN;

import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import domain.participant.Players;
import exception.DuplicatePlayerNameException;
import exception.InvalidPlayersSizeException;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayersTest {

    @Test
    @DisplayName("참여자 이름 중복시 예외가 발생한다.")
    void duplicatePlayerName() {
        //given
        final List<String> names = List.of("redy", "redy");

        //when & then
        Assertions.assertThatThrownBy(() -> Players.from(names))
                .isInstanceOf(DuplicatePlayerNameException.class);
    }

    @DisplayName("총 참여자 수가 2이상 8이하이면 참여자를 생성한다.")
    @ParameterizedTest
    @MethodSource("validPlayersSizeParameterProvider")
    void validPlayersSize(final List<String> names) {
        Assertions.assertThatCode(() -> Players.from(names))
                .doesNotThrowAnyException();
    }

    @DisplayName("총 참여자 수는 2이상 8이하가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("invalidPlayersSizeParameterProvider")
    void invalidPlayersSize(final List<String> names) {
        Assertions.assertThatThrownBy(() -> Players.from(names))
                .isInstanceOf(InvalidPlayersSizeException.class);
    }

    @Test
    @DisplayName("참가자 중 버스트 되지 않은 참가자가 있다면 isAllBust가 False를 반환한다.")
    void isAllBustFalse() {
        //given
        final Player bustPlayer = new Player(new Name("레디"), bustHands);
        final Player noBustPlayer = new Player(new Name("제제"), noBustHands);
        final Players players = new Players(List.of(bustPlayer, noBustPlayer));

        //when && then
        Assertions.assertThat(players.isAllBust()).isFalse();
    }

    @Test
    @DisplayName("모든 참가자가 버스트되면 isAllBust가 True를 반환한다.")
    void isAllBustTrue() {
        //given
        Player player1 = new Player(new Name("레디"), bustHands);
        Player player2 = new Player(new Name("제제"), bustHands);
        Player player3 = new Player(new Name("수달"), bustHands);
        Player player4 = new Player(new Name("피케이"), bustHands);

        Players players = new Players(List.of(player1, player2, player3, player4));

        //when && then
        Assertions.assertThat(players.isAllBust()).isTrue();
    }

    @Test
    @DisplayName("참여자의 승패무를 판단한다.")
    void playerResult() {
        //given
        Player loser = new Player(new Name("레디"), sum18Size2);
        Player winner = new Player(new Name("제제"), sum21Size2);
        Player tier = new Player(new Name("수달"), sum20Size3);

        Players players = new Players(List.of(loser, winner, tier));
        Dealer dealer = new Dealer(CardDeck.generate(), sum20Size3);

        //when & then
        Map<Player, Result> expected = Map.of(loser, LOSE, winner, WIN, tier, TIE);
        Assertions.assertThat(players.getPlayersResult(dealer)).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러가 버스트일때 참여자가 버스트가 아니면 WIN")
    void all() {
        //given
        Dealer bustDealer = new Dealer(CardDeck.generate(), bustHands);
        Player winner1 = new Player(new Name("레디"), sum18Size2);
        Player winner2 = new Player(new Name("브라운"), sum20Size2);
        Player loser = new Player(new Name("제제"), bustHands);

        Players players = new Players(List.of(winner1, winner2, loser));

        //when
        Map<Player, Result> expectedPlayerResult = Map.of(winner1, WIN, winner2, WIN, loser, LOSE);
        Map<Result, Integer> expectedDealerResult = Map.of(WIN, 1, LOSE, 2);

        //then
        Assertions.assertThat(players.getPlayersResult(bustDealer)).isEqualTo(expectedPlayerResult);
        Assertions.assertThat(bustDealer.getDealerResult(players)).isEqualTo(expectedDealerResult);
    }

    static Stream<Arguments> validPlayersSizeParameterProvider() {
        return Stream.of(
                Arguments.of(
                        List.of("pobi", "jason")
                ),
                Arguments.of(
                        List.of("1", "2", "3", "4", "5", "6", "7", "8")
                )
        );
    }

    static Stream<Arguments> invalidPlayersSizeParameterProvider() {
        return Stream.of(
                Arguments.of(
                        List.of("pobi")
                ),
                Arguments.of(
                        List.of("1", "2", "3", "4", "5", "6", "7", "8", "9")
                )
        );
    }
}
