package model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import dto.Card;
import dto.PlayerName;
import org.junit.jupiter.api.Test;

public class TestScorer {

    @Test
    public void 일반_카드_변환_정상_작동() {
        Card card = new Card(Shape.CLOVER,CardNumber.EIGHT);

        assertThat(Scorer.calculate(card)).isEqualTo(card.cardNumber().getScore());
    }

    @Test
    public void 블랙잭_점수_판정_정상_작동_테스트() {
        Player player = new Player(new PlayerName("player1"));

        player.addCard(new Card(Shape.CLOVER, CardNumber.ACE));
        player.addCard(new Card(Shape.SPADE, CardNumber.ACE));
        player.addScore(2);

        Scorer.updateFinalScore(player);

        assertThat(player.getResult().score()).isEqualTo(12);

        Player player2 = new Player(new PlayerName("player2"));

        player2.addCard(new Card(Shape.CLOVER, CardNumber.ACE));
        player2.addCard(new Card(Shape.SPADE, CardNumber.ACE));
        player2.addCard(new Card(Shape.CLOVER, CardNumber.KING));
        player2.addScore(12);

        Scorer.updateFinalScore(player2);

        assertThat(player2.getResult().score()).isEqualTo(12);
    }

}
