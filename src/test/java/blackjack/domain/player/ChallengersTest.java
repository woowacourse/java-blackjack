package blackjack.domain.player;

import blackjack.domain.card.Cards;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChallengersTest {

    private List<Challenger> challengers = new ArrayList<>();

    @BeforeEach
    void setUp() {
        for (int i = 1; i <= 9; ++i) {
            challengers.add(new Challenger(new Cards(new ArrayList<>()), new Name("pobi" + i)));
        }
    }

    @Test
    @DisplayName("참가자의 수가 9명이면, 예외가 발생한다.")
    void nineChallengers() {
        assertThatThrownBy(() -> new Challengers(challengers)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}