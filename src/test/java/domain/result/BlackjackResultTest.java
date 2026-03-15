package domain.result;

import domain.card.Card;
import domain.card.CardRank;
import domain.card.CardShape;
import domain.dto.BlackjackResultDto;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import testutil.PlayerTestUtil;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BlackjackResultTest {
    private BlackjackResultDto createResultDto(Dealer dealer, Player player) {
        return createResultDto(dealer, PlayerTestUtil.createSinglePlayerSet(player));
    }

    private BlackjackResultDto createResultDto(Dealer dealer, Players players) {
        BlackjackResult blackjackResult = BlackjackResult.from(dealer, players);
        ProfitResult profitResult = ProfitResult.from(blackjackResult);
        return BlackjackResultDto.from(profitResult);
    }

    
    @Test
    @DisplayName("참가자가 딜러보다 낮은 점수면 딜러가 이긴다")
    void 참가자가_딜러보다_점수_낮음() {
        // given

        Player player = PlayerTestUtil.createPlayer(List.of(
                new Card(CardShape.SPADE, CardRank.TEN),
                new Card(CardShape.SPADE, CardRank.JACK)
        )); // 20

        Dealer dealer = PlayerTestUtil.createDealer(List.of(
                new Card(CardShape.SPADE, CardRank.TEN),
                new Card(CardShape.SPADE, CardRank.JACK),
                new Card(CardShape.SPADE, CardRank.ACE)
        )); // 21

        // when
        BlackjackResultDto blackjackResultDto = createResultDto(dealer, player);

        // then
        long expected = PlayerTestUtil.DEFAULT_BET_AMOUNT;
        assertThat(blackjackResultDto.dealerBenefit()).isEqualTo(expected);
        assertThat(blackjackResultDto.playerProfitMap().get(player.getName())).isEqualTo(-expected);
    }


    @Test
    @DisplayName("참가자가 딜러보다 높은 점수면 딜러가 진다")
    void 참가자가_딜러보다_점수_높음() {
        // given
        Player player = PlayerTestUtil.createPlayer(List.of(
                new Card(CardShape.SPADE, CardRank.TEN),
                new Card(CardShape.SPADE, CardRank.TEN),
                new Card(CardShape.SPADE, CardRank.ACE)
        )); // 21

        Dealer dealer = PlayerTestUtil.createDealer(List.of(
                new Card(CardShape.SPADE, CardRank.TEN),
                new Card(CardShape.SPADE, CardRank.JACK)
        )); // 20

        // when
        BlackjackResultDto blackjackResultDto = createResultDto(dealer, player);

        // then
        long expected = PlayerTestUtil.DEFAULT_BET_AMOUNT;
        assertThat(blackjackResultDto.dealerBenefit()).isEqualTo(-expected);
        assertThat(blackjackResultDto.playerProfitMap().get(player.getName())).isEqualTo(expected);
    }

    @Test
    @DisplayName("참가자가 블랙잭이고 높은 점수면 딜러가 지고 베팅금의 1.5배를 참가자가 받음")
    void 참가자_블랙잭_딜러보다_점수_높음() {
        // given
        Player player = PlayerTestUtil.createBlackjackPlayer();

        Dealer dealer = PlayerTestUtil.createDealer(List.of(
                new Card(CardShape.SPADE, CardRank.TEN),
                new Card(CardShape.SPADE, CardRank.JACK)
        )); // 20

        // when
        BlackjackResultDto blackjackResultDto = createResultDto(dealer, player);

        // then
        long expected = (long) (PlayerTestUtil.DEFAULT_BET_AMOUNT * 1.5);
        assertThat(blackjackResultDto.dealerBenefit()).isEqualTo(-expected);
        assertThat(blackjackResultDto.playerProfitMap().get(player.getName())).isEqualTo(expected);
    }

    @Test
    @DisplayName("참가자와 딜러가 둘 다 블랙잭이면 수익 0")
    void 참가자_딜러_블랙잭() {
        // given
        Player player = PlayerTestUtil.createBlackjackPlayer();

        Dealer dealer = PlayerTestUtil.createBlackjackDealer(); // 20

        // when
        BlackjackResultDto blackjackResultDto = createResultDto(dealer, player);

        // then
        final int noBenefit = 0;
        assertThat(blackjackResultDto.dealerBenefit()).isEqualTo(noBenefit);
        assertThat(blackjackResultDto.playerProfitMap().get(player.getName())).isEqualTo(noBenefit);
    }

    @DisplayName("참가자와 딜러 점수가 둘 다 같으면 무승부")
    @Test
    void 참가자_딜러_무승부() {
        // given
        Player player = PlayerTestUtil.createPlayer(List.of(
                new Card(CardShape.SPADE, CardRank.TEN),
                new Card(CardShape.SPADE, CardRank.ACE)
        )); // 21

        Dealer dealer = PlayerTestUtil.createDealer(List.of(
                new Card(CardShape.SPADE, CardRank.TEN),
                new Card(CardShape.SPADE, CardRank.ACE)
        )); // 21

        // when
        BlackjackResultDto blackjackResultDto = createResultDto(dealer, player);

        // then
        final int noBenefit = 0;
        assertThat(blackjackResultDto.dealerBenefit()).isEqualTo(noBenefit);
        assertThat(blackjackResultDto.playerProfitMap().get(player.getName())).isEqualTo(noBenefit);
    }

    @DisplayName("참가자가 버스트고 딜러가 살면 딜러 승")
    @Test
    void 참가자_버스트_딜러_생존() {
        // given
        Player player = PlayerTestUtil.createPlayer(List.of(
                new Card(CardShape.SPADE, CardRank.TEN),
                new Card(CardShape.SPADE, CardRank.QUEEN),
                new Card(CardShape.SPADE, CardRank.TWO)
        )); // 22

        Dealer dealer = PlayerTestUtil.createDealer(List.of(
                new Card(CardShape.SPADE, CardRank.TWO)
        )); // 2

        // when
        BlackjackResultDto blackjackResultDto = createResultDto(dealer, player);

        // then
        long expected = PlayerTestUtil.DEFAULT_BET_AMOUNT;
        assertThat(blackjackResultDto.dealerBenefit()).isEqualTo(expected);
        assertThat(blackjackResultDto.playerProfitMap().get(player.getName())).isEqualTo(-expected);
    }

    @DisplayName("참가자가 생존하고 딜러가 버스트면 딜러 패")
    @Test
    void 참가자_생존_딜러_버스트() {
        // given
        Player player = PlayerTestUtil.createPlayer(List.of(
                new Card(CardShape.SPADE, CardRank.TWO)
        )); // 22

        Dealer dealer = PlayerTestUtil.createDealer(List.of(
                new Card(CardShape.SPADE, CardRank.TEN),
                new Card(CardShape.SPADE, CardRank.QUEEN),
                new Card(CardShape.SPADE, CardRank.TWO)
        )); // 2

        // when
        BlackjackResultDto blackjackResultDto = createResultDto(dealer, player);

        // then
        long expected = PlayerTestUtil.DEFAULT_BET_AMOUNT;
        assertThat(blackjackResultDto.dealerBenefit()).isEqualTo(-expected);
        assertThat(blackjackResultDto.playerProfitMap().get(player.getName())).isEqualTo(expected);
    }

    @DisplayName("생존한 참가자 있고 딜러 버스트면 딜러 승리, 패배 하나씩")
    @Test
    void 생존_참가자_존재_및_딜러_버스트() {
        // given
        Player player1 = PlayerTestUtil.createPlayer(List.of(
                new Card(CardShape.SPADE, CardRank.NINE),
                new Card(CardShape.SPADE, CardRank.QUEEN),
                new Card(CardShape.SPADE, CardRank.TWO)
        ), "p1"); // 21
        Player player2 = PlayerTestUtil.createPlayer(List.of(
                new Card(CardShape.SPADE, CardRank.NINE),
                new Card(CardShape.SPADE, CardRank.QUEEN),
                new Card(CardShape.SPADE, CardRank.THREE)
        ), "p2"); // 22
        Player player3 = PlayerTestUtil.createPlayer(List.of(
                new Card(CardShape.SPADE, CardRank.KING),
                new Card(CardShape.SPADE, CardRank.QUEEN)
        ), "p3"); // 20
        Players players = new Players(List.of(
                player1,
                player2,
                player3
        ));

        Dealer dealer = PlayerTestUtil.createDealer(List.of(
                new Card(CardShape.SPADE, CardRank.TEN),
                new Card(CardShape.SPADE, CardRank.QUEEN),
                new Card(CardShape.SPADE, CardRank.TWO)
        )); // 22

        // when
        BlackjackResultDto blackjackResultDto = createResultDto(dealer, players);

        // then

        // 딜러는 참가자 2명에게 패배, 1명에게 승
        assertThat(blackjackResultDto.dealerBenefit()).isEqualTo(-PlayerTestUtil.DEFAULT_BET_AMOUNT);
        assertThat(blackjackResultDto.playerProfitMap().get(player1.getName())).isEqualTo(PlayerTestUtil.DEFAULT_BET_AMOUNT);
        assertThat(blackjackResultDto.playerProfitMap().get(player2.getName())).isEqualTo(-PlayerTestUtil.DEFAULT_BET_AMOUNT);
        assertThat(blackjackResultDto.playerProfitMap().get(player3.getName())).isEqualTo(PlayerTestUtil.DEFAULT_BET_AMOUNT);
    }

    @DisplayName("딜러의 손실액은 참가자들 수익 합계와 반대 부호")
    @Test
    void 딜러_손실액_참가자_수익합의_반대() {
        // given
        Player player1 = PlayerTestUtil.createPlayer(List.of(
                new Card(CardShape.SPADE, CardRank.NINE),
                new Card(CardShape.SPADE, CardRank.QUEEN),
                new Card(CardShape.SPADE, CardRank.TWO)
        ), "p1"); // 21
        Player player2 = PlayerTestUtil.createPlayer(List.of(
                new Card(CardShape.SPADE, CardRank.NINE),
                new Card(CardShape.SPADE, CardRank.QUEEN),
                new Card(CardShape.SPADE, CardRank.THREE)
        ), "p2"); // bust
        Player player3 = PlayerTestUtil.createPlayer(List.of(
                new Card(CardShape.SPADE, CardRank.KING),
                new Card(CardShape.SPADE, CardRank.QUEEN),
                new Card(CardShape.SPADE, CardRank.TWO)
        ), "p3"); // bust
        Players players = new Players(List.of(
                player1,
                player2,
                player3
        ));

        Dealer dealer = PlayerTestUtil.createDealer(List.of(
                new Card(CardShape.SPADE, CardRank.TEN),
                new Card(CardShape.SPADE, CardRank.QUEEN),
                new Card(CardShape.SPADE, CardRank.ACE)
        )); // 21

        // when
        BlackjackResultDto blackjackResultDto = createResultDto(dealer, players);

        // then
        long summation = blackjackResultDto.playerProfitMap().values().stream()
                .reduce(0L, Long::sum);
        assertThat(blackjackResultDto.dealerBenefit()).isEqualTo((-1) * summation);
    }

    @DisplayName("수익 결과의 플레이어 순서는 입력 순서와 동일하다")
    @Test
    void 수익_결과_플레이어_순서_보장() {
        // given
        Player player1 = PlayerTestUtil.createPlayer(List.of(
                new Card(CardShape.SPADE, CardRank.TEN),
                new Card(CardShape.SPADE, CardRank.JACK)
        ), "p1");
        Player player2 = PlayerTestUtil.createPlayer(List.of(
                new Card(CardShape.SPADE, CardRank.NINE),
                new Card(CardShape.SPADE, CardRank.QUEEN)
        ), "p2");
        Player player3 = PlayerTestUtil.createPlayer(List.of(
                new Card(CardShape.SPADE, CardRank.KING),
                new Card(CardShape.SPADE, CardRank.QUEEN)
        ), "p3");
        Players players = new Players(List.of(player1, player2, player3));

        Dealer dealer = PlayerTestUtil.createDealer(List.of(
                new Card(CardShape.SPADE, CardRank.TEN),
                new Card(CardShape.SPADE, CardRank.JACK)
        ));

        // when
        BlackjackResultDto blackjackResultDto = createResultDto(dealer, players);

        // then
        assertThat(blackjackResultDto.playerProfitMap().keySet())
                .containsExactly("p1", "p2", "p3");
    }
}
