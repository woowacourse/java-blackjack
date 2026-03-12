package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.Card;
import blackjack.domain.MatchResult;
import blackjack.domain.PlayingCards;
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
        Player player = new Player("pobi", Role.PLAYER, 1000);

        // when & then
        assertAll(
            () -> assertThat(player.getNickname()).isEqualTo("pobi"),
            () -> assertThat(player.isDealer()).isFalse()
        );
    }

    @Test
    @DisplayName("플레이어의 점수가 21점 미만이면 카드를 받을 수 있다.")
    void isDrawableWhenNotBustedOrNotBlackjack() {
        // given
        Player player = new Player("pobi", Role.PLAYER, 1000);
        PlayingCards blackjackCards = PlayingCards.from(List.of(
            new Card(Rank.EIGHT, Suit.SPADE),
            new Card(Rank.NINE, Suit.HEART)
        ));

        // when
        player.receiveCard(blackjackCards);

        // then
        assertThat(player.isDrawable()).isTrue();
    }

    @Test
    @DisplayName("플레이어의 점수가 21점 이상이면 더 이상 카드를 받을 수 없다.")
    void isNotDrawableWhenBustedOrBlackjack() {
        // given
        Player player = new Player("pobi", Role.PLAYER, 1000);
        PlayingCards blackjackCards = PlayingCards.from(List.of(
            new Card(Rank.ACE, Suit.SPADE),
            new Card(Rank.JACK, Suit.HEART)
        ));

        // when
        player.receiveCard(blackjackCards);

        // then
        assertThat(player.isDrawable()).isFalse();
    }

    @Test
    @DisplayName("플레이어가 명시적으로 stop()을 호출하면 점수와 상관없이 카드를 받을 수 없다.")
    void stopDrawing() {
        // given
        Player player = new Player("pobi", Role.PLAYER, 1000);

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
        PlayingCards bustedCard = PlayingCards.from(List.of(
            new Card(Rank.SIX, Suit.SPADE),
            new Card(Rank.TEN, Suit.HEART),
            new Card(Rank.NINE, Suit.DIAMOND)
        ));
        PlayingCards notBustedCard = PlayingCards.from(List.of(
            new Card(Rank.TEN, Suit.SPADE),
            new Card(Rank.SEVEN, Suit.HEART)
        ));
        dealer.receiveCard(bustedCard);
        long amount = 10000L;
        Player player = new Player("boye", Role.PLAYER, amount);
        player.receiveCard(notBustedCard);

        // when
        PlayerGameResult playerGameResult = player.determinePlayerResult(dealer);

        // then
        assertAll(
            () -> assertThat(playerGameResult.nickname()).isEqualTo("boye"),
            () -> assertThat(playerGameResult.matchResult()).isEqualTo(MatchResult.WIN),
            () -> assertThat(playerGameResult.profit()).isEqualTo(amount)
        );
    }

    @Test
    @DisplayName("딜러와 플레이어가 모두 동시에 블랙잭인 경우 플레이어의 수익금은 0이다.")
    void determineDealerAndPlayerBothBlackJack() {
        // given
        Dealer dealer = Dealer.from();
        PlayingCards blackjackCard = PlayingCards.from(List.of(
            new Card(Rank.ACE, Suit.SPADE),
            new Card(Rank.JACK, Suit.HEART)
        ));
        dealer.receiveCard(blackjackCard);
        long amount = 10000L;
        Player player = new Player("boye", Role.PLAYER, amount);
        player.receiveCard(blackjackCard);

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
        PlayingCards blackjackCard = PlayingCards.from(List.of(
            new Card(Rank.ACE, Suit.SPADE),
            new Card(Rank.JACK, Suit.HEART)
        ));
        PlayingCards notBustedCard = PlayingCards.from(List.of(
            new Card(Rank.TEN, Suit.SPADE),
            new Card(Rank.SEVEN, Suit.HEART)
        ));
        dealer.receiveCard(notBustedCard);
        long amount = 10000L;
        Player player = new Player("boye", Role.PLAYER, amount);
        player.receiveCard(blackjackCard);

        // when
        PlayerGameResult playerGameResult = player.determinePlayerResult(dealer);

        // then
        assertAll(
            () -> assertThat(playerGameResult.nickname()).isEqualTo("boye"),
            () -> assertThat(playerGameResult.matchResult()).isEqualTo(MatchResult.WIN),
            () -> assertThat(playerGameResult.profit()).isEqualTo((long) (amount * 1.5))
        );
    }

    @Test
    @DisplayName("플레이어가 bust되면 돈을 모두 잃는다.")
    void determineWhenPlayerBusted() {
        // given
        Dealer dealer = Dealer.from();
        PlayingCards bustedCard = PlayingCards.from(List.of(
            new Card(Rank.SIX, Suit.SPADE),
            new Card(Rank.TEN, Suit.HEART),
            new Card(Rank.NINE, Suit.DIAMOND)
        ));
        PlayingCards notBustedCard = PlayingCards.from(List.of(
            new Card(Rank.TEN, Suit.SPADE),
            new Card(Rank.SEVEN, Suit.HEART)
        ));
        dealer.receiveCard(notBustedCard);
        long amount = 10000L;
        Player player = new Player("boye", Role.PLAYER, amount);
        player.receiveCard(bustedCard);

        // when
        PlayerGameResult playerGameResult = player.determinePlayerResult(dealer);

        // then
        assertAll(
            () -> assertThat(playerGameResult.nickname()).isEqualTo("boye"),
            () -> assertThat(playerGameResult.matchResult()).isEqualTo(MatchResult.LOSE),
            () -> assertThat(playerGameResult.profit()).isEqualTo(-amount)
        );
    }

    @Test
    @DisplayName("딜러와 플레이어 모두 카드의 합이 21 미만일 경우 플레이어가 이긴다면 원금만큼 수익을 얻는다.")
    void determineWhenPlayerWin() {
        // given
        Dealer dealer = Dealer.from();
        PlayingCards cardsOfScore17 = PlayingCards.from(List.of(
            new Card(Rank.TEN, Suit.SPADE),
            new Card(Rank.SEVEN, Suit.HEART)
        ));
        PlayingCards cardsOfScore20 = PlayingCards.from(List.of(
            new Card(Rank.TEN, Suit.SPADE),
            new Card(Rank.SEVEN, Suit.HEART),
            new Card(Rank.THREE, Suit.HEART)
        ));
        dealer.receiveCard(cardsOfScore17);
        long amount = 10000L;
        Player player = new Player("boye", Role.PLAYER, amount);
        player.receiveCard(cardsOfScore20);

        // when
        PlayerGameResult playerGameResult = player.determinePlayerResult(dealer);

        // then
        assertAll(
            () -> assertThat(playerGameResult.nickname()).isEqualTo("boye"),
            () -> assertThat(playerGameResult.matchResult()).isEqualTo(MatchResult.WIN),
            () -> assertThat(playerGameResult.profit()).isEqualTo(amount)
        );
    }

    @Test
    @DisplayName("딜러와 플레이어가 무승부일 경우 플레이어는 원금을 회수한다.")
    void determineWhenPlayerAndDealerTie() {
        // given
        Dealer dealer = Dealer.from();
        PlayingCards cardsOfScore17 = PlayingCards.from(List.of(
            new Card(Rank.TEN, Suit.SPADE),
            new Card(Rank.SEVEN, Suit.HEART)
        ));
        dealer.receiveCard(cardsOfScore17);
        long amount = 10000L;
        Player player = new Player("boye", Role.PLAYER, amount);
        player.receiveCard(cardsOfScore17);

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
        PlayingCards cardsOfScore17 = PlayingCards.from(List.of(
            new Card(Rank.TEN, Suit.SPADE),
            new Card(Rank.SEVEN, Suit.HEART)
        ));
        PlayingCards cardsOfScore20 = PlayingCards.from(List.of(
            new Card(Rank.TEN, Suit.SPADE),
            new Card(Rank.SEVEN, Suit.HEART),
            new Card(Rank.THREE, Suit.HEART)
        ));
        dealer.receiveCard(cardsOfScore20);
        long amount = 10000L;
        Player player = new Player("boye", Role.PLAYER, amount);
        player.receiveCard(cardsOfScore17);

        // when
        PlayerGameResult playerGameResult = player.determinePlayerResult(dealer);

        // then
        assertAll(
            () -> assertThat(playerGameResult.nickname()).isEqualTo("boye"),
            () -> assertThat(playerGameResult.matchResult()).isEqualTo(MatchResult.LOSE),
            () -> assertThat(playerGameResult.profit()).isEqualTo(-amount)
        );
    }
}
