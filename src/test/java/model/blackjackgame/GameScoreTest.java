package model.blackjackgame;

import static model.card.CardNumber.ACE;
import static model.card.CardNumber.EIGHT;
import static model.card.CardNumber.JACK;
import static model.card.CardShape.HEART;
import static model.card.CardShape.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import model.card.Card;
import model.card.Hand;
import model.dealer.Dealer;
import model.player.Player;
import model.result.ParticipantScore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GameScoreTest {

    @DisplayName("플레이어의 카드 합이 21 또는 21에 가깝게 스코어를 계산한다")
    @ParameterizedTest
    @MethodSource("provideCardsAndExpectedScore")
    void testCalculateGameScoreForPlayer(Hand hand, int expectedScore) {
        Player player = new Player("jojo", hand);
        ParticipantScore playerScore = ParticipantScore.from(player);
        assertThat(playerScore.getScore()).isEqualTo(expectedScore);
    }

    @DisplayName("딜러의 카드 합이 21 또는 21에 가깝게 스코어를 계산한다")
    @ParameterizedTest
    @MethodSource("provideCardsAndExpectedScore")
    void testCalculateGameScoreForDealer(Hand hand, int expectedScore) {
        Dealer dealer = new Dealer(hand);
        ParticipantScore dealerScore = ParticipantScore.from(dealer);
        assertThat(dealerScore.getScore()).isEqualTo(expectedScore);
    }

    private static Stream<Arguments> provideCardsAndExpectedScore() {
        return Stream.of(
            Arguments.of(
                new Hand(List.of(new Card(ACE, SPADE), new Card(JACK, HEART))),
                21
            ),
            Arguments.of(
                new Hand(
                    List.of(new Card(ACE, SPADE), new Card(ACE, SPADE), new Card(ACE, SPADE))),
                13
            ),
            Arguments.of(
                new Hand(
                    List.of(new Card(ACE, SPADE), new Card(ACE, SPADE), new Card(EIGHT, HEART))),
                20
            )
        );
    }
}
