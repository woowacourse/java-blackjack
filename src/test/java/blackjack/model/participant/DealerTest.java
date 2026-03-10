package blackjack.model.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import blackjack.model.cardDeck.CardDeck;
import blackjack.model.cardDeck.PickStrategy;
import blackjack.model.result.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    PickStrategy mustPickTen = cards -> Card.opened(Rank.TEN, Suit.CLOVER);
    PickStrategy mustPickFive = cards -> Card.opened(Rank.FIVE, Suit.CLOVER);
    PickStrategy mustPickAce = cards -> Card.opened(Rank.ACE, Suit.CLOVER);

    @Test
    @DisplayName("딜러가 뽑은 두 장의 카드 중 한 장만 오픈돼 있다.")
    void pickInitCards() {
        // given
        Dealer dealer = Dealer.create();
        CardDeck cardDeck = CardDeck.of(mustPickTen);

        // when
        dealer.pickInitCards(cardDeck);

        //then
        assertThat(dealer.getOpenedCards().size()).isEqualTo(1);
        assertThat(dealer.getAllCard().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("딜러의 점수가 16점을 초과하면 false를 반환한다.")
    void canPick() {
        //given
        Dealer dealer = Dealer.create();
        CardDeck cardDeck = CardDeck.of(mustPickTen);

        dealer.pickAdditionalCard(cardDeck);
        dealer.pickAdditionalCard(cardDeck);

        // when & then
        assertThat(dealer.canPick()).isFalse();
    }

    @Test
    @DisplayName("딜러랑 플레이어 핸드 비교 결과 테스트")
    void judgePlayerResult() {
        // given
        Dealer dealer = Dealer.create();

        CardDeck cardDeckForMustPickTen = CardDeck.of(mustPickTen);
        dealer.pickInitCards(cardDeckForMustPickTen);

        Player player1 = Player.of("player1");

        CardDeck cardDeckForMustPickFive = CardDeck.of(mustPickFive);
        player1.pickAdditionalCard(cardDeckForMustPickFive);
        player1.pickAdditionalCard(cardDeckForMustPickFive);

        Player player2 = Player.of("player2");

        CardDeck cardDeckForMustPickAce = CardDeck.of(mustPickAce);
        player2.pickAdditionalCard(cardDeckForMustPickTen);
        player2.pickAdditionalCard(cardDeckForMustPickAce);

        Player player3 = Player.of("player3");

        player3.pickAdditionalCard(cardDeckForMustPickTen);
        player3.pickAdditionalCard(cardDeckForMustPickTen);

        // when & then
        assertThat(dealer.judgePlayerResult(player1)).isEqualTo(Result.LOSE);
        assertThat(dealer.judgePlayerResult(player2)).isEqualTo(Result.WIN);
        assertThat(dealer.judgePlayerResult(player3)).isEqualTo(Result.DRAW);
    }
}
