package domain.game;

import domain.card.TestCardGenerator;
import domain.card.Rank;

import domain.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.*;

class ResultsTest {

    private TestCardGenerator testCardGenerator;
    private Results results;

    @BeforeEach
    public void beforeEach() {
        //given
        final List<String> participantNames = List.of("준팍", "범블비", "파워", "서브웨이");
        final List<Rank> ranks = List.of(Rank.ACE, Rank.ACE, Rank.SIX, Rank.SEVEN, Rank.ACE, Rank.ACE, Rank.FOUR, Rank.FIVE, Rank.KING, Rank.JACK);
        testCardGenerator = TestCardGenerator.from(ranks);

        final BlackjackGame blackjackGame = BlackjackGame.from(participantNames, testCardGenerator);

        blackjackGame.drawCards();
        results = Results.of(blackjackGame.getDealer(), blackjackGame.getParticipants());
    }


    @Test
    @DisplayName("패자 리스트를 반환한다.")
    void getLosersTest() {
        //when
        final List<String> resultNames = results.getLosers().stream()
                .map(Player::getName)
                .collect(toList());

        //then
        assertThat(resultNames).isEqualTo(List.of("파워"));
    }

    @Test
    @DisplayName("승자 리스트를 반환한다.")
    void getWinnersTest() {
        //when
        final List<String> resultNames = results.getWinners().stream()
                .map(Player::getName)
                .collect(toList());

        //then
        assertThat(resultNames).isEqualTo(List.of("준팍", "서브웨이"));
    }

    @Test
    @DisplayName("무승부 리스트를 반환한다.")
    void getDrawParticipantsTest() {
        //when
        final List<String> resultNames = results.getDrawParticipants().stream()
                .map(Player::getName)
                .collect(toList());

        //then
        assertThat(resultNames).isEqualTo(List.of("범블비"));
    }


}
