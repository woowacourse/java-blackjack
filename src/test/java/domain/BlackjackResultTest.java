//package domain;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.util.List;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import domain.dto.BlackjackResultDto;
//import testutil.PlayerTestUtil;
//
//class BlackjackResultTest {
//    @Test
//    @DisplayName("참가자가 딜러보다 낮은 점수면 딜러가 이긴다")
//    void 참가자가_딜러보다_점수_낮음() {
//        Player player = PlayerTestUtil.createPlayer(List.of(
//                new Card(CardShape.SPADE, CardRank.TEN),
//                new Card(CardShape.SPADE, CardRank.JACK)
//        )); // 20
//
//        Dealer dealer = PlayerTestUtil.createDealer(List.of(
//                new Card(CardShape.SPADE, CardRank.TEN),
//                new Card(CardShape.SPADE, CardRank.JACK),
//                new Card(CardShape.SPADE, CardRank.ACE)
//        )); // 21
//
//        BlackjackResult blackjackResult = BlackjackResult.from(dealer, createSinglePlayerSet(player));
//        BlackjackResultDto blackjackResultDto = blackjackResult.toResultDto();
//        assertThat(blackjackResultDto.winCount()).isEqualTo(1);
//        assertThat(blackjackResultDto.loseCount()).isEqualTo(0);
//    }
//
//    @Test
//    @DisplayName("참가자가 딜러보다 높은 점수면 딜러가 진다")
//    void 참가자가_딜러보다_점수_높음() {
//        Player player = PlayerTestUtil.createPlayer(List.of(
//                new Card(CardShape.SPADE, CardRank.TEN),
//                new Card(CardShape.SPADE, CardRank.ACE)
//        )); // 21
//
//        Dealer dealer = PlayerTestUtil.createDealer(List.of(
//                new Card(CardShape.SPADE, CardRank.TEN),
//                new Card(CardShape.SPADE, CardRank.JACK)
//        )); // 20
//
//        BlackjackResult blackjackResult = BlackjackResult.from(dealer, createSinglePlayerSet(player));
//        BlackjackResultDto blackjackResultDto = blackjackResult.toResultDto();
//        assertThat(blackjackResultDto.loseCount()).isEqualTo(1);
//        assertThat(blackjackResultDto.winCount()).isEqualTo(0);
//    }
//
//    @DisplayName("참가자와 딜러 점수가 둘 다 같으면 무승부")
//    @Test
//    void 참가자_딜러_무승부() {
//        Player player = PlayerTestUtil.createPlayer(List.of(
//                new Card(CardShape.SPADE, CardRank.TEN),
//                new Card(CardShape.SPADE, CardRank.ACE)
//        )); // 21
//
//        Dealer dealer = PlayerTestUtil.createDealer(List.of(
//                new Card(CardShape.SPADE, CardRank.TEN),
//                new Card(CardShape.SPADE, CardRank.ACE)
//        )); // 21
//
//        BlackjackResult blackjackResult = BlackjackResult.from(dealer, createSinglePlayerSet(player));
//        BlackjackResultDto blackjackResultDto = blackjackResult.toResultDto();
//        assertThat(blackjackResultDto.drawCount()).isEqualTo(1);
//    }
//
//    @DisplayName("참가자가 버스트고 딜러가 살면 딜러 승")
//    @Test
//    void 참가자_버스트_딜러_생존() {
//        Player player = PlayerTestUtil.createPlayer(List.of(
//                new Card(CardShape.SPADE, CardRank.TEN),
//                new Card(CardShape.SPADE, CardRank.QUEEN),
//                new Card(CardShape.SPADE, CardRank.TWO)
//        )); // 22
//
//        Dealer dealer = PlayerTestUtil.createDealer(List.of(
//                new Card(CardShape.SPADE, CardRank.TWO)
//        )); // 2
//
//        BlackjackResult blackjackResult = BlackjackResult.from(dealer, createSinglePlayerSet(player));
//        BlackjackResultDto blackjackResultDto = blackjackResult.toResultDto();
//        assertThat(blackjackResultDto.winCount()).isEqualTo(1);
//        assertThat(blackjackResultDto.loseCount()).isEqualTo(0);
//    }
//
//    @DisplayName("참가자가 생존하고 딜러가 버스트면 딜러 패")
//    @Test
//    void 참가자_생존_딜러_버스트() {
//        Player player = PlayerTestUtil.createPlayer(List.of(
//                new Card(CardShape.SPADE, CardRank.TWO)
//        )); // 22
//
//        Dealer dealer = PlayerTestUtil.createDealer(List.of(
//                new Card(CardShape.SPADE, CardRank.TEN),
//                new Card(CardShape.SPADE, CardRank.QUEEN),
//                new Card(CardShape.SPADE, CardRank.TWO)
//        )); // 2
//
//        BlackjackResult blackjackResult = BlackjackResult.from(dealer, createSinglePlayerSet(player));
//        BlackjackResultDto blackjackResultDto = blackjackResult.toResultDto();
//        assertThat(blackjackResultDto.loseCount()).isEqualTo(1);
//        assertThat(blackjackResultDto.winCount()).isEqualTo(0);
//    }
//
//    @DisplayName("생존한 참가자 있고 딜러 버스트면 딜러 승리, 패배 하나씩")
//    @Test
//    void 생존_참가자_존재_및_딜러_버스트() {
//        Players players = new Players(
//                List.of(PlayerTestUtil.createPlayer(List.of(
//                                new Card(CardShape.SPADE, CardRank.NINE),
//                                new Card(CardShape.SPADE, CardRank.QUEEN),
//                                new Card(CardShape.SPADE, CardRank.THREE)
//                        )), // 22
//                        PlayerTestUtil.createPlayer(List.of(
//                                new Card(CardShape.SPADE, CardRank.NINE),
//                                new Card(CardShape.SPADE, CardRank.QUEEN),
//                                new Card(CardShape.SPADE, CardRank.TWO)
//                        ))) // 21
//        );
//
//        Dealer dealer = PlayerTestUtil.createDealer(List.of(
//                new Card(CardShape.SPADE, CardRank.TEN),
//                new Card(CardShape.SPADE, CardRank.QUEEN),
//                new Card(CardShape.SPADE, CardRank.TWO)
//        )); // 22
//
//        BlackjackResult blackjackResult = BlackjackResult.from(dealer, players);
//        BlackjackResultDto blackjackResultDto = blackjackResult.toResultDto();
//        assertThat(blackjackResultDto.loseCount()).isEqualTo(1);
//        assertThat(blackjackResultDto.winCount()).isEqualTo(1);
//    }
//
//
//    @DisplayName("참가자가 bust되면 배팅금액이 마이너스가 된다.")
//    @Test
//    void 참가자_bust_배팅금액_마이너스() {
//        Player player = PlayerTestUtil.createPlayer(List.of(
//                new Card(CardShape.SPADE, CardRank.TEN),
//                new Card(CardShape.SPADE, CardRank.NINE),
//                new Card(CardShape.SPADE, CardRank.EIGHT)
//        )); // 27
//        player.betMoney(10000);
//
//        Dealer dealer = PlayerTestUtil.createDealer(List.of(
//                new Card(CardShape.SPADE, CardRank.TEN),
//                new Card(CardShape.SPADE, CardRank.ACE)
//        )); // 21
//
//        assertThat(player.getBettingScore()).isEqualTo(10000);
//        BlackjackResult blackjackResult = BlackjackResult.from(dealer, createSinglePlayerSet(player));
//        assertThat(player.getBettingScore()).isEqualTo(-10000);
//        assertThat(dealer.getBettingScore()).isEqualTo(10000);
//    }
//
//    @DisplayName("참가자가 블랙잭이 되면 배팅금액이 1.5배가 된다.")
//    @Test
//    void 참가자_블랙잭_배팅금액_보너스() {
//        Player player = PlayerTestUtil.createPlayer(List.of(
//                new Card(CardShape.SPADE, CardRank.TEN),
//                new Card(CardShape.SPADE, CardRank.ACE)
//        )); // 21
//        player.betMoney(10000);
//
//        Players players = new Players(List.of(player));
//
//        Dealer dealer = PlayerTestUtil.createDealer(List.of(
//                new Card(CardShape.SPADE, CardRank.TEN),
//                new Card(CardShape.SPADE, CardRank.TEN)
//        )); // 21
//
//        assertThat(player.getBettingScore()).isEqualTo(10000);
//        BlackjackResult blackjackResult = BlackjackResult.from(dealer, createSinglePlayerSet(player));
//        blackjackResult.calculateMatchResult(dealer,players);
//        assertThat(player.getBettingScore()).isEqualTo(15000);
//        assertThat(dealer.getBettingScore()).isEqualTo(10000);
//    }
//
//    @DisplayName("딜러와 참가자 모두 블랙잭이면 배팅한 금액을 돌려받는다..")
//    @Test
//    void 딜러_참가자_블랙잭_배팅금액_반환() {
//        Player player = PlayerTestUtil.createPlayer(List.of(
//                new Card(CardShape.SPADE, CardRank.TEN),
//                new Card(CardShape.SPADE, CardRank.ACE)
//        )); // 21
//        player.betMoney(10000);
//
//        Players players = new Players(List.of(player));
//
//        Dealer dealer = PlayerTestUtil.createDealer(List.of(
//                new Card(CardShape.SPADE, CardRank.TEN),
//                new Card(CardShape.SPADE, CardRank.ACE)
//        )); // 21
//
//        assertThat(player.getBettingScore()).isEqualTo(10000);
//        BlackjackResult blackjackResult = BlackjackResult.from(dealer, createSinglePlayerSet(player));
//        blackjackResult.calculateMatchResult(dealer,players);
//        assertThat(player.getBettingScore()).isEqualTo(10000);
//        assertThat(dealer.getBettingScore()).isEqualTo(10000);
//    }
//
//    private Players createSinglePlayerSet(Player player) {
//        return new Players(List.of(player));
//    }
//}