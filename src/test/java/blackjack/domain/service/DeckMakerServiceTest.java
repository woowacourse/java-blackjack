package blackjack.domain.service;

import blackjack.domain.model.Card;
import blackjack.domain.model.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;

public class DeckMakerServiceTest {
    @Test
    @DisplayName("덱 메이커 생성자 테스트")
    void constructorTest(){
        assertThatNoException().isThrownBy(()-> new DeckMakerService());
    }

    @Test
    @DisplayName("중복되지 않은 52장의 카드 생성 테스트")
    void makeDeckTest(){
        // given
        DeckMakerService deckMakerService = new DeckMakerService();

        // when
        List<Card> deck = deckMakerService.makeDeck();
        HashSet<Card> cardSet = new HashSet<>(deck);
        // then
        assertThat(cardSet.size()).isEqualTo(deck.size());
        assertThat(deck.size()).isEqualTo(52);
    }
}
