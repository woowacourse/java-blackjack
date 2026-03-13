package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {
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
