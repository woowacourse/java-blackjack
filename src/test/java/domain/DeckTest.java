package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardScore;
import domain.card.Deck;
import domain.card.RandomCardGenerator;
import domain.fake.AceCardGenerator;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    void 덱_생성_테스트() {
        //given
        final RandomCardGenerator randomCardGenerator = new RandomCardGenerator();

        //when
        final Deck deck = Deck.of(randomCardGenerator);

        //then
        assertThat(deck).isInstanceOf(Deck.class);
    }

    @Test
    void 카드를_한장_뽑는다() {
        //given
        final AceCardGenerator aceCardGenerator = new AceCardGenerator();

        //when
        final Deck deck = Deck.of(aceCardGenerator);
        final Card card = deck.pollCard();

        //then
        assertThat(card.score()).isEqualTo(CardScore.ACE);

    }
}
