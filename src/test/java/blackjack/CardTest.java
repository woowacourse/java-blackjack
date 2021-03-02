package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    public void create(){
        Card card = new Card("스페이드",10);
        assertThat(card).isEqualTo(new Card("스페이드",10));
    }

    @Test
    public void getName(){
        Card card = new Card("다이아몬드",3);
        assertThat(card.getName()).isEqualTo("3다이아몬드");
    }

    @Test
    public void getScore(){
        Card card = new Card("다이아몬드",3);
        assertThat(card.getScore()).isEqualTo(3);
    }

}
