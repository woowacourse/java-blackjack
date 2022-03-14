package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import blackjack.domain.Answer;
import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GamerTest {

    @Test
    @DisplayName("이름이 공백인 경우 예외를 발생시킨다.")
    void createGamerExceptionNameEmpty() {
        assertThatThrownBy(() -> new Gamer("", new Bet(1000)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] Gamer의 이름은 공백일 수 없습니다.");
    }

    @Test
    @DisplayName("이름이 딜러인경우 예외를 발생시킨다.")
    void createGamerExceptionNameDealer() {
        assertThatThrownBy(() -> new Gamer("딜러", new Bet(1000)))
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
    @DisplayName("21미만 일 때 카드를 받을 수 있다.")
    void checkReceivableConditionTrue() {
        Gamer gamer = new Gamer("judy", new Bet(1000));
        gamer.receiveCard(new Card(Suit.DIAMOND, Denomination.JACK));
        gamer.receiveCard(new Card(Suit.HEART, Denomination.JACK));

        assertTrue(gamer.isSatisfyReceiveCondition());
    }

    @Test
    @DisplayName("21이상 일 때 카드를 받을 수 없다.")
    void checkReceivableConditionFalse() {
        Gamer gamer = initGamer();
        assertFalse(gamer.isSatisfyReceiveCondition());
    }

    @Test
    @DisplayName("gamer가 hit한다는 응답을 받는다")
    void checkGamerAnswerHit() {
        Gamer gamer = new Gamer("judy", new Bet(1000));
        assertTrue(gamer.isHit(Answer.YES));
    }

    private Gamer initGamer() {
        Gamer gamer = new Gamer("judy", new Bet(1000));

        gamer.receiveCard(new Card(Suit.CLOVER, Denomination.JACK));
        gamer.receiveCard(new Card(Suit.DIAMOND, Denomination.ACE));
        return gamer;
    }
}
