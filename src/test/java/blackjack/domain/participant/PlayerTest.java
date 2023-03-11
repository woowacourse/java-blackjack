package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.card.Number;
import blackjack.domain.card.Pattern;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    @DisplayName("이름의 길이는 최소 1글자에서 최대 5글자이다.")
    void validateNameLength() {
        // given
        String name = "millie";

        // expect
        assertThatIllegalArgumentException().isThrownBy(() ->
                new Player(new Participant(Hand.generateEmptyCards()), name)
        ).withMessage("[ERROR] 이름 길이는 최소 1글자에서 최대 5글자 입니다.");
    }

    @Test
    @DisplayName("카드를 새로 받는 기능 테스트")
    void addCardTest() {
        // given
        Player player = new Player(new Participant(Hand.generateEmptyCards()), "doggy");

        // when
        player.receiveCard(new Card(Number.ACE, Pattern.HEART));

        // then
        assertThat(player.getHand().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("갖고 있는 카드의 총점 계산")
    void calculateTotalScore() {
        // given
        Player player = new Player(new Participant(Hand.generateEmptyCards()), "doggy");

        // when
        player.receiveCard(new Card(Number.Q, Pattern.CLUB));
        player.receiveCard(new Card(Number.K, Pattern.CLUB));

        // then
        assertThat(player.calculateTotalScore().getValue()).isEqualTo(20);
    }

    @Test
    @DisplayName("갖고 있는 카드가 버스트인지 확인")
    void isBust() {
        // given
        Player player = new Player(new Participant(Hand.generateEmptyCards()), "doggy");

        // when
        player.receiveCard(new Card(Number.Q, Pattern.CLUB));
        player.receiveCard(new Card(Number.K, Pattern.CLUB));
        player.receiveCard(new Card(Number.K, Pattern.HEART));

        // then
        assertThat(player.isBust()).isTrue();
    }
}
