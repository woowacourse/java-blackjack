package domain.result;

import domain.card.Card;
import domain.card.Cards;
import domain.participant.Dealer;
import domain.name.Name;
import domain.participant.Participant;
import domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import vo.BettingMoney;

import java.util.List;
import java.util.stream.Stream;

import static domain.card.CardNumber.*;
import static domain.card.CardShape.SPADE;
import static domain.result.GameResultStatus.*;
import static org.assertj.core.api.Assertions.assertThat;

class GameResultStatusTest {

    @DisplayName("참가자들의 카드를 비교해 승패무를 정한다.")
    @MethodSource
    @ParameterizedTest
    void resultStatusOf(Cards dealerCards, Cards playerCards, GameResultStatus expected) {
        Dealer dealer = new Dealer(dealerCards);
        Player player = new Player(new Name("hotea"), new BettingMoney(5000));
        receiveCards(dealer, dealerCards);
        receiveCards(player, playerCards);
        GameResultStatus status = GameResultStatus.comparedTo(player.score(), dealer.score());
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
