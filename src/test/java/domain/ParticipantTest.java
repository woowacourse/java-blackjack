package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class ParticipantTest {
    @ParameterizedTest
    @DisplayName("이름이 공백이거나 공백이나 특수문자가 포함된 경우 오류가 발생한다.")
    @EmptySource
    @ValueSource(strings = {" ", "공 백문자포함", "특수문자포함!"})
    void shouldThrowExceptionForInvalidName(String name) {
        // when & then
        assertThatThrownBy(
                () -> new TestParticipant(name)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("참가자가 카드를 뽑으면, 참가자의 덱에 카드가 추가된다.")
    void shouldAddCardToParticipantDeck() {
        // given
        String name = "테스트플레이어";
        TestParticipant testParticipant = new TestParticipant(name);
        Card card = new Card(CardShape.SPADE, CardContents.A);

        // when
        testParticipant.addCard(card);

        // then
        assertThat(testParticipant.getCards()).contains(card);
    }

    private static class TestParticipant extends Participant {
        //TODO: Mockito 사용으로 더미 구현 코드 제거 고민해볼 것
        public TestParticipant(String name) {
            super(name);
        }

        @Override
        public List<Card> getInitialVisibleCards() { // 추상 메서드 더미 구현
            return List.of();
        }

        @Override
        public boolean isDrawable() {
            return true;
        }
    }
}
