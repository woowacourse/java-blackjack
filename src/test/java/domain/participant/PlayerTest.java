package domain.participant;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @DisplayName("사용자의 점수가 21이하면 카드를 받을 수 있다.")
    @Test
    void canHit() {
        Player player = new Player(new Name("user"));
        player.receiveCard(new Card(Shape.HEART, Rank.KING));
        player.receiveCard(new Card(Shape.DIA, Rank.FIVE));
        player.receiveCard(new Card(Shape.HEART, Rank.SIX));

        boolean canHit = player.canHit();

        assertThat(canHit).isTrue();
    }

    @DisplayName("사용자의 점수가 22이상이면 카드를 받을 수 없다.")
    @Test
    void canNotHit() {
        Player player = new Player(new Name("user"));
        player.receiveCard(new Card(Shape.HEART, Rank.KING));
        player.receiveCard(new Card(Shape.DIA, Rank.KING));
        player.receiveCard(new Card(Shape.HEART, Rank.TWO));

        boolean canHit = player.canHit();

        assertThat(canHit).isFalse();
    }
}
