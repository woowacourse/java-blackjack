package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.domain.card.Denomination.*;
import static blackjack.domain.card.Symbol.*;
import static blackjack.domain.fixture.FixedSequenceDeck.generateDeck;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Players 클래스")
class PlayersTest {

    @Test
    @DisplayName("현재 순서의 player를 반환한다")
    void testGetCurrentPlayer() {
        Deck deck = generateDeck(new Card(CLOVER, JACK), new Card(DIAMOND, EIGHT), new Card(SPADE, JACK), new Card(HEART, ACE));
        Player first = new Player("pobi", deck.initialDraw());
        Player second = new Player("jason", deck.initialDraw());
        Players players = new Players(List.of(first, second));

        assertThat(players.getCurrentTurn()).isEqualTo(first);
    }

    @Test
    @DisplayName("순서를 다음 player로 넘긴다")
    void testPassTurnToNext() {
        Deck deck = generateDeck(new Card(CLOVER, JACK), new Card(DIAMOND, EIGHT), new Card(SPADE, JACK), new Card(HEART, ACE));
        Player first = new Player("pobi", deck.initialDraw());
        Player second = new Player("jason", deck.initialDraw());
        Players players = new Players(List.of(first, second));

        players.passTurnToNext();

        assertThat(players.getCurrentTurn()).isEqualTo(second);
    }

    @Test
    @DisplayName("hit이 가능한 플레이어가 나타날 때까지 turn을 넘긴다")
    void testPassTurnUntilHitable() {
        // given
        Deck deck = generateDeck(new Card(CLOVER, JACK), new Card(CLOVER, TWO),
                new Card(DIAMOND, JACK), new Card(DIAMOND, THREE),
                new Card(SPADE, JACK), new Card(SPADE, FOUR),
                new Card(HEART, JACK), new Card(HEART, FIVE));
        Player first = new Player("1", deck.initialDraw());
        Player second = new Player("2", deck.initialDraw());
        Player third = new Player("3", deck.initialDraw());
        Player fourth = new Player("4", deck.initialDraw());
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
        Deck deck = generateDeck(new Card(CLOVER, JACK), new Card(CLOVER, TWO),
                new Card(DIAMOND, JACK), new Card(DIAMOND, THREE),
                new Card(SPADE, JACK), new Card(SPADE, FOUR),
                new Card(HEART, JACK), new Card(HEART, FIVE),
                new Card(HEART, JACK), new Card(HEART, FIVE),
                new Card(HEART, JACK), new Card(HEART, FIVE),
                new Card(HEART, JACK), new Card(HEART, FIVE),
                new Card(HEART, JACK), new Card(HEART, FIVE)
        );
        Player first = new Player("1", deck.initialDraw());
        Player second = new Player("2", deck.initialDraw());
        Player third = new Player("3", deck.initialDraw());
        Player fourth = new Player("4", deck.initialDraw());
        Player fifth = new Player("5", deck.initialDraw());
        Player sixth = new Player("6", deck.initialDraw());
        Player seventh = new Player("7", deck.initialDraw());
        Player eighth = new Player("8", deck.initialDraw());

        assertThatThrownBy(() -> new Players(List.of(first, second, third, fourth, fifth, sixth, seventh, eighth)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("플레이어의 이름은 중복될 수 없다")
    void throwExceptionWhenNameDuplicated() {
        Deck deck = generateDeck(new Card(CLOVER, JACK), new Card(DIAMOND, EIGHT), new Card(SPADE, JACK), new Card(HEART, ACE));

        Player first = new Player("1", deck.initialDraw());
        Player second = new Player("1", deck.initialDraw());

        assertThatThrownBy(() -> new Players(List.of(first, second)))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @Nested
    @DisplayName("isPossibleToPlay 메서드는")
    class Describe_isPossibleToPlay {

        private Player bustPlayer;
        private Player stayPlayer;
        private Player playablePlayer;
        private final Deck deck = generateDeck(new Card(CLOVER, JACK), new Card(CLOVER, QUEEN), new Card(CLOVER, JACK),
                new Card(CLOVER, QUEEN), new Card(CLOVER, JACK), new Card(CLOVER, QUEEN));

        @BeforeEach
        void setUp() {
            bustPlayer = new Player("1", deck.initialDraw());
            bustPlayer.addCard(new Card(HEART, TWO));

            stayPlayer = new Player("2", deck.initialDraw());
            stayPlayer.stay();

            playablePlayer = new Player("2", deck.initialDraw());
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
