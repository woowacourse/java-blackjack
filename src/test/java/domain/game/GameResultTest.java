package domain.game;

import domain.player.Dealer;
import domain.player.Participant;
import domain.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.*;

class GameResultTest {

    private GameResult gameResult;

    @BeforeEach
    public void beforeEach() {
        //given
        final List<Participant> participants = List.of(Participant.of("준팍", 13),
                Participant.of("범블비", 12),
                Participant.of("파워", 9),
                Participant.of("서브웨이", 20));


        gameResult = GameResult.of(Dealer.create(12), participants);
    }


    @Test
    @DisplayName("패자 리스트를 반환한다.")
    void getLosersTest() {
        //when
        final List<String> resultNames = gameResult.getLosers().stream()
                .map(Player::getName)
                .collect(toList());

        //then
        assertThat(resultNames).isEqualTo(List.of("파워"));
    }

    @Test
    @DisplayName("승자 리스트를 반환한다.")
    void getWinnersTest() {
        //when
        final List<String> resultNames = gameResult.getWinners().stream()
                .map(Player::getName)
                .collect(toList());

        //then
        assertThat(resultNames).isEqualTo(List.of("준팍", "서브웨이"));
    }

    @Test
    @DisplayName("무승부 리스트를 반환한다.")
    void getDrawParticipantsTest() {
        //when
        final List<String> resultNames = gameResult.getDrawParticipants().stream()
                .map(Player::getName)
                .collect(toList());

        //then
        assertThat(resultNames).isEqualTo(List.of("범블비"));
    }


}
