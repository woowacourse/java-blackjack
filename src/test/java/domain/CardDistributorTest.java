package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.CardDeckMaker;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CardDistributorTest {

    @Test
    @DisplayName("카드는 한 번 뽑으면 삭제된다.")
    void removeCardWhenPicked() {
        CardDistributor cardDistributor = new CardDistributor(CardDeckMaker.generate());

        Card card = cardDistributor.distribute();

        assertThat(cardDistributor.getDeckSize()).isEqualTo(51);
        assertThat(card).isNotNull();
    }

    @Test
    @DisplayName("게임 시작시 카드를 2장씩 분배한다.")
    void distributeTwoCardWhenStartGame() {
        CardDistributor cardDistributor = new CardDistributor(CardDeckMaker.generate());

        List<Card> cards = cardDistributor.distributeInitialCard();

        assertThat(cards.size()).isEqualTo(2);
    }

}
