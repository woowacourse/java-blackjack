package blackjack.model.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import blackjack.model.cardDeck.CardDeck;
import blackjack.model.cardDeck.PickStrategy;
import blackjack.model.result.Result;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    PickStrategy mustPickTen = cards -> Card.openedCard(Rank.TEN, Suit.CLOVER);
    PickStrategy mustPickFive = cards -> Card.openedCard(Rank.FIVE, Suit.CLOVER);
    PickStrategy mustPickAce = cards -> Card.openedCard(Rank.ACE, Suit.CLOVER);

    @Test
    @DisplayName("딜러가 뽑은 두 장의 카드 중 한 장만 오픈돼 있다.")
    void pickInitialCards() {
        // given
        Dealer dealer = new Dealer();
        CardDeck cardDeck = CardDeck.of(mustPickTen);

        // when
        dealer.pickInitialCards(cardDeck);

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
        CardDeck cardDeck = CardDeck.of(mustPickTen);

        dealer.pickAdditionalCard(cardDeck);
        System.out.println(dealer.getAllCard());
        dealer.pickAdditionalCard(cardDeck);

        System.out.println(dealer.getAllCard());

        // when & then
        assertThat(dealer.canPick()).isFalse();
    }

    @Test
    @DisplayName("딜러랑 플레이어 핸드 비교 결과 테스트")
    void compare() {
        // given
        Dealer dealer =  new Dealer();

        CardDeck cardDeckForMustPickTen = CardDeck.of(mustPickTen);
        dealer.pickInitialCards(cardDeckForMustPickTen);

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
        assertThat(dealer.compare(player1)).isEqualTo(Result.LOSE);
        assertThat(dealer.compare(player2)).isEqualTo(Result.WIN);
        assertThat(dealer.compare(player3)).isEqualTo(Result.DRAW);
    }
}