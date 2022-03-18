package blackjack.domain.game;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blackjack.domain.participant.PlayerTest.getCardList;
import static org.assertj.core.api.Assertions.assertThat;

public class MatchResultTest {
    @Test
    @DisplayName("플레이어와 딜러가 모두 블랙잭이면 DRAW")
    void match_draw() {
        Player player = new Player(new Name("name"),
                new Cards(getCardList(Denomination.ACE, Denomination.JACK)));
        Dealer dealer = new Dealer(new Name(Dealer.NAME),
                new Cards(getCardList(Denomination.ACE, Denomination.QUEEN)));

        assertThat(MatchResult.judge(player, dealer)).isEqualTo(MatchResult.DRAW);
    }

    @Test
    @DisplayName("플레이어와 딜러가 모두 버스트가 아니고 숫자가 같으면 DRAW")
    void match_draw1() {
        Player player = new Player(new Name("name"),
                new Cards(getCardList(Denomination.ACE, Denomination.ACE)));
        Dealer dealer = new Dealer(new Name(Dealer.NAME),
                new Cards(getCardList(Denomination.ACE, Denomination.ACE)));

        assertThat(MatchResult.judge(player, dealer)).isEqualTo(MatchResult.DRAW);
    }

    @Test
    @DisplayName("플레이어와 딜러가 모두 버스트이면 DRAW")
    void match_draw2() {
        Player player = new Player(new Name("name"),
                new Cards(getCardList(Denomination.QUEEN, Denomination.JACK, Denomination.KING)));
        Dealer dealer = new Dealer(new Name(Dealer.NAME),
                new Cards(getCardList(Denomination.QUEEN, Denomination.JACK, Denomination.KING)));

        assertThat(MatchResult.judge(player, dealer)).isEqualTo(MatchResult.DRAW);
    }

    @Test
    @DisplayName("플레이어가 블랙잭이고 딜러가 블랙잭이 아니면 BLACKJACK")
    void match_win() {
        Player player = new Player(new Name("name"),
                new Cards(getCardList(Denomination.ACE, Denomination.JACK)));
        Dealer dealer = new Dealer(new Name(Dealer.NAME),
                new Cards(getCardList(Denomination.ACE, Denomination.TWO, Denomination.NINE)));

        assertThat(MatchResult.judge(player, dealer)).isEqualTo(MatchResult.BLACKJACK);
    }

    @Test
    @DisplayName("플레이어가 버스트가 아니고 딜러가 버스트이면 WIN")
    void match_win1() {
        Player player = new Player(new Name("name"),
                new Cards(getCardList(Denomination.QUEEN)));
        Dealer dealer = new Dealer(new Name(Dealer.NAME),
                new Cards(getCardList(Denomination.QUEEN, Denomination.JACK, Denomination.KING)));

        assertThat(MatchResult.judge(player, dealer)).isEqualTo(MatchResult.WIN);
    }

    @Test
    @DisplayName("플레이어와 딜러 둘다 버스트가 아니고 플레이어의 점수가 딜러 점수보다 높으면 WIN")
    void match_win2() {
        Player player = new Player(new Name("name"),
                new Cards(getCardList(Denomination.QUEEN, Denomination.JACK)));
        Dealer dealer = new Dealer(new Name(Dealer.NAME),
                new Cards(getCardList(Denomination.QUEEN)));

        assertThat(MatchResult.judge(player, dealer)).isEqualTo(MatchResult.WIN);
    }

    @Test
    @DisplayName("플레이어가 블랙잭이 아니고 딜러가 블랙잭이면 LOSE")
    void match_lose() {
        Player player = new Player(new Name("name"),
                new Cards(getCardList(Denomination.ACE, Denomination.TWO, Denomination.NINE)));
        Dealer dealer = new Dealer(new Name(Dealer.NAME),
                new Cards(getCardList(Denomination.ACE, Denomination.JACK)));

        assertThat(MatchResult.judge(player, dealer)).isEqualTo(MatchResult.LOSE);
    }

    @Test
    @DisplayName("플레이어가 버스트이고 딜러가 버스트가 아니면 LOSE")
    void match_lose1() {
        Player player = new Player(new Name("name"),
                new Cards(getCardList(Denomination.QUEEN, Denomination.JACK, Denomination.KING)));
        Dealer dealer = new Dealer(new Name(Dealer.NAME),
                new Cards(getCardList(Denomination.QUEEN)));

        assertThat(MatchResult.judge(player, dealer)).isEqualTo(MatchResult.LOSE);
    }

    @Test
    @DisplayName("플레이어와 딜러 둘다 버스트가 아니고 플레이어의 점수가 딜러 점수보다 낮으면 LOSE")
    void match_lose2() {
        Player player = new Player(new Name("name"),
                new Cards(getCardList(Denomination.QUEEN)));
        Dealer dealer = new Dealer(new Name(Dealer.NAME),
                new Cards(getCardList(Denomination.QUEEN, Denomination.JACK)));

        assertThat(MatchResult.judge(player, dealer)).isEqualTo(MatchResult.LOSE);
    }
}
