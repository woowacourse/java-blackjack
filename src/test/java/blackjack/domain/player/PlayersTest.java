package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.domain.card.Denomination.*;
import static blackjack.domain.card.Symbol.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Players 클래스")
class PlayersTest {

    Player first;
    Player second;
    Players players;

    @BeforeEach
    void setUp() {

        first = new Player("pobi", Cards.of(List.of(new Card(CLOVER, JACK), new Card(DIAMOND, EIGHT))));
        second = new Player("jason", Cards.of(List.of(new Card(SPADE, JACK), new Card(HEART, ACE))));
        players = new Players(List.of(first, second));
    }

    @Test
    @DisplayName("현재 순서의 player를 반환한다")
    void testGetCurrentPlayer() {
        assertThat(players.getCurrentTurn()).isEqualTo(first);
    }

    @Test
    @DisplayName("순서를 다음 player로 넘긴다")
    void testPassTurnToNext() {
        players.passTurnToNext();
        assertThat(players.getCurrentTurn()).isEqualTo(second);
    }

    @Test
    @DisplayName("hit이 가능한 플레이어가 나타날 때까지 turn을 넘긴다")
    void testPassTurnUntilHitable() {
        // given
        Player first = new Player("1", Cards.of(List.of(new Card(CLOVER, JACK), new Card(CLOVER, TWO))));
        Player second = new Player("2", Cards.of(List.of(new Card(DIAMOND, JACK), new Card(DIAMOND, THREE))));
        Player third = new Player("3", Cards.of(List.of(new Card(SPADE, JACK), new Card(SPADE, FOUR))));
        Player fourth = new Player("4", Cards.of(List.of(new Card(HEART, JACK), new Card(HEART, FIVE))));
        Players players = new Players(List.of(first, second, third, fourth));

        first.stay();
        second.stay();
        third.stay();

        Player firstTurn = players.getCurrentTurn();

        // when
        players.passTurnUntilHitable();

        // then
        assertThat(firstTurn).isEqualTo(first);
        assertThat(players.getCurrentTurn()).isEqualTo(fourth);
    }

    @Test
    @DisplayName("최대 가능 플레이어 수를 초과하면 예외를 발생시킨다")
    void throwExceptionWhenOverMaxPlayerSize() {
        Player first = new Player("1", Cards.of(List.of(new Card(CLOVER, JACK), new Card(CLOVER, TWO))));
        Player second = new Player("2", Cards.of(List.of(new Card(DIAMOND, JACK), new Card(DIAMOND, THREE))));
        Player third = new Player("3", Cards.of(List.of(new Card(SPADE, JACK), new Card(SPADE, FOUR))));
        Player fourth = new Player("4", Cards.of(List.of(new Card(HEART, JACK), new Card(HEART, FIVE))));
        Player fifth = new Player("5", Cards.of(List.of(new Card(HEART, JACK), new Card(HEART, FIVE))));
        Player sixth = new Player("6", Cards.of(List.of(new Card(HEART, JACK), new Card(HEART, FIVE))));
        Player seventh = new Player("7", Cards.of(List.of(new Card(HEART, JACK), new Card(HEART, FIVE))));
        Player eighth = new Player("8", Cards.of(List.of(new Card(HEART, JACK), new Card(HEART, FIVE))));

        assertThatThrownBy(() -> new Players(List.of(first, second, third, fourth, fifth, sixth, seventh, eighth)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("플레이어의 이름은 중복될 수 없다")
    void throwExceptionWhenNameDuplicated() {
        Player first = new Player("1", Cards.of(List.of(new Card(CLOVER, JACK), new Card(CLOVER, TWO))));
        Player second = new Player("1", Cards.of(List.of(new Card(DIAMOND, JACK), new Card(DIAMOND, THREE))));

        assertThatThrownBy(() -> new Players(List.of(first, second)))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @Nested
    @DisplayName("isPossibleToPlay 메서드는")
    class Describe_isPossibleToPlay {

        private Player bustPlayer;
        private Player stayPlayer;
        private Player playablePlayer;

        @BeforeEach
        void setUp() {
            bustPlayer = new Player("1", Cards.of(List.of(new Card(CLOVER, JACK), new Card(CLOVER, QUEEN))));
            bustPlayer.addCard(new Card(HEART, TWO));

            stayPlayer = new Player("2", Cards.of(List.of(new Card(DIAMOND, JACK), new Card(DIAMOND, THREE))));
            stayPlayer.stay();

            playablePlayer = new Player("2", Cards.of(List.of(new Card(DIAMOND, JACK), new Card(DIAMOND, THREE))));
        }

        @Nested
        @DisplayName("플레이 가능한 플레이어가 남아있다면")
        class Context_with_remain_playable_player {

            @Test
            @DisplayName("참을 반환한다")
            void it_returns_true() {
                Players players = new Players(List.of(playablePlayer, bustPlayer));
                assertThat(players.isPossibleToPlay()).isTrue();
            }
        }

        @Nested
        @DisplayName("플레이 가능한 플레이어가 남아있지 않다면")
        class Context_with_no_playable_player {

            @Test
            @DisplayName("거짓을 반환한다")
            void it_returns_false() {
                Players players = new Players(List.of(stayPlayer, bustPlayer));
                assertThat(players.isPossibleToPlay()).isFalse();
            }
        }
    }
}
