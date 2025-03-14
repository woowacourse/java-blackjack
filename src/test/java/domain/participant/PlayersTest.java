package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.TrumpNumber;
import domain.card.TrumpShape;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    void 게임_참가자들을_관리하는_모델을_생성한다() {
        // given
        List<Player> players = List.of(Player.of("pobi1", Money.of(1000)),
                Player.of("pobi2", Money.of(1000)),
                Player.of("pobi3", Money.of(1000)),
                Player.of("pobi4", Money.of(1000)),
                Player.of("pobi5", Money.of(1000)));

        // when & then
        assertThatCode(() -> Players.of(players))
                .doesNotThrowAnyException();
    }

    @Test
    void 게임_참가자_최소_인원_미만_시_예외가_발생한다() {
        // given
        List<Player> players = List.of();

        // when & then
        assertThatThrownBy(() -> Players.of(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("참여 가능한 플레이어는 최소 1명, 최대 5명 입니다.");
    }

    @Test
    void 게임_참가자_최대_인원_초과_시_예외가_발생한다() {
        // given
        List<Player> players = List.of(Player.of("pobi1", Money.of(1000)),
                Player.of("pobi2", Money.of(1000)),
                Player.of("pobi3", Money.of(1000)),
                Player.of("pobi4", Money.of(1000)),
                Player.of("pobi5", Money.of(1000)),
                Player.of("pobi6", Money.of(1000)));

        // when & then
        assertThatThrownBy(() -> Players.of(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("참여 가능한 플레이어는 최소 1명, 최대 5명 입니다.");
    }

    @Test
    void 참여자들에게_카드를_한_장씩_나눠준다() {
        // given
        CardDeck cardDeck = CardDeck.of();
        Players players = Players.of(
                List.of(
                        Player.of("pobi1", Money.of(1000)),
                        Player.of("pobi2", Money.of(1000)),
                        Player.of("pobi3", Money.of(1000))
                )
        );
        Dealer dealer = Dealer.of(cardDeck);

        // when
        players.receiveCards(dealer);

        // then
        assertThat(cardDeck.getCards()).hasSize(49);
    }

    @Test
    void 이름으로_플레이어를_반환한다() {
        // given
        Players players = Players.of(
                List.of(
                        Player.of("pobi1", Money.of(1000)),
                        Player.of("pobi2", Money.of(1000)),
                        Player.of("pobi3", Money.of(1000))
                )
        );

        // when
        Player player = players.findByName("pobi1");

        // then
        assertThat(player.getName()).isEqualTo("pobi1");
    }

    @Test
    void 플레이어들_중_블랙잭인_플레이어에_대해_금액을_업데이트한다() {
        // given
        Player winner = Player.of("pobi1", Money.of(1000));
        Player player = Player.of("pobi2", Money.of(1000));
        Players players = Players.of(List.of(winner, player));

        winner.receive(Card.of(TrumpNumber.ACE, TrumpShape.SPADE));
        winner.receive(Card.of(TrumpNumber.JACK, TrumpShape.SPADE));
        player.receive(Card.of(TrumpNumber.FIVE, TrumpShape.DIAMOND));
        player.receive(Card.of(TrumpNumber.SIX, TrumpShape.DIAMOND));

        Dealer dealer = Dealer.of(CardDeck.of());
        dealer.receive(Card.of(TrumpNumber.FIVE, TrumpShape.DIAMOND));
        dealer.receive(Card.of(TrumpNumber.SEVEN, TrumpShape.SPADE));

        // when
        players.judgeBlackjack(dealer);

        // then
        Assertions.assertAll(() -> {
            assertThat(winner.getTotalWinnings()).isEqualTo(1500);
            assertThat(dealer.getTotalWinnings()).isEqualTo(-1500);
        });
    }

    @Test
    void 플레이어와_딜러_모두_블랙잭이면_플레이어는_베팅금을_돌려받는다() {
        // given
        Player player = Player.of("pobi1", Money.of(1000));
        player.receive(Card.of(TrumpNumber.ACE, TrumpShape.SPADE));
        player.receive(Card.of(TrumpNumber.JACK, TrumpShape.SPADE));
        Players players = Players.of(List.of(player));

        Dealer dealer = Dealer.of(CardDeck.of());
        dealer.receive(Card.of(TrumpNumber.ACE, TrumpShape.DIAMOND));
        dealer.receive(Card.of(TrumpNumber.JACK, TrumpShape.SPADE));

        // when
        players.judgeBlackjack(dealer);

        // then
        Assertions.assertAll(() -> {
            assertThat(player.getTotalWinnings()).isEqualTo(0);
            assertThat(dealer.getTotalWinnings()).isEqualTo(0);
        });
    }

    @Test
    void 카드_결과로_최종_손익을_계산한다() {
        // given
        // 20
        Player player1 = Player.of("player1", Money.of(1000));
        player1.receive(Card.of(TrumpNumber.ACE, TrumpShape.SPADE));
        player1.receive(Card.of(TrumpNumber.NINE, TrumpShape.SPADE));

        // 17
        Player player2 = Player.of("player2", Money.of(2000));
        player2.receive(Card.of(TrumpNumber.ACE, TrumpShape.SPADE));
        player2.receive(Card.of(TrumpNumber.SIX, TrumpShape.SPADE));

        // bust
        Player player3 = Player.of("player3", Money.of(3000));
        player3.receive(Card.of(TrumpNumber.JACK, TrumpShape.SPADE));
        player3.receive(Card.of(TrumpNumber.NINE, TrumpShape.SPADE));
        player3.receive(Card.of(TrumpNumber.QUEEN, TrumpShape.SPADE));

        Players players = Players.of(List.of(player1, player2, player3));

        // 18
        Dealer dealer = Dealer.of(CardDeck.of());
        dealer.receive(Card.of(TrumpNumber.ACE, TrumpShape.DIAMOND));
        dealer.receive(Card.of(TrumpNumber.SEVEN, TrumpShape.SPADE));

        // when
        players.calculateResult(dealer);

        // then
        Assertions.assertAll(() -> {
            assertThat(player1.getTotalWinnings()).isEqualTo(1000);
            assertThat(player2.getTotalWinnings()).isEqualTo(-2000);
            assertThat(player3.getTotalWinnings()).isEqualTo(-3000);
            assertThat(dealer.getTotalWinnings()).isEqualTo(4000);
        });
    }

    @Test
    void 살아있는_모든_플레이어가_베팅_금액을_받는다() {
        // given
        // 20
        Player player1 = Player.of("player1", Money.of(1000));
        player1.receive(Card.of(TrumpNumber.ACE, TrumpShape.SPADE));
        player1.receive(Card.of(TrumpNumber.NINE, TrumpShape.SPADE));

        // 17
        Player player2 = Player.of("player2", Money.of(2000));
        player2.receive(Card.of(TrumpNumber.ACE, TrumpShape.SPADE));
        player2.receive(Card.of(TrumpNumber.SIX, TrumpShape.SPADE));

        // bust
        Player player3 = Player.of("player3", Money.of(3000));
        player3.receive(Card.of(TrumpNumber.JACK, TrumpShape.SPADE));
        player3.receive(Card.of(TrumpNumber.NINE, TrumpShape.SPADE));
        player3.receive(Card.of(TrumpNumber.QUEEN, TrumpShape.SPADE));

        Players players = Players.of(List.of(player1, player2, player3));
        Dealer dealer = Dealer.of(CardDeck.of());

        // when
        players.winAll(dealer);

        // then
        Assertions.assertAll(() -> {
            assertThat(player1.getTotalWinnings()).isEqualTo(1000);
            assertThat(player2.getTotalWinnings()).isEqualTo(2000);
            assertThat(player3.getTotalWinnings()).isEqualTo(0);
        });
    }
}
