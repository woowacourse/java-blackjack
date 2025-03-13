package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.TrumpNumber;
import domain.card.TrumpShape;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GameManagerTest {

    @Test
    void 게임_메니저를_생성한다() {
        // given
        CardDeck cardDeck = CardDeck.of();
        Dealer dealer = Dealer.of(cardDeck);
        Players players = Players.of(
                List.of(
                        Player.of("pobi1", Money.of(1000)),
                        Player.of("pobi2", Money.of(1000)),
                        Player.of("pobi3", Money.of(1000))
                )
        );

        // when & then
        assertThatCode(() -> GameManager.of(dealer, players))
                .doesNotThrowAnyException();
    }

    @Test
    void 게임을_시작하고_카드를_두_장씩_배부한다() {
        // given
        CardDeck cardDeck = CardDeck.of();
        Dealer dealer = Dealer.of(cardDeck);
        Players players = Players.of(
                List.of(
                        Player.of("pobi1", Money.of(1000)),
                        Player.of("pobi2", Money.of(1000)),
                        Player.of("pobi3", Money.of(1000))
                )
        );
        GameManager gameManager = GameManager.of(dealer, players);

        // when
        gameManager.distributeCards();

        // then
        assertThat(cardDeck.getCards()).hasSize(44);
    }

    @Test
    void 참가자들의_이름_목록을_가져온다() {
        // given
        CardDeck cardDeck = CardDeck.of();
        Dealer dealer = Dealer.of(cardDeck);
        Players players = Players.of(
                List.of(
                        Player.of("pobi1", Money.of(1000)),
                        Player.of("pobi2", Money.of(1000)),
                        Player.of("pobi3", Money.of(1000))
                )
        );
        GameManager gameManager = GameManager.of(dealer, players);

        // when
        List<String> names = gameManager.getPlayersName();

        // then
        assertThat(names).hasSize(3).contains("pobi1", "pobi2", "pobi3");
    }

    @Test
    void 이름을_받아_해당_플레이어에게_카드를_준다() {
        // given
        CardDeck cardDeck = CardDeck.of();
        Player target = Player.of("pobi1", Money.of(1000));
        Dealer dealer = Dealer.of(cardDeck);
        Players players = Players.of(
                List.of(
                        target,
                        Player.of("pobi2", Money.of(1000)),
                        Player.of("pobi3", Money.of(1000))
                )
        );
        GameManager gameManager = GameManager.of(dealer, players);

        // when
        gameManager.passCardToPlayer("pobi1");

        // then
        assertThat(target.getOwnedCards()).hasSize(1);
    }

    @Test
    void 플레이어의_현재_카드_점수를_계산한다() {
        // given
        CardDeck cardDeck = CardDeck.of();
        Player target = Player.of("pobi1", Money.of(1000));
        target.receive(Card.of(TrumpNumber.NINE, TrumpShape.CLUB));

        Dealer dealer = Dealer.of(cardDeck);
        Players players = Players.of(
                List.of(
                        target,
                        Player.of("pobi2", Money.of(1000)),
                        Player.of("pobi3", Money.of(1000))
                )
        );
        GameManager gameManager = GameManager.of(dealer, players);

        // when
        int score = gameManager.getScoreOf("pobi1");

        // then
        assertThat(score).isEqualTo(9);
    }

    @Test
    void 딜러의_카드_합이_16_이하면_카드를_한_장_받고_true를_반환한다() {
        // given
        CardDeck cardDeck = CardDeck.of();
        Dealer dealer = Dealer.of(cardDeck);
        dealer.receive(Card.of(TrumpNumber.ACE, TrumpShape.CLUB));
        dealer.receive(Card.of(TrumpNumber.FIVE, TrumpShape.CLUB));
        Players players = Players.of(
                List.of(
                        Player.of("pobi1", Money.of(1000)),
                        Player.of("pobi2", Money.of(1000)),
                        Player.of("pobi3", Money.of(1000))
                )
        );
        GameManager gameManager = GameManager.of(dealer, players);

        // when
        boolean result = gameManager.passCardToDealer();

        // then
        assertThat(result).isTrue();
    }

    @Test
    void 딜러의_카드_합이_16_초과면_카드를_받지_않고_false를_반환한다() {
        // given
        CardDeck cardDeck = CardDeck.of();
        Dealer dealer = Dealer.of(cardDeck);
        dealer.receive(Card.of(TrumpNumber.ACE, TrumpShape.CLUB));
        dealer.receive(Card.of(TrumpNumber.SEVEN, TrumpShape.CLUB));
        Players players = Players.of(
                List.of(
                        Player.of("pobi1", Money.of(1000)),
                        Player.of("pobi2", Money.of(1000)),
                        Player.of("pobi3", Money.of(1000))
                )
        );
        GameManager gameManager = GameManager.of(dealer, players);

        // when
        boolean result = gameManager.passCardToDealer();

        // then
        assertThat(result).isFalse();
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

        GameManager gameManager = GameManager.of(dealer, players);

        // when
        gameManager.calculateResult();

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
        GameManager gameManager = GameManager.of(dealer, players);

        // when
        gameManager.winAllPlayers();

        // then
        Assertions.assertAll(() -> {
            assertThat(player1.getTotalWinnings()).isEqualTo(1000);
            assertThat(player2.getTotalWinnings()).isEqualTo(2000);
            assertThat(player3.getTotalWinnings()).isEqualTo(0);
            assertThat(dealer.getTotalWinnings()).isEqualTo(-3000);
        });
    }
}
