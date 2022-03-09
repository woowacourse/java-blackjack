package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GamerTest {

    @Test
    @DisplayName("이름이 공백인 경우 예외를 발생시킨다.")
    void createGamerExceptionNameEmpty() {
        assertThatThrownBy(() -> new Gamer(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] Gamer의 이름은 공백일 수 없습니다.");
    }

    @Test
    @DisplayName("이름이 딜러인경우 예외를 발생시킨다.")
    void createGamerExceptionNameDealer() {
        assertThatThrownBy(() -> new Gamer("딜러"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] Gamer의 이름은 딜러일 수 없습니다.");
    }

    @Test
    @DisplayName("카드를 받아 저장하고 전체 카드를 공개한다.")
    void getTwoCardsAtFirst() {
        Gamer gamer = initGamer();
        assertThat(gamer.showCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("카드 2장을 오픈한다.")
    void openTwoCards() {
        Gamer gamer = initGamer();

        List<Card> cards = gamer.openCards();
        assertAll(
                () -> assertThat(cards).contains(new Card(Suit.CLOVER, Denomination.JACK)
                        , new Card(Suit.DIAMOND, Denomination.ACE)),
                () -> assertThat(cards.size()).isEqualTo(2)
        );
    }

    @Test
    @DisplayName("Gamer가 보유하는 카드의 점수를 계산한다.")
    void calculateGamerPoint() {
        Gamer gamer = initGamer();
        assertThat(gamer.calculateResult()).isEqualTo(21);
    }

    @Test
    @DisplayName("21이하 일 때 카드를 받을 수 있다.")
    void checkReceivableConditionTrue() {
        Gamer gamer = initGamer();
        assertTrue(gamer.isReceivable());
    }

    @Test
    @DisplayName("21이상 일 때 카드를 받을 수 없다.")
    void checkReceivableConditionFalse() {
        Gamer gamer = initGamer();
        gamer.receiveCard(new Card(Suit.DIAMOND, Denomination.JACK));
        gamer.receiveCard(new Card(Suit.HEART, Denomination.JACK));

        assertFalse(gamer.isReceivable());
    }

    private Gamer initGamer() {
        Gamer gamer = new Gamer("judy");

        gamer.receiveCard(new Card(Suit.CLOVER, Denomination.JACK));
        gamer.receiveCard(new Card(Suit.DIAMOND, Denomination.ACE));
        return gamer;
    }
}
