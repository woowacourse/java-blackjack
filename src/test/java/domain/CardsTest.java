package domain;

import factory.CardFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardsTest {
    @Test
    void create(){
        assertThat(new Cards()).isInstanceOf(Cards.class);
    }
}
