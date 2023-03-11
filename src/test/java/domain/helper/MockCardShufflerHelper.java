package domain.helper;

import domain.CardShuffler;
import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardPattern;

import java.util.List;

public class MockCardShufflerHelper {
    public static CardShuffler createDealerBlackJackShuffler() {
        return (card) -> List.of(
                Card.create(CardPattern.HEART, CardNumber.ACE),
                Card.create(CardPattern.HEART, CardNumber.KING),
                Card.create(CardPattern.SPADE, CardNumber.TWO),
                Card.create(CardPattern.SPADE, CardNumber.THREE));
    }

    public static CardShuffler createPlayerBlackJackShuffler() {
        return (card) -> List.of(
                Card.create(CardPattern.SPADE, CardNumber.TWO),
                Card.create(CardPattern.SPADE, CardNumber.THREE),
                Card.create(CardPattern.HEART, CardNumber.ACE),
                Card.create(CardPattern.HEART, CardNumber.KING));
    }

    public static CardShuffler createBustShuffler() {
        return (card) -> List.of(
                Card.create(CardPattern.SPADE, CardNumber.KING),
                Card.create(CardPattern.SPADE, CardNumber.QUEEN),
                Card.create(CardPattern.SPADE, CardNumber.THREE),
                Card.create(CardPattern.HEART, CardNumber.JACK),
                Card.create(CardPattern.HEART, CardNumber.QUEEN),
                Card.create(CardPattern.HEART, CardNumber.FOUR));
    }

    public static CardShuffler createDealerHighScoreShuffler() {
        return (card) -> List.of(
                Card.create(CardPattern.SPADE, CardNumber.KING),
                Card.create(CardPattern.SPADE, CardNumber.QUEEN),
                Card.create(CardPattern.HEART, CardNumber.JACK),
                Card.create(CardPattern.HEART, CardNumber.THREE));
    }

    public static CardShuffler createPlayerHighScoreShuffler() {
        return (card) -> List.of(
                Card.create(CardPattern.HEART, CardNumber.JACK),
                Card.create(CardPattern.HEART, CardNumber.THREE),
                Card.create(CardPattern.SPADE, CardNumber.KING),
                Card.create(CardPattern.SPADE, CardNumber.QUEEN));
    }
}
