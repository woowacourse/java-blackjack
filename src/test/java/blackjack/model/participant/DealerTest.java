package blackjack.model.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.dto.CardDto;
import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import blackjack.model.carddeck.CardDeck;
import blackjack.model.result.PlayerResult;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러의 점수가 16점을 초과하면 false를 반환한다.")
    void canPick() {
        //given
        Dealer dealer = Dealer.create();
        CardDeck tenCloverCardDeck = CardDeck.of(cards -> Card.of(Rank.TEN, Suit.CLOVER));

        dealer.pickAdditionalCard(tenCloverCardDeck);
        dealer.pickAdditionalCard(tenCloverCardDeck);

        // when & then
        assertThat(dealer.canPick()).isFalse();
    }

    @Test
    @DisplayName("딜러랑 플레이어 핸드 비교 결과 테스트")
    void judgePlayerResult() {
        // given
        Dealer dealer = Dealer.create();

        CardDeck tenCloverCardDeck = CardDeck.of(cards -> Card.of(Rank.TEN, Suit.CLOVER));
        dealer.pickInitCards(tenCloverCardDeck);

        Player player1 = Player.of("player1");

        CardDeck fiveCloverCardDeck = CardDeck.of(cards -> Card.of(Rank.FIVE, Suit.CLOVER));
        player1.pickAdditionalCard(fiveCloverCardDeck);
        player1.pickAdditionalCard(fiveCloverCardDeck);

        Player player2 = Player.of("player2");

        CardDeck aceCloverCardDeck = CardDeck.of(cards -> Card.of(Rank.ACE, Suit.CLOVER));
        player2.pickAdditionalCard(tenCloverCardDeck);
        player2.pickAdditionalCard(aceCloverCardDeck);

        Player player3 = Player.of("player3");

        player3.pickAdditionalCard(tenCloverCardDeck);
        player3.pickAdditionalCard(tenCloverCardDeck);

        // when & then
        assertThat(dealer.judgePlayerResult(player1)).isEqualTo(PlayerResult.LOSE);
        assertThat(dealer.judgePlayerResult(player2)).isEqualTo(PlayerResult.WIN);
        assertThat(dealer.judgePlayerResult(player3)).isEqualTo(PlayerResult.DRAW);
    }

    @Test
    @DisplayName("첫 카드 뽑기 테스트")
    void pickInitCardsTest() {
        // given
        Card tenClover = Card.of(Rank.TEN, Suit.CLOVER);
        Dealer dealer = Dealer.create();

        // when
        dealer.pickInitCards(CardDeck.of(cards -> tenClover));

        // then
        List<CardDto> cards = dealer.getAllCards();
        assertThat(cards).hasSize(2);
        assertThat(cards).containsExactlyInAnyOrder(
                CardDto.from(tenClover),
                CardDto.from(tenClover)
        );
    }

    @Test
    @DisplayName("카드 덱에서 추가 1장 더 가져오기")
    void pickAdditionalTest() {
        // given
        Card tenClover = Card.of(Rank.TEN, Suit.CLOVER);
        Dealer dealer = Dealer.create();
        CardDeck mustPickTenClover = CardDeck.of(cards -> tenClover);

        // when
        dealer.pickAdditionalCard(mustPickTenClover);

        // then
        assertThat(dealer.getAllCards()).hasSize(1);
        assertThat(dealer.getAllCards().getFirst()).isEqualTo(CardDto.from(tenClover));
    }

    @Test
    @DisplayName("버스트 테스트")
    void isBustTest() {
        // given
        Card tenClover = Card.of(Rank.TEN, Suit.CLOVER);
        CardDeck mustPickTenClover = CardDeck.of(cards -> tenClover);

        Dealer dealer = Dealer.create();

        // when
        dealer.pickAdditionalCard(mustPickTenClover);
        dealer.pickAdditionalCard(mustPickTenClover);
        dealer.pickAdditionalCard(mustPickTenClover); // pick 30

        // then
        assertThat(dealer.isBust()).isTrue();
    }

    @Test
    @DisplayName("플레이어가 버스트하면 플레이어 패")
    void playerBust() {
        // given
        Dealer dealer = Dealer.create();

        Player player = Player.of("player");
        CardDeck mustPickTenClover = CardDeck.of(cards -> Card.of(Rank.TEN, Suit.CLOVER));
        player.pickAdditionalCard(mustPickTenClover);
        player.pickAdditionalCard(mustPickTenClover);
        player.pickAdditionalCard(mustPickTenClover);

        assertThat(dealer.judgePlayerResult(player)).isEqualTo(PlayerResult.LOSE);
    }

    @Test
    @DisplayName("딜러만 버스트하면 플레이어 승")
    void dealerBust() {
        // given
        CardDeck mustPickTenClover = CardDeck.of(cards -> Card.of(Rank.TEN, Suit.CLOVER));
        Player player = Player.of("player");

        Dealer dealer = Dealer.create();
        dealer.pickAdditionalCard(mustPickTenClover);
        dealer.pickAdditionalCard(mustPickTenClover);
        dealer.pickAdditionalCard(mustPickTenClover);

        assertThat(dealer.judgePlayerResult(player)).isEqualTo(PlayerResult.WIN);
    }

    @Test
    @DisplayName("딜러가 플레이어보다 점수가 높으면 플레이어 패")
    void dealerHigherThanPlayer() {
        // given
        CardDeck mustPickTenClover = CardDeck.of(cards -> Card.of(Rank.TEN, Suit.CLOVER));
        Player player = Player.of("player");
        player.pickAdditionalCard(mustPickTenClover);

        Dealer dealer = Dealer.create();
        dealer.pickAdditionalCard(mustPickTenClover);
        dealer.pickAdditionalCard(mustPickTenClover);

        assertThat(dealer.judgePlayerResult(player)).isEqualTo(PlayerResult.LOSE);
    }

    @Test
    @DisplayName("플레이어가 딜러보다 높으면 플레이어 승")
    void playerHigherThanDealer() {
        // given
        CardDeck mustPickTenClover = CardDeck.of(cards -> Card.of(Rank.TEN, Suit.CLOVER));
        Player player = Player.of("player");
        player.pickAdditionalCard(mustPickTenClover);
        player.pickAdditionalCard(mustPickTenClover);

        Dealer dealer = Dealer.create();
        dealer.pickAdditionalCard(mustPickTenClover);

        assertThat(dealer.judgePlayerResult(player)).isEqualTo(PlayerResult.WIN);
    }

    @Test
    @DisplayName("플레이어와 딜러가 같으면 무승부")
    void playerEqualDealer() {
        // given
        CardDeck mustPickTenClover = CardDeck.of(cards -> Card.of(Rank.TEN, Suit.CLOVER));
        Player player = Player.of("player");
        player.pickAdditionalCard(mustPickTenClover);
        player.pickAdditionalCard(mustPickTenClover);

        Dealer dealer = Dealer.create();
        dealer.pickAdditionalCard(mustPickTenClover);
        dealer.pickAdditionalCard(mustPickTenClover);

        assertThat(dealer.judgePlayerResult(player)).isEqualTo(PlayerResult.DRAW);
    }
}
