package model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import dto.Card;
import org.junit.jupiter.api.Test;

public class TestScorer {

    @Test
    public void 블랙잭_점수_판정_정상_작동_테스트_에이스_11일때() {
        Player player = new Player(new PlayerName("player1"));

        player.draw(new Card(Shape.CLOVER, CardNumber.ACE));
        player.draw(new Card(Shape.SPADE, CardNumber.ACE));

        Scorer.updateAceScore(player);

        assertThat(player.getResult().score()).isEqualTo(12);

    }

    @Test
    public void 블랙잭_점수_판정_정상_작동_테스트_에이스_1일때() {
        Player player = new Player(new PlayerName("player1"));

        player.draw(new Card(Shape.SPADE, CardNumber.ACE));
        player.draw(new Card(Shape.CLOVER, CardNumber.KING));
        player.draw(new Card(Shape.CLOVER, CardNumber.TEN));

        Scorer.updateAceScore(player);

        assertThat(player.getResult().score()).isEqualTo(21);

    }

}
