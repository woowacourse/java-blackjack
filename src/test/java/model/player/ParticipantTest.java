package model.player;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Stream;
import model.BettingMoney;
import model.card.Card;
import model.card.CardDeck;
import model.card.CardNumber;
import model.card.CardShape;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ParticipantTest {

    @DisplayName("참가자의 수가 1명 미만이면 예외가 발생한다.")
    @Test
    void validateUnderOneParticipant() {
        assertThatThrownBy(() -> new Participants(List.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("카드의 합이 21이하일 때는 거짓을 반환한다.")
    @Test
    void noticeFalse() {
        Participant notBustParticipant = createNotBustParticipant();
        assertFalse(notBustParticipant.isBust());
    }

    @DisplayName("카드의 합이 21초과일 때는 참을 반환한다.")
    @Test
    void noticeTrue() {
        Participant bustParticipant = createBustParticipant();
        assertTrue(bustParticipant.isBust());
    }

    @DisplayName("둘 다 21을 넘지 않았을 때, 합이 같으면 참가자의 수익은 베팅금액의 0배다.")
    @Test
    void findOutcomeDraw() {
        Participant sameScoreParticipant = new Participant(new Name("배키"),
                List.of(new Card(CardShape.SPACE, CardNumber.NINE),
                        new Card(CardShape.SPACE, CardNumber.FIVE)),
                new BettingMoney(100));
        Dealer sameScoreDealer = new Dealer(new CardDeck(CardDeck.createCards()), () ->
                List.of(new Card(CardShape.CLOVER, CardNumber.NINE),
                        new Card(CardShape.HEART, CardNumber.FIVE)));

        Double playerProfit = sameScoreParticipant.calculateProfit(sameScoreDealer);

        Assertions.assertThat(playerProfit).isEqualTo(0);
    }

    @DisplayName("참가자와 딜러 모두 카드의 합이 21을 넘으면 참가자의 수익은 베팅금액의 1배다.")
    @Test
    void loseWhenBothOverThreshold() {
        Participant bustParticipant = createBustParticipant();
        Dealer bustDealer = createBustDealer();

        Double playerProfit = bustParticipant.calculateProfit(bustDealer);

        Assertions.assertThat(playerProfit).isEqualTo(100);
    }

    @DisplayName("참가자, 딜러 모두 21을 넘지 않았을 때 21과의 차이가 먼 참가쟈의 수익은 베팅금액의 -1배다.")
    @Test
    void loseWhenParticipantFarFromThresholdThanDealer() {
        Participant participant = new Participant(new Name("배키"),
                List.of(new Card(CardShape.SPACE, CardNumber.NINE),
                        new Card(CardShape.SPACE, CardNumber.FIVE)),
                new BettingMoney(100));

        Dealer dealer = new Dealer(new CardDeck(CardDeck.createCards()), () ->
                List.of(new Card(CardShape.SPACE, CardNumber.KING),
                        new Card(CardShape.SPACE, CardNumber.JACK)));

        Double playerProfit = participant.calculateProfit(dealer);

        Assertions.assertThat(playerProfit).isEqualTo(-100);
    }

    @DisplayName("둘 다 21을 넘지 않은 경우, 21과의 차이가 가까운 참가자의 수익은 베팅금액의 1배다.")
    @Test
    void findOutcomeWin() {
        Participant participant = new Participant(new Name("배키"),
                List.of(new Card(CardShape.SPACE, CardNumber.QUEEN),
                        new Card(CardShape.SPACE, CardNumber.KING)),
                new BettingMoney(100));
        Dealer dealer = new Dealer(new CardDeck(CardDeck.createCards()), () ->
                List.of(new Card(CardShape.SPACE, CardNumber.EIGHT),
                        new Card(CardShape.CLOVER, CardNumber.NINE)));

        Double playerProfit = participant.calculateProfit(dealer);

        Assertions.assertThat(playerProfit).isEqualTo(100);
    }

    static Stream<Arguments> createParticipant() {
        Participant bustParticipant = createBustParticipant();
        Participant notBustParticipant = createNotBustParticipant();
        return Stream.of(Arguments.of(bustParticipant, notBustParticipant));
    }

    @DisplayName("딜러가 버스트면 참가자의 수익률은 베팅금액의 1배이다.")
    @ParameterizedTest
    @MethodSource("createParticipant")
    void calculateWinProfit(Participant participant) {
        Dealer bustDealer = createBustDealer();

        double playerProfit = participant.calculateProfit(bustDealer);

        Assertions.assertThat(playerProfit).isEqualTo(100);
    }

    @DisplayName("참가자가 버스트면 참가자의 수익률은 배팅금액의 -1배이다.")
    @Test
    void calculateLoseProfit() {
        Participant bustParticipant = createBustParticipant();
        Dealer notBustDealer = createNotBustDealer();

        double playerProfit = bustParticipant.calculateProfit(notBustDealer);

        Assertions.assertThat(playerProfit).isEqualTo(-100);
    }

    @DisplayName("참가자가 블랙잭이고 딜러가 블랙잭이면 수익률은 베팅금액의 1배이다.")
    @Test
    void calculateAllBlackjackProfit() {
        Participant blackjackParticipant = createBlackjackParticipant();
        Dealer blackjackDealer = createBlackjackDealer();

        double playerProfit = blackjackParticipant.calculateProfit(blackjackDealer);

        Assertions.assertThat(playerProfit).isEqualTo(100);
    }

    @DisplayName("참가자만 블랙잭이면 수익률은 베팅금액의 1.5배이다.")
    @Test
    void calculateOnlyParticipantBlackjackProfit() {
        Participant blackjackParticipant = createBlackjackParticipant();
        Dealer notBustDealer = createNotBustDealer();

        double playerProfit = blackjackParticipant.calculateProfit(notBustDealer);

        Assertions.assertThat(playerProfit).isEqualTo(150);
    }

    private static Dealer createBustDealer() {
        Dealer dealer = new Dealer(new CardDeck(CardDeck.createCards()), () ->
                List.of(new Card(CardShape.SPACE, CardNumber.EIGHT),
                        new Card(CardShape.CLOVER, CardNumber.NINE)));
        dealer.addCards(new Card(CardShape.CLOVER, CardNumber.TEN));
        return dealer;
    }

    private static Dealer createNotBustDealer() {
        return new Dealer(new CardDeck(CardDeck.createCards()), () ->
                List.of(new Card(CardShape.SPACE, CardNumber.EIGHT),
                        new Card(CardShape.CLOVER, CardNumber.NINE)));
    }

    private static Participant createBustParticipant() {
        Participant bustParticipant = new Participant(new Name("배키"),
                List.of(new Card(CardShape.HEART, CardNumber.NINE),
                        new Card(CardShape.HEART, CardNumber.FIVE)),
                new BettingMoney(100));
        bustParticipant.addCards(new Card(CardShape.HEART, CardNumber.EIGHT));
        return bustParticipant;
    }

    private static Participant createNotBustParticipant() {
        return new Participant(new Name("배키"),
                List.of(new Card(CardShape.DIAMOND, CardNumber.NINE),
                        new Card(CardShape.DIAMOND, CardNumber.FIVE)),
                new BettingMoney(100));
    }

    private static Participant createBlackjackParticipant() {
        return new Participant(new Name("켬미"),
                List.of(new Card(CardShape.SPACE, CardNumber.ACE),
                        new Card(CardShape.SPACE, CardNumber.JACK)),
                new BettingMoney(100));
    }

    private static Dealer createBlackjackDealer() {
        return new Dealer(new CardDeck(CardDeck.createCards()), () ->
                List.of(new Card(CardShape.CLOVER, CardNumber.ACE),
                        new Card(CardShape.CLOVER, CardNumber.TEN)));
    }
}
