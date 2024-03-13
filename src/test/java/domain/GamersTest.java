package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import controller.dto.gamer.GamerHandScore;
import controller.dto.gamer.GamerHandStatus;
import domain.constants.Score;
import domain.constants.Shape;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamersTest {
    @DisplayName("게이머들의 이름 목록을 반환온다.")
    @Test
    void getGamerNames() {
        // given
        List<Gamer> gamerList = List.of(
                new Gamer("pobi"),
                new Gamer("jason")
        );
        Gamers gamers = new Gamers(gamerList);

        // when
        List<String> gamerNames = gamers.getNames();

        // then
        assertThat(gamerNames).containsExactly("pobi", "jason");
    }

    @DisplayName("게이머들이 가지고 있는 카드와 총점 정보를 반환한다.")
    @Test
    void getCurrentGamerHandScore() {
        // given
        List<Gamer> gamerList = List.of(
                new Gamer("pobi", createNormalWithTwoCards())
        );
        Gamers gamers = new Gamers(gamerList);

        // when
        List<GamerHandScore> gamerHandScores = gamers.getCurrentGamerHandScore();
        GamerHandScore pobiHandScore = gamerHandScores.get(0);
        GamerHandStatus pobiHandStatus = pobiHandScore.gamerHandStatus();

        // then
        assertAll(
                () -> assertThat(pobiHandStatus.name()).isEqualTo("pobi"),
                () -> assertThat(pobiHandStatus.hands()).isEqualTo("10클로버, A다이아몬드"),
                () -> assertThat(pobiHandScore.score()).isEqualTo(21)
        );
    }

    private Hand createNormalWithTwoCards() {
        Hand hand = new Hand();
        hand.saveCards(new ArrayList<>(Arrays.asList(
                new Card(Score.TEN, Shape.CLOVER),
                new Card(Score.ACE, Shape.DIAMOND)
        )));
        return hand;
    }
}
