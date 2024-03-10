package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import static domain.BlackjackResultStatus.LOSE;
import static domain.BlackjackResultStatus.PUSH;
import static domain.BlackjackResultStatus.WIN;
import static domain.card.CardGenerator.cardOf;
import static domain.card.CardRank.ACE;
import static domain.card.CardRank.FIVE;
import static domain.card.CardRank.KING;
import static domain.card.CardRank.SIX;
import static domain.card.CardRank.TWO;
import static domain.card.CardSuit.SPADE;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import domain.BlackjackResultStatus;
import domain.card.Card;
import domain.card.Cards;
import domain.participant.dealer.Dealer;
import domain.participant.player.Player;

class DealerTest {

    @DisplayName("딜러는 카드를 참가자에게 줄 수 있다.")
    @Test
    void deal() {
        Card card = new Card(SPADE, ACE);
        Dealer dealer = Dealer.from(Cards.from(List.of(card)));
        Player player = Player.from(new Name("Zeus"));

        dealer.deal(player);
        Cards cards = player.hand();

        assertThat(cards.draw()).isEqualTo(card);
    }

    static Stream<Arguments> resultStatusOf() {
        return Stream.of(
                Arguments.of(cardsOf22(), cardsOf22(), PUSH),
                Arguments.of(cardsOf22(), cardsOf20(), LOSE),
                Arguments.of(cardsOf20(), cardsOf22(), WIN),
                Arguments.of(cardsOf20(), cardsOf15(), WIN),
                Arguments.of(cardsOf15(), cardsOf20(), LOSE),
                Arguments.of(cardsOf20(), cardsOf20(), PUSH)
        );
    }

    static Cards cardsOf22() {
        return Cards.from(List.of(cardOf(KING), cardOf(KING), cardOf(TWO)));
    }

    static Cards cardsOf20() {
        return Cards.from(List.of(cardOf(KING), cardOf(KING)));
    }

    static Cards cardsOf15() {
        return Cards.from(List.of(cardOf(KING), cardOf(FIVE)));
    }

    @DisplayName("플레이어와 자신의 카드를 비교해 승패무를 정한다.")
    @MethodSource
    @ParameterizedTest
    void resultStatusOf(Cards dealerCards, Cards playerCards, BlackjackResultStatus expected) {
        Dealer dealer = Dealer.from(dealerCards);
        Player player = Player.from(new Name("hotea"));
        receiveCards(dealer, dealerCards);
        receiveCards(player, playerCards);
        BlackjackResultStatus status = dealer.resultStatusAgainst(player);
        assertThat(status).isEqualTo(expected);
    }

    private void receiveCards(Participant participant, Cards cards) {
        cards.stream().forEach(participant::receive);
    }

    static Stream<Arguments> isBust() {
        return Stream.of(
                Arguments.of(Cards.from(List.of(cardOf(ACE), cardOf(FIVE), cardOf(SIX))), 22, true),
                Arguments.of(Cards.from(List.of(cardOf(FIVE), cardOf(SIX), cardOf(ACE))), 12, false)
        );
    }

    @DisplayName("딜러의 카드 합계를 계산해 버스트를 판단한다.")
    @MethodSource
    @ParameterizedTest
    void isBust(Cards cards, int expectedTotal, boolean expectedBust) {
        Dealer dealer = Dealer.from(cards);
        dealer.deal(dealer, 3);
        assertAll(
                () -> assertThat(dealer.score()).isEqualTo(expectedTotal),
                () -> assertThat(dealer.isBust()).isEqualTo(expectedBust)
        );
    }
}
