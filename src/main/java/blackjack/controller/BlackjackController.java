package blackjack.controller;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import blackjack.domain.deck.Deck;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamer;
import blackjack.domain.result.GameResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import com.sun.tools.internal.ws.wsdl.document.Output;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackController {

    private final Deck deck;

    public BlackjackController() {
        List<Card> wholeCards = Arrays.stream(Denomination.values())
            .flatMap(denomination -> Arrays.stream(Shape.values()).map(shape -> Card.of(denomination, shape)))
            .collect(Collectors.toList());
        deck = new Deck(wholeCards);
        deck.shuffle();
    }

    public void play() {
        String[] gamerNames = InputView.inputGamerNames();
        List<Gamer> gamers = initGamers(gamerNames);
        Dealer dealer = initDealer();
        OutputView.printGameStartMessage(dealer, gamers);

        for (Gamer gamer : gamers) {
            while (gamer.canDraw() && InputView.inputHitOrStand(gamer.getName())) {
                gamer.addCard(deck.draw());
                OutputView.printPlayerCardInfo(gamer);
            }
        }

        while (dealer.canDraw()) {
            dealer.addCard(deck.draw());
            OutputView.printDealerOnemoreDrawMessage();
        }

        OutputView.printPlayersScoreInfo(dealer, gamers);
        GameResult gameResult = dealer.judgeGameResultWithGamers(gamers);
        OutputView.printGameResult(gameResult);
    }

    private List<Gamer> initGamers(String[] gamerNames) {
        return Arrays.stream(gamerNames)
            .map(gamerName -> new Gamer(gamerName, Cards.of(deck.drawTwoStartCards())))
            .collect(Collectors.toList());
    }

    private Dealer initDealer() {
        return new Dealer(Cards.of(deck.drawTwoStartCards()));
    }

}
