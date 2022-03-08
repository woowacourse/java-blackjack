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
        assertThatThrownBy(()-> new Gamer("딜러"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] Gamer의 이름은 딜러일 수 없습니다.");
    }

    @Test
    @DisplayName("카드를 받아 저장한다.")
    void getTwoCardsAtFirst() {
        Gamer gamer = new Gamer("judy");
        gamer.receiveCard(new Card(Suit.CLOVER, Denomination.FIVE));

        assertThat(gamer.showCards().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("카드 2장을 오픈한다.")
    void openTwoCards() {
        Gamer gamer = new Gamer("judy");

        Card cloverFive = new Card(Suit.CLOVER, Denomination.FIVE);
        Card diamondFive = new Card(Suit.DIAMOND, Denomination.FIVE);

        gamer.receiveCard(cloverFive);
        gamer.receiveCard(diamondFive);

        List<Card> cards = gamer.openCards();
        assertAll(
                () -> assertThat(cards).contains(cloverFive, diamondFive),
                () -> assertThat(cards.size()).isEqualTo(2)
        );
    }
}
