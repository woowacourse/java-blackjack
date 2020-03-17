package domain.gamer.dto;

import domain.card.Card;
import domain.card.PlayingCards;
import domain.card.Symbol;
import domain.card.Type;
import domain.gamer.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GamerWithCardsDtoTest {
    private GamerWithCardsDto gamerWithCardsDto;

    @BeforeEach
    void setUp() {
        PlayingCards playingCards = new PlayingCards(Collections.singletonList(new Card(Symbol.QUEEN, Type.CLOVER)));
        gamerWithCardsDto = GamerWithCardsDto.of(new Player(playingCards, "testName"));
    }

    @Test
    @DisplayName("생성 테스트")
    void of() {
        assertThat(GamerWithCardsDto.of(new Player(new PlayingCards(Collections.emptyList()), "testName"))).isNotNull();
    }

    @Test
    @DisplayName("한 장의 카드 리스트만 가진 Dto 생성")
    void ofWithFirstCard() {
        GamerWithCardsDto gamerWithCardsDto = GamerWithCardsDto.ofWithFirstCard(new Player(new PlayingCards(Arrays.asList(
                new Card(Symbol.KING, Type.CLOVER),
                new Card(Symbol.QUEEN, Type.CLOVER))), "testName"));
        assertThat(gamerWithCardsDto.getCards()).hasSize(1);
    }

    @Test
    @DisplayName("Getter name 테스트")
    void getName() {
        assertThat(gamerWithCardsDto.getName()).isEqualTo("testName");
    }

    @Test
    @DisplayName("Getter Cards 테스트")
    void getCards() {
        List<Card> cards = Collections.singletonList(new Card(Symbol.QUEEN, Type.CLOVER));
        assertThat(gamerWithCardsDto.getCards().get(0)).isEqualTo(cards.get(0));
    }
}