package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.PlayerName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

public class GamerTest {

    @Test
    @DisplayName("hit 메서드는 입력받은 카드를 카드뭉치에 저장한다.")
    void hit_test() {
        Gamer gamer = new Player(new PlayerName("aki"));
        gamer.hit(new Card(CardNumber.EIGHT, CardType.CLOVER));
        gamer.hit(new Card(CardNumber.FIVE, CardType.SPADE));
        gamer.hit(new Card(CardNumber.TEN, CardType.SPADE));
        List<Card> cards = gamer.getCards().get();

        assertThat(cards.get(0)).isEqualTo(new Card(CardNumber.EIGHT, CardType.CLOVER));
        assertThat(cards.get(1)).isEqualTo(new Card(CardNumber.FIVE, CardType.SPADE));
        assertThat(cards.get(2)).isEqualTo(new Card(CardNumber.TEN, CardType.SPADE));
    }

    @Test
    @DisplayName("Gamer 클래스가 가진 카드의 점수를 계산하여 반환한다.")
    void get_score() {
        Gamer gamer = new Player(new PlayerName("aki"));
        gamer.hit(new Card(CardNumber.EIGHT, CardType.CLOVER));
        gamer.hit(new Card(CardNumber.THREE, CardType.SPADE));

        assertThat(gamer.getScore()).isEqualTo(11);
    }

    @Test
    @DisplayName("Gamer 클래스가 가진 카드의 점수가 21 이상이면 더이상 hit할 수 없다.")
    void validate_range() {
        Gamer gamer = new Player(new PlayerName("aki"));
        gamer.hit(new Card(CardNumber.EIGHT, CardType.CLOVER));
        gamer.hit(new Card(CardNumber.FIVE, CardType.SPADE));
        gamer.hit(new Card(CardNumber.TEN, CardType.SPADE));

        assertThat(gamer.isValidRange()).isFalse();
    }

    @Test
    @DisplayName("Ace 가진 카드의 총합이 21을 넘지 않으면 Ace는 11로 계산한다.")
    void ace_calculate_11() {
        Gamer gamer = new Player(new PlayerName("aki"));
        gamer.hit(new Card(CardNumber.TEN, CardType.CLOVER));
        gamer.hit(new Card(CardNumber.ACE, CardType.SPADE));

        assertThat(gamer.getScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("Ace 가진 카드의 총합이 21을 넘으면 Ace는 1로 계산한다.")
    void ace_calculate_1() {
        Gamer gamer = new Player(new PlayerName("aki"));
        gamer.hit(new Card(CardNumber.TEN, CardType.CLOVER));
        gamer.hit(new Card(CardNumber.THREE, CardType.HEART));
        gamer.hit(new Card(CardNumber.ACE, CardType.SPADE));

        assertThat(gamer.getScore()).isEqualTo(14);
    }
}
