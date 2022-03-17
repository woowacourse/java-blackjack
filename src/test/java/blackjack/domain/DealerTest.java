package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @DisplayName("카드 나눠주는 기능 테스트")
    @Test
    void handOut() {
        Dealer dealer = new Dealer();
        NumberGenerator numberGenerator = new IntendedNumberGenerator(List.of(2));
        Card card = dealer.handOutCard(numberGenerator);

        assertThat(card.type()).isEqualTo(BlackjackCardType.DIAMOND_3);
    }

    @DisplayName("오픈할 카드 고르는 기능 테스트")
    @Test
    void pickOneCardToOpenTest() {
        Dealer dealer = new Dealer();
        NumberGenerator numberGenerator = new IntendedNumberGenerator(List.of(1, 2));
        dealer.addCard(dealer.handOutCard(numberGenerator));
        dealer.addCard(dealer.handOutCard(numberGenerator));

        assertThat(dealer.pickOpenCardsInInitCards()).isEqualTo(Card.generateCard(BlackjackCardType.DIAMOND_2));
    }
}
