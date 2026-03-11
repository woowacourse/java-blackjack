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
    CardDeck mustPickFive = CardDeck.of(cards -> Card.openedCard(Rank.FIVE, Suit.CLOVER));

    @Test
    @DisplayName("딜러가 뽑은 두 장의 카드 중 한 장만 오픈돼 있다.")
    void pickInitialCards() {
        // given
        Dealer dealer = new Dealer();

        // when
        dealer.pickInitialCards(mustPickTen);

        //then
        List<Card> cards = dealer.getOpenedCards();

        assertThat(cards.size()).isEqualTo(1);
        assertThat(cards.getFirst().getDefaultScore()).isEqualTo(10);
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
        assertThat(dealer.awardPrize(players).getFirst().getPrize())
                .isEqualTo((int) (blackjack_player.getPrize() * 1.5));

        assertThat(dealer.awardPrize(players).getLast().getPrize())
                .isNotEqualTo((int) (not_blackjack_player.getPrize() * 1.5));
    }

    @Test
    @DisplayName("딜러와 플레이어가 버스트가 아니고 플레이어 점수가 딜러 점수 이상이면(단 플레이어는 블랙잭이 아니다), 최종 수익을 배팅 금액으로 처리한다.")
    void award_prize_when_dealer_player_are_not_bust() {
        //given
        Player winner = Player.of(
                "winner",
                1000
        );
        winner.pickAdditionalCard(mustPickTen);
        winner.pickAdditionalCard(mustPickFive);
        winner.pickAdditionalCard(mustPickAce);   //10 + 5 + 1 = 16점

        Player loser = Player.of(
                "loser",
                1000
        );
        loser.pickAdditionalCard(mustPickFive);
        loser.pickAdditionalCard(mustPickFive); // 5 + 5 = 10점

        List<Player> players = List.of(winner, loser);

        Dealer dealer =  new Dealer();
        dealer.pickAdditionalCard(mustPickTen);
        dealer.pickAdditionalCard(mustPickFive); // 10 + 5 = 15점

        //when & then
        assertThat(dealer.awardPrize(players).getFirst().getPrize())
                .isEqualTo(winner.getPrize());

        assertThat(dealer.awardPrize(players).getLast().getPrize())
                .isEqualTo(-1 * loser.getPrize());
    }

    @Test
    @DisplayName("딜러의 최종 수익과 모든 플레이어의 수익의 합은 0이다.")
    void getProfit() {
        //given
        Player winner = Player.of(
                "winner",
                1000
        );
        winner.pickAdditionalCard(mustPickTen);
        winner.pickAdditionalCard(mustPickFive);
        winner.pickAdditionalCard(mustPickAce);   //10 + 5 + 1 = 16점

        Player loser = Player.of(
                "loser",
                1000
        );
        loser.pickAdditionalCard(mustPickFive);
        loser.pickAdditionalCard(mustPickFive); // 5 + 5 = 10점

        List<Player> players = List.of(winner, loser);

        Dealer dealer =  new Dealer();
        dealer.pickAdditionalCard(mustPickTen);
        dealer.pickAdditionalCard(mustPickFive); // 10 + 5 = 15점

        players = dealer.awardPrize(players);

        //when & then
        assertThat(dealer.getProfit(players)).isEqualTo(0);
    }
}