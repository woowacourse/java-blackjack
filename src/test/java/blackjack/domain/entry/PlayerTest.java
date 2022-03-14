package blackjack.domain.entry;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import blackjack.domain.PlayerOutcome;
import blackjack.domain.card.Card;
import blackjack.domain.card.HoldCards;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    @DisplayName("두장의 카드를 지급받아 카드의 합을 계산한다.")
    void getTwoCards() {
        Player player = new Player("jason", HoldCards
            .initTwoCards(Card.valueOf(Suit.SPADE, CardNumber.KING), Card.valueOf(Suit.SPADE, CardNumber.ACE)));

        assertThat(player.countCards()).isEqualTo(21);
    }

    @Test
    @DisplayName("기본 카드가 주어진 후 한장의 카드를 더 추가한다.")
    void putCard() {
        Player player = new Player("jason", HoldCards
            .initTwoCards(Card.valueOf(Suit.SPADE, CardNumber.NINE), Card.valueOf(Suit.SPADE, CardNumber.ACE)));
        player.addCard(Card.valueOf(Suit.HEART, CardNumber.ACE));

        assertThat(player.countCards()).isEqualTo(21);
    }

    @Test
    @DisplayName("플레이어의 합이 높을 경우 승리를 반환한다.")
    void playerIsLoseByOver21() {
        Player player = new Player("jason", HoldCards
            .initTwoCards(Card.valueOf(Suit.SPADE, CardNumber.KING), Card.valueOf(Suit.SPADE, CardNumber.ACE)));

        assertThat(player.match(new Dealer(HoldCards
            .initTwoCards(Card.valueOf(Suit.SPADE, CardNumber.EIGHT), Card.valueOf(Suit.SPADE, CardNumber.ACE)))))
            .isEqualTo(PlayerOutcome.WIN);
    }

    @Test
    @DisplayName("플레이어의 합이 낮을 경우 패배를 반환한다.")
    void playerIsWinByDealerOver21() {
        Player player = new Player("jason", HoldCards
            .initTwoCards(Card.valueOf(Suit.SPADE, CardNumber.NINE), Card.valueOf(Suit.SPADE, CardNumber.ACE)));

        assertThat(player.match(new Dealer(HoldCards
            .initTwoCards(Card.valueOf(Suit.SPADE, CardNumber.KING), Card.valueOf(Suit.SPADE, CardNumber.ACE)))))
            .isEqualTo(PlayerOutcome.LOSE);
    }

    @Test
    @DisplayName("플레이어의 합과 같을 경우 무승부를 반환한다.")
    void playerIsDrawByDealerAndPlayerOver21() {
        Player player = new Player("jason", HoldCards
            .initTwoCards(Card.valueOf(Suit.SPADE, CardNumber.KING), Card.valueOf(Suit.SPADE, CardNumber.ACE)));

        assertThat(player.match(new Dealer(HoldCards
            .initTwoCards(Card.valueOf(Suit.HEART, CardNumber.KING), Card.valueOf(Suit.HEART, CardNumber.ACE)))))
            .isEqualTo(PlayerOutcome.DRAW);
    }

    @Test
    @DisplayName("플레이어의 이름이 딜러인 경우 예외를 발생한다.")
    void throwExceptionContainsDealerName() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new Player("딜러", HoldCards
                .initTwoCards(Card.valueOf(Suit.SPADE, CardNumber.KING), Card.valueOf(Suit.SPADE, CardNumber.ACE))))
            .withMessage("플레이어의 이름에는 딜러가 포함될 수 없습니다.");
    }
}
