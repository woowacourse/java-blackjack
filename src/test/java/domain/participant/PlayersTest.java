package domain.participant;

import static message.ErrorMessage.PLAYER_NAME_DUPLICATED;
import static message.ErrorMessage.PLAYER_NUMBER_OUT_OF_RANGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import domain.card.Card;
import domain.enums.GameResult;
import domain.enums.Rank;
import domain.enums.Suit;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayersTest {

    private Players players;
    private Dealer dealer;

    @BeforeEach
    void set_up() {
        players = new Players(List.of("스타크", "피즈"));
        dealer = new Dealer();

        players.initializeCards(new Name("스타크"),
                List.of(new Card(Rank.ACE, Suit.DIAMOND), new Card(Rank.JACK, Suit.DIAMOND)));
        players.initializeCards(new Name("피즈"),
                List.of(new Card(Rank.THREE, Suit.DIAMOND), new Card(Rank.SEVEN, Suit.DIAMOND)));
        dealer.receiveInitialCards(List.of(new Card(Rank.TEN, Suit.DIAMOND), new Card(Rank.KING, Suit.DIAMOND)));
    }

    @DisplayName("모든 플레이어가 정상적으로 생성된다.")
    @Test
    void 모든_플레이어가_정상적으로_생성된다() {
        //given
        Players players = new Players(List.of("피즈", "스타크"));

        //when
        List<Name> allPlayersName = players.getAllPlayersName();

        //then
        assertSoftly(softAssertions -> {
            softAssertions.assertThat(allPlayersName).contains(new Name("피즈"));
            softAssertions.assertThat(allPlayersName).contains(new Name("스타크"));
        });
    }

    @DisplayName("특정 플레이어의 카드 가져오기")
    @Test
    void 특정_플레이어_카드_가져오기() {
        //given
        List<Card> playerCards = players.getPlayerCards(new Name("스타크"));
        //when
        //then
        assertThat(playerCards).containsExactly(new Card(Rank.ACE, Suit.DIAMOND), new Card(Rank.JACK, Suit.DIAMOND));
    }

    @DisplayName("특정 플레이어 점수 계산하기")
    @Test
    void 특정_플레이어_점수_계산한다() {
        //given
        int playerScore = players.getPlayerScore(new Name("스타크"));
        //when

        //then
        assertThat(playerScore).isEqualTo(21);
    }

    @DisplayName("플레이어의 블랙잭 게임 결과를 생성한다.")
    @Test
    void 블랙잭_게임_결과_생성() {
        //given
        //when
        Map<Name, GameResult> gameResults = players.decidePlayerResults(dealer);
        //then
        assertSoftly(softAssertions -> {
            softAssertions.assertThat(gameResults.get(new Name("스타크"))).isEqualTo(GameResult.BLACKJACK_WIN);
            softAssertions.assertThat(gameResults.get(new Name("피즈"))).isEqualTo(GameResult.LOSE);
        });
    }

    @Nested
    @DisplayName("예외 경우")
    class failure {

        @DisplayName("플레이어 이름이 중복된 경우 예외가 발생한다.")
        @Test
        void 플레이어_이름이_중복된_경우_예외가_발생한다() {
            List<String> duplicatedNames = List.of("피즈", "피즈", "스타크");

            assertThatThrownBy(() -> new Players(duplicatedNames))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(PLAYER_NAME_DUPLICATED.getMessage());
        }

        private static Stream<Arguments> playerNumberOutOfRange() {
            return Stream.of(
                    Arguments.of(List.of()),
                    Arguments.of(List.of("피즈1", "피즈2", "피즈3", "피즈4", "피즈5", "피즈6", "피즈7", "피즈8"))
            );
        }

        @DisplayName("게임에 참여하는 플레이어 인원이 1~7명이 아닐 경우 예외가 발생한다.")
        @ParameterizedTest
        @MethodSource("playerNumberOutOfRange")
        void 게임_참여_플레이어가_1명에서_7명_사이가_아닐_경우_예외가_발생한다(List<String> names) {
            assertThatThrownBy(() -> new Players(names))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(PLAYER_NUMBER_OUT_OF_RANGE.getMessage());
        }
    }
}
