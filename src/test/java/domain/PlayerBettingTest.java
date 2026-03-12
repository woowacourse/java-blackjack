package domain;

import domain.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class PlayerBettingTest {
    @Test
    void 플레이어_베팅_금액_저장_테스트() {
        // given
        Player phobi = Player.of("phobi");

        // when
        PlayerBetting playerBetting = PlayerBetting.of(phobi, 10_000);

        // then
        assertThat(playerBetting.getPlayer()).isEqualTo(phobi);
        assertThat(playerBetting.getValue()).isEqualTo(10_000);
    }

    @Test
    void 플레이어_베팅_금액_저장_실패_테스트() {
        // given
        Player phobi = Player.of("phobi");

        // when, then
        assertThatThrownBy(() -> {
            PlayerBetting.of(phobi, -10_000);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 플레이어_배팅_금액_수익률_적용_테스트() {
        //given
        Player player = Player.of("phobi");
        PlayerBetting playerBetting = PlayerBetting.of(player, 10_000);
        List<Card> cards1 = List.of(
                Card.of(CardRank.EIGHT, CardShape.HEART),
                Card.of(CardRank.TEN, CardShape.CLUB)
        );
        Deck deck1 = Deck.of(cards1);
        player.assignDeck(deck1);

        List<Card> cards2 = List.of(
                Card.of(CardRank.THREE, CardShape.HEART),
                Card.of(CardRank.TWO, CardShape.CLUB)
        );
        Dealer dealer = Dealer.of(Deck.of(cards2));
        player.changeStatus(PlayerStatus.WIN);

        // when
        playerBetting.applyBetting(dealer);

        // then
        assertThat(player.getProfit()).isEqualTo(10_000);
        assertThat(dealer.getProfit()).isEqualTo(-10_000);
    }

    @Test
    void 플레이어_블랙잭일때_배팅_금액_수익률_적용_테스트() {
        //given
        Player player = Player.of("phobi");
        PlayerBetting playerBetting = PlayerBetting.of(player, 10_000);
        List<Card> cards1 = List.of(
                Card.of(CardRank.ACE, CardShape.HEART),
                Card.of(CardRank.TEN, CardShape.CLUB)
        );
        Deck deck1 = Deck.of(cards1);
        player.assignDeck(deck1);

        List<Card> cards2 = List.of(
                Card.of(CardRank.THREE, CardShape.HEART),
                Card.of(CardRank.TWO, CardShape.CLUB)
        );
        Dealer dealer = Dealer.of(Deck.of(cards2));
        player.changeStatus(PlayerStatus.WIN);

        // when
        playerBetting.applyBetting(dealer);

        // then
        assertThat(player.getProfit()).isEqualTo(15_000);
        assertThat(dealer.getProfit()).isEqualTo(-15_000);
    }

    @Test
    void 플레이어_패배일때_배팅_금액_수익률_적용_테스트() {
        //given
        Player player = Player.of("phobi");
        PlayerBetting playerBetting = PlayerBetting.of(player, 10_000);
        List<Card> cards1 = List.of(
                Card.of(CardRank.TWO, CardShape.HEART),
                Card.of(CardRank.THREE, CardShape.CLUB)
        );
        Deck deck1 = Deck.of(cards1);
        player.assignDeck(deck1);

        List<Card> cards2 = List.of(
                Card.of(CardRank.EIGHT, CardShape.HEART),
                Card.of(CardRank.TEN, CardShape.CLUB)
        );
        Dealer dealer = Dealer.of(Deck.of(cards2));
        player.changeStatus(PlayerStatus.LOSS);

        // when
        playerBetting.applyBetting(dealer);

        // then
        assertThat(player.getProfit()).isEqualTo(-10_000);
        assertThat(dealer.getProfit()).isEqualTo(10_000);
    }

    @Test
    void 플레이어_무승부일때_배팅_금액_수익률_적용_테스트() {
        //given
        Player player = Player.of("phobi");
        PlayerBetting playerBetting = PlayerBetting.of(player, 10_000);
        List<Card> cards1 = List.of(
                Card.of(CardRank.TWO, CardShape.HEART),
                Card.of(CardRank.THREE, CardShape.CLUB)
        );
        Deck deck1 = Deck.of(cards1);
        player.assignDeck(deck1);

        List<Card> cards2 = List.of(
                Card.of(CardRank.TWO, CardShape.DIAMOND),
                Card.of(CardRank.THREE, CardShape.SPADE)
        );
        Dealer dealer = Dealer.of(Deck.of(cards2));
        player.changeStatus(PlayerStatus.DRAW);

        // when
        playerBetting.applyBetting(dealer);

        // then
        assertThat(player.getProfit()).isEqualTo(0);
        assertThat(dealer.getProfit()).isEqualTo(0);
    }

    @Test
    void 플레이어와_딜러_둘다_블랙잭_무승부일때_배팅_금액_수익률_적용_테스트() {
        //given
        Player player = Player.of("phobi");
        PlayerBetting playerBetting = PlayerBetting.of(player, 10_000);
        List<Card> cards1 = List.of(
                Card.of(CardRank.ACE, CardShape.HEART),
                Card.of(CardRank.TEN, CardShape.CLUB)
        );
        Deck deck1 = Deck.of(cards1);
        player.assignDeck(deck1);

        List<Card> cards2 = List.of(
                Card.of(CardRank.ACE, CardShape.DIAMOND),
                Card.of(CardRank.TEN, CardShape.SPADE)
        );
        Dealer dealer = Dealer.of(Deck.of(cards2));
        player.changeStatus(PlayerStatus.DRAW);

        // when
        playerBetting.applyBetting(dealer);

        // then
        assertThat(player.getProfit()).isEqualTo(0);
        assertThat(dealer.getProfit()).isEqualTo(0);
    }
}
