package blackjack.domain.gameresult;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSuit;
import blackjack.domain.card.Hand;
import blackjack.domain.money.BettingMoney;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.ParticipantName;
import blackjack.domain.participant.Player;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultReaderTest {

    @Test
    @DisplayName("딜러와 플레이어의 값이 21보다 작고 딜러가 플레이어보다 점수가 높으면 WIN을 반환한다.")
    void checkDealerWin1() {
        //given
        Dealer dealer = new Dealer(new ParticipantName("딜러"), new Hand());
        Player player = generatePlayer();
        List<Card> dealerCards = List.of(
            new Card(CardNumber.TEN, CardSuit.SPADE),
            new Card(CardNumber.NINE, CardSuit.SPADE));
        List<Card> playerCards = List.of(
            new Card(CardNumber.NINE, CardSuit.CLUB),
            new Card(CardNumber.TWO, CardSuit.DIAMOND));

        for (Card card : dealerCards) {
            dealer.hit(card);
        }
        for (Card card : playerCards) {
            player.hit(card);
        }
        ResultReader resultReader = new ResultReader();

        //when
        WinningResult dealerResult = resultReader.calulateDealerResult(dealer, player);
        WinningResult playerResult = resultReader.calulatePlayerResult(dealer, player);
        //then
        assertThat(dealerResult).isEqualTo(WinningResult.WIN);
        assertThat(playerResult).isEqualTo(WinningResult.LOSE);
    }

    @Test
    @DisplayName("딜러의 값이 21보다 작고 플레이어의 값이 21보다 높으면 WIN을 반환한다.")
    void checkDealerWin2() {
        //given
        Dealer dealer = new Dealer(new ParticipantName("딜러"), new Hand());
        Player player = generatePlayer();
        List<Card> dealerCards = List.of(
            new Card(CardNumber.TEN, CardSuit.SPADE),
            new Card(CardNumber.NINE, CardSuit.SPADE));
        List<Card> playerCards = List.of(
            new Card(CardNumber.NINE, CardSuit.CLUB),
            new Card(CardNumber.THREE, CardSuit.DIAMOND),
            new Card(CardNumber.QUEEN, CardSuit.HEART));

        for (Card card : dealerCards) {
            dealer.hit(card);
        }
        for (Card card : playerCards) {
            player.hit(card);
        }
        ResultReader resultReader = new ResultReader();

        //when
        WinningResult dealerResult = resultReader.calulateDealerResult(dealer, player);
        WinningResult playerResult = resultReader.calulatePlayerResult(dealer, player);

        //then
        assertThat(dealerResult).isEqualTo(WinningResult.WIN);
        assertThat(playerResult).isEqualTo(WinningResult.LOSE);
    }

    @Test
    @DisplayName("딜러가 블랙잭이고 플레이어가 블랙잭이 아닌 21점일 때 WIN을 반환한다.")
    void checkDealerWin3() {
        //given
        Dealer dealer = new Dealer(new ParticipantName("딜러"), new Hand());
        Player player = generatePlayer();
        List<Card> dealerCards = List.of(
            new Card(CardNumber.TEN, CardSuit.SPADE),
            new Card(CardNumber.ACE, CardSuit.SPADE));
        List<Card> playerCards = List.of(
            new Card(CardNumber.NINE, CardSuit.CLUB),
            new Card(CardNumber.TWO, CardSuit.DIAMOND),
            new Card(CardNumber.QUEEN, CardSuit.HEART));

        for (Card card : dealerCards) {
            dealer.hit(card);
        }
        for (Card card : playerCards) {
            player.hit(card);
        }
        ResultReader resultReader = new ResultReader();

        //when
        WinningResult dealerResult = resultReader.calulateDealerResult(dealer, player);
        WinningResult playerResult = resultReader.calulatePlayerResult(dealer, player);

        //then
        assertThat(dealerResult).isEqualTo(WinningResult.WIN);
        assertThat(playerResult).isEqualTo(WinningResult.LOSE);
    }

    @Test
    @DisplayName("딜러와 플레이어 모드 21보다 작거나 같은 때 점수가 같으면 PUSH를 반환한다")
    void checkDealerDraw1() {
        //given
        Dealer dealer = new Dealer(new ParticipantName("딜러"), new Hand());
        Player player = generatePlayer();
        List<Card> dealerCards = List.of(
            new Card(CardNumber.TEN, CardSuit.SPADE),
            new Card(CardNumber.EIGHT, CardSuit.SPADE));
        List<Card> playerCards = List.of(
            new Card(CardNumber.NINE, CardSuit.CLUB),
            new Card(CardNumber.THREE, CardSuit.DIAMOND),
            new Card(CardNumber.SIX, CardSuit.HEART));

        for (Card card : dealerCards) {
            dealer.hit(card);
        }
        for (Card card : playerCards) {
            player.hit(card);
        }
        ResultReader resultReader = new ResultReader();

        //when
        WinningResult dealerResult = resultReader.calulateDealerResult(dealer, player);
        WinningResult playerResult = resultReader.calulatePlayerResult(dealer, player);

        //then
        assertThat(dealerResult).isEqualTo(WinningResult.PUSH);
        assertThat(playerResult).isEqualTo(WinningResult.PUSH);
    }

    @Test
    @DisplayName("딜러와 플레이어가 모두 21점을 넘으면 PUSH를 반환한다.")
    void checkDealerDraw2() {
        //given
        Dealer dealer = new Dealer(new ParticipantName("딜러"), new Hand());
        Player player = generatePlayer();
        List<Card> dealerCards = List.of(
            new Card(CardNumber.TEN, CardSuit.SPADE),
            new Card(CardNumber.NINE, CardSuit.SPADE),
            new Card(CardNumber.NINE, CardSuit.CLUB));
        List<Card> playerCards = List.of(
            new Card(CardNumber.NINE, CardSuit.CLUB),
            new Card(CardNumber.THREE, CardSuit.DIAMOND),
            new Card(CardNumber.QUEEN, CardSuit.HEART));

        for (Card card : dealerCards) {
            dealer.hit(card);
        }
        for (Card card : playerCards) {
            player.hit(card);
        }
        ResultReader resultReader = new ResultReader();

        //when
        WinningResult dealerResult = resultReader.calulateDealerResult(dealer, player);
        WinningResult playerResult = resultReader.calulatePlayerResult(dealer, player);

        //then
        assertThat(dealerResult).isEqualTo(WinningResult.PUSH);
        assertThat(playerResult).isEqualTo(WinningResult.PUSH);
    }

    @Test
    @DisplayName("딜러와 플레이어가 모두 블랙잭이면 PUSH를 반환한다.")
    void checkDealerDraw3() {
        //given
        Dealer dealer = new Dealer(new ParticipantName("딜러"), new Hand());
        Player player = generatePlayer();
        List<Card> dealerCards = List.of(
            new Card(CardNumber.TEN, CardSuit.SPADE),
            new Card(CardNumber.ACE, CardSuit.SPADE));
        List<Card> playerCards = List.of(
            new Card(CardNumber.ACE, CardSuit.CLUB),
            new Card(CardNumber.QUEEN, CardSuit.HEART));

        for (Card card : dealerCards) {
            dealer.hit(card);
        }
        for (Card card : playerCards) {
            player.hit(card);
        }
        ResultReader resultReader = new ResultReader();

        //when
        WinningResult dealerResult = resultReader.calulateDealerResult(dealer, player);
        WinningResult playerResult = resultReader.calulatePlayerResult(dealer, player);

        //then
        assertThat(dealerResult).isEqualTo(WinningResult.PUSH);
        assertThat(playerResult).isEqualTo(WinningResult.PUSH);
    }

    @Test
    @DisplayName("딜러의 값이 21보다 작고 플레이어보다 값이 작으면 LOSE를 반환한다.")
    void checkDealerLose1() {
        //given
        Dealer dealer = new Dealer(new ParticipantName("딜러"), new Hand());
        Player player = generatePlayer();
        List<Card> dealerCards = List.of(
            new Card(CardNumber.TEN, CardSuit.SPADE),
            new Card(CardNumber.NINE, CardSuit.SPADE));
        List<Card> playerCards = List.of(
            new Card(CardNumber.TEN, CardSuit.CLUB),
            new Card(CardNumber.QUEEN, CardSuit.HEART));

        for (Card card : dealerCards) {
            dealer.hit(card);
        }
        for (Card card : playerCards) {
            player.hit(card);
        }
        ResultReader resultReader = new ResultReader();

        //when
        WinningResult dealerResult = resultReader.calulateDealerResult(dealer, player);
        WinningResult playerResult = resultReader.calulatePlayerResult(dealer, player);

        //then
        assertThat(dealerResult).isEqualTo(WinningResult.LOSE);
        assertThat(playerResult).isEqualTo(WinningResult.WIN);
    }

    @Test
    @DisplayName("딜러의 값이 21보다 크고 플레이어의 값이 21이하이면 LOSE를 반환한다.")
    void checkDealerLose2() {
        //given
        Dealer dealer = new Dealer(new ParticipantName("딜러"), new Hand());
        Player player = generatePlayer();
        List<Card> dealerCards = List.of(
            new Card(CardNumber.TEN, CardSuit.SPADE),
            new Card(CardNumber.NINE, CardSuit.SPADE),
            new Card(CardNumber.EIGHT, CardSuit.HEART));
        List<Card> playerCards = List.of(
            new Card(CardNumber.TEN, CardSuit.CLUB),
            new Card(CardNumber.QUEEN, CardSuit.HEART));

        for (Card card : dealerCards) {
            dealer.hit(card);
        }
        for (Card card : playerCards) {
            player.hit(card);
        }
        ResultReader resultReader = new ResultReader();

        //when
        WinningResult dealerResult = resultReader.calulateDealerResult(dealer, player);
        WinningResult playerResult = resultReader.calulatePlayerResult(dealer, player);

        //then
        assertThat(dealerResult).isEqualTo(WinningResult.LOSE);
        assertThat(playerResult).isEqualTo(WinningResult.WIN);
    }

    @Test
    @DisplayName("딜러의 값이 블랙잭이 아닌 21이고 플레이어의 값이 블랙잭이면 LOSE를 반환한다.")
    void checkDealerLose3() {
        //given
        Dealer dealer = new Dealer(new ParticipantName("딜러"), new Hand());
        Player player = generatePlayer();
        List<Card> dealerCards = List.of(
            new Card(CardNumber.TEN, CardSuit.SPADE),
            new Card(CardNumber.NINE, CardSuit.SPADE),
            new Card(CardNumber.TWO, CardSuit.DIAMOND));
        List<Card> playerCards = List.of(
            new Card(CardNumber.TEN, CardSuit.CLUB),
            new Card(CardNumber.ACE, CardSuit.HEART));

        for (Card card : dealerCards) {
            dealer.hit(card);
        }
        for (Card card : playerCards) {
            player.hit(card);
        }
        ResultReader resultReader = new ResultReader();

        //when
        WinningResult dealerRsult = resultReader.calulateDealerResult(dealer, player);
        WinningResult playerResult = resultReader.calulatePlayerResult(dealer, player);

        //then
        assertThat(dealerRsult).isEqualTo(WinningResult.LOSE);
        assertThat(playerResult).isEqualTo(WinningResult.WIN);
    }

    private Player generatePlayer() {
        return new Player(new ParticipantName("아코"), new Hand(), new BettingMoney("1000"));
    }
}