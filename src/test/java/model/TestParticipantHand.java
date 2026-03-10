package model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import dto.Card;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TestParticipantHand {

    static Stream<Arguments> scoreCases() {
        return Stream.of(
            Arguments.of(
                List.of(new Card(Shape.CLOVER, CardNumber.ACE),
                        new Card(Shape.SPADE, CardNumber.ACE)),
                12
            ),
            Arguments.of(
                List.of(new Card(Shape.CLOVER, CardNumber.ACE),
                        new Card(Shape.SPADE, CardNumber.ACE),
                        new Card(Shape.HEART, CardNumber.KING)),
                12
            ),
            Arguments.of(
                List.of(new Card(Shape.SPADE, CardNumber.ACE),
                        new Card(Shape.CLOVER, CardNumber.KING),
                        new Card(Shape.CLOVER, CardNumber.TEN)),
                21
            ),
            Arguments.of(
                List.of(new Card(Shape.CLOVER, CardNumber.TEN),
                        new Card(Shape.HEART, CardNumber.EIGHT)),
                18
            )
        );
    }

    @ParameterizedTest
    @MethodSource("scoreCases")
    public void 점수_계산(List<Card> cards, int expectedScore) {
        Player player = new Player(new PlayerName("player1"), new BattingMoney("10000"));
        cards.forEach(player::draw);

        assertThat(player.getScore()).isEqualTo(expectedScore);
    }
}


