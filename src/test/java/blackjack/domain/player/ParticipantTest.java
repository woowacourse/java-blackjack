package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Type;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParticipantTest {

    @Test
    @DisplayName("Player 클래스는 이름을 입력받으면 정상적으로 생성된다.")
    void create_player() {
        Name name = new Name("aki");

        assertThatCode(() -> new Participant(name)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("hit 메서드는 입력받은 카드를 카드뭉치에 저장한다.")
    void hit_test() {
        Player player = new Participant(new Name("aki"));
        player.hit(Card.of(CardNumber.EIGHT, Type.CLOVER));
        player.hit(Card.of(CardNumber.FIVE, Type.SPADE));
        player.hit(Card.of(CardNumber.TEN, Type.DIAMOND));
        List<Card> playerCards = player.getPlayerCards().get();

        assertThat(playerCards.get(0)).isEqualTo(Card.of(CardNumber.EIGHT, Type.CLOVER));
        assertThat(playerCards.get(1)).isEqualTo(Card.of(CardNumber.FIVE, Type.SPADE));
        assertThat(playerCards.get(2)).isEqualTo(Card.of(CardNumber.TEN, Type.DIAMOND));
    }

    @Test
    @DisplayName("Player 클래스가 가진 카드의 점수를 계산하여 반환한다.")
    void get_score() {
        Player player = new Participant(new Name("aki"));
        player.hit(Card.of(CardNumber.EIGHT, Type.CLOVER));
        player.hit(Card.of(CardNumber.THREE, Type.SPADE));

        assertThat(player.getScore()).isEqualTo(11);
    }

    @Test
    @DisplayName("isValidRange 메서드는 카드의 총합이 21이상인지 검사한다.")
    void validate_range() {
        Player player = new Participant(new Name("aki"));
        player.hit(Card.of(CardNumber.EIGHT, Type.CLOVER));
        player.hit(Card.of(CardNumber.FIVE, Type.SPADE));
        player.hit(Card.of(CardNumber.TEN, Type.DIAMOND));

        assertThat(player.isValidRange()).isFalse();
    }

    @Test
    @DisplayName("Ace 가진 카드의 총합이 21을 넘지 않으면 Ace는 11로 계산한다.")
    void ace_calculate_11() {
        Player player = new Participant(new Name("aki"));
        player.hit(Card.of(CardNumber.TEN, Type.CLOVER));
        player.hit(Card.of(CardNumber.ACE, Type.SPADE));

        assertThat(player.getScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("Ace 가진 카드의 총합이 21을 넘으면 Ace는 1로 계산한다.")
    void ace_calculate_1() {
        Player player = new Participant(new Name("aki"));
        player.hit(Card.of(CardNumber.TEN, Type.CLOVER));
        player.hit(Card.of(CardNumber.THREE, Type.HEART));
        player.hit(Card.of(CardNumber.ACE, Type.SPADE));

        assertThat(player.getScore()).isEqualTo(14);
    }
}
