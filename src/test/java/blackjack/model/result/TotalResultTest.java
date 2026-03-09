package blackjack.model.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import blackjack.model.cardDeck.CardDeck;
import blackjack.model.cardDeck.PickStrategy;
import blackjack.model.participant.Dealer;
import blackjack.model.participant.Player;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TotalResultTest {

    PickStrategy mustPickTen = cards -> Card.openedCard(Rank.TEN, Suit.CLOVER);
    PickStrategy mustPickFive = cards -> Card.openedCard(Rank.FIVE, Suit.CLOVER);
    PickStrategy mustPickAce = cards -> Card.openedCard(Rank.ACE, Suit.CLOVER);
    CardDeck cardDeckForMustPickTen = CardDeck.of(mustPickTen);
    CardDeck cardDeckForMustPickFive = CardDeck.of(mustPickFive);
    CardDeck cardDeckForMustPickAce = CardDeck.of(mustPickAce);

    @Test
    @DisplayName("딜러랑 플레이어 결과 생성 테스트")
    void totalResultTest() {
        // given
        Dealer dealer = Dealer.create();
        dealer.pickAdditionalCard(cardDeckForMustPickTen);
        dealer.pickAdditionalCard(cardDeckForMustPickTen); // 20

        Player player1 = Player.of("player1");
        player1.pickAdditionalCard(cardDeckForMustPickTen);
        player1.pickAdditionalCard(cardDeckForMustPickAce); // 21

        Player player2 = Player.of("player2");
        player2.pickAdditionalCard(cardDeckForMustPickTen);
        player2.pickAdditionalCard(cardDeckForMustPickTen); // 20

        Player player3 = Player.of("player3");
        player3.pickAdditionalCard(cardDeckForMustPickTen);
        player3.pickAdditionalCard(cardDeckForMustPickFive); // 15

        Player player4 = Player.of("player4");
        player3.pickAdditionalCard(cardDeckForMustPickTen);
        player3.pickAdditionalCard(cardDeckForMustPickFive); // 15

        // when
        TotalResult totalResult = TotalResult.of(List.of(player1, player2, player3, player4), dealer);

        // then
        assertThat(totalResult.getDealerResult()).isEqualTo("2승 1무 1패");
        assertThat(totalResult.getPlayerResults()).containsExactlyInAnyOrder(
                "player1: 승",
                "player2: 무",
                "player3: 패",
                "player4: 패"
        );
    }
}