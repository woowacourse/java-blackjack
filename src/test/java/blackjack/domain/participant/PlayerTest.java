package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.Amount;
import blackjack.domain.Card;
import blackjack.domain.Hand;
import blackjack.domain.MatchResult;
import blackjack.domain.Nickname;
import blackjack.domain.Rank;
import blackjack.domain.Suit;
import blackjack.dto.PlayerGameResult;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("플레이어가 정상적으로 생성된다.")
    void createPlayer() {
        // given
        Player player = new Player(Nickname.from("boye"), Role.PLAYER, Amount.from("1000"));

        // when & then
        assertAll(
            () -> assertThat(player.getNickname()).isEqualTo("boye"),
            () -> assertThat(player.isDealer()).isFalse()
        );
    }

    @Test
    @DisplayName("플레이어의 점수가 21점 미만이면 카드를 받을 수 있다.")
    void isDrawableWhenNotBustedOrNotBlackjack() {
        // given
        Player player = new Player(Nickname.from("boye"), Role.PLAYER, Amount.from("1000"));
        Hand blackjackCards = Hand.from(List.of(
            new Card(Rank.EIGHT, Suit.SPADE),
            new Card(Rank.NINE, Suit.HEART)
        ));

        // when
        player.receiveCard(blackjackCards.getCards());

        // then
        assertThat(player.isDrawable()).isTrue();
    }

    @Test
    @DisplayName("플레이어가 블랙잭이어도 아직 멈추지 않았다면 카드 draw 여부를 물어본다.")
    void isDrawableWhenBlackjack() {
        // given
        Player player = new Player(Nickname.from("boye"), Role.PLAYER, Amount.from("1000"));
        Hand blackjackCards = Hand.from(List.of(
            new Card(Rank.ACE, Suit.SPADE),
            new Card(Rank.JACK, Suit.HEART)
        ));

        // when
        player.receiveCard(blackjackCards.getCards());

        // then
        assertThat(player.isDrawable()).isTrue();
    }

    @Test
    @DisplayName("플레이어가 bust되면 카드를 더이상 받지 않는다.")
    void isNotDrawableWhenBusted() {
        // given
        Player player = new Player(Nickname.from("boye"), Role.PLAYER, Amount.from("1000"));
        Hand bustedCards = Hand.from(List.of(
            new Card(Rank.TEN, Suit.SPADE),
            new Card(Rank.TEN, Suit.HEART),
            new Card(Rank.TWO, Suit.DIAMOND)
        ));

        // when
        player.receiveCard(bustedCards.getCards());

        // then
        assertThat(player.isDrawable()).isFalse();
    }

    @Test
    @DisplayName("플레이어가 명시적으로 stop()을 호출하면 점수와 상관없이 카드를 받을 수 없다.")
    void stopDrawing() {
        // given
        Player player = new Player(Nickname.from("boye"), Role.PLAYER, Amount.from("1000"));

        // when
        player.stop();

        // then
        assertThat(player.isDrawable()).isFalse();
    }

    @Test
    @DisplayName("딜러가 bust면 플레이어의 승리이고, 원금만큼 수익금을 얻는다.")
    void determinePlayerResultIfDealerBusted() {
        // given
        Dealer dealer = Dealer.from();
        Hand bustedCard = Hand.from(List.of(
            new Card(Rank.SIX, Suit.SPADE),
            new Card(Rank.TEN, Suit.HEART),
            new Card(Rank.NINE, Suit.DIAMOND)
        ));
        Hand notBustedCard = Hand.from(List.of(
            new Card(Rank.TEN, Suit.SPADE),
            new Card(Rank.SEVEN, Suit.HEART)
        ));
        dealer.receiveCard(bustedCard.getCards());
        Player player = new Player(Nickname.from("boye"), Role.PLAYER, Amount.from("10000"));
        player.receiveCard(notBustedCard.getCards());

        // when
        PlayerGameResult playerGameResult = player.determinePlayerResult(dealer);

        // then
        assertAll(
            () -> assertThat(playerGameResult.nickname()).isEqualTo("boye"),
            () -> assertThat(playerGameResult.matchResult()).isEqualTo(MatchResult.WIN),
            () -> assertThat(playerGameResult.profit()).isEqualTo(10000L)
        );
    }

    @Test
    @DisplayName("딜러와 플레이어가 모두 동시에 블랙잭인 경우 플레이어의 수익금은 0이다.")
    void determineDealerAndPlayerBothBlackJack() {
        // given
        Dealer dealer = Dealer.from();
        Hand blackjackCard = Hand.from(List.of(
            new Card(Rank.ACE, Suit.SPADE),
            new Card(Rank.JACK, Suit.HEART)
        ));
        dealer.receiveCard(blackjackCard.getCards());
        Player player = new Player(Nickname.from("boye"), Role.PLAYER, Amount.from("10000"));
        player.receiveCard(blackjackCard.getCards());

        // when
        PlayerGameResult playerGameResult = player.determinePlayerResult(dealer);

        // then
        assertAll(
            () -> assertThat(playerGameResult.nickname()).isEqualTo("boye"),
            () -> assertThat(playerGameResult.matchResult()).isEqualTo(MatchResult.TIE),
            () -> assertThat(playerGameResult.profit()).isEqualTo(0)
        );
    }

    @Test
    @DisplayName("플레이어가 블랙잭이고 딜러가 블랙잭이 아니라면 플레이어는 2.5배의 금액을 받는다.")
    void determineWhenOnlyPlayerHasBlackjack() {
        // given
        Dealer dealer = Dealer.from();
        Hand blackjackCard = Hand.from(List.of(
            new Card(Rank.ACE, Suit.SPADE),
            new Card(Rank.JACK, Suit.HEART)
        ));
        Hand notBustedCard = Hand.from(List.of(
            new Card(Rank.TEN, Suit.SPADE),
            new Card(Rank.SEVEN, Suit.HEART)
        ));
        dealer.receiveCard(notBustedCard.getCards());
        Player player = new Player(Nickname.from("boye"), Role.PLAYER, Amount.from("10000"));
        player.receiveCard(blackjackCard.getCards());

        // when
        PlayerGameResult playerGameResult = player.determinePlayerResult(dealer);

        // then
        assertAll(
            () -> assertThat(playerGameResult.nickname()).isEqualTo("boye"),
            () -> assertThat(playerGameResult.matchResult()).isEqualTo(MatchResult.WIN),
            () -> assertThat(playerGameResult.profit()).isEqualTo((long) (10000L * 1.5))
        );
    }

    @Test
    @DisplayName("플레이어가 블랙잭이고 딜러가 카드 3장으로 21점이면 플레이어가 승리한다.")
    void determinePlayerBlackjackWinsAgainstDealerTwentyOne() {
        // given
        Dealer dealer = Dealer.from();
        Hand dealerTwentyOne = Hand.from(List.of(
            new Card(Rank.SEVEN, Suit.SPADE),
            new Card(Rank.SEVEN, Suit.HEART),
            new Card(Rank.SEVEN, Suit.DIAMOND)
        ));
        Hand playerBlackjack = Hand.from(List.of(
            new Card(Rank.ACE, Suit.SPADE),
            new Card(Rank.JACK, Suit.HEART)
        ));
        dealer.receiveCard(dealerTwentyOne.getCards());
        Player player = new Player(Nickname.from("boye"), Role.PLAYER, Amount.from("10000"));
        player.receiveCard(playerBlackjack.getCards());

        // when
        PlayerGameResult playerGameResult = player.determinePlayerResult(dealer);

        // then
        assertAll(
            () -> assertThat(playerGameResult.matchResult()).isEqualTo(MatchResult.WIN),
            () -> assertThat(playerGameResult.profit()).isEqualTo(15000L)
        );
    }

    @Test
    @DisplayName("딜러가 블랙잭이고 플레이어가 카드 3장으로 21점이면 플레이어는 패배한다.")
    void determineDealerBlackjackWinsAgainstPlayerTwentyOne() {
        // given
        Dealer dealer = Dealer.from();
        Hand dealerBlackjack = Hand.from(List.of(
            new Card(Rank.ACE, Suit.SPADE),
            new Card(Rank.JACK, Suit.HEART)
        ));
        Hand playerTwentyOne = Hand.from(List.of(
            new Card(Rank.SEVEN, Suit.SPADE),
            new Card(Rank.SEVEN, Suit.HEART),
            new Card(Rank.SEVEN, Suit.DIAMOND)
        ));
        dealer.receiveCard(dealerBlackjack.getCards());
        Player player = new Player(Nickname.from("boye"), Role.PLAYER, Amount.from("10000"));
        player.receiveCard(playerTwentyOne.getCards());

        // when
        PlayerGameResult playerGameResult = player.determinePlayerResult(dealer);

        // then
        assertAll(
            () -> assertThat(playerGameResult.matchResult()).isEqualTo(MatchResult.LOSE),
            () -> assertThat(playerGameResult.profit()).isEqualTo(-10000L)
        );
    }

    @Test
    @DisplayName("플레이어가 bust되면 돈을 모두 잃는다.")
    void determineWhenPlayerBusted() {
        // given
        Dealer dealer = Dealer.from();
        Hand bustedCard = Hand.from(List.of(
            new Card(Rank.SIX, Suit.SPADE),
            new Card(Rank.TEN, Suit.HEART),
            new Card(Rank.NINE, Suit.DIAMOND)
        ));
        Hand notBustedCard = Hand.from(List.of(
            new Card(Rank.TEN, Suit.SPADE),
            new Card(Rank.SEVEN, Suit.HEART)
        ));
        dealer.receiveCard(notBustedCard.getCards());
        Player player = new Player(Nickname.from("boye"), Role.PLAYER, Amount.from("10000"));
        player.receiveCard(bustedCard.getCards());

        // when
        PlayerGameResult playerGameResult = player.determinePlayerResult(dealer);

        // then
        assertAll(
            () -> assertThat(playerGameResult.nickname()).isEqualTo("boye"),
            () -> assertThat(playerGameResult.matchResult()).isEqualTo(MatchResult.LOSE),
            () -> assertThat(playerGameResult.profit()).isEqualTo(-10000L)
        );
    }

    @Test
    @DisplayName("딜러와 플레이어 모두 카드의 합이 21 미만일 경우 플레이어가 이긴다면 원금만큼 수익을 얻는다.")
    void determineWhenPlayerWin() {
        // given
        Dealer dealer = Dealer.from();
        Hand cardsOfScore17 = Hand.from(List.of(
            new Card(Rank.TEN, Suit.SPADE),
            new Card(Rank.SEVEN, Suit.HEART)
        ));
        Hand cardsOfScore20 = Hand.from(List.of(
            new Card(Rank.TEN, Suit.SPADE),
            new Card(Rank.SEVEN, Suit.HEART),
            new Card(Rank.THREE, Suit.HEART)
        ));
        dealer.receiveCard(cardsOfScore17.getCards());
        Player player = new Player(Nickname.from("boye"), Role.PLAYER, Amount.from("10000"));
        player.receiveCard(cardsOfScore20.getCards());

        // when
        PlayerGameResult playerGameResult = player.determinePlayerResult(dealer);

        // then
        assertAll(
            () -> assertThat(playerGameResult.nickname()).isEqualTo("boye"),
            () -> assertThat(playerGameResult.matchResult()).isEqualTo(MatchResult.WIN),
            () -> assertThat(playerGameResult.profit()).isEqualTo(10000L)
        );
    }

    @Test
    @DisplayName("딜러와 플레이어가 무승부일 경우 플레이어는 원금을 회수한다.")
    void determineWhenPlayerAndDealerTie() {
        // given
        Dealer dealer = Dealer.from();
        Hand cardsOfScore17 = Hand.from(List.of(
            new Card(Rank.TEN, Suit.SPADE),
            new Card(Rank.SEVEN, Suit.HEART)
        ));
        dealer.receiveCard(cardsOfScore17.getCards());
        Player player = new Player(Nickname.from("boye"), Role.PLAYER, Amount.from("10000"));
        player.receiveCard(cardsOfScore17.getCards());

        // when
        PlayerGameResult playerGameResult = player.determinePlayerResult(dealer);

        // then
        assertAll(
            () -> assertThat(playerGameResult.nickname()).isEqualTo("boye"),
            () -> assertThat(playerGameResult.matchResult()).isEqualTo(MatchResult.TIE),
            () -> assertThat(playerGameResult.profit()).isEqualTo(0)
        );
    }

    @Test
    @DisplayName("딜러와 플레이어 모두 카드의 합이 21 미만일 경우 플레이어가 진다면 돈을 모두 잃는다.")
    void determineWHenPlayerLose() {
        // given
        Dealer dealer = Dealer.from();
        Hand cardsOfScore17 = Hand.from(List.of(
            new Card(Rank.TEN, Suit.SPADE),
            new Card(Rank.SEVEN, Suit.HEART)
        ));
        Hand cardsOfScore20 = Hand.from(List.of(
            new Card(Rank.TEN, Suit.SPADE),
            new Card(Rank.SEVEN, Suit.HEART),
            new Card(Rank.THREE, Suit.HEART)
        ));
        dealer.receiveCard(cardsOfScore20.getCards());
        Player player = new Player(Nickname.from("boye"), Role.PLAYER, Amount.from("10000"));
        player.receiveCard(cardsOfScore17.getCards());

        // when
        PlayerGameResult playerGameResult = player.determinePlayerResult(dealer);

        // then
        assertAll(
            () -> assertThat(playerGameResult.nickname()).isEqualTo("boye"),
            () -> assertThat(playerGameResult.matchResult()).isEqualTo(MatchResult.LOSE),
            () -> assertThat(playerGameResult.profit()).isEqualTo(-10000L)
        );
    }

    @Test
    @DisplayName("베팅 금액이 음수라면 예외가 발생한다.")
    void validateNegativeBettingAmount() {
        // given
        String amount = "-10000";

        // when & then
        assertThatThrownBy(() -> new Player(Nickname.from("boye"), Role.PLAYER, Amount.from(amount)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("베팅 금액은 0보다 큰 양수여야 합니다.");
    }

    @Test
    @DisplayName("베팅 금액이 0이라면 예외가 발생한다.")
    void validateZeroBettingAmount() {
        // given
        String amount = "0";

        // when & then
        assertThatThrownBy(() -> new Player(Nickname.from("boye"), Role.PLAYER, Amount.from(amount)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("베팅 금액은 0보다 큰 양수여야 합니다.");
    }
}
