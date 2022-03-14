package blackjack.domain.player;

import blackjack.domain.card.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ParticipantTest {

    private Deck deck;
    private List<Card> initCards;

    @BeforeEach
    void setup() {
        deck = new Deck(new RandomCardGenerator());
        initCards = List.of(deck.draw(), deck.draw());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("참여자 이름은 비어있을 수 없다")
    void checkNameNullOrEmpty(String name) {
        assertThatThrownBy(() -> new Participant(initCards, name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이름은 비어있을 수 없습니다.");
    }

    @Test
    @DisplayName("참가자는 시작시 카드를 2장 받는다.")
    void checkParticipantCardSize() {
        Participant participant = new Participant(initCards, "pobi");
        assertThat(participant.getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("참가자는 추가로 카드를 받을 수 있다.")
    void addParticipantCard() {
        Participant participant = new Participant(initCards, "pobi");
        int size = participant.getCards().size();
        participant.addCard(deck.draw());
        assertThat(participant.getCards().size()).isEqualTo(size + 1);
    }

    @ParameterizedTest
    @MethodSource("notBurstCase")
    @DisplayName("참가자는 Burst가 되지 않으면 카드를 받을 수 있다.")
    void acceptCardWhenNotBurst(List<Card> cards, boolean acceptable) {
        Participant participant = new Participant(cards, "pobi");

        assertThat(participant.acceptableCard()).isEqualTo(acceptable);
    }

    private static Stream<Arguments> notBurstCase() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(Type.SPADE, Score.EIGHT),
                                new Card(Type.HEART, Score.EIGHT)),
                        true
                ),
                Arguments.of(
                        List.of(
                                new Card(Type.SPADE, Score.ACE),
                                new Card(Type.HEART, Score.KING)),
                        true
                )
        );
    }

    @Test
    @DisplayName("참가자는 Burst가 되면 카드를 받을 수 없다.")
    void notAcceptCardWhenBurst() {
        Participant participant = new Participant(List.of(
                new Card(Type.SPADE, Score.EIGHT),
                new Card(Type.HEART, Score.EIGHT)), "pobi");
        participant.addCard(new Card(Type.DIAMOND, Score.SIX));
        assertThat(participant.acceptableCard()).isEqualTo(false);
    }
}
