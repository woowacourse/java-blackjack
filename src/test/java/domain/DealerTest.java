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
    void 딜러는_점수가_16점_이하인_경우_True를_반환한다() {
        List<Card> cards = List.of(new Card(CardType.DIAMOND_J), new Card(CardType.DIAMOND_2));
        CardGroup cardGroup = new CardGroup(cards);
        RandomCardGenerator randomCardGenerator = new RandomCardGenerator();
        Dealer dealer = new Dealer(cardGroup, randomCardGenerator);

        boolean status = dealer.isLessThen(16);

        assertThat(status).isTrue();
    }

    @Test
    void 딜러_점수가_16이하면_카드를_계속_뽑는다() {
        //given
        CardGroup cardGroup = new CardGroup();
        final FaceCardGenerator faceCardGenerator = new FaceCardGenerator();

        //when
        Dealer dealer = new Dealer(cardGroup, faceCardGenerator);
        final int count = dealer.giveCardsToDealer();
        //then
        assertThat(count).isEqualTo(2);
    }
}
