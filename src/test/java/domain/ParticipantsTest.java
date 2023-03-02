package domain;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ParticipantsTest {

    @Test
    @DisplayName("중복된 이름이 입력되면, 예외가 발생한다")
    void givenDuplicateName_thenFail() {
        assertThatThrownBy(() -> Participants.from(List.of("준팍", "준팍", "파워", "파워")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복되지 않은 이름만 입력해주세요");
    }

    @Test
    @DisplayName("카드를 뽑으면 덱의 장수가 줄어든다")
    void drawCardTest() {
        //given
        final Participants participants = Participants.from(List.of("준팍", "파워", "범블비", "서브웨이"));
        final Deck deck = Deck.from(new RandomCardGenerator());
        //when
        participants.drawCard(deck);

        //then
        assertThat(deck)
                .extracting("deck", InstanceOfAssertFactories.collection(ArrayDeque.class))
                .hasSize(52 - 8);
    }
}
