package domain;

import static org.assertj.core.api.Assertions.assertThat;

import static domain.GameResultStatus.PUSH;
import static domain.GameResultStatus.LOSE;
import static domain.GameResultStatus.WIN;
import static domain.CardNumber.ACE;
import static domain.CardNumber.FIVE;
import static domain.CardNumber.KING;
import static domain.CardNumber.TWO;
import static domain.CardShape.SPADE;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DealerTest {

    @DisplayName("딜러는 카드를 한 장 뽑을 수 있다.")
    @Test
    void drawCard() {
        Card card = new Card(SPADE, ACE);
        Dealer dealer = new Dealer(new Cards(List.of(card)));
        assertThat(dealer.drawCard()).isEqualTo(card);
    }

    @DisplayName("딜러는 카드를 참가자에게 줄 수 있다.")
    @Test
    void deal() {
        Card card = new Card(SPADE, ACE);
        Dealer dealer = new Dealer(new Cards(List.of(card)));
        Player player = new Player(new Name("Zeus"));

        dealer.deal(player);
        Cards cards = player.cards();

        assertThat(cards.draw()).isEqualTo(card);
    }

    @DisplayName("플레이어와 자신의 카드를 비교해 승패무를 정한다.")
    @MethodSource
    @ParameterizedTest
    void resultStatusOf(Cards dealerCards, Cards playerCards, GameResultStatus expected) {
        Dealer dealer = new Dealer(dealerCards);
        Player player = new Player(new Name("hotea"));
        receiveCards(dealer, dealerCards);
        receiveCards(player, playerCards);
        GameResultStatus status = dealer.resultStatusOf(player);
        assertThat(status).isEqualTo(expected);
    }

    private void receiveCards(Participant participant, Cards cards) {
        for (Card card : cards.toList()) {
            participant.receive(card);
        }
    }

    static Stream<Arguments> resultStatusOf() {
        return Stream.of(
                Arguments.of(cardOf22(), cardOf22(), PUSH),
                Arguments.of(cardOf22(), cardOf20(), WIN),
                Arguments.of(cardOf20(), cardOf22(), LOSE),
                Arguments.of(cardOf20(), cardOf15(), LOSE),
                Arguments.of(cardOf15(), cardOf20(), WIN),
                Arguments.of(cardOf20(), cardOf20(), PUSH)
        );
    }

    static Cards cardOf22() {
        return new Cards(List.of(new Card(SPADE, KING), new Card(SPADE, KING), new Card(SPADE, TWO)));
    }

    static Cards cardOf20() {
        return new Cards(List.of(new Card(SPADE, KING), new Card(SPADE, KING)));
    }

    static Cards cardOf15() {
        return new Cards(List.of(new Card(SPADE, KING), new Card(SPADE, FIVE)));
    }
}
