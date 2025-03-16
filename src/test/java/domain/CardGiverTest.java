package domain;

import static org.assertj.core.api.Assertions.*;
import static util.ExceptionConstants.*;

import fixture.CardFixture;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardGiverTest {

    @DisplayName("모든 참여자들에게 카드를 2장 배분한다")
    @Test
    void test1() {
        // given
        List<Card> fixedCards = CardFixture.createFixedCards();
        CardGiver cardGiver = new CardGiver(new Deck(new ArrayList<>(fixedCards)));
        Player player = new Player("이름", Hand.createEmpty());
        Dealer dealer = Dealer.createEmpty();
        //when
        cardGiver.dealingTo(new ArrayList<>(List.of(player, dealer)));

        //then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(player.getHand()).isEqualTo(new Hand(List.of(fixedCards.get(0), fixedCards.get(1))));
            softly.assertThat(dealer.getHand()).isEqualTo(new Hand(List.of(fixedCards.get(2), fixedCards.get(3))));
        });
    }

    @DisplayName("배분할 카드가 2장 미만일 시 예외가 발생한다")
    @Test
    void test19() {
        // given
        List<Card> emptyCards = CardFixture.createEmptyCards();
        CardGiver cardGiver = new CardGiver(new Deck(new ArrayList<>(emptyCards)));
        Dealer dealer = Dealer.createEmpty();

        //when & then
        assertThatThrownBy(
                () -> cardGiver.dealingTo(new ArrayList<>(List.of(dealer)))
        ).isInstanceOf(IllegalStateException.class)
                .hasMessage(ERROR_HEADER + "카드가 충분하지 않습니다.");
    }
}
