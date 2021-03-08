package blackjack.domain.player.strategy;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Cards;
import blackjack.domain.player.Dealer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OneCardOpenStrategyTest {
    @DisplayName("카드 반환 개수 전략패턴 테스트 - 1개만 반환(기본 설정 )")
    @Test
    void oneCardOpenStrategyDefaultSetting() {
        Cards allCards = Cards.createAllShuffledCards();
        Dealer dealer = new Dealer();

        dealer.drawRandomTwoCards(allCards);
        assertThat(dealer.getCards().size()).isEqualTo(1);

        dealer.drawRandomTwoCards(allCards);
        assertThat(dealer.getCards().size()).isEqualTo(1);
    }

    @DisplayName("카드 반환 개수 전략패턴 테스트 - 1개만 반환(수동 설정)")
    @Test
    void oneCardOpenStrategyManualSetting() {
        Cards allCards = Cards.createAllShuffledCards();
        Dealer dealer = new Dealer();

        dealer.drawRandomTwoCards(allCards);
        dealer.setCardOpenStrategy(new AllCardsOpenStrategy());
        assertThat(dealer.getCards().size()).isEqualTo(2);

        dealer.setCardOpenStrategy(new OneCardOpenStrategy());
        assertThat(dealer.getCards().size()).isEqualTo(1);

        dealer.drawRandomTwoCards(allCards);
        assertThat(dealer.getCards().size()).isEqualTo(1);

        dealer.setCardOpenStrategy(new AllCardsOpenStrategy());
        assertThat(dealer.getCards().size()).isEqualTo(4);
    }
}