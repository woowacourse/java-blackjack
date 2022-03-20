package blakjack.domain.state.finished;

import blakjack.domain.Chip;
import blakjack.domain.PrivateArea;
import blakjack.domain.card.Card;
import blakjack.domain.state.State;
import blakjack.domain.state.gameresult.Draw;
import blakjack.domain.state.gameresult.Lose;
import blakjack.domain.state.gameresult.Win;
import blakjack.domain.state.running.Init;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static blakjack.domain.card.MockCard.*;
import static org.assertj.core.api.Assertions.assertThat;

public class StayTest {

    private State playerState;
    private State dealerState;

    @BeforeEach
    void setUp() {
        final PrivateArea playerArea = new PrivateArea("칙촉");
        final Chip playerChip = new Chip(1000);
        playerState = new Init(playerArea, playerChip);
        final PrivateArea dealerArea = new PrivateArea("딜러");
        final Chip dealerChip = new Chip(1000);
        dealerState = new Init(dealerArea, dealerChip);
    }

    @ParameterizedTest
    @DisplayName("둘 다 스테이인 경우")
    @MethodSource("provideCardsWithState")
    void bothStay(final Class<State> expected, final List<Card> playerCards, final List<Card> dealerCards) {
        for (final Card playerCard : playerCards) {
            playerState = playerState.draw(playerCard);
        }
        playerState = playerState.stay();

        for (final Card dealerCard : dealerCards) {
            dealerState = dealerState.draw(dealerCard);
        }
        dealerState = dealerState.stay();

        assertThat(playerState.compare(dealerState)).isExactlyInstanceOf(expected);
    }

    public static Stream<Arguments> provideCardsWithState() {
        return Stream.of(
                Arguments.of(Draw.class, List.of(CLUB_TEN, CLUB_TEN), List.of(CLUB_TEN, CLUB_TEN)),
                Arguments.of(Win.class, List.of(CLUB_TEN, CLUB_TEN), List.of(CLUB_TEN, CLUB_NINE)),
                Arguments.of(Lose.class, List.of(CLUB_NINE, CLUB_NINE), List.of(CLUB_TEN, CLUB_NINE))
        );
    }

    @Test
    @DisplayName("플레이어가 스테이, 딜러가 버스트인 경우 우승 반환")
    void win() {
        playerState = playerState.draw(CLUB_TEN);
        playerState = playerState.draw(CLUB_TEN);
        playerState = playerState.stay();

        dealerState = dealerState.draw(CLUB_TEN);
        dealerState = dealerState.draw(CLUB_TEN);
        dealerState = dealerState.draw(CLUB_TEN);

        assertThat(playerState.compare(dealerState)).isExactlyInstanceOf(Win.class);
    }

    @Test
    @DisplayName("플레이어가 스테이, 딜러가 블랙잭인 경우 패배 반환")
    void lose() {
        playerState = playerState.draw(CLUB_TEN);
        playerState = playerState.draw(CLUB_TEN);
        playerState = playerState.stay();

        dealerState = dealerState.draw(CLUB_TEN);
        dealerState = dealerState.draw(HEART_ACE);

        assertThat(playerState.compare(dealerState)).isExactlyInstanceOf(Lose.class);
    }

    @Test
    @DisplayName("딜러가 스테이, 플레이어가 블랙잭인 경우 패배 반환")
    void lose2() {
        dealerState = dealerState.draw(CLUB_TEN);
        dealerState = dealerState.draw(CLUB_TEN);
        dealerState = dealerState.stay();

        playerState = playerState.draw(CLUB_TEN);
        playerState = playerState.draw(HEART_ACE);

        assertThat(dealerState.compare(playerState)).isExactlyInstanceOf(Lose.class);
    }

}
