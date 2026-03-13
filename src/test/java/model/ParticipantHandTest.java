package model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class ParticipantHandTest {

    @Test
    public void 일반_카드_점수를_합산한다() {
        Player player = new Player(new PlayerName("player1"), new BettingMoney("10000"));
        List<Card> cards = List.of(
                new Card(Shape.CLOVER, CardNumber.TEN),
                new Card(Shape.HEART, CardNumber.EIGHT)
        );

        cards.forEach(player::draw);

        assertThat(player.getScore()).isEqualTo(18);
    }

    @Test
    public void 에이스가_있고_21을_넘지_않으면_10을_추가한다() {
        Player player = new Player(new PlayerName("player1"), new BettingMoney("10000"));
        List<Card> cards = List.of(
                new Card(Shape.CLOVER, CardNumber.ACE),
                new Card(Shape.SPADE, CardNumber.KING)
        );

        cards.forEach(player::draw);

        assertThat(player.getScore()).isEqualTo(21);
    }

    @Test
    public void 에이스가_있어도_10을_추가하면_21을_넘으면_추가하지_않는다() {
        Player player = new Player(new PlayerName("player1"), new BettingMoney("10000"));
        List<Card> cards = List.of(
                new Card(Shape.CLOVER, CardNumber.ACE),
                new Card(Shape.SPADE, CardNumber.ACE),
                new Card(Shape.HEART, CardNumber.KING)
        );

        cards.forEach(player::draw);

        assertThat(player.getScore()).isEqualTo(12);
    }
}
