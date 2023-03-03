package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.InstanceOfAssertFactories.collection;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.Objects;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Nested;
import java.util.List;
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
                    () -> {
                        assertThat(participants).extracting("participants", collection(Participant.class))
                                .first()
                                .isInstanceOf(Dealer.class);
                    },
                    () -> {
                        assertThat(participants).extracting("participants", collection(Participant.class))
                                .filteredOn((participant) -> Objects.equals(Player.class, participant.getClass()))
                                .hasSize(playerNames.size());
                    }
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
}
