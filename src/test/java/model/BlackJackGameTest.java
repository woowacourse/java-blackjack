package model;

import model.card.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class BlackJackGameTest {

    private BlackJackGame blackJackGame;

    @BeforeEach
    void init() {
        blackJackGame = new BlackJackGame(new Deck());
    }

    @Test
    @DisplayName("카드를 뽑는지 테스트한다")
    void pickCardTest() {
        //given, when
        final Card card = blackJackGame.pickCard();

        //then
        assertAll(
                () -> assertThat(card.getClass()).isEqualTo(Card.class),
                () -> assertThat(card)
                        .extracting("shape")
                        .isNotNull(),
                () -> assertThat(card)
                        .extracting("value")
                        .isNotNull()
        );
    }
}
