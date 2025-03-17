package participant;

import card.Card;
import card.CardNumber;
import card.CardType;
import deck.Deck;
import deck.DeckCreateStrategy;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    private Player player;

    @BeforeEach
    void beforeEach() {
        player = new Player(new Nickname("율무"), new BettingMoney(10000));
    }

    @Test
    @DisplayName("플레이어는 bust면 카드를 받을 수 없다.")
    void test1() {
        // given
        Deck deck = new Deck(new DeckCreateStrategy() {
            @Override
            public List<Card> createAllCards() {
                return List.of(
                    new Card(CardType.DIAMOND, CardNumber.FOUR),
                    new Card(CardType.SPADE, CardNumber.TEN),
                    new Card(CardType.CLOVER, CardNumber.TEN)
                );
            }
        });
        player.prepareGame(deck.draw(), deck.draw());
        player.hit(deck.draw());

        // when
        // then
        Assertions.assertThat(player.isFinished())
                .isTrue();
    }

    @Test
    @DisplayName("플레이어는 bust가 아니면 카드를 받을 수 있다.")
    void test2() {
        // given
        Deck deck = new Deck(new DeckCreateStrategy() {
            @Override
            public List<Card> createAllCards() {
                return List.of(
                        new Card(CardType.SPADE, CardNumber.TEN),
                        new Card(CardType.CLOVER, CardNumber.TEN),
                        new Card(CardType.DIAMOND, CardNumber.ACE)
                );
            }
        });
        player.prepareGame(deck.draw(), deck.draw());

        // when
        boolean result = player.isFinished();

        // then
        Assertions.assertThat(result)
                .isFalse();
    }

    @Test
    @DisplayName("플레이어가 우승하면 배팅 금액만큼 수익을 얻는다.")
    void player_win() {
        // given
        Player player = new Player(new Nickname("율무"), new BettingMoney(20000));
        player.prepareGame(
                new Card(CardType.SPADE, CardNumber.TWO),
                new Card(CardType.CLOVER, CardNumber.THREE)
        );
        player.stand();

        // when
        int profit = player.calculateProfit(GameResult.LOSE);

        // then
        Assertions.assertThat(profit)
                .isEqualTo(20000);
    }

    @Test
    @DisplayName("플레이어가 패배하면 배팅 금액만큼 수익을 잃는다.")
    void player_lose() {
        // given
        Player player = new Player(new Nickname("율무"), new BettingMoney(20000));
        player.prepareGame(
                new Card(CardType.SPADE, CardNumber.TWO),
                new Card(CardType.CLOVER, CardNumber.THREE)
        );
        player.stand();

        // when
        int profit = player.calculateProfit(GameResult.WIN);

        // then
        Assertions.assertThat(profit)
                .isEqualTo(-20000);
    }

    @Test
    @DisplayName("플레이어와 딜러가 무승부면 수익은 0원이다.")
    void player_draw() {
        // given
        Player player = new Player(new Nickname("율무"), new BettingMoney(20000));
        player.prepareGame(
                new Card(CardType.SPADE, CardNumber.TWO),
                new Card(CardType.CLOVER, CardNumber.THREE)
        );
        player.stand();

        // when
        int profit = player.calculateProfit(GameResult.DRAW);

        // then
        Assertions.assertThat(profit)
                .isEqualTo(0);
    }

    @Test
    @DisplayName("플레이어가 블랙잭으로 이기면 배팅 금액의 1.5배를 수익으로 얻는다.")
    void player_blackjack() {
        // given
        Player player = new Player(new Nickname("율무"), new BettingMoney(20000));
        player.prepareGame(
                new Card(CardType.SPADE, CardNumber.ACE),
                new Card(CardType.CLOVER, CardNumber.TEN)
        );

        // when
        int profit = player.calculateProfit(GameResult.LOSE);

        // then
        Assertions.assertThat(profit)
                .isEqualTo(30000);
    }
}
