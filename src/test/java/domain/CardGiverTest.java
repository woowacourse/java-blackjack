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

    @DisplayName("카드를 2장 배분한다")
    @Test
    void test1() {
        // given
        List<Card> fixedCards = CardFixture.createFixedCards();
        CardGiver cardGiver = new CardGiver(new Deck(new ArrayList<>(fixedCards)));

        //when
        Hand hand = cardGiver.giveDefault();

        //then
        assertThat(hand.getCards()).containsExactly(fixedCards.get(0), fixedCards.get(1));
    }

    @DisplayName("배분할 카드가 2장 미만일 시 예외가 발생한다")
    @Test
    void test19() {
        // given
        List<Card> emptyCards = CardFixture.createEmptyCards();
        CardGiver cardGiver = new CardGiver(new Deck(new ArrayList<>(emptyCards)));

        //when & then
        assertThatThrownBy(
                cardGiver::giveDefault
        ).isInstanceOf(IllegalStateException.class)
                .hasMessage(ERROR_HEADER + "카드가 충분하지 않습니다.");
    }

    @DisplayName("카드를 랜덤으로 1장 생성하여 배분한다")
    @Test
    void test4() {
        //given
        List<Card> fixedCards = CardFixture.createFixedCards();
        CardGiver cardGiver = new CardGiver(new Deck(new ArrayList<>(fixedCards)));
        //when
        Card card = cardGiver.giveOne();
        //then
        assertThat(card).isEqualTo(fixedCards.get(0));
    }

    @DisplayName("모든 참여자에게 기본적으로 2장씩 배분한다.")
    @Test
    void test33() {
        //given
        List<Card> fixedCards = CardFixture.createFixedCards();
        CardGiver cardGiver = new CardGiver(new Deck(new ArrayList<>(fixedCards)));

        Dealer dealer = Dealer.createEmpty();
        Player player = new Player("mimi", Hand.createEmpty());

        List<Participant> participants = List.of(dealer, player);
        //when
        cardGiver.giveDefaultTo(participants);

        //then
        SoftAssertions.assertSoftly(softly ->{
            softly.assertThat(dealer.hand).isEqualTo(new Hand(List.of(
                    fixedCards.get(0),
                    fixedCards.get(1)
            )));
            softly.assertThat(player.hand).isEqualTo(new Hand(List.of(
                    fixedCards.get(2),
                    fixedCards.get(3)
            )));
        });
    }
}
