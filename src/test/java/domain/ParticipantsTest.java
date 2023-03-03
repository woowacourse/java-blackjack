package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.InstanceOfAssertFactories.collection;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    @Nested
    class 생성 {
        @Test
        void should_딜러와플레이어를생성한다_when_create호출() {
            //given
            List<String> playerNames = List.of("에밀", "포이");

            //when
            Participants participants = Participants.create(playerNames);

            //then
            assertAll(
                    () -> assertThat(participants).extracting("participants", collection(Participant.class))
                            .first()
                            .isInstanceOf(Dealer.class),
                    () -> assertThat(participants).extracting("participants", collection(Participant.class))
                            .filteredOn((participant) -> Objects.equals(Player.class, participant.getClass()))
                            .hasSize(playerNames.size())
            );
        }

        @Test
        void should_예외를던진다_when_플레이어가0명인경우() {
            //given
            List<String> playerNames = Collections.EMPTY_LIST;

            //when
            ThrowingCallable throwingCallable = () -> Participants.create(playerNames);

            //then
            assertThatThrownBy(throwingCallable)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("플레이어는 1명 이상이어야 합니다.");
        }
    }

    @Nested
    class 카드받기 {
        @Test
        void should_참여자는카드를두장씩받는다_when_readyForGame호출() {
            //given
            List<String> playerNames = List.of("에밀", "포이");
            Participants participants = Participants.create(playerNames);
            Deck deck = Deck.create();

            //when
            participants.readyForGame(deck);

            //then
            assertThat(participants).extracting("participants", collection(Participant.class))
                    .filteredOn((participant) -> participant.getHand().size() == 2)
                    .hasSize(3);
        }
    }

    @Nested
    class 카드를뽑을수있는플레이어존재여부판단 {
        @Test
        void should_hasDrawablePlayer가true반환_when_카드를뽑을수있는플레이어존재() {
            //given
            Participants participants = Participants.create(List.of("포이"));
            Deck deck = Deck.create();
            participants.readyForGame(deck);

            //when
            boolean existingDrawablePlayer = participants.hasDrawablePlayer();

            //then
            assertThat(existingDrawablePlayer).isTrue();
        }

        @Test
        void should_hasDrawablePlayer가false반환_when_카드를뽑을수있는플레이어없을때() {
            //given
            Participants participants = Participants.create(List.of("포이"));
            Deck deck = Deck.create();
            deck.shuffle((cards) -> {
                cards.clear();
                cards.add(new Card(Suit.SPADE, Number.ACE));
                cards.add(new Card(Suit.SPADE, Number.JACK));
                cards.add(new Card(Suit.SPADE, Number.ACE));
                cards.add(new Card(Suit.SPADE, Number.JACK));
            });
            participants.readyForGame(deck);

            //when
            boolean existingDrawablePlayer = participants.hasDrawablePlayer();

            //then
            assertThat(existingDrawablePlayer).isFalse();
        }
    }

    @Nested
    class 카드를받을수있는플레이어이름반환 {
        @Test
        void should_카드를받을다음플레이어이름반환_when_카드를받을수있는플레이어가존재할시() {
            //given
            Participants participants = Participants.create(List.of("포이", "에밀"));
            Deck deck = Deck.create();
            participants.readyForGame(deck);
            String expected = "포이";

            //when
            String actual = participants.nextDrawablePlayerName();

            //then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void should_예외를반환한다_when_카드를받을수있는플레이어가없을시() {
            //given
            Participants participants = Participants.create(List.of("포이", "에밀"));
            Deck deck = Deck.create();
            deck.shuffle((cards) -> {
                cards.clear();
                cards.add(new Card(Suit.SPADE, Number.ACE));
                cards.add(new Card(Suit.SPADE, Number.JACK));
                cards.add(new Card(Suit.SPADE, Number.ACE));
                cards.add(new Card(Suit.SPADE, Number.JACK));
                cards.add(new Card(Suit.SPADE, Number.ACE));
                cards.add(new Card(Suit.SPADE, Number.JACK));
            });
            participants.readyForGame(deck);

            //when
            ThrowingCallable throwingCallable = participants::nextDrawablePlayerName;

            //then
            assertThatThrownBy(throwingCallable)
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("카드를 받을 수 있는 플레이어가 없습니다.");
        }
    }

    @Nested
    class 다음플레이어에게카드를나눠준다 {
        @Test
        void should_다음플레이어는카드를한장받는다_when_handOutCardToPlayer호출시() {
            //given
            Participants participants = Participants.create(List.of("포이", "에밀"));
            Deck deck = Deck.create();
            participants.readyForGame(deck);
            String expected = "에밀";

            //when
            participants.handOutCardToPlayer(new Card(Suit.SPADE, Number.ACE));
            String actual = participants.nextDrawablePlayerName();

            //then
            assertThat(actual).isEqualTo(expected);

        }
    }

    @Nested
    class 플레이어가카드를그만받는다 {
        @Test
        void should_카드를받을수있는플레이어가다음으로넘어간다_when_현재플레이어가stand할시() {
            //given
            Participants participants = Participants.create(List.of("포이", "에밀"));
            Deck deck = Deck.create();
            participants.readyForGame(deck);
            String expected = "에밀";

            //when
            participants.standCurrentPlayer();
            String actual = participants.nextDrawablePlayerName();

            //then
            assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    class 딜러가카드를뽑을수있는지여부판단 {
        @Test
        void should_isDealerDrawable가true반환_when_딜러점수가17점보다작을때() {
            //given
            Participants participants = Participants.create(List.of("포이"));
            Deck deck = Deck.create();
            deck.shuffle((cards) -> {
                cards.clear();
                cards.add(new Card(Suit.SPADE, Number.JACK));
                cards.add(new Card(Suit.SPADE, Number.JACK));
                cards.add(new Card(Suit.SPADE, Number.TEN));
                cards.add(new Card(Suit.SPADE, Number.SIX));
            });
            participants.readyForGame(deck);

            //when
            boolean dealerDrawable = participants.isDealerDrawable();

            //then
            assertThat(dealerDrawable).isTrue();
        }

        @Test
        void should_isDealerDrawable가false반환_when_딜러점수가17점이상일때() {
            //given
            Participants participants = Participants.create(List.of("포이"));
            Deck deck = Deck.create();
            deck.shuffle((cards) -> {
                cards.clear();
                cards.add(new Card(Suit.SPADE, Number.SEVEN));
                cards.add(new Card(Suit.SPADE, Number.JACK));
                cards.add(new Card(Suit.SPADE, Number.SEVEN));
                cards.add(new Card(Suit.SPADE, Number.JACK));
            });
            participants.readyForGame(deck);

            //when
            boolean dealerDrawable = participants.isDealerDrawable();

            //then
            assertThat(dealerDrawable).isFalse();
        }
    }

    @Nested
    class 딜러에게카드를나눠준다 {
        @Test
        void should_딜러는카드를한장받는다_when_handOutCardToDealer호출시() {
            //given
            Participants participants = Participants.create(List.of("포이"));
            Deck deck = Deck.create();
            deck.shuffle((cards) -> {
                cards.clear();
                cards.add(new Card(Suit.SPADE, Number.SEVEN));
                cards.add(new Card(Suit.SPADE, Number.NINE));
                cards.add(new Card(Suit.SPADE, Number.SEVEN));
                cards.add(new Card(Suit.SPADE, Number.NINE));
            });
            participants.readyForGame(deck);
            boolean expected = false;

            //when
            participants.handOutCardToDealer(new Card(Suit.SPADE, Number.ACE));
            boolean actual = participants.isDealerDrawable();

            //then
            assertThat(actual).isEqualTo(expected);

        }
    }
}
