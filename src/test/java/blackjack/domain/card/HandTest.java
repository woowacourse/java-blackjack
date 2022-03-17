package blackjack.domain.card;

import static blackjack.domain.fixture.CardRepository.CLOVER4;
import static blackjack.domain.fixture.CardRepository.CLOVER5;
import static blackjack.domain.fixture.CardRepository.CLOVER6;
import static blackjack.domain.fixture.CardRepository.CLOVER7;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HandTest {

    @DisplayName("of 팩토리 메소드는 두개의 카드를 받아 Hand 인스턴스를 생성한다.")
    @Test
    void of_initsNewHandWithTwoCards() {
        CardDeck cardDeck = new CardDeck();
        Hand hand = Hand.of(cardDeck.pop(), cardDeck.pop());

        assertThat(hand).isNotNull();
    }

    @DisplayName("add 메서드로 새로운 카드를 추가할 수 있다.")
    @Test
    void add() {
        Hand hand = Hand.of(CLOVER4, CLOVER5);
        hand.add(CLOVER6);

        assertThat(hand.getCards())
                .contains(CLOVER4, CLOVER5, CLOVER6);
    }

    @DisplayName("add 메서드로 중복된 카드를 추가하려고 하면 예외가 발생한다.")
    @Test
    void add_throwsExceptionOnDuplicateCard() {
        Hand hand = Hand.of(CLOVER4, CLOVER5);

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> hand.add(CLOVER5))
                .withMessage("중복된 카드는 존재할 수 없습니다.");
    }

    @DisplayName("getInitialCards 메서드는 게임 시작 시 받은 2장의 카드를 리스트로 반환한다.")
    @Test
    void getInitialCards_returnsInitialTwoCards() {
        // given
        Hand hand = Hand.of(CLOVER4, CLOVER5);
        hand.add(CLOVER6);
        hand.add(CLOVER7);

        // when
        List<Card> actual = hand.getInitialCards();

        // then
        assertThat(actual).containsExactly(CLOVER4, CLOVER5);
    }
}
