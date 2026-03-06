package domain.participant;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

public class DealerTest {
    @Test
    void 정상적으로_생성되어야_한다(){
        assertDoesNotThrow(() -> new Dealer("dealer"));
    }
}
