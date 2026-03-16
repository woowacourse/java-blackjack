package domain.player;

import domain.card.Card;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    @Test
    @DisplayName("초기 2장의 합이 21, 블랙잭인지 확인")
    void 초기_2장의_합이_21_블랙잭인지_검증() {
        Participant participant = new Participant("pobi");
        participant.addCard(new Card("K", "다이아몬드"));
        participant.addCard(new Card("A", "다이아몬드"));

        Assertions.assertThat(participant.isBlackJack()).isEqualTo(true);
    }
}