package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Type;
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
    @DisplayName("Participant는 딜러가 아니다.")
    void check_dealer() {
        Player participant = new Participant(new Name("alien"));

        assertThat(participant.isDealer()).isFalse();
    }

    @Test
    @DisplayName("isValidRange 메서드는 카드의 총합이 21미만이면 참이 반환된다.")
    void validate_range_true() {
        Player participant = new Participant(new Name("alien"));
        participant.hit(Card.of(CardNumber.TEN, Type.CLOVER));
        participant.hit(Card.of(CardNumber.TEN, Type.SPADE));

        assertThat(participant.canHit()).isTrue();
}
}
