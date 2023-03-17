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
                Card.create(CardPattern.SPADE, CardNumber.THREE),
                Card.create(CardPattern.DIAMOND, CardNumber.FOUR),
                Card.create(CardPattern.CLOVER, CardNumber.FIVE));
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
                Card.create(CardPattern.SPADE, CardNumber.KING),
                Card.create(CardPattern.HEART, CardNumber.JACK),
                Card.create(CardPattern.HEART, CardNumber.THREE),
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

    public static CardShuffler createDealerBustShufflerWithTwoPlayer() {
        return (card) -> List.of(
                Card.create(CardPattern.SPADE, CardNumber.KING),
                Card.create(CardPattern.HEART, CardNumber.JACK),
                Card.create(CardPattern.SPADE, CardNumber.EIGHT),
                Card.create(CardPattern.SPADE, CardNumber.NINE),
                Card.create(CardPattern.HEART, CardNumber.SIX),
                Card.create(CardPattern.HEART, CardNumber.SEVEN),
                Card.create(CardPattern.HEART, CardNumber.KING));
    }

    public static CardShuffler createAllBlackJackShuffler() {
        return (card) -> List.of(
                Card.create(CardPattern.HEART, CardNumber.ACE),
                Card.create(CardPattern.HEART, CardNumber.KING),
                Card.create(CardPattern.SPADE, CardNumber.ACE),
                Card.create(CardPattern.SPADE, CardNumber.KING));
    }

    public static CardShuffler createOneWinAndOneLoseShuffler() {
        return (card) -> List.of(
                Card.create(CardPattern.HEART, CardNumber.EIGHT),
                Card.create(CardPattern.HEART, CardNumber.KING),
                Card.create(CardPattern.SPADE, CardNumber.TEN),
                Card.create(CardPattern.DIAMOND, CardNumber.QUEEN),
                Card.create(CardPattern.CLOVER, CardNumber.SEVEN),
                Card.create(CardPattern.CLOVER, CardNumber.JACK)
        );
    }
}
