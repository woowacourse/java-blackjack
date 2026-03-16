package blackjack.model.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.dto.PlayerProfitDto;
import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import blackjack.model.carddeck.CardDeck;
import blackjack.model.money.Money;
import blackjack.model.participant.Dealer;
import blackjack.model.participant.Player;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerProfitsTest {

    @Test
    @DisplayName("플레이어와 딜러 수익 계산 테스트")
    void playerAndDealerProfitTest() {
        // given
        CardDeck tenCloverDeck = CardDeck.of(cards -> Card.of(Rank.TEN, Suit.CLOVER));
        CardDeck aceCloverDeck = CardDeck.of(cards -> Card.of(Rank.ACE, Suit.CLOVER));

        Player p1 = Player.of("p1");
        p1.bet(10_000);
        p1.pickAdditionalCard(tenCloverDeck);

        Player p2 = Player.of("p1");
        p2.bet(20_000);
        p2.pickAdditionalCard(tenCloverDeck);
        p2.pickAdditionalCard(aceCloverDeck);

        Dealer dealer = Dealer.create();
        dealer.pickAdditionalCard(tenCloverDeck);
        dealer.pickAdditionalCard(tenCloverDeck);

        // when
        PlayerProfits playerProfits = PlayerProfits.of(List.of(p1, p2), dealer);

        // then
        assertThat(playerProfits.getDealerProfit().profit()).isEqualTo(Money.of(-20_000));
        assertThat(playerProfits.getPlayerResults().results()).containsExactlyInAnyOrder(
                PlayerProfitDto.from(p1, Money.of(-10_000)),
                PlayerProfitDto.from(p2, Money.of(30_000))
        );
    }

}