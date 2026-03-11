package domain.result;

import domain.card.Card;
import domain.card.CardRank;
import domain.card.CardShape;
import domain.dto.BlackjackResultDto;
import domain.participant.BetMap;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import testutil.PlayerTestUtil;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BlackjackResultTest {
    private static final BetMap betMap = new  BetMap();

    @Test
    @DisplayName("참가자가 딜러보다 낮은 점수면 딜러가 이긴다")
    void 참가자가_딜러보다_점수_낮음() {
        Player player = PlayerTestUtil.createPlayer(List.of(
                new Card(CardShape.SPADE, CardRank.TEN),
                new Card(CardShape.SPADE, CardRank.JACK)
        )); // 20

        Dealer dealer = PlayerTestUtil.createDealer(List.of(
                new Card(CardShape.SPADE, CardRank.TEN),
                new Card(CardShape.SPADE, CardRank.JACK),
                new Card(CardShape.SPADE, CardRank.ACE)
        )); // 21

        BlackjackResult blackjackResult = BlackjackResult.from(dealer, createSinglePlayerSet(player), betMap);
        BlackjackResultDto blackjackResultDto = BlackjackResultDto.from(blackjackResult);
        assertThat(blackjackResultDto.winCount()).isEqualTo(1);
        assertThat(blackjackResultDto.loseCount()).isEqualTo(0);
    }

    @Test
    @DisplayName("참가자가 딜러보다 높은 점수면 딜러가 진다")
    void 참가자가_딜러보다_점수_높음() {
        Player player = PlayerTestUtil.createPlayer(List.of(
                new Card(CardShape.SPADE, CardRank.TEN),
                new Card(CardShape.SPADE, CardRank.ACE)
        )); // 21

        Dealer dealer = PlayerTestUtil.createDealer(List.of(
                new Card(CardShape.SPADE, CardRank.TEN),
                new Card(CardShape.SPADE, CardRank.JACK)
        )); // 20

        BlackjackResult blackjackResult = BlackjackResult.from(dealer, createSinglePlayerSet(player), betMap);
        BlackjackResultDto blackjackResultDto = BlackjackResultDto.from(blackjackResult);
        assertThat(blackjackResultDto.loseCount()).isEqualTo(1);
        assertThat(blackjackResultDto.winCount()).isEqualTo(0);
    }

    @DisplayName("참가자와 딜러 점수가 둘 다 같으면 무승부")
    @Test
    void 참가자_딜러_무승부() {
        Player player = PlayerTestUtil.createPlayer(List.of(
                new Card(CardShape.SPADE, CardRank.TEN),
                new Card(CardShape.SPADE, CardRank.ACE)
        )); // 21

        Dealer dealer = PlayerTestUtil.createDealer(List.of(
                new Card(CardShape.SPADE, CardRank.TEN),
                new Card(CardShape.SPADE, CardRank.ACE)
        )); // 21

        BlackjackResult blackjackResult = BlackjackResult.from(dealer, createSinglePlayerSet(player), betMap);
        BlackjackResultDto blackjackResultDto = BlackjackResultDto.from(blackjackResult);
        assertThat(blackjackResultDto.drawCount()).isEqualTo(1);
    }

    @DisplayName("참가자가 버스트고 딜러가 살면 딜러 승")
    @Test
    void 참가자_버스트_딜러_생존() {
        Player player = PlayerTestUtil.createPlayer(List.of(
                new Card(CardShape.SPADE, CardRank.TEN),
                new Card(CardShape.SPADE, CardRank.QUEEN),
                new Card(CardShape.SPADE, CardRank.TWO)
        )); // 22

        Dealer dealer = PlayerTestUtil.createDealer(List.of(
                new Card(CardShape.SPADE, CardRank.TWO)
        )); // 2

        BlackjackResult blackjackResult = BlackjackResult.from(dealer, createSinglePlayerSet(player), betMap);
        BlackjackResultDto blackjackResultDto = BlackjackResultDto.from(blackjackResult);
        assertThat(blackjackResultDto.winCount()).isEqualTo(1);
        assertThat(blackjackResultDto.loseCount()).isEqualTo(0);
    }

    @DisplayName("참가자가 생존하고 딜러가 버스트면 딜러 패")
    @Test
    void 참가자_생존_딜러_버스트() {
        Player player = PlayerTestUtil.createPlayer(List.of(
                new Card(CardShape.SPADE, CardRank.TWO)
        )); // 22

        Dealer dealer = PlayerTestUtil.createDealer(List.of(
                new Card(CardShape.SPADE, CardRank.TEN),
                new Card(CardShape.SPADE, CardRank.QUEEN),
                new Card(CardShape.SPADE, CardRank.TWO)
        )); // 2

        BlackjackResult blackjackResult = BlackjackResult.from(dealer, createSinglePlayerSet(player), betMap);
        BlackjackResultDto blackjackResultDto = BlackjackResultDto.from(blackjackResult);
        assertThat(blackjackResultDto.loseCount()).isEqualTo(1);
        assertThat(blackjackResultDto.winCount()).isEqualTo(0);
    }

    @DisplayName("생존한 참가자 있고 딜러 버스트면 딜러 승리, 패배 하나씩")
    @Test
    void 생존_참가자_존재_및_딜러_버스트() {
        Players players = new Players(
                List.of(PlayerTestUtil.createPlayer(List.of(
                                new Card(CardShape.SPADE, CardRank.NINE),
                                new Card(CardShape.SPADE, CardRank.QUEEN),
                                new Card(CardShape.SPADE, CardRank.THREE)
                        )), // 22
                        PlayerTestUtil.createPlayer(List.of(
                                new Card(CardShape.SPADE, CardRank.NINE),
                                new Card(CardShape.SPADE, CardRank.QUEEN),
                                new Card(CardShape.SPADE, CardRank.TWO)
                        ))) // 21
        );

        Dealer dealer = PlayerTestUtil.createDealer(List.of(
                new Card(CardShape.SPADE, CardRank.TEN),
                new Card(CardShape.SPADE, CardRank.QUEEN),
                new Card(CardShape.SPADE, CardRank.TWO)
        )); // 22

        BlackjackResult blackjackResult = BlackjackResult.from(dealer, players, betMap);
        BlackjackResultDto blackjackResultDto = BlackjackResultDto.from(blackjackResult);
        assertThat(blackjackResultDto.loseCount()).isEqualTo(1);
        assertThat(blackjackResultDto.winCount()).isEqualTo(1);
    }

    private Players createSinglePlayerSet(Player player) {
        return new Players(List.of(player));
    }
}
