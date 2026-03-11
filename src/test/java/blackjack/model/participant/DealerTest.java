package blackjack.model.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import blackjack.model.cardDeck.CardDeck;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    CardDeck mustPickTen = CardDeck.of(cards -> Card.openedCard(Rank.TEN, Suit.CLOVER));
    CardDeck mustPickAce = CardDeck.of(cards -> Card.openedCard(Rank.ACE, Suit.CLOVER));

    @Test
    @DisplayName("딜러가 뽑은 두 장의 카드 중 한 장만 오픈돼 있다.")
    void pickInitialCards() {
        // given
        Dealer dealer = new Dealer();

        // when
        dealer.pickInitialCards(mustPickTen);  //10 + 10 = 20

        //then
        List<Card> cards = dealer.getOpenedCards();

        assertThat(cards.size()).isEqualTo(1);
        assertThat(cards.getFirst().getDefaultScore()).isEqualTo(20);
    }

    @Test
    @DisplayName("딜러의 점수가 16점을 초과하면 false를 반환한다.")
    void canPick() {
        //given
        Dealer dealer =  new Dealer();

        dealer.pickAdditionalCard(mustPickTen);
        dealer.pickAdditionalCard(mustPickTen); // 10 + 10 = 20

        // when & then
        assertThat(dealer.canPick()).isFalse();
    }

//    @Test
//    @DisplayName("딜러랑 플레이어 핸드 비교 결과 테스트")
//    void determineResultOf() {
//        // given
//        Dealer dealer =  new Dealer();
//
//        CardDeck cardDeckForMustPickTen = CardDeck.of(mustPickTen);
//        dealer.pickInitialCards(cardDeckForMustPickTen);
//
//        Player player1 = Player.of("player1", 1000);
//
//        CardDeck cardDeckForMustPickFive = CardDeck.of(mustPickFive);
//        player1.pickAdditionalCard(cardDeckForMustPickFive);
//        player1.pickAdditionalCard(cardDeckForMustPickFive);
//
//        Player player2 = Player.of("player2", 1000);
//
//        CardDeck cardDeckForMustPickAce = CardDeck.of(mustPickAce);
//        player2.pickAdditionalCard(cardDeckForMustPickTen);
//        player2.pickAdditionalCard(cardDeckForMustPickAce);
//
//        Player player3 = Player.of("player3", 1000);
//
//        player3.pickAdditionalCard(cardDeckForMustPickTen);
//        player3.pickAdditionalCard(cardDeckForMustPickTen);
//
//        // when & then
//        assertThat(dealer.determineResultOf(player1)).isEqualTo(Result.LOSE);
//        assertThat(dealer.determineResultOf(player2)).isEqualTo(Result.WIN);
//        assertThat(dealer.determineResultOf(player3)).isEqualTo(Result.DRAW);
//    }

    @Test
    @DisplayName("딜러가 버스트이면, 최종 수익을 배팅 금액으로 처리한다.")
    void award_prize_when_dealer_bust() {
        //given
        List<Player> players = List.of(
                Player.of("player1", 1000),
                Player.of("player2", 1000)
        );

        Dealer dealer =  new Dealer();
        dealer.pickAdditionalCard(mustPickTen);
        dealer.pickAdditionalCard(mustPickTen);
        dealer.pickAdditionalCard(mustPickTen); // 10 + 10 + 10 = 30

        //when & then
        assertThat(dealer.awardPrize(players))
                .containsExactlyElementsOf(players);
    }

    @Test
    @DisplayName("딜러가 블랙잭이 아니면, 블랙잭인 플레이어는 최종 수익을 배팅 금액의 1.5배로 처리한다.")
    void award_prize_when_only_player_blackjack() {
        //given
        Player blackjack_player = Player.of(
                "blackjack_player",
                1000
        );
        blackjack_player.pickAdditionalCard(mustPickTen);
        blackjack_player.pickAdditionalCard(mustPickAce);   //10 + 11 = 21점

        Player not_blackjack_player = Player.of(
                "not_blackjack_player",
                1000
        );
        not_blackjack_player.pickAdditionalCard(mustPickTen);
        not_blackjack_player.pickAdditionalCard(mustPickTen); // 10 + 10 = 20점

        List<Player> players = List.of(blackjack_player, not_blackjack_player);

        Dealer dealer =  new Dealer();
        dealer.pickAdditionalCard(mustPickTen);
        dealer.pickAdditionalCard(mustPickTen); // 10 + 10 = 20점

        //when & then
        assertThat(dealer.awardPrize(players).get(0).getPrize())
                .isEqualTo(blackjack_player.getPrize() * 1.5);
    }
}