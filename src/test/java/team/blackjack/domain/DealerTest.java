package team.blackjack.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @Test
    void hit하면_핸드에_카드가_추가된다() {
        Dealer dealer = new Dealer();
        dealer.hit(Card.ACE_OF_HEARTS);
        dealer.hit(Card.KING_OF_HEARTS);

        assertThat(dealer.getHand().getCards())
                .containsExactly(Card.ACE_OF_HEARTS, Card.KING_OF_HEARTS);
    }
    @Test
    void 킹과_에이스를_각각_1장씩_받은_딜러의_점수는_21로_정상_계산된다() {
        Dealer dealer = new Dealer();

        dealer.hit(Card.ACE_OF_HEARTS);
        dealer.hit(Card.KING_OF_CLUBS);

        assertThat(dealer.getScore()).isEqualTo(21);
    }

    @Test
    void 딜러가_카드를_받지않은_경우_점수는_0으로_정상_계산된다() {
        Dealer dealer = new Dealer();
        assertThat(dealer.getScore()).isEqualTo(0);
    }
}
