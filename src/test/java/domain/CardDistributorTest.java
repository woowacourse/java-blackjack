package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import type.Letter;
import type.Shape;
import util.InitialCardMaker;

public class CardDistributorTest {

    @Test
    @DisplayName("카드는 한 번 뽑으면 삭제된다.")
    void removeCardWhenPicked() {
        CardDistributor cardDistributor = new CardDistributor(InitialCardMaker.generate());

        Card card = cardDistributor.distribute();

        assertThat(cardDistributor.getDeckSize()).isEqualTo(51);
        assertThat(card).isNotNull();
    }

    @Test
    @DisplayName("게임 시작시 카드를 2장씩 분배한다.")
    void distributeTwoCardWhenStartGame() {
        CardDistributor cardDistributor = new CardDistributor(InitialCardMaker.generate());

        Hand hand = cardDistributor.distributeInitialCard();

        assertThat(hand.getSize()).isEqualTo(2);
    }

    @Test
    @DisplayName("초기 분배할 수 있는 카드가 남아있지 않다면 예외가 발생한다")
    void exceptionWhenNoMoreCardToRun() {
        ArrayList<Card> cards = new ArrayList<>() {{
            add(Card.of(Shape.CLOVER, Letter.ACE));
        }};
        CardDistributor cardDistributor = new CardDistributor(cards);

        assertThatThrownBy(cardDistributor::distributeInitialCard)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("모든 참여자에게 카드를 분배할 수 없습니다.");
    }

    @Test
    @DisplayName("카드 분배기에 분배 가능한 카드가 남아있는지 확인한다.")
    void checkCardDistributorHasCardLeft() {
        ArrayList<Card> cards = new ArrayList<>() {{
            add(Card.of(Shape.CLOVER, Letter.ACE));
        }};
        CardDistributor cardDistributor = new CardDistributor(cards);

        assertThat(cardDistributor.isCardLeft()).isTrue();
    }

    @Test
    @DisplayName("카드 분배기에 분배 가능한 카드가 없는지 확인한다.")
    void checkCardDistributorNoCardLeft() {
        ArrayList<Card> cards = new ArrayList<>() {{
            add(Card.of(Shape.CLOVER, Letter.ACE));
        }};
        CardDistributor cardDistributor = new CardDistributor(cards);

        cardDistributor.distribute();

        assertThat(cardDistributor.isCardLeft()).isFalse();
    }

}
