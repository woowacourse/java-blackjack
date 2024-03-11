package domain;

import domain.constant.CardNumber;
import domain.constant.CardType;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    @DisplayName("게이머는 자신의 패를 반환할 수 있다.")
    void getHand() {
        Player player = new Player(new Name("test"));
        List<Card> cards = player.getCards();
        Assertions.assertThat(cards).isNotNull();
    }

    @Test
    @DisplayName("게이머는 카드를 한 장 가져갈 수 있다.")
    void takeCard() {
        Card card = new Card(CardType.SPADE, CardNumber.ACE);
        Player player = new Player(new Name("test"), List.of(card));
        Assertions.assertThat(player.getCards()).isEqualTo(List.of(card));
    }

    @Test
    @DisplayName("게이머의 이름이 일치하는지 확인 할 수 있다.")
    void createName() {
        Player player = new Player(new Name("test"));
        Assertions.assertThat(player.isNameOf("test")).isTrue();
    }

    @Test
    @DisplayName("게이머의 점수 합계를 반환한다.")
    void getTotalScore() {
        Player player = new Player(new Name("test"),
                List.of(
                        new Card(CardType.SPADE, CardNumber.ACE),
                        new Card(CardType.SPADE, CardNumber.TEN)
                ));
        Assertions.assertThat(player.getTotalScore())
                .isEqualTo(21);
    }

    @Test
    @DisplayName("Bust이면 true를 반환한다.")
    void isBustTrue() {
        Player player = new Player(new Name("test"),
                List.of(
                        new Card(CardType.SPADE, CardNumber.TEN),
                        new Card(CardType.SPADE, CardNumber.TEN),
                        new Card(CardType.SPADE, CardNumber.TEN)
                ));
        Assertions.assertThat(player.isBust())
                .isTrue();
    }

    @Test
    @DisplayName("Bust가 아니면 false를 반환한다.")
    void isBustFalse() {
        Player player = new Player(new Name("test"), List.of(
                new Card(CardType.SPADE, CardNumber.TEN),
                new Card(CardType.SPADE, CardNumber.TEN)
        ));
        Assertions.assertThat(player.isBust())
                .isFalse();
    }
}
