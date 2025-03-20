package participant;

import betting.BettingMoney;
import card.Card;
import card.CardNumber;
import card.CardType;
import deck.Deck;
import deck.DeckShuffleStrategy;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultTest {

    private Player player;
    private Dealer dealer;

    @BeforeEach
    void beforeEach() {
        player = new Player(new Nickname("율무"), new BettingMoney(10000));
        dealer = new Dealer();
    }

    @Test
    @DisplayName("둘 다 블랙잭이면 딜러가 이긴다.")
    void both_blackjack() {
        // given
        Deck deck = new Deck(new DeckShuffleStrategy() {
            @Override
            public List<Card> createAllCards() {
                return List.of(
                        new Card(CardType.DIAMOND, CardNumber.ACE),
                        new Card(CardType.DIAMOND, CardNumber.JACK),
                        new Card(CardType.SPADE, CardNumber.ACE),
                        new Card(CardType.CLOVER, CardNumber.TEN)
                );
            }
        });

        player.prepareGame(deck.draw(), deck.draw());
        dealer.prepareGame(deck.draw(), deck.draw());

        // when
        GameResult result = GameResult.judge(dealer, player);

        // then
        Assertions.assertThat(result)
                .isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("둘 다 버스트면 딜러가 이긴다.")
    void both_bust() {
        // given
        Deck deck = new Deck(new DeckShuffleStrategy() {
            @Override
            public List<Card> createAllCards() {
                return List.of(
                        new Card(CardType.DIAMOND, CardNumber.QUEEN),
                        new Card(CardType.DIAMOND, CardNumber.JACK),
                        new Card(CardType.DIAMOND, CardNumber.TEN),
                        new Card(CardType.SPADE, CardNumber.QUEEN),
                        new Card(CardType.CLOVER, CardNumber.TEN),
                        new Card(CardType.SPADE, CardNumber.KING)
                );
            }
        });

        player.prepareGame(deck.draw(), deck.draw());
        player.hit(deck.draw());
        dealer.prepareGame(deck.draw(), deck.draw());
        dealer.hit(deck.draw());

        // when
        GameResult result = GameResult.judge(dealer, player);

        // then
        Assertions.assertThat(result)
                .isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("둘 다 Bust가 아니고, 딜러 점수가 높으면 딜러가 이긴다.")
    void not_bust_everybody_and_dealer_score_higher_then_player() {
        // given
        Deck deck = new Deck(new DeckShuffleStrategy() {
            @Override
            public List<Card> createAllCards() {
                return List.of(
                        new Card(CardType.DIAMOND, CardNumber.QUEEN),
                        new Card(CardType.DIAMOND, CardNumber.TWO),
                        new Card(CardType.SPADE, CardNumber.QUEEN),
                        new Card(CardType.CLOVER, CardNumber.TEN)
                );
            }
        });

        player.prepareGame(deck.draw(), deck.draw());
        dealer.prepareGame(deck.draw(), deck.draw());

        // when
        GameResult result = GameResult.judge(dealer, player);

        // then
        Assertions.assertThat(result)
                .isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("둘 다 Bust가 아니고, 플레이어 점수가 높으면 딜러는 진다.")
    void not_bust_everybody_and_player_score_higher_then_dealer() {
        // given
        Deck deck = new Deck(new DeckShuffleStrategy() {
            @Override
            public List<Card> createAllCards() {
                return List.of(
                        new Card(CardType.DIAMOND, CardNumber.TEN),
                        new Card(CardType.DIAMOND, CardNumber.JACK),
                        new Card(CardType.SPADE, CardNumber.THREE),
                        new Card(CardType.CLOVER, CardNumber.THREE)
                );
            }
        });

        player.prepareGame(deck.draw(), deck.draw());
        dealer.prepareGame(deck.draw(), deck.draw());

        // when
        GameResult result = GameResult.judge(dealer, player);

        // then
        Assertions.assertThat(result)
                .isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("플레이어가 bust면 딜러가 이긴다.")
    void player_is_bust() {
        // given
        Deck deck = new Deck(new DeckShuffleStrategy() {
            @Override
            public List<Card> createAllCards() {
                return List.of(
                        new Card(CardType.DIAMOND, CardNumber.TEN),
                        new Card(CardType.DIAMOND, CardNumber.JACK),
                        new Card(CardType.SPADE, CardNumber.SEVEN),
                        new Card(CardType.SPADE, CardNumber.THREE),
                        new Card(CardType.CLOVER, CardNumber.THREE)
                );
            }
        });

        player.prepareGame(deck.draw(), deck.draw());
        player.hit(deck.draw());
        dealer.prepareGame(deck.draw(), deck.draw());

        // when
        GameResult result = GameResult.judge(dealer, player);

        // then
        Assertions.assertThat(result)
                .isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("딜러와 플레이어 점수가 같으면 비긴다.")
    void player_and_dealer_same_score() {
        // given
        Deck deck = new Deck(new DeckShuffleStrategy() {
            @Override
            public List<Card> createAllCards() {
                return List.of(
                        new Card(CardType.DIAMOND, CardNumber.TEN),
                        new Card(CardType.DIAMOND, CardNumber.SEVEN),
                        new Card(CardType.SPADE, CardNumber.TEN),
                        new Card(CardType.SPADE, CardNumber.SEVEN)
                );
            }
        });

        player.prepareGame(deck.draw(), deck.draw());
        player.stand();
        dealer.prepareGame(deck.draw(), deck.draw());

        // when
        GameResult result = GameResult.judge(dealer, player);

        // then
        Assertions.assertThat(result)
                .isEqualTo(GameResult.DRAW);
    }
}
