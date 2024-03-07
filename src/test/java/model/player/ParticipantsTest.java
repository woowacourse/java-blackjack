package model.player;

import java.util.List;
import model.card.Cards;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    @DisplayName("중복되는 이름 가진 참가자가 있으면 예외가 발생한다.")
    @Test
    void validate() {
        Assertions.assertThatThrownBy(() ->
                        new Participants(List.of(
                                new Participant("켬미"),
                                new Participant("켬미"))))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
