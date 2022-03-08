package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GamerTest {

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
                () -> assertThat(cards).contains(new Card(Suit.CLOVER, Denomination.FIVE)
                        , new Card(Suit.DIAMOND, Denomination.FIVE)),
                () -> assertThat(cards.size()).isEqualTo(2)
        );
    }

    @Test
    @DisplayName("Gamer가 보유하는 카드의 점수를 계산한다.")
    void calculateGamerPoint() {
        Gamer gamer = initGamer();
        assertThat(gamer.calculateResult()).isEqualTo(10);
    }

    private Gamer initGamer() {
        Gamer gamer = new Gamer("judy");
        gamer.receiveCard(new Card(Suit.CLOVER, Denomination.FIVE));
        gamer.receiveCard(new Card(Suit.DIAMOND, Denomination.FIVE));
        return gamer;
    }
}
