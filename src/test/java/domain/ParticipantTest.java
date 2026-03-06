package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.enums.Rank;
import domain.enums.Suit;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ParticipantTest {

    private static Stream<Arguments> safeScoreCards() {
        return Stream.of(
                Arguments.of(List.of(new Card(Rank.FIVE, Suit.CLOVER), new Card(Rank.SIX, Suit.CLOVER)), 11),
                Arguments.of(List.of(new Card(Rank.JACK, Suit.CLOVER), new Card(Rank.QUEEN, Suit.CLOVER), new Card(Rank.TWO, Suit.CLOVER)), 22),
                Arguments.of(List.of(new Card(Rank.JACK, Suit.CLOVER), new Card(Rank.QUEEN, Suit.CLOVER), new Card(Rank.ACE, Suit.CLOVER), new Card(Rank.ACE, Suit.DIAMOND)), 22),
                Arguments.of(List.of(new Card(Rank.ACE, Suit.CLOVER), new Card(Rank.ACE, Suit.DIAMOND), new Card(Rank.SEVEN, Suit.CLOVER)), 19),
                Arguments.of(List.of(new Card(Rank.ACE, Suit.CLOVER), new Card(Rank.ACE, Suit.DIAMOND),
                        new Card(Rank.ACE, Suit.SPADE), new Card(Rank.ACE, Suit.HEART), new Card(Rank.JACK, Suit.CLOVER)), 14)
        );
    }

    @DisplayName("카드 점수 합계를 정상적으로 계산한다.(버스트 고려 X)")
    @ParameterizedTest
    @MethodSource("safeScoreCards")
    public void 카드_점수_합계를_정상적으로_계산한다(List<Card> cards, int expectedValue) {
        Participant participant = new Dealer();
        cards.forEach(participant::addCard);
        int score = participant.cardBoard.calculateScore();

        assertThat(score).isEqualTo(expectedValue);
    }

    @DisplayName("카드 점수 합계가 21을 초과하면 버스트를 판정한다.")
    @Test
    public void 카드_점수_합계가_21을_넘으면_버스트를_판정한다() {
        Participant participant = new Player("stark");
        List<Card> burstCards = List.of(new Card(Rank.JACK, Suit.CLOVER), new Card(Rank.QUEEN, Suit.CLOVER), new Card(Rank.TWO, Suit.CLOVER));
        burstCards.forEach(participant::addCard);

        assertThat(participant.cardBoard.isBurst()).isTrue();
    }
}
