package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Score;
import blackjack.domain.card.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

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

    @ParameterizedTest
    @MethodSource("participantList")
    @DisplayName("참가자는 카드에 따라 올바른 점수를 부여받는다.")
    void calculateParticipantScore(List<Card> cards, List<Card> addCards, int score) {
        Participant participant = new Participant(cards, "pobi");

        for (Card card : addCards) {
            participant.addCard(card);
        }

        assertThat(participant.calculateScore())
                .isEqualTo(score);
    }

    private static Stream<Arguments> participantList() {
        return Stream.of(
                Arguments.of(List.of(
                        new Card(Type.SPADE, Score.EIGHT),
                        new Card(Type.HEART, Score.EIGHT)
                ), Collections.emptyList(), 16),
                Arguments.of(List.of(
                        new Card(Type.SPADE, Score.ACE),
                        new Card(Type.HEART, Score.ACE)
                ), Collections.emptyList(), 12),
                Arguments.of(List.of(
                        new Card(Type.SPADE, Score.ACE),
                        new Card(Type.HEART, Score.ACE)
                ), List.of(new Card(Type.HEART, Score.NINE)), 21),
                Arguments.of(List.of(
                        new Card(Type.SPADE, Score.ACE),
                        new Card(Type.HEART, Score.ACE)
                ), List.of(new Card(Type.HEART, Score.NINE),
                        new Card(Type.DIAMOND, Score.NINE)), 20),
                Arguments.of(List.of(
                        new Card(Type.SPADE, Score.ACE),
                        new Card(Type.HEART, Score.KING)
                ), Collections.emptyList(), 21),
                Arguments.of(List.of(
                        new Card(Type.SPADE, Score.ACE),
                        new Card(Type.HEART, Score.ACE)
                ),List.of(new Card(Type.HEART, Score.SIX)), 18),
                Arguments.of(List.of(
                        new Card(Type.SPADE, Score.FIVE),
                        new Card(Type.HEART, Score.SIX)
                ),List.of(new Card(Type.HEART, Score.ACE)), 12)
        );
    }
}