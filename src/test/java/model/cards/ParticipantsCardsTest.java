package model.cards;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.IllegalBlackjackStateException;
import java.util.ArrayList;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsCardsTest {

    @DisplayName("플레이어 이름을 통해 Cards를 찾는다.")
    @Test
    void test1() {
        PlayerCards pobiCards = new PlayerCards(new ArrayList<>());
        ParticipantsCards participantsCards = new ParticipantsCards(
                new DealerCards(new ArrayList<>()),
                Map.of(
                "pobi", pobiCards,
                "jason", new PlayerCards(new ArrayList<>())
        ));

        assertThat(participantsCards.findCardsByName("pobi")).isEqualTo(pobiCards);
    }

    @DisplayName("플레이어가 존재하지 않을 경우 예외가 발생한다.")
    @Test
    void test2() {
        ParticipantsCards participantsCards = new ParticipantsCards(
                new DealerCards(new ArrayList<>()),
                Map.of(
                "pobi", new PlayerCards(new ArrayList<>()),
                "jason", new PlayerCards(new ArrayList<>())
        ));

        assertThatThrownBy(() -> participantsCards.findCardsByName("none"))
                .isInstanceOf(IllegalBlackjackStateException.class);
    }
}
