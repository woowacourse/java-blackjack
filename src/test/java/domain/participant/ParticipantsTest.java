package domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.card.CardDeck;
import domain.shuffler.FixedCardsShuffler;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    @DisplayName("전체 참가자의 수는 2명 이상 8명 이하여야 한다.")
    @Test
    void validateSizeTest() {
        CardDeck cardDeck = new CardDeck(new FixedCardsShuffler());
        assertAll(
                () -> assertDoesNotThrow(() -> new Participants(List.of("깃짱", "망고"), cardDeck)),
                () -> assertThatThrownBy(() -> new Participants(List.of(), cardDeck))
                        .isInstanceOf(IllegalArgumentException.class),
                () -> assertThatThrownBy(
                        () -> new Participants(List.of("깃짱", "망고", "저문", "이리내", "디노", "오잉", "체인저", "토리"), cardDeck))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }
}
