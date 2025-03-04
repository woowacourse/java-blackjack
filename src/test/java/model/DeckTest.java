package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {
    @Test
    void makeDeck() {
        //given
        Card firstCard = new Card(CardRank.FOUR, CardSuit.CLOVER);
        Deck deck = new Deck(new ArrayList<>(Arrays.asList(
                firstCard
        )));

        //when
        Card card = deck.pick();

        assertThat(card).isEqualTo(firstCard);
    }

    @Test
    @DisplayName("카드의 합 계산하는 로직")
    void 카드_합_계산하는_로직(){
        //given
        Deck deck = new Deck(new ArrayList<>(Arrays.asList(
                new Card(CardRank.FOUR, CardSuit.CLOVER),
                new Card(CardRank.FIVE, CardSuit.DIAMOND)
        )));
        List<Card> cards = new ArrayList<>();
        cards.add(deck.pick());
        cards.add(deck.pick());

        ParticipantHand participantHand = new ParticipantHand(cards);

        //when
        int result = participantHand.calculateScoreSum();
        int expect = 9;

        //then
        assertThat(result).isEqualTo(expect);
    }

    @Test
    @DisplayName("카드의 합 계산하는 로직")
    void 카드_합_계산하는_로직_2(){
        //given
        Deck deck = new Deck(new ArrayList<>(Arrays.asList(
                new Card(CardRank.SIX, CardSuit.CLOVER),
                new Card(CardRank.JACK, CardSuit.DIAMOND)
        )));
        List<Card> cards = new ArrayList<>();
        cards.add(deck.pick());
        cards.add(deck.pick());

        ParticipantHand participantHand = new ParticipantHand(cards);

        //when
        int result = participantHand.calculateScoreSum();
        int expect = 16;

        //then
        assertThat(result).isEqualTo(expect);
    }
}
