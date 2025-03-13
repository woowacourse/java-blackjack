package card;

import static org.assertj.core.api.Assertions.assertThat;

import constant.TrumpEmblem;
import constant.TrumpNumber;
import game.Card;
import game.Cards;
import game.Deck;
import strategy.DeckSettingStrategy;
import strategy.DeckShuffleStrategy;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class DeckTest {


    @Test
    void 덱_생성_시_사이즈가_52임을_확인한다() {
        // when
        Deck deck = new Deck(new DeckShuffleStrategy());

        // then
        assertThat(deck.getSize()).isEqualTo(52);
    }

    @Test
    void 카드_한_장을_뽑을_시_카드_숫자의_범위를_확인한다() {
        // given
        Deck deck = new Deck(new DeckShuffleStrategy());

        // when
        Card card = deck.drawOneCard();

        // then
        assertThat(card.getNumber().getValue()).isBetween(1, 11);
    }

    @Test
    void 초기_카드를_두_장_뽑는다() {
        // given
        Deck deck = new Deck(new DeckShuffleStrategy());

        // when
        Cards cards = deck.drawInitialCards();

        // then
        assertThat(cards.getSize()).isEqualTo(2);
    }

    @Test
    void 덱_세팅_전략에_따라_덱을_구성한다() {
        Deck deck = new Deck(new DeckSettingStrategy() {
            @Override
            public Cards initialize() {
                List<Card> cards = Arrays.stream(TrumpNumber.values())
                        .flatMap(number -> Arrays.stream(TrumpEmblem.values())
                                .map(emblem -> new Card(number, emblem)))
                        .collect(Collectors.toList());
                return new Cards(cards);
            }
        });

        assertThat(deck.drawOneCard().getEmblem()).isEqualTo(TrumpEmblem.DIAMOND);
        assertThat(deck.drawOneCard().getNumber()).isEqualTo(TrumpNumber.KING);
    }
}
