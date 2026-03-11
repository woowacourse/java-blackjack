package blackjack.model.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import blackjack.model.cardDeck.CardDeck;
import blackjack.model.participant.Dealer;
import blackjack.model.participant.Player;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TotalResultTest {

    private TotalResult totalResult;

    @BeforeEach
    void beforeEach() {
        Dealer dealer =  new Dealer();
        dealer.pickAdditionalCard(createCardDeckOf(Rank.TEN));
        dealer.pickAdditionalCard(createCardDeckOf(Rank.TEN));  //20

        Player player1 = Player.of("player1", 1000);
        player1.pickAdditionalCard(createCardDeckOf(Rank.TEN));
        player1.pickAdditionalCard(createCardDeckOf(Rank.ACE)); // 21

        Player player2 = Player.of("player2", 1000);
        player2.pickAdditionalCard(createCardDeckOf(Rank.TEN));
        player2.pickAdditionalCard(createCardDeckOf(Rank.TEN)); // 20

        Player player3 = Player.of("player3", 1000);
        player3.pickAdditionalCard(createCardDeckOf(Rank.TEN));
        player3.pickAdditionalCard(createCardDeckOf(Rank.FIVE)); // 15

        Player player4 = Player.of("player4", 1000);
        player3.pickAdditionalCard(createCardDeckOf(Rank.TEN));
        player3.pickAdditionalCard(createCardDeckOf(Rank.FIVE)); // 15

         totalResult = TotalResult.of(
                List.of(player1, player2, player3, player4),
                dealer
        );
    }

    @Test
    @DisplayName("각 플레이어에 대한 딜러의 승/무/패 횟수를 %d승 %d무 %d패 형식으로 반환한다.")
    void getDealerResult() {
        assertThat(totalResult.getDealerResult())
                .isEqualTo("2승 1무 1패");
    }

    @Test
    @DisplayName("딜러 대한 각 플레이어의 결과를 이름:결과 형식으로 반환한다.")
    void getPlayerResults() {
        assertThat(totalResult.getPlayerResults()).containsExactlyInAnyOrder(
                "player1: 승",
                "player2: 무",
                "player3: 패",
                "player4: 패"
        );
    }

    private CardDeck createCardDeckOf (Rank rank) {
        return CardDeck.of(
                cards -> Card.openedCard(rank, Suit.CLOVER)
        );
    }
}