package domain;

import domain.card.GameCardDeck;
import domain.participant.Dealer;
import domain.participant.Participant;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    void 딜러_생성_테스트() {
        //given
        Dealer dealer = new Dealer();

        //when & then
        Assertions.assertThat(dealer).isInstanceOf(Dealer.class);
    }

    @Test
    void 딜러_드로우_가능() {
        //given
        Dealer dealer = new Dealer();

        //when & then
        Assertions.assertThat(dealer.ableToDraw()).isTrue();
    }

    @Test
    void 딜러_드로우_실패() {
        //given
        Dealer dealer = new Dealer();

        GameCardDeck gameCardDeck = GameCardDeck.generateFullPlayingCard();
        dealer.drawCard(gameCardDeck, 12);
        //when & then
        Assertions.assertThat(dealer.ableToDraw()).isFalse();
    }

    @Test
    void 딜러_확인_성공() {
        //given
        Participant dealer = new Dealer();

        //when & then
        Assertions.assertThat(dealer.isDealer()).isTrue();
    }

}
