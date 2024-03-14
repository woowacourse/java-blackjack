package model.player;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import model.BettingMoney;
import model.card.Card;
import model.card.CardDeck;
import model.card.CardNumber;
import model.card.CardShape;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    @DisplayName("참가자의 수가 1명 미만이면 예외가 발생한다.")
    @Test
    void validateUnderOneParticipant() {
        assertThatThrownBy(() -> new Participants(List.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("카드의 합이 21이하일 때는 참을 반환한다.")
    @Test
    void noticeTrue() {
        Participant participant = new Participant("배키",
                List.of(new Card(CardShape.SPACE, CardNumber.NINE),
                        new Card(CardShape.SPACE, CardNumber.FIVE)),
                new BettingMoney(100));
        assertFalse(participant.isBust());
    }

    @DisplayName("카드의 합이 21초과일 때는 거짓을 반환한다.")
    @Test
    void noticeFalse() {
        Participant participant = new Participant("배키",
                List.of(new Card(CardShape.SPACE, CardNumber.NINE),
                        new Card(CardShape.SPACE, CardNumber.FIVE)),
                new BettingMoney(100));
        participant.addCard(new Card(CardShape.CLOVER, CardNumber.NINE));
        participant.addCard(new Card(CardShape.CLOVER, CardNumber.NINE));
        participant.addCard(new Card(CardShape.CLOVER, CardNumber.NINE));
        assertTrue(participant.isBust());
    }

    @DisplayName("둘 다 21을 넘지 않았을 때, 합이 같으면 참가자의 수익은 베팅금액의 0배다.")
    @Test
    void findOutcomeDraw() {
        Participant participant = new Participant("배키",
                List.of(new Card(CardShape.SPACE, CardNumber.NINE),
                        new Card(CardShape.SPACE, CardNumber.FIVE)),
                new BettingMoney(100));
        Dealer dealer = new Dealer(new CardDeck(CardDeck.createCards()), () ->
                List.of(new Card(CardShape.CLOVER, CardNumber.NINE),
                        new Card(CardShape.HEART, CardNumber.FIVE)));

        Double playerProfit = participant.calculateProfit(dealer);

        Assertions.assertThat(playerProfit).isEqualTo(0);
    }

    @DisplayName("참가자와 딜러 모두 카드의 합이 21을 넘으면 참가자의 수익은 베팅금액의 1배다.")
    @Test
    void loseWhenBothOverThreshold() {
        Participant participant = new Participant("켬미",
                List.of(new Card(CardShape.SPACE, CardNumber.TEN),
                        new Card(CardShape.HEART, CardNumber.TEN)),
                new BettingMoney(100));
        participant.addCard(new Card(CardShape.SPACE, CardNumber.NINE));
        Dealer dealer = new Dealer(new CardDeck(CardDeck.createCards()), () ->
                List.of(new Card(CardShape.CLOVER, CardNumber.TEN),
                        new Card(CardShape.DIAMOND, CardNumber.TEN)));
        dealer.addCard(new Card(CardShape.SPACE, CardNumber.EIGHT));

        Double playerProfit = participant.calculateProfit(dealer);

        Assertions.assertThat(playerProfit).isEqualTo(100);
    }

    @DisplayName("참가자, 딜러 모두 21을 넘지 않았을 때 21과의 차이가 먼 참가쟈의 수익은 베팅금액의 -1배다.")
    @Test
    void loseWhenParticipantFarFromThresholdThanDealer() {
        Participant participant = new Participant("켬미",
                List.of(new Card(CardShape.SPACE, CardNumber.KING),
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
    void findOutcomeWin() { // todo 변수명에 버스트 되었는지, 안되었는지 표시하기
        Participant participant = new Participant("켬미",
                List.of(new Card(CardShape.SPACE, CardNumber.KING),
                        new Card(CardShape.SPACE, CardNumber.JACK)),
                new BettingMoney(100));
        Dealer dealer = new Dealer(new CardDeck(CardDeck.createCards()), () ->
                List.of(new Card(CardShape.SPACE, CardNumber.EIGHT),
                        new Card(CardShape.CLOVER, CardNumber.NINE)));

        Double playerProfit = participant.calculateProfit(dealer);

        Assertions.assertThat(playerProfit).isEqualTo(100);
    }

    @DisplayName("딜러가 버스트면 참가자의 수익률은 베팅금액의 1배이다.") //todo '참가자의 버스트 여부'와 상관없이 -> parameterize
    @Test
    void calculateWinProfit() {
        Participant participant = new Participant("켬미",
                List.of(new Card(CardShape.SPACE, CardNumber.KING),
                        new Card(CardShape.SPACE, CardNumber.JACK)),
                new BettingMoney(100));
        Dealer dealer = new Dealer(new CardDeck(CardDeck.createCards()), () ->
                List.of(new Card(CardShape.SPACE, CardNumber.EIGHT),
                        new Card(CardShape.CLOVER, CardNumber.NINE)));
        dealer.addCard(new Card(CardShape.CLOVER, CardNumber.TEN));

        double playerProfit = participant.calculateProfit(dealer);

        Assertions.assertThat(playerProfit).isEqualTo(100);
    }

    @DisplayName("참가자가 버스트면 참가자의 수익률은 배팅금액의 -1배이다.")
    @Test
    void calculateLoseProfit() {//TODO : bust된 참가자와 딜러, bust되지 않은 참가자와 딜러로 나누기(중복 제거)
        Participant participant = new Participant("켬미",
                List.of(new Card(CardShape.SPACE, CardNumber.KING),
                        new Card(CardShape.SPACE, CardNumber.JACK)),
                new BettingMoney(100));
        participant.addCard(new Card(CardShape.CLOVER, CardNumber.FIVE));
        Dealer dealer = new Dealer(new CardDeck(CardDeck.createCards()), () ->
                List.of(new Card(CardShape.SPACE, CardNumber.EIGHT),
                        new Card(CardShape.CLOVER, CardNumber.NINE)));

        double playerProfit = participant.calculateProfit(dealer);

        Assertions.assertThat(playerProfit).isEqualTo(-100);
    }

    @DisplayName("참가자가 블랙잭이고 딜러가 블랙잭이면 수익률은 베팅금액의 1배이다.")
    @Test
    void calculateAllBlackjackProfit() {
        Participant participant = new Participant("켬미",
                List.of(new Card(CardShape.SPACE, CardNumber.ACE),
                        new Card(CardShape.SPACE, CardNumber.JACK)),
                new BettingMoney(100));
        Dealer dealer = new Dealer(new CardDeck(CardDeck.createCards()), () ->
                List.of(new Card(CardShape.CLOVER, CardNumber.ACE),
                        new Card(CardShape.CLOVER, CardNumber.TEN)));

        double playerProfit = participant.calculateProfit(dealer);

        Assertions.assertThat(playerProfit).isEqualTo(100);
    }

    @DisplayName("참가자만 블랙잭이면 수익률은 베팅금액의 1.5배이다.")
    @Test
    void calculateOnlyParticipantBlackjackProfit() {
        Participant participant = new Participant("켬미",
                List.of(new Card(CardShape.SPACE, CardNumber.ACE),
                        new Card(CardShape.SPACE, CardNumber.JACK)),
                new BettingMoney(100));
        Dealer dealer = new Dealer(new CardDeck(CardDeck.createCards()), () ->
                List.of(new Card(CardShape.CLOVER, CardNumber.FIVE),
                        new Card(CardShape.CLOVER, CardNumber.TEN)));
        dealer.addCard(new Card(CardShape.HEART, CardNumber.SIX));

        double playerProfit = participant.calculateProfit(dealer);

        Assertions.assertThat(playerProfit).isEqualTo(150);
    }
}
