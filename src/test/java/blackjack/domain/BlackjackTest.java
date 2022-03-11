package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackjackTest {
    private Blackjack blackjack;
    private IntendedNumberGenerator intendedNumberGenerator;

    @BeforeEach
    void setUp() {
        blackjack = new Blackjack();
        intendedNumberGenerator = new IntendedNumberGenerator(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13));
    }

    @DisplayName("처음 두장 나눠주는 기능 테스트")
    @Test
    void distributeInitialCardsTest() {
        List<Card> cards = blackjack.distributeInitialCards(intendedNumberGenerator);
        assertThat(cards.size()).isEqualTo(2);
    }

    @DisplayName("딜러에게 처음 두장 나눠주는 기능 테스트")
    @Test
    void distributeInitialCardsToDealerTest() {
        blackjack.distributeInitialCardsToDealer(intendedNumberGenerator);
        assertThat(blackjack.getDealer().getMyCards().size()).isEqualTo(2);
    }

    @DisplayName("한장 나눠주는 기능 테스트")
    @Test
    void distributeCardTest() {
        Card card = blackjack.distributeCard(intendedNumberGenerator);
        assertThat(Objects.nonNull(card)).isTrue();
    }

    @DisplayName("딜러가 hit할때까지 나눠주는 기능 테스트")
    @Test
    void distributeCardToDealerUntilHitTest() {
        int numberOfAddedCards = blackjack.distributeCardToDealerUntilHit(intendedNumberGenerator);
        assertThat(numberOfAddedCards).isEqualTo(4);
    }
}
