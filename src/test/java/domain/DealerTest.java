package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardGroup;
import domain.card.CardType;
import domain.card.RandomCardGenerator;
import domain.fake.FaceCardGenerator;
import domain.gamer.Dealer;
import java.util.List;
import org.junit.jupiter.api.Test;

public class DealerTest {
    @Test
    void 딜러_점수가_16이하면_카드를_계속_뽑는다() {
        //given
        CardGroup cardGroup = new CardGroup();
        final FaceCardGenerator faceCardGenerator = new FaceCardGenerator();

        //when
        Dealer dealer = new Dealer(cardGroup, faceCardGenerator);
        dealer.hitCardUntilStand();
        int count = dealer.getReceivedCardCount() + GameManager.START_RECEIVE_CARD;

        //then
        assertThat(count).isEqualTo(2);
    }
}
