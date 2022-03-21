package blackjack.domain.entry;

import blackjack.domain.PlayerName;
import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.HoldCards;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {
    PlayerName playerName1 = new PlayerName("플레이어1");
    PlayerName playerName2 = new PlayerName("플레이어2");
    PlayerName playerName3 = new PlayerName("플레이어3");
    PlayerName playerName4 = new PlayerName("플레이어4");

    @Test
    @DisplayName("두장의 카드를 지급받아 카드의 합을 계산한다.")
    void getTwoCards() {
        Dealer dealer = new Dealer(HoldCards.init(List.of(Card.valueOf(Suit.SPADE, Denomination.KING), Card.valueOf(Suit.SPADE, Denomination.ACE))));

        assertThat(dealer.calculateCardsSum()).isEqualTo(21);
    }

    @Test
    @DisplayName("보유한 카드의 합이 16이하인지 판단한다.")
    void shouldHaveMoreCard() {
        Dealer dealer = new Dealer(HoldCards.init(List.of(Card.valueOf(Suit.SPADE, Denomination.EIGHT), Card.valueOf(Suit.HEART, Denomination.EIGHT))));

        assertThat(dealer.canHit()).isTrue();
    }

    @Test
    @DisplayName("보유한 카드의 합이 17이상이면 hit할 수 없는지 확인한다.")
    void cannotHitCard() {
        Dealer dealer = new Dealer(HoldCards.init(List.of(Card.valueOf(Suit.SPADE, Denomination.EIGHT), Card.valueOf(Suit.HEART, Denomination.NINE))));

        assertThat(dealer.canHit()).isFalse();
    }

    @Test
    @DisplayName("딜러의 최종 수익을 구한다.")
    void getDealerTotalMoney() {
        Dealer dealer = new Dealer(HoldCards.init(List.of(Card.valueOf(Suit.SPADE, Denomination.EIGHT), Card.valueOf(Suit.HEART, Denomination.NINE))));
        Map<Participant, Integer> bettingResult = new HashMap<>();

        Player player1 = new Player(playerName1, 10000, HoldCards.init(List.of(Card.valueOf(Suit.HEART, Denomination.EIGHT), Card.valueOf(Suit.CLUB, Denomination.EIGHT))));
        Player player2 = new Player(playerName2, 20000, HoldCards.init(List.of(Card.valueOf(Suit.HEART, Denomination.THREE), Card.valueOf(Suit.CLUB, Denomination.TWO))));
        Player player3 = new Player(playerName3, 15000, HoldCards.init(List.of(Card.valueOf(Suit.HEART, Denomination.ACE), Card.valueOf(Suit.CLUB, Denomination.FOUR))));

        bettingResult.put(player1, 10000);
        bettingResult.put(player2, -20000);
        bettingResult.put(player3, 0);

        assertThat(dealer.calculateBettingMoney(bettingResult)).isEqualTo(10000);
    }

    @Test
    @DisplayName("딜러의 최종 수익을 구한다.(총 수익이 마이너스인 경우)")
    void getDealerNegativeTotalMoney() {
        Dealer dealer = new Dealer(HoldCards.init(List.of(Card.valueOf(Suit.SPADE, Denomination.EIGHT), Card.valueOf(Suit.HEART, Denomination.NINE))));
        Map<Participant, Integer> bettingResult = new HashMap<>();

        Player player1 = new Player(playerName1, 10000, HoldCards.init(List.of(Card.valueOf(Suit.HEART, Denomination.EIGHT), Card.valueOf(Suit.CLUB, Denomination.EIGHT))));
        Player player2 = new Player(playerName2, 20000, HoldCards.init(List.of(Card.valueOf(Suit.HEART, Denomination.THREE), Card.valueOf(Suit.CLUB, Denomination.TWO))));
        Player player3 = new Player(playerName3, 15000, HoldCards.init(List.of(Card.valueOf(Suit.HEART, Denomination.ACE), Card.valueOf(Suit.CLUB, Denomination.FOUR))));
        Player player4 = new Player(playerName4, 30000, HoldCards.init(List.of(Card.valueOf(Suit.HEART, Denomination.NINE), Card.valueOf(Suit.CLUB, Denomination.FIVE))));

        bettingResult.put(player1, 10000);
        bettingResult.put(player2, -20000);
        bettingResult.put(player3, 0);
        bettingResult.put(player4, 30000);

        assertThat(dealer.calculateBettingMoney(bettingResult)).isEqualTo(-20000);
    }
}
