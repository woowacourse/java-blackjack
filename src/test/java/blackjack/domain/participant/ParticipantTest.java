package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.generator.ManualDeckGenerator;

class ParticipantTest {

    private static final int INITIALLY_DRAW_CARD_COUNT = 2;

    private final ManualDeckGenerator manualCardStrategy = new ManualDeckGenerator();

    @DisplayName("게임을 시작할 때, 참여자는 2장의 카드를 뽑아야 한다.")
    @ParameterizedTest
    @MethodSource("blackjack.domain.participant.provider.ParticipantTestProvider#provideForInitiallyDrawCardsTest")
    void initiallyDrawCardsTest(final List<Card> expectedCards) {
        final Deck deck = this.generateDeck(expectedCards);
        final Participant participant = this.makeParticipant(deck);

        final List<Card> actualCards = participant.getCards();
        assertThat(actualCards.size()).isEqualTo(INITIALLY_DRAW_CARD_COUNT);
    }

    @DisplayName("참여자는 카드를 뽑을 수 있어야 한다.")
    @ParameterizedTest
    @MethodSource("blackjack.domain.participant.provider.ParticipantTestProvider#provideForDrawCardTest")
    void drawCardTest(final List<Card> initiallyDrewCards, final List<Card> drewCards) {
        final List<Card> expectedCards = concatCardList(initiallyDrewCards, drewCards);

        final Deck deck = this.generateDeck(expectedCards);
        final Participant participant = this.makeParticipant(deck);
        for (int i = 0; i < drewCards.size(); i++) {
            participant.drawCard(deck);
        }

        final List<Card> actualCards = participant.getCards();
        assertThat(actualCards).isEqualTo(expectedCards);
    }

    private Deck generateDeck(final List<Card> initializedCards) {
        manualCardStrategy.initCards(initializedCards);
        return Deck.generate(manualCardStrategy);
    }

    private Participant makeParticipant(final Deck deck) {
        return new Participant("Participant", deck) {
            @Override
            public boolean isPossibleToDrawCard() {
                return true;
            }
        };
    }

    private List<Card> concatCardList(final List<Card> cards1, final List<Card> cards2) {
        final List<Card> cards = new ArrayList<>();
        cards.addAll(cards1);
        cards.addAll(cards2);
        return cards;
    }

}
