package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.enums.CardRank;
import domain.enums.CardShape;
import domain.enums.MatchCase;
import testutil.PlayerTestUtil;
import utils.generator.CardsGenerator;

class GameTest {
    private static final String DEALER = "딜러";
    private static final String PLAYER_NAME_1 = "아티";
    private static final String PLAYER_NAME_2 = "요크";
    private Deck deck;

    @BeforeEach
    void setUp() {
        CardsGenerator cardsGenerator = new PlayerTestUtil.FakeShuffledCardsGenerator();
        this.deck = new Deck(cardsGenerator);
    }

    @Test
    @DisplayName("참가자가 딜러보다 낮은 점수면 딜러가 이긴다")
    void 참가자가_딜러보다_점수_낮음() {
        Player player = PlayerTestUtil.createPlayer(
                PLAYER_NAME_1,
                List.of(
                        new Card(CardShape.SPADE, CardRank.TEN),
                        new Card(CardShape.SPADE, CardRank.JACK)
                )); // 20

        Player dealer = PlayerTestUtil.createPlayer(
                DEALER,
                List.of(
                        new Card(CardShape.SPADE, CardRank.TEN),
                        new Card(CardShape.SPADE, CardRank.JACK),
                        new Card(CardShape.SPADE, CardRank.ACE)
                )); // 21

        Players players = new Players(List.of(player));
        Game game = new Game(deck, players, dealer);

        Map<String, MatchCase> result = game.calculateMatch();
        assertThat(result.get(PLAYER_NAME_1)).isEqualTo(MatchCase.LOSE);
    }


    @Test
    @DisplayName("참가자가 딜러보다 높은 점수면 딜러가 진다")
    void 참가자가_딜러보다_점수_높음() {
        Player player = PlayerTestUtil.createPlayer(
                PLAYER_NAME_1,
                List.of(
                        new Card(CardShape.SPADE, CardRank.TEN),
                        new Card(CardShape.SPADE, CardRank.ACE)
                )); // 21

        Player dealer = PlayerTestUtil.createPlayer(
                DEALER,
                List.of(
                        new Card(CardShape.SPADE, CardRank.TEN),
                        new Card(CardShape.SPADE, CardRank.JACK)
                )); // 20

        Players players = new Players(List.of(player));
        Game game = new Game(deck, players, dealer);
        Map<String, MatchCase> result = game.calculateMatch();

        assertThat(player.getCardsTotalSum()).isEqualTo(21);
        assertThat(dealer.getCardsTotalSum()).isEqualTo(20);
        assertThat(result.get(PLAYER_NAME_1)).isEqualTo(MatchCase.WIN);
    }

    @DisplayName("참가자와 딜러 점수가 둘 다 같으면 무승부")
    @Test
    void 참가자_딜러_무승부() {
        Player player = PlayerTestUtil.createPlayer(
                PLAYER_NAME_1,
                List.of(
                        new Card(CardShape.SPADE, CardRank.TEN),
                        new Card(CardShape.SPADE, CardRank.ACE)
                )); // 21

        Player dealer = PlayerTestUtil.createPlayer(
                DEALER,
                List.of(
                        new Card(CardShape.SPADE, CardRank.TEN),
                        new Card(CardShape.SPADE, CardRank.ACE)
                )); // 21

        Players players = new Players(List.of(player));
        Game game = new Game(deck, players, dealer);
        Map<String, MatchCase> result = game.calculateMatch();


        assertThat(player.getCardsTotalSum()).isEqualTo(21);
        assertThat(dealer.getCardsTotalSum()).isEqualTo(21);
        assertThat(result.get(PLAYER_NAME_1)).isEqualTo(MatchCase.DRAW);
    }

    @DisplayName("참가자가 버스트고 딜러가 살면 딜러 승")
    @Test
    void 참가자_버스트_딜러_생존() {
        Player player = PlayerTestUtil.createPlayer(
                PLAYER_NAME_1,
                List.of(
                        new Card(CardShape.SPADE, CardRank.TEN),
                        new Card(CardShape.SPADE, CardRank.QUEEN),
                        new Card(CardShape.SPADE, CardRank.TWO)
                )); // 22

        Player dealer = PlayerTestUtil.createPlayer(
                DEALER,
                List.of(
                        new Card(CardShape.SPADE, CardRank.TEN),
                        new Card(CardShape.SPADE, CardRank.ACE)
                )); // 21

        Players players = new Players(List.of(player));
        Game game = new Game(deck, players, dealer);
        Map<String, MatchCase> result = game.calculateMatch();


        assertThat(player.getCardsTotalSum()).isEqualTo(22);
        assertThat(dealer.getCardsTotalSum()).isEqualTo(21);
        assertThat(result.get(PLAYER_NAME_1)).isEqualTo(MatchCase.LOSE);
    }

    @DisplayName("참가자가 생존하고 딜러가 버스트면 딜러 패")
    @Test
    void 참가자_생존_딜러_버스트() {
        Player player = PlayerTestUtil.createPlayer(
                PLAYER_NAME_1,
                List.of(
                        new Card(CardShape.SPADE, CardRank.TWO),
                        new Card(CardShape.SPADE, CardRank.TEN)
                )); // 12

        Player dealer = PlayerTestUtil.createPlayer(
                DEALER,
                List.of(
                        new Card(CardShape.SPADE, CardRank.TEN),
                        new Card(CardShape.SPADE, CardRank.NINE),
                        new Card(CardShape.SPADE, CardRank.TEN)
                )); // 29

        Players players = new Players(List.of(player));
        Game game = new Game(deck, players, dealer);
        Map<String, MatchCase> result = game.calculateMatch();


        assertThat(player.getCardsTotalSum()).isEqualTo(12);
        assertThat(dealer.getCardsTotalSum()).isEqualTo(29);
        assertThat(result.get(PLAYER_NAME_1)).isEqualTo(MatchCase.WIN);
    }

    //
    @DisplayName("생존한 참가자 있고 딜러 버스트면 딜러 승리, 패배 하나씩")
    @Test
    void 생존_참가자_존재_및_딜러_버스트() {
        Players players = new Players(
                List.of(PlayerTestUtil.createPlayer(
                                PLAYER_NAME_1,
                                List.of(
                                        new Card(CardShape.SPADE, CardRank.NINE),
                                        new Card(CardShape.SPADE, CardRank.QUEEN)
                                )), // 19
                        PlayerTestUtil.createPlayer(
                                PLAYER_NAME_2,
                                List.of(
                                        new Card(CardShape.SPADE, CardRank.NINE),
                                        new Card(CardShape.SPADE, CardRank.QUEEN),
                                        new Card(CardShape.SPADE, CardRank.THREE)
                                ))) // 22
        );

        Player dealer = PlayerTestUtil.createPlayer(
                DEALER,
                List.of(
                        new Card(CardShape.SPADE, CardRank.TEN),
                        new Card(CardShape.SPADE, CardRank.NINE),
                        new Card(CardShape.SPADE, CardRank.TEN)
                )); // 29

        Game game = new Game(deck, players, dealer);
        Map<String, MatchCase> result = game.calculateMatch();

        assertThat(dealer.getCardsTotalSum()).isEqualTo(29);
        assertThat(result.get(PLAYER_NAME_1)).isEqualTo(MatchCase.WIN);
        assertThat(result.get(PLAYER_NAME_2)).isEqualTo(MatchCase.LOSE);
    }


    @DisplayName("참가자가 bust되면 배팅금액이 마이너스가 된다.")
    @Test
    void 참가자_bust_배팅금액_마이너스() {
        Player player = PlayerTestUtil.createPlayer(
                PLAYER_NAME_1,
                List.of(
                new Card(CardShape.SPADE, CardRank.TEN),
                new Card(CardShape.SPADE, CardRank.NINE),
                new Card(CardShape.SPADE, CardRank.EIGHT)
        )); // 27
        player.betMoney(10000);

        Player dealer = PlayerTestUtil.createPlayer(
                DEALER,
                List.of(
                        new Card(CardShape.SPADE, CardRank.TEN),
                        new Card(CardShape.SPADE, CardRank.NINE)
                )); // 19

        Players players = new Players(List.of(player));
        Game game = new Game(deck, players, dealer);
        game.calculateMatch();

        assertThat(player.getBettingScore()).isEqualTo(-10000);
//        assertThat(dealer.getBettingScore()).isEqualTo(10000);
    }

    @DisplayName("참가자가 블랙잭이 되면 배팅금액이 1.5배가 된다.")
    @Test
    void 참가자_블랙잭_배팅금액_보너스() {
        Player player = PlayerTestUtil.createPlayer(
                PLAYER_NAME_1,
                List.of(
                        new Card(CardShape.SPADE, CardRank.TEN),
                        new Card(CardShape.SPADE, CardRank.ACE)
                )); // 21
        player.betMoney(10000);

        Player dealer = PlayerTestUtil.createPlayer(
                DEALER,
                List.of(
                        new Card(CardShape.SPADE, CardRank.TEN),
                        new Card(CardShape.SPADE, CardRank.NINE),
                        new Card(CardShape.SPADE, CardRank.TEN)
                )); // 29

        Players players = new Players(List.of(player));
        Game game = new Game(deck, players, dealer);
        game.calculateMatch();

        assertThat(player.getBettingScore()).isEqualTo(15000);
//        assertThat(dealer.getBettingScore()).isEqualTo(-15000);
    }

    @DisplayName("딜러와 참가자 모두 블랙잭이면 배팅한 금액을 돌려받는다..")
    @Test
    void 딜러_참가자_블랙잭_배팅금액_반환() {
        Player player = PlayerTestUtil.createPlayer(
                PLAYER_NAME_1,
                List.of(
                        new Card(CardShape.SPADE, CardRank.TEN),
                        new Card(CardShape.SPADE, CardRank.ACE)
                )); // 21
        player.betMoney(10000);

        Player dealer = PlayerTestUtil.createPlayer(
                DEALER,
                List.of(
                        new Card(CardShape.SPADE, CardRank.TEN),
                        new Card(CardShape.SPADE, CardRank.ACE)
                )); // 21

        Players players = new Players(List.of(player));
        Game game = new Game(deck, players, dealer);
        game.calculateMatch();

        assertThat(player.getBettingScore()).isEqualTo(0);
        assertThat(dealer.getBettingScore()).isEqualTo(0);
    }

}