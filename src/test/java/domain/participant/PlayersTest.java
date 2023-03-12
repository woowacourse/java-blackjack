package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.InstanceOfAssertFactories.collection;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Number;
import domain.card.Suit;
import java.util.Collections;
import java.util.List;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Nested
    class 생성 {
        @Test
        void should_플레이어를생성한다_when_from호출() {
            //given
            final List<Player> rawPlayers = List.of(new Player(new Name("에밀"), new Hand(), new BettingMoney(1000)),
                    new Player(new Name("포이"), new Hand(), new BettingMoney(2000)));

            //when
            Players players = new Players(rawPlayers);

            //then
            List<Player> playerList = players.values();

            assertAll(
                    () -> assertThat(playerList.get(0).name()).isEqualTo("에밀"),
                    () -> assertThat(playerList.get(1).name()).isEqualTo("포이")
            );
        }

        @Test
        void should_예외를던진다_when_플레이어가0명인경우() {
            //given
            List<Player> playerNames = Collections.EMPTY_LIST;

            //when
            ThrowingCallable throwingCallable = () -> new Players(playerNames);

            //then
            assertThatThrownBy(throwingCallable)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("플레이어는 1명 이상이어야 합니다.");
        }
    }

    @Nested
    class 카드받기 {
        @Test
        void should_플레이어는카드를한장씩받는다_when_receiveCard호출() {
            //given
            final List<Player> rawPlayers = List.of(new Player(new Name("에밀"), new Hand(), new BettingMoney(1000)),
                    new Player(new Name("포이"), new Hand(), new BettingMoney(2000)));
            Players players = new Players(rawPlayers);
            Deck deck = Deck.create();

            //when
            players.receiveCard(deck);

            //then
            assertThat(players).extracting("players", collection(Player.class))
                    .filteredOn((player) -> player.hand().size() == 1)
                    .hasSize(2);
        }
    }

    @Nested
    class 카드를뽑을수있는플레이어존재여부판단 {
        @Test
        void should_hasDrawablePlayer가true반환_when_카드를뽑을수있는플레이어존재() {
            //given
            final List<Player> rawPlayers = List.of(new Player(new Name("에밀"), new Hand(), new BettingMoney(1000)),
                    new Player(new Name("포이"), new Hand(), new BettingMoney(2000)));
            Players players = new Players(rawPlayers);
            Deck deck = Deck.create();
            deck.shuffle((cards) -> {
                cards.clear();
                cards.add(new Card(Suit.SPADE, Number.ACE));
                cards.add(new Card(Suit.SPADE, Number.ACE));
            });
            players.receiveCard(deck);

            //when
            boolean existingDrawablePlayer = players.hasDrawablePlayer();

            //then
            assertThat(existingDrawablePlayer).isTrue();
        }

        @Test
        void should_hasDrawablePlayer가false반환_when_카드를뽑을수있는플레이어없을때() {
            //given
            final List<Player> rawPlayers = List.of(new Player(new Name("포이"), new Hand(), new BettingMoney(1000)));
            Players players = new Players(rawPlayers);
            Deck deck = Deck.create();
            deck.shuffle((cards) -> {
                cards.clear();
                cards.add(new Card(Suit.SPADE, Number.ACE));
                cards.add(new Card(Suit.SPADE, Number.JACK));
            });
            players.receiveCard(deck);
            players.receiveCard(deck);

            //when
            boolean existingDrawablePlayer = players.hasDrawablePlayer();

            //then
            assertThat(existingDrawablePlayer).isFalse();
        }
    }

    @Nested
    class 카드를받을수있는플레이어이름반환 {
        @Test
        void should_카드를받을다음플레이어이름반환_when_카드를받을수있는플레이어가존재할시() {
            //given
            final List<Player> rawPlayers = List.of(new Player(new Name("포이"), new Hand(), new BettingMoney(2000)));
            Players players = new Players(rawPlayers);
            String expected = "포이";
            Deck deck = Deck.create();
            players.receiveCard(deck);

            //when
            String actual = players.findCurrentDrawablePlayer().name();

            //then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void should_예외를반환한다_when_카드를받을수있는플레이어가없을시() {
            //given
            final List<Player> rawPlayers = List.of(new Player(new Name("포이"), new Hand(), new BettingMoney(1000)));
            Players players = new Players(rawPlayers);
            Deck deck = Deck.create();
            deck.shuffle((cards) -> {
                cards.clear();
                cards.add(new Card(Suit.SPADE, Number.ACE));
                cards.add(new Card(Suit.SPADE, Number.JACK));
            });
            players.receiveCard(deck);
            players.receiveCard(deck);

            //when
            ThrowingCallable throwingCallable = players::findCurrentDrawablePlayer;

            //then
            assertThatThrownBy(throwingCallable)
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("카드를 받을 수 있는 플레이어가 없습니다.");
        }
    }

    @Nested
    class 현재플레이어의행동 {
        @Test
        void should_현재플레이어는카드를받는다_when_카드를받을수있는플레이어가있을때() {
            //given
            final List<Player> rawPlayers = List.of(new Player(new Name("포이"), new Hand(), new BettingMoney(1000)));
            Players players = new Players(rawPlayers);
            Deck deck = Deck.create();
            deck.shuffle((cards) -> {
                cards.clear();
                cards.add(new Card(Suit.SPADE, Number.TWO));
                cards.add(new Card(Suit.SPADE, Number.THREE));
            });
            players.receiveCard(deck);
            players.receiveCard(deck);

            //when
            players.handOutCardToCurrentPlayer(new Card(Suit.SPADE, Number.ACE));
            final List<Card> hand = players.values().get(0).hand();

            //then
            assertThat(hand).hasSize(3).contains(new Card(Suit.SPADE, Number.ACE));
        }

        @Test
        void should_현재플레이어를다음플레이어로이동시킨다_when_현재플레이어가카드를받을수있어도() {
            //given
            final Player poi = new Player(new Name("포이"), new Hand(), new BettingMoney(1000));
            final Player expected = new Player(new Name("에밀"), new Hand(), new BettingMoney(2000));
            final List<Player> rawPlayers = List.of(poi, expected);
            Players players = new Players(rawPlayers);
            Deck deck = Deck.create();
            deck.shuffle((cards) -> {
                cards.clear();
                cards.add(new Card(Suit.SPADE, Number.TWO));
                cards.add(new Card(Suit.SPADE, Number.THREE));
            });
            players.receiveCard(deck);

            //when
            players.standCurrentPlayer();
            final Player actual = players.findCurrentDrawablePlayer();

            //then
            assertThat(expected).isEqualTo(actual);
        }
    }
}
