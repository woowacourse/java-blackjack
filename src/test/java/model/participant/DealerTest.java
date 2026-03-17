package model.participant;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import model.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DealerTest {

    Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = Dealer.of();
    }

    @Test
    void 딜러_정상_생성_테스트() {
        // given
        // when

        // then
        assertThat(dealer.getName()).isEqualTo("딜러");
    }

    @Test
    void 첫번째_턴의_딜러의_카드_오픈_테스트() {
        // given
        Card card1 = Card.of("스페이드", 3);
        Card card2 = Card.of("스페이드", 4);
        dealer.draw(card1);
        dealer.draw(card2);

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
        Card card3 = Card.of("스페이드", 5);
        dealer.draw(card1);
        dealer.draw(card2);

        Dealer dealer2 = Dealer.of();
        dealer2.draw(card1);
        dealer2.draw(card2);
        dealer2.draw(card3);

        // when
        dealer.open();
        List<String> opened = dealer.open();
        List<String> opened2 = dealer2.open();

        // then
        assertThat(opened).hasSize(1);
        assertThat(opened2).hasSize(3);
    }
}