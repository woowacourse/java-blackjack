package model.participant;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import model.card.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DealerTest {
    Participant dealer;

    @BeforeEach
    void setUp() {
        dealer = Dealer.from("딜러");
    }

    @Test
    void 첫번째_턴의_딜러의_카드_오픈_테스트() {
        // given
        Card card1 = Card.of("스페이드", 3);
        Card card2 = Card.of("스페이드", 4);
        dealer.receive(card1);
        dealer.receive(card2);

        // when
        List<String> opened = dealer.open();

        // then
        assertThat(opened.getFirst()).isEqualTo(card1.toString());
        assertThat(opened).hasSize(1);
    }

    @Test
    void 첫번째가_아닌_턴의_딜러의_카드_오픈_테스트() {
        // given
        Card card1 = Card.of("스페이드", 3);
        Card card2 = Card.of("스페이드", 4);
        dealer.receive(card1);
        dealer.receive(card2);

        // when
        dealer.open();
        List<String> opened = dealer.open();

        // then
        assertThat(opened).contains(card1.toString(), card2.toString());
        assertThat(opened).containsAll(List.of(card1.toString(), card2.toString()));
        assertThat(opened).hasSize(2);
    }
}
