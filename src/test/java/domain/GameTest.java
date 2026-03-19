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

    private Deck deck;

    @BeforeEach
    void setUp() {
        CardsGenerator cardsGenerator = new PlayerTestUtil.FakeShuffledCardsGenerator();
        this.deck = new Deck(cardsGenerator);
    }

    @DisplayName("딜러가 받은 카드의 점수 합이 16이하이면 추가로 받는다.")
    @Test
    void 딜러_카드_추가_배부_필요_정상_테스트() {
        Players players = PlayerTestUtil.createPlayers();
        Deck deck = PlayerTestUtil.createDeck();
        Dealer dealer = PlayerTestUtil.createDealer(
                List.of(
                        new Card(CardShape.SPADE, CardRank.TWO),
                        new Card(CardShape.SPADE, CardRank.JACK)
                )); // 12
        Game game = new Game(deck, players, dealer);

        boolean isDealerNeedAdditionalCard = game.needAdditionalCard();

        assertThat(isDealerNeedAdditionalCard).isTrue();
    }

    @DisplayName("딜러가 받은 카드의 점수 합이 17 이상이면 추가로 받지 않는다.")
    @Test
    void 딜러_카드_추가_배부_불필요_정상_테스트() {
        Players players = PlayerTestUtil.createPlayers();
        Deck deck = PlayerTestUtil.createDeck();
        Dealer dealer = PlayerTestUtil.createDealer(
                List.of(
                        new Card(CardShape.SPADE, CardRank.QUEEN),
                        new Card(CardShape.SPADE, CardRank.JACK)
                )); // 20
        Game game = new Game(deck, players, dealer);

        boolean isDealerNeedAdditionalCard = game.needAdditionalCard();

        assertThat(isDealerNeedAdditionalCard).isFalse();
    }

    @Test
    @DisplayName("참가자가 딜러보다 낮은 점수면 딜러가 이긴다")
    void 참가자가_딜러보다_점수_낮음() {
        Player player = PlayerTestUtil.createNonBurstPlayer(10000);
        Dealer dealer = PlayerTestUtil.createBlackjackDealer();

        Players players = new Players(List.of(player));
        Game game = new Game(deck, players, dealer);

        Map<String, MatchCase> result = game.calculateMatch();
        assertThat(result.get(player.getName())).isEqualTo(MatchCase.LOSE);
    }


    @Test
    @DisplayName("참가자가 딜러보다 높은 점수면 딜러가 진다")
    void 참가자가_딜러보다_점수_높음() {
        Player player = PlayerTestUtil.createBlackjackPlayer(10000);

        Dealer dealer = PlayerTestUtil.createNonBurstDealer();

        Players players = new Players(List.of(player));
        Game game = new Game(deck, players, dealer);
        Map<String, MatchCase> result = game.calculateMatch();

        assertThat(player.getCardsTotalSum()).isEqualTo(21);
        assertThat(dealer.getCardsTotalSum()).isEqualTo(20);
        assertThat(result.get(player.getName())).isEqualTo(MatchCase.WIN);
    }

    @DisplayName("참가자와 딜러 점수가 둘 다 같으면 무승부")
    @Test
    void 참가자_딜러_무승부() {
        Player player = PlayerTestUtil.createNonBurstPlayer(10000);
        Dealer dealer = PlayerTestUtil.createNonBurstDealer();

        Players players = new Players(List.of(player));
        Game game = new Game(deck, players, dealer);
        Map<String, MatchCase> result = game.calculateMatch();


        assertThat(player.getCardsTotalSum()).isEqualTo(20);
        assertThat(dealer.getCardsTotalSum()).isEqualTo(20);
        assertThat(result.get(player.getName())).isEqualTo(MatchCase.DRAW);
    }

    @DisplayName("참가자가 버스트고 딜러가 살면 딜러 승")
    @Test
    void 참가자_버스트_딜러_생존() {
        Player player = PlayerTestUtil.createBurstPlayer(10000);
        Dealer dealer = PlayerTestUtil.createNonBurstDealer();

        Players players = new Players(List.of(player));
        Game game = new Game(deck, players, dealer);
        Map<String, MatchCase> result = game.calculateMatch();


        assertThat(player.getCardsTotalSum()).isEqualTo(29);
        assertThat(dealer.getCardsTotalSum()).isEqualTo(20);
        assertThat(result.get(player.getName())).isEqualTo(MatchCase.LOSE);
    }

    @DisplayName("참가자가 생존하고 딜러가 버스트면 딜러 패")
    @Test
    void 참가자_생존_딜러_버스트() {
        Player player = PlayerTestUtil.createNonBurstPlayer(10000);
        Dealer dealer = PlayerTestUtil.createBurstDealer();

        Players players = new Players(List.of(player));
        Game game = new Game(deck, players, dealer);
        Map<String, MatchCase> result = game.calculateMatch();


        assertThat(player.getCardsTotalSum()).isEqualTo(20);
        assertThat(dealer.getCardsTotalSum()).isEqualTo(29);
        assertThat(result.get(player.getName())).isEqualTo(MatchCase.WIN);
    }

    //
    @DisplayName("생존한 참가자 있고 딜러 버스트면 딜러 승리, 패배 하나씩")
    @Test
    void 생존_참가자_존재_및_딜러_버스트() {
        Player player1 = PlayerTestUtil.createNonBurstPlayer(10000);
        Player player2 = PlayerTestUtil.createBurstPlayer(10000);


        Players players = new Players(
                List.of(player1, player2)
        );

        Dealer dealer = PlayerTestUtil.createBurstDealer();

        Game game = new Game(deck, players, dealer);
        Map<String, MatchCase> result = game.calculateMatch();

        assertThat(dealer.getCardsTotalSum()).isEqualTo(29);
        assertThat(result.get(player1.getName())).isEqualTo(MatchCase.WIN);
        assertThat(result.get(player2.getName())).isEqualTo(MatchCase.LOSE);
    }


    @DisplayName("참가자가 bust되면 배팅금액이 마이너스가 된다.")
    @Test
    void 참가자_bust_배팅금액_마이너스() {
        Player player = PlayerTestUtil.createBurstPlayer(10000);

        Dealer dealer = PlayerTestUtil.createNonBurstDealer();

        Players players = new Players(List.of(player));
        Game game = new Game(deck, players, dealer);
        game.calculateMatch();

        assertThat(player.getBettingScore()).isEqualTo(-10000);
//        assertThat(dealer.getBettingScore()).isEqualTo(10000);
    }

    @DisplayName("참가자가 블랙잭이 되면 배팅금액이 1.5배가 된다.")
    @Test
    void 참가자_블랙잭_배팅금액_보너스() {
        Player player = PlayerTestUtil.createBlackjackPlayer(10000);

        Dealer dealer = PlayerTestUtil.createNonBurstDealer();

        Players players = new Players(List.of(player));
        Game game = new Game(deck, players, dealer);
        game.calculateMatch();

        assertThat(player.getBettingScore()).isEqualTo(15000);
//        assertThat(dealer.getBettingScore()).isEqualTo(-15000);
    }

    @DisplayName("딜러와 참가자 모두 블랙잭이면 배팅한 금액을 돌려받는다..")
    @Test
    void 딜러_참가자_블랙잭_배팅금액_반환() {
        Player player = PlayerTestUtil.createBlackjackPlayer(10000);
        Dealer dealer = PlayerTestUtil.createBlackjackDealer();

        Players players = new Players(List.of(player));
        Game game = new Game(deck, players, dealer);
        game.calculateMatch();

        assertThat(player.getBettingScore()).isEqualTo(0);
        assertThat(game.getDealerBettingScore()).isEqualTo(0);
    }

}