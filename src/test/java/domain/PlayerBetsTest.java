package domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerBetsTest {

    private final List<Card> blackjackCards = List.of(
            Card.of(Rank.ACE, Suit.SPADE), Card.of(Rank.J, Suit.HEART));
    private final List<Card> winCards = List.of(
            Card.of(Rank.J, Suit.SPADE), Card.of(Rank.Q, Suit.HEART), Card.of(Rank.ACE, Suit.DIAMOND));
    private final List<Card> drawCards = List.of(
            Card.of(Rank.TEN, Suit.DIAMOND), Card.of(Rank.TEN, Suit.CLOVER));
    private final List<Card> loseCards = List.of(
            Card.of(Rank.TEN, Suit.HEART), Card.of(Rank.SEVEN, Suit.CLOVER));
    private final List<Card> bustCards = List.of(
            Card.of(Rank.J, Suit.SPADE), Card.of(Rank.Q, Suit.CLOVER), Card.of(Rank.K, Suit.HEART));

    private final List<String> userNames = List.of("블랙잭 플레이어", "승리 플레이어", "무승부 플레이어", "패배 플레이어", "버스트 플레이어");
    private final BigDecimal bettingAmount = new BigDecimal(10_000);

    @Test
    @DisplayName("(딜러 - 블랙잭) 블랙잭 플레이어는 수익 0이 되고, 나머진 배팅 금액을 잃는다.")
    void dealer_blackjackCards_player_betting_result() {
        PlayerBets playerBets = new PlayerBets();
        Dealer dealer = Dealer.of(blackjackCards);
        initializePlayers(playerBets);

        playerBets.applyGameResult(dealer);
        List<BigDecimal> bettingAmounts = playerBets.bettingAmounts();

        Assertions.assertThat(bettingAmounts.stream()
                        .map(amount -> amount.stripTrailingZeros().toPlainString())
                        .toList())
                .containsExactly("0", "-10000", "-10000", "-10000", "-10000");
    }

    @Test
    @DisplayName("(딜러 - 버스트) 버스트 플레이어만 배팅 금액을 잃는다")
    void dealer_bust_player_betting_result() {
        PlayerBets playerBets = new PlayerBets();
        Dealer dealer = Dealer.of(bustCards);
        initializePlayers(playerBets);

        playerBets.applyGameResult(dealer);
        List<BigDecimal> bettingAmounts = playerBets.bettingAmounts();

        Assertions.assertThat(bettingAmounts.stream()
                        .map(amount -> amount.stripTrailingZeros().toPlainString())
                        .toList())
                .containsExactly("15000", "10000", "10000", "10000", "-10000");
    }

    @Test
    @DisplayName("(딜러 - 20) 무승부 플레이어 배팅 결과는 0이 된다")
    void player_dealer_compare_betting_result() {
        PlayerBets playerBets = new PlayerBets();
        Dealer dealer = Dealer.of(drawCards);
        initializePlayers(playerBets);

        playerBets.applyGameResult(dealer);
        List<BigDecimal> bettingAmounts = playerBets.bettingAmounts();

        Assertions.assertThat(bettingAmounts.stream()
                        .map(amount -> amount.stripTrailingZeros().toPlainString())
                        .toList())
                .containsExactly("15000", "10000", "0", "-10000", "-10000");
    }

    private void initializePlayers(PlayerBets playerBets) {
        Players players = Players.of(userNames);
        List<List<Card>> playersCards = getPlayerCards();

        addInitialPlayersCards(playersCards, players);

        addPlayersBet(players, playerBets);
    }

    private List<List<Card>> getPlayerCards() {
        return new ArrayList<>(List.of(
                blackjackCards,
                winCards,
                drawCards,
                loseCards,
                bustCards
        ));
    }

    private static void addInitialPlayersCards(List<List<Card>> playersCards, Players players) {
        for (int i = 0; i < playersCards.size(); i++) {
            players.getPlayers().get(i).addInitialCards(playersCards.get(i));
        }
    }

    private void addPlayersBet(Players players, PlayerBets playerBets) {
        for (Player player : players.getPlayers()) {
            playerBets.add(PlayerBet.of(player, bettingAmount));
        }
    }

}