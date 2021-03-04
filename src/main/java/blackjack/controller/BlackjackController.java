package blackjack.controller;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import blackjack.domain.deck.Deck;
import blackjack.domain.player.Gamer;
import blackjack.view.InputView;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackController {

    private final Deck deck;

    public BlackjackController() {
        List<Card> wholeCards = Arrays.stream(Denomination.values())
            .flatMap(denomination -> Arrays.stream(Shape.values()).map(shape -> Card.of(denomination, shape)))
            .collect(Collectors.toList());
        this.deck = new Deck(wholeCards);
    }

    public void play() {
        String[] gamerNames = InputView.inputGamerNames();
        List<Gamer> gamers = initGamers(gamerNames);
    }

    private List<Gamer> initGamers(String[] gamerNames) {
        return Arrays.stream(gamerNames)
            .map(gamerName -> new Gamer(gamerName, Cards.of(deck.drawTwoStartCards())))
            .collect(Collectors.toList());
    }

}
