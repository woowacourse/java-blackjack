package blackjack.domain.player;

import blackjack.domain.card.Deck;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ParticipantTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("참여자 이름은 비어있을 수 없다")
    void checkNameNullOrEmpty(String name) {
        Deck deck = new Deck();
        assertThatThrownBy(() -> new Participant(deck.initDistributeCard(), name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이름은 비어있을 수 없습니다.");
    }

    @Test
    @DisplayName("참가자는 시작시 카드를 2장 받는다.")
    void checkParticipantCardSize() {
        Deck deck = new Deck();
        Participant participant = new Participant(deck.initDistributeCard(), "pobi");
        assertThat(participant.getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("참가자는 추가로 카드를 받을 수 있다.")
    void addParticipantCard() {
        Deck deck = new Deck();
        Participant participant = new Participant(deck.initDistributeCard(), "pobi");
        int size = participant.getCards().size();
        participant.addCard(deck.draw());
        assertThat(participant.getCards().size()).isEqualTo(size + 1);
    }
}