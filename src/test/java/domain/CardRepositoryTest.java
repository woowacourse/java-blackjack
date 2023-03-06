package domain;

import domain.card.Card;
import domain.card.CardRepository;
import domain.card.Number;
import domain.card.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class CardRepositoryTest {
    private CardRepository cardRepository;

    @BeforeEach
    void setUp() {
        cardRepository = CardRepository.create(cards -> cards);
    }

    @Test
    @DisplayName("CardRepository가 생성되면 모든 종류의 카드가 저장되어 있다.")
    void initializingCardRepository() {
        assertThat(cardRepository.size()).isEqualTo(52);
    }

    @Test
    @DisplayName("CardRepository에서 인덱스가 주어지면 그 인덱스에 해당하는 카드를 가져온다.")
    void findCardByIndex() {
        Card card = cardRepository.findAnyOneCard();

        assertAll(
                () -> assertThat(card).isEqualTo(new Card(Shape.DIAMOND, Number.JACK)),
                () -> assertThat(cardRepository.size()).isEqualTo(51)
        );
    }
}