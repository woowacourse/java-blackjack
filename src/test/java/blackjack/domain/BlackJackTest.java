package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackTest {
    @Test
    @DisplayName("카드를 2장씩 나눈다.")
    void divideCard() {
        Player pepper = new Player("페퍼");
        Player ash = new Player("애쉬");
        PlayerGroup playerGroup = new PlayerGroup(Arrays.asList(pepper, ash));
        BlackJack blackJack = new BlackJack(playerGroup);
        blackJack.divideCards();

        List<Integer> cardSizes = List.of(pepper.getCardsSize(),
                ash.getCardsSize(),
                blackJack.getDealer().getCardsSize());

        assertThat(cardSizes)
                .containsExactly(2, 2, 2)
                .doesNotContain(0);
    }

    @Test
    @DisplayName("카드가 1장 더 추가되는지 테스트 한다.")
    void addCard() {
        Player pepper = new Player("페퍼");
        PlayerGroup playerGroup = new PlayerGroup(List.of(pepper));
        BlackJack blackJack = new BlackJack(playerGroup);
        int pepperCardsSize = pepper.getCardsSize();
        blackJack.addCard(pepper);

        assertThat(pepper.getCardsSize()).isEqualTo(pepperCardsSize + 1);
    }
}
