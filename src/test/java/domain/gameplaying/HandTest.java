package domain.gameplaying;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import domain.gameplaying.strategy.InfiniteDeck;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class HandTest {

    @Test
    @DisplayName("카드가 한 장 손 패에 추가되어야 한다.")
    void 카드_한_장_뽑기() {
        Hand hand = new Hand(new InfiniteDeck(), new ArrayList<>());
        hand.drawCard();

        int expected = 1;
        int actual = hand.cards().size();

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("randomCards")
    @DisplayName("손패 카드의 합을 반환해야 한다.")
    void 손패_합_반환(List<Card> cards, int sum) {
        Hand hand = new Hand(new InfiniteDeck(), cards);

        int actual = hand.scoreSum();

        assertEquals(sum, actual);
    }

    @Test
    @DisplayName("손패의 카드 정보들을 반환해야 한다.")
    void 손패_카드들_정보_반환() {
        Card card = new Card(CardRank.QUEEN, CardMark.SPADE);
        List<Card> cards = List.of(card);
        Hand hand = new Hand(new InfiniteDeck(), cards);


        assertThat(hand.cards().getFirst().toString()).isEqualTo(card.toString());
    }

    @ParameterizedTest
    @MethodSource("bustedHands")
    @DisplayName("버스트 상태를 확인할 수 있어야 한다.")
    void 버스트_상태_확인(Hand bustedHand) {
        assertThat(bustedHand.isBusted()).isTrue();
    }

    private static Stream<Hand> bustedHands() {
        return TestFixtures.bustedHands();
    }

    private static Stream<Arguments> randomCards() {
        return Stream.of(
                Arguments.arguments(List.of(new Card(CardRank.QUEEN, CardMark.SPADE)), 10),
                Arguments.arguments(
                        List.of(new Card(CardRank.QUEEN, CardMark.SPADE), new Card(CardRank.EIGHT, CardMark.HEART)),
                        18),
                Arguments.arguments(
                        List.of(new Card(CardRank.QUEEN, CardMark.SPADE), new Card(CardRank.EIGHT, CardMark.HEART),
                                new Card(CardRank.QUEEN, CardMark.CLOVER)), 28)
        );
    }
}
