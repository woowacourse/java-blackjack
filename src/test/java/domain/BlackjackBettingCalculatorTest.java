package domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackBettingCalculatorTest {

    private final List<String> userNames = List.of("블랙잭 플레이어", "승리 플레이어", "무승부 플레이어", "패배 플레이어", "버스트 플레이어");
    private final BigDecimal bettingAmount = new BigDecimal(10_000);

    @Test
    @DisplayName("(딜러 - 블랙잭) 블랙잭 플레이어는 수익 0이 되고, 나머진 배팅 금액을 잃는다.")
    void dealer_blackjackCards_player_betting_result() {
        List<Card> dealerCards = createCards("블랙잭");
        PlayerBets playerBets = new PlayerBets();

        calculateBettingResultWith(dealerCards, playerBets);
        List<BigDecimal> bettingAmounts = playerBets.getPlayersBets().stream()
                .map(PlayerBet::amount)
                .toList();

        Assertions.assertThat(bettingAmounts.stream()
                        .map(amount -> amount.stripTrailingZeros().toPlainString())
                        .toList())
                .containsExactly("0", "-10000", "-10000", "-10000", "-10000");
    }

    @Test
    @DisplayName("(딜러 - 버스트) 버스트 플레이어만 배팅 금액을 잃는다")
    void dealer_bust_player_betting_result() {
        List<Card> dealerCards = createCards("버스트");
        PlayerBets playerBets = new PlayerBets();

        calculateBettingResultWith(dealerCards, playerBets);
        List<BigDecimal> bettingAmounts = playerBets.getPlayersBets().stream()
                .map(PlayerBet::amount)
                .toList();

        Assertions.assertThat(bettingAmounts.stream()
                .map(amount -> amount.stripTrailingZeros().toPlainString())
                .toList())
                .containsExactly("15000", "10000", "10000", "10000", "-10000");
    }

    @Test
    @DisplayName("(딜러 - 20) 무승부 플레이어 배팅 결과는 0이 된다")
    void player_dealer_compare_betting_result() {
        List<Card> dealerCards = createCards("무승부");
        PlayerBets playerBets = new PlayerBets();

        calculateBettingResultWith(dealerCards, playerBets);
        List<BigDecimal> bettingAmounts = playerBets.getPlayersBets().stream()
                .map(PlayerBet::amount)
                .toList();

        Assertions.assertThat(bettingAmounts.stream()
                        .map(amount -> amount.stripTrailingZeros().toPlainString())
                        .toList())
                .containsExactly("15000", "10000", "0", "-10000", "-10000");
    }

    private void calculateBettingResultWith(List<Card> dealerCards, PlayerBets playersBet) {
        Dealer dealer = Dealer.of(dealerCards);
        Players players = Players.of(userNames);
        List<List<Card>> playersCards = getPlayerCards();

        addInitialPlayersCards(playersCards, players);

        addPlayersBet(players, playersBet);

        BlackjackBettingCalculator.calculate(dealer, playersBet);
    }

    private List<List<Card>> getPlayerCards() {
        return new ArrayList<>(List.of(
                createCards("블랙잭"),
                createCards("승리"),
                createCards("무승부"),
                createCards("패배"),
                createCards("버스트")
        ));
    }


    private List<Card> createCards(String input) {
        if (input.equals("블랙잭")) {
            return List.of(Card.of(Rank.ACE, Suit.SPADE), Card.of(Rank.J, Suit.HEART)); // 블랙잭
        }
        if (input.equals("승리")) {
            return List.of(Card.of(Rank.J, Suit.SPADE), Card.of(Rank.Q, Suit.HEART),
                    Card.of(Rank.ACE, Suit.DIAMOND)); // 21
        }
        if (input.equals("무승부")) {
            return List.of(Card.of(Rank.TEN, Suit.DIAMOND), Card.of(Rank.TEN, Suit.CLOVER)); // 20
        }
        if (input.equals("패배")) {
            return List.of(Card.of(Rank.TEN, Suit.HEART), Card.of(Rank.SEVEN, Suit.CLOVER)); // 17
        }
        return List.of(Card.of(Rank.J, Suit.SPADE), Card.of(Rank.Q, Suit.CLOVER), Card.of(Rank.K, Suit.HEART)); // 버스트
    }

    private static void addInitialPlayersCards(List<List<Card>> playersCards, Players players) {
        for (int i = 0; i < playersCards.size(); i++) {
            players.getPlayers().get(i).addInitialCards(playersCards.get(i));
        }
    }

    private void addPlayersBet(Players players, PlayerBets playersBet) {
        for (Player player : players.getPlayers()) {
            playersBet.add(PlayerBet.of(player, bettingAmount));
        }
    }
}