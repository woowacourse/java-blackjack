package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import blackjack.domain.Bet;
import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("Player는 플레이어여야 한다.")
    void isPlayer_true() {
        // given
        Player player = new Player("glen", Collections.emptyList(), Bet.of(1000));

        // expect
        assertThat(player.isPlayer())
                .isTrue();
    }

    @Test
    @DisplayName("이름이 딜러면 예외가 발생해야 한다.")
    void validateBlacklist() {
        // expect
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Player("딜러", Collections.emptyList(), Bet.of(1000));
        }).withMessage("[ERROR] 딜러는 사용할 수 없는 이름입니다.");
    }

    @Test
    @DisplayName("Player가 정상적으로 생성되어야 한다.")
    void create_success() {
        // given
        Player player = new Player("123", Collections.emptyList(), Bet.of(1000));

        // expect
        assertThat(player.getName().getName())
                .isEqualTo("123");
    }

    @Test
    @DisplayName("사용자가 카드를 더 뽑을 수 없으면 거짓을 반환해야 한다.")
    void canDrawCard_false() {
        // given
        Player player = new Player("glen", List.of(
                Card.of(Suit.DIAMOND, Rank.TEN),
                Card.of(Suit.DIAMOND, Rank.TEN),
                Card.of(Suit.DIAMOND, Rank.TEN)
        ), Bet.of(1000));

        // expect
        assertThat(player.canDrawCard())
                .isFalse();
    }

    @Test
    @DisplayName("사용자가 카드를 더 뽑을 수 있으면 참을 반환해야 한다.")
    void canDrawCard_true() {
        // given
        Player player = new Player("glen", List.of(
                Card.of(Suit.DIAMOND, Rank.TEN)
        ), Bet.of(1000));

        // expect
        assertThat(player.canDrawCard())
                .isTrue();
    }

    @Test
    @DisplayName("처음 2장의 카드가 블랙잭이면 배팅금의 1.5배를 얻는다.")
    void matchGameWithBet_blackjack() {
        // given
        Player player = new Player("glen", List.of(
                Card.of(Suit.DIAMOND, Rank.ACE),
                Card.of(Suit.DIAMOND, Rank.KING)
        ), Bet.of(1000));

        Dealer dealer = new Dealer(Collections.emptyList());

        // when
        Bet bet = player.matchGameWithBet(dealer);

        // then
        assertThat(bet.getBet())
                .isEqualTo(1500);
    }

    @Test
    @DisplayName("처음 2장의 카드가 블랙잭이여도 딜러가 블랙잭이면 수익이 없다.")
    void matchGameWithBet_blackjackWithDealerBlackjack() {
        // given
        Player player = new Player("glen", List.of(
                Card.of(Suit.DIAMOND, Rank.ACE),
                Card.of(Suit.DIAMOND, Rank.KING)
        ), Bet.of(1000));

        Dealer dealer = new Dealer(List.of(
                Card.of(Suit.DIAMOND, Rank.ACE),
                Card.of(Suit.DIAMOND, Rank.KING)
        ));

        // when
        Bet bet = player.matchGameWithBet(dealer);

        // then
        assertThat(bet.getBet())
                .isZero();
    }

    @Test
    @DisplayName("딜러의 점수가 내 점수보다 낮으면 배팅금을 얻는다.")
    void matchGameWithBet_win() {
        // given
        Player player = new Player("glen", List.of(
                Card.of(Suit.DIAMOND, Rank.KING)
        ), Bet.of(1000));
        Dealer dealer = new Dealer(Collections.emptyList());

        // when
        Bet bet = player.matchGameWithBet(dealer);

        // then
        assertThat(bet.getBet())
                .isEqualTo(1000);
    }

    @Test
    @DisplayName("딜러의 점수가 내 점수보다 높으면 배팅금을 잃는다.")
    void matchGameWithBet_lose() {
        // given
        Player player = new Player("glen", Collections.emptyList(), Bet.of(1000));
        Dealer dealer = new Dealer(List.of(
                Card.of(Suit.DIAMOND, Rank.KING)
        ));

        // when
        Bet bet = player.matchGameWithBet(dealer);

        // then
        assertThat(bet.getBet())
                .isEqualTo(-1000);
    }

    @Test
    @DisplayName("딜러의 점수가 나와 같으면 수익이 없다.")
    void matchGameWithBet_draw() {
        // given
        Player player = new Player("glen", List.of(
                Card.of(Suit.DIAMOND, Rank.KING)
        ), Bet.of(1000));
        Dealer dealer = new Dealer(List.of(
                Card.of(Suit.DIAMOND, Rank.KING)
        ));

        // when
        Bet bet = player.matchGameWithBet(dealer);

        // then
        assertThat(bet.getBet())
                .isZero();
    }

    @Test
    @DisplayName("나의 점수가 21점을 넘으면 배팅금을 잃는다.")
    void matchGameWithBet_overScore() {
        // given
        Player player = new Player("glen", List.of(
                Card.of(Suit.DIAMOND, Rank.KING),
                Card.of(Suit.DIAMOND, Rank.KING),
                Card.of(Suit.DIAMOND, Rank.KING)
        ), Bet.of(1000));
        Dealer dealer = new Dealer(Collections.emptyList());

        // when
        Bet bet = player.matchGameWithBet(dealer);

        // then
        assertThat(bet.getBet())
                .isEqualTo(-1000);
    }

    @Test
    @DisplayName("딜러의 점수가 21점을 넘고 내가 넘지 않으면 배팅금을 얻는다.")
    void matchGameWithBet_otherOverScore() {
        // given
        Player player = new Player("glen", Collections.emptyList(), Bet.of(1000));
        Dealer dealer = new Dealer(List.of(
                Card.of(Suit.DIAMOND, Rank.KING),
                Card.of(Suit.DIAMOND, Rank.KING),
                Card.of(Suit.DIAMOND, Rank.KING)
        ));

        // when
        Bet bet = player.matchGameWithBet(dealer);

        // then
        assertThat(bet.getBet())
                .isEqualTo(1000);
    }

    @Test
    @DisplayName("딜러와 나의 점수가 21점을 넘으면 배팅금을 잃는다.")
    void matchGameWithBet_sameOverScore() {
        // given
        Player player = new Player("glen", List.of(
                Card.of(Suit.DIAMOND, Rank.KING),
                Card.of(Suit.DIAMOND, Rank.KING),
                Card.of(Suit.DIAMOND, Rank.KING)
        ), Bet.of(1000));
        Dealer dealer = new Dealer(List.of(
                Card.of(Suit.DIAMOND, Rank.KING),
                Card.of(Suit.DIAMOND, Rank.KING),
                Card.of(Suit.DIAMOND, Rank.KING)
        ));

        // when
        Bet bet = player.matchGameWithBet(dealer);

        // then
        assertThat(bet.getBet())
                .isEqualTo(-1000);
    }
}
