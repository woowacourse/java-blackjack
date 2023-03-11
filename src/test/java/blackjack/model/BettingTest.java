package blackjack.model;

import blackjack.model.card.*;
import blackjack.model.participant.Dealer;
import blackjack.model.participant.Name;
import blackjack.model.participant.Player;
import blackjack.model.participant.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class BettingTest {

    public static final Card CLUB_ACE = Card.of(CardSuit.CLUB, CardNumber.ACE);
    public static final Card CLUB_JACK = Card.of(CardSuit.CLUB, CardNumber.JACK);
    public static final Card DIA_EIGHT = Card.of(CardSuit.DIAMOND, CardNumber.EIGHT);
    public static final Card CLUB_SEVEN = Card.of(CardSuit.CLUB, CardNumber.SEVEN);
    public static final Card DIA_NINE = Card.of(CardSuit.DIAMOND, CardNumber.NINE);
    public static final Card HEART_EIGHT = Card.of(CardSuit.HEART, CardNumber.EIGHT);
    public static final Card DIA_JACK = Card.of(CardSuit.DIAMOND, CardNumber.JACK);
    public static final Card DIA_ACE = Card.of(CardSuit.DIAMOND, CardNumber.ACE);
    public static final Card DIA_KING = Card.of(CardSuit.DIAMOND, CardNumber.KING);
    public static final Card DIA_FIVE = Card.of(CardSuit.DIAMOND, CardNumber.FIVE);
    public static final Card CLUB_TWO = Card.of(CardSuit.CLUB, CardNumber.TWO);

    @Test
    @DisplayName("버스트 경우 배팅 계산 - 성공 : 카드를 추가로 뽑아 21을 초과하면 배팅 금액을 모두 잃게 된다.")
    void bust_case_betting_success() {
        //given
        Map<String, Integer> players = new HashMap<>();
        players.put("a", 1000);
        BlackjackGame game = new BlackjackGame(new Players(players), new Dealer());
        CardDeck cardDeck = new CardDeck(List.of(CLUB_SEVEN, HEART_EIGHT, DIA_NINE, DIA_NINE, CLUB_JACK));
        game.distributeCards(cardDeck);

        // when
        game.drawPlayerCard(cardDeck, game.getPlayerId("a"));
        Map<String, WinningResult> winningResults = game.participantProfits();
        WinningResult result = winningResults.get("a");

        //then
        assertThat(result.getBetting()).isEqualTo(-1000);
    }

    @Test
    @DisplayName("블랙잭 경우 배팅 계산 - 성공 : 블랙잭이 되면 베팅 금액의 1.5 배를 딜러에게 받는다.")
    void blackjack_case_betting_success() {
        //given
        Map<String, Integer> players = new HashMap<>();
        players.put("a", 1000);
        BlackjackGame game = new BlackjackGame(new Players(players), new Dealer());
        CardDeck cardDeck = new CardDeck(List.of(DIA_EIGHT, CLUB_JACK, CLUB_ACE, CLUB_JACK));
        game.distributeCards(cardDeck);

        // when
        Map<String, WinningResult> winningResults = game.participantProfits();
        WinningResult result = winningResults.get("a");
        WinningResult dealerResult = winningResults.get("딜러");

        //then
        List<Double> bettingResult = List.of(result.getBetting(), dealerResult.getBetting());
        assertThat(bettingResult).containsExactly(1500d, -1500d);
    }

    @Test
    @DisplayName("딜러와 플레이어가 모두 동시에 블랙잭인 경우 - 성공 : 플레이어는 베팅한 금액을 돌려받는다.")
    void all_blackjack_betting_case_success() {
        //given
        Map<String, Integer> players = new HashMap<>();
        players.put("a", 1000);
        BlackjackGame game = new BlackjackGame(new Players(players), new Dealer());
        CardDeck cardDeck = new CardDeck(List.of(DIA_ACE, DIA_JACK, CLUB_ACE, CLUB_JACK));
        game.distributeCards(cardDeck);

        // when
        Map<String, WinningResult> winningResult = game.participantProfits();
        WinningResult result = winningResult.get("a");

        //then
        assertThat(result.getBetting()).isEqualTo(0);
    }

    @Test
    @DisplayName("딜러가 버스트 인 경우 - 성공 : 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리해 베팅 금액을 받는다.")
    void dealer_bust_case_betting_success() {
        //given
        Player player1 = new Player(new Name("a"), new HandCard(List.of(CLUB_TWO, CLUB_JACK)), 1000);
        Player player2 = new Player(new Name("b"), new HandCard(List.of(CLUB_SEVEN, DIA_EIGHT, DIA_NINE)), 2000);
        Dealer dealer = new Dealer(new HandCard(List.of(HEART_EIGHT, DIA_KING, DIA_FIVE)));

        BlackjackGame game = new BlackjackGame(new Players(player1, player2), dealer);

        // when
        Map<String, WinningResult> winningResult = game.participantProfits();
        WinningResult result = winningResult.get("a");

        //then
        assertThat(result.getBetting()).isEqualTo(1000);
    }
}
