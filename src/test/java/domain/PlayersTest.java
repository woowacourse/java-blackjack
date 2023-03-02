package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.InstanceOfAssertFactories.collection;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.List;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.ThrowingSupplier;

class PlayersTest {
    @Nested
    class 생성 {
        @Test
        void should_예외를던진다_when_참여자가0명인경우() {
            //given
            List<String> rawNames = Collections.EMPTY_LIST;

            //when
            ThrowingCallable throwingCallable = () -> Players.from(rawNames);

            //then
            assertThatThrownBy(throwingCallable)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("참여자는 1명 이상이어야 합니다.");
        }

        @Test
        void should_정상생성_when_참여자가1명이상() {
            //given
            List<String> rawNames = List.of("이름1");

            //when
            ThrowingSupplier<Players> playersThrowingSupplier = () -> Players.from(rawNames);

            //then
            assertDoesNotThrow(playersThrowingSupplier);
        }
    }

    @Nested
    class 카드분배 {
        @Test
        void should_플레이어가카드를받는다_when_initialize호출() {
            //given
            Players players = Players.from(List.of("에밀", "poi"));
            Deck deck = Deck.create();

            //when
            players.receiveTwoCards(deck);

            //then
            assertThat(players).extracting("players", collection(Player.class))
                    .asList()
                    .element(0)
                    .extracting("hand")
                    .extracting("cards", collection(Card.class))
                    .hasSize(2)
                    .containsSequence(new Card(Suit.CLUB, Number.QUEEN), new Card(Suit.CLUB, Number.JACK));

            assertThat(players).extracting("players", collection(Player.class))
                    .asList()
                    .element(1)
                    .extracting("hand")
                    .extracting("cards", collection(Card.class))
                    .hasSize(2)
                    .containsSequence(new Card(Suit.CLUB, Number.TEN), new Card(Suit.CLUB, Number.KING));
        }
    }
}