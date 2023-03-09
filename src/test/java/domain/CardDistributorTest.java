package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.CardsMaker;

import static org.assertj.core.api.Assertions.assertThat;

public class CardDistributorTest {

    @Test
    @DisplayName("카드는 한 번 뽑으면 삭제된다.")
    void removeCardWhenPicked() {
        CardDistributor cardDistributor = new CardDistributor(CardsMaker.generate());

        Card card = cardDistributor.distribute();

        assertThat(cardDistributor.getCardsSize()).isEqualTo(51);
        assertThat(card).isNotNull();
    }

    @Test
    @DisplayName("게임 시작시 카드를 2장씩 분배한다.")
    void distributeTwoCardWhenStartGame() {
        CardDistributor cardDistributor = new CardDistributor(CardsMaker.generate());

        Cards cards = cardDistributor.distributeInitialCard();

        assertThat(cards.getSize()).isEqualTo(2);
    }

}
