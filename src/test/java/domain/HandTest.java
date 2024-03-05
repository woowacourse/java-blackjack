package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HandTest {

    @DisplayName("손패 인스턴스가 생성된다.")
    @Test
    void createHandTest() {
        // When
        Hand hand = Hand.init();

        // Then
        assertThat(hand).isNotNull();
    }
}
