package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.entry;

import domain.batting.Bet;
import domain.batting.BettingPool;
import domain.card.Card;
import domain.card.CardDeck;
import domain.card.CardDeckGenerator;
import domain.card.TrumpNumber;
import domain.card.TrumpShape;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    @Test
    void 게임_메니저를_생성한다() {
        // given
        CardDeck cardDeck = CardDeck.of(CardDeckGenerator.generateCardDeck());
        Dealer dealer = Dealer.of();
        Players players = Players.of(
                List.of(
                        Player.of("pobi1"),
                        Player.of("pobi2"),
                        Player.of("pobi3")
                )
        );

        // when & then
        assertThatCode(() -> BlackjackGame.of(cardDeck, dealer, players))
                .doesNotThrowAnyException();
    }

    @Test
    void 게임을_시작하고_카드를_두_장씩_배부한다() {
        // given
        CardDeck cardDeck = CardDeck.of(CardDeckGenerator.generateCardDeck());
        Dealer dealer = Dealer.of();
        Players players = Players.of(
                List.of(
                        Player.of("pobi1"),
                        Player.of("pobi2"),
                        Player.of("pobi3")
                )
        );
        BlackjackGame blackjackGame = BlackjackGame.of(cardDeck, dealer, players);

        // when
        blackjackGame.distributeCards();

        // then
        assertThat(cardDeck.getCards()).hasSize(44);
    }

    @Test
    void 참가자들의_이름_목록을_가져온다() {
        // given
        CardDeck cardDeck = CardDeck.of(CardDeckGenerator.generateCardDeck());
        Dealer dealer = Dealer.of();
        Players players = Players.of(
                List.of(
                        Player.of("pobi1"),
                        Player.of("pobi2"),
                        Player.of("pobi3")
                )
        );
        BlackjackGame blackjackGame = BlackjackGame.of(cardDeck, dealer, players);

        // when
        List<String> names = blackjackGame.getPlayersName();

        // then
        assertThat(names).hasSize(3).contains("pobi1", "pobi2", "pobi3");
    }

    @Test
    void 이름을_받아_해당_플레이어에게_카드를_준다() {
        // given
        CardDeck cardDeck = CardDeck.of(CardDeckGenerator.generateCardDeck());
        Player target = Player.of("pobi1");
        Dealer dealer = Dealer.of();
        Players players = Players.of(
                List.of(
                        target,
                        Player.of("pobi2"),
                        Player.of("pobi3")
                )
        );
        BlackjackGame blackjackGame = BlackjackGame.of(cardDeck, dealer, players);

        // when
        blackjackGame.passCardToPlayer("pobi1");

        // then
        assertThat(target.getOwnedCards()).hasSize(1);
    }

    @Test
    void 딜러의_카드_합이_16_이하면_카드를_한_장_받고_true를_반환한다() {
        // given
        List<Card> cards = List.of(
                Card.of(TrumpNumber.ACE, TrumpShape.CLUB),
                Card.of(TrumpNumber.FIVE, TrumpShape.CLUB),
                Card.of(TrumpNumber.FIVE, TrumpShape.CLUB)
        );
        CardDeck cardDeck = CardDeck.of(cards);
        Dealer dealer = Dealer.of();
        dealer.receive(cardDeck.popCard());
        dealer.receive(cardDeck.popCard());
        Players players = Players.of(
                List.of(
                        Player.of("pobi1"),
                        Player.of("pobi2"),
                        Player.of("pobi3")
                )
        );
        BlackjackGame blackjackGame = BlackjackGame.of(cardDeck, dealer, players);

        // when
        boolean result = blackjackGame.passCardToDealer();

        // then
        assertThat(result).isTrue();
    }

    @Test
    void 딜러의_카드_합이_16_초과면_카드를_받지_않고_false를_반환한다() {
        // given
        List<Card> cards = List.of(
                Card.of(TrumpNumber.ACE, TrumpShape.CLUB),
                Card.of(TrumpNumber.SEVEN, TrumpShape.CLUB)
        );
        CardDeck cardDeck = CardDeck.of(cards);
        Dealer dealer = Dealer.of();
        dealer.receive(cardDeck.popCard());
        dealer.receive(cardDeck.popCard());
        Players players = Players.of(
                List.of(
                        Player.of("pobi1"),
                        Player.of("pobi2"),
                        Player.of("pobi3")
                )
        );
        BlackjackGame blackjackGame = BlackjackGame.of(cardDeck, dealer, players);

        // when
        boolean result = blackjackGame.passCardToDealer();

        // then
        assertThat(result).isFalse();
    }

    @Test
    void 배팅_풀을_통해_플레이어_승리의_이익을_반환한다() {
        // given
        Player player = Player.of("pobi");
        Dealer dealer = Dealer.of();

        BettingPool bettingPool = BettingPool.of();
        bettingPool.wager(player, Bet.of(10000));

        dealer.receive(Card.of(TrumpNumber.TWO, TrumpShape.HEART));
        player.receive(Card.of(TrumpNumber.THREE, TrumpShape.HEART));

        BlackjackGame blackjackGame = BlackjackGame.of(CardDeck.of(List.of()), dealer, Players.of(List.of(player)));

        // when
        Map<Player, Integer> profits = blackjackGame.calculatePlayerProfit(bettingPool);

        // then
        assertThat(profits).hasSize(1)
                .contains(entry(Player.of("pobi"), 10000));
    }

    @Test
    void 배팅_풀을_통해_플레이어_무승부의_이익을_반환한다() {
        // given
        Player player = Player.of("pobi");
        Dealer dealer = Dealer.of();

        BettingPool bettingPool = BettingPool.of();
        bettingPool.wager(player, Bet.of(10000));

        dealer.receive(Card.of(TrumpNumber.TWO, TrumpShape.HEART));
        player.receive(Card.of(TrumpNumber.TWO, TrumpShape.HEART));

        BlackjackGame blackjackGame = BlackjackGame.of(CardDeck.of(List.of()), dealer, Players.of(List.of(player)));

        // when
        Map<Player, Integer> profits = blackjackGame.calculatePlayerProfit(bettingPool);

        // then
        assertThat(profits).hasSize(1)
                .contains(entry(Player.of("pobi"), 0));
    }

    @Test
    void 배팅_풀을_통해_플레이어_패배의_이익을_반환한다() {
        // given
        Player player = Player.of("pobi");
        Dealer dealer = Dealer.of();

        BettingPool bettingPool = BettingPool.of();
        bettingPool.wager(player, Bet.of(10000));

        dealer.receive(Card.of(TrumpNumber.THREE, TrumpShape.HEART));
        player.receive(Card.of(TrumpNumber.TWO, TrumpShape.HEART));

        BlackjackGame blackjackGame = BlackjackGame.of(CardDeck.of(List.of()), dealer, Players.of(List.of(player)));

        // when
        Map<Player, Integer> profits = blackjackGame.calculatePlayerProfit(bettingPool);

        // then
        assertThat(profits).hasSize(1)
                .contains(entry(Player.of("pobi"), -10000));
    }
}
