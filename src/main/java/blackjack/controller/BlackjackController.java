package blackjack.controller;

import blackjack.domain.Money;
import blackjack.domain.batting.Betting;
import blackjack.domain.batting.BettingResult;
import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import blackjack.domain.deck.Deck;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamer;
import blackjack.domain.result.GameResult;
import blackjack.domain.state.State;
import blackjack.domain.state.StateFactory;
import blackjack.dto.BettingResultDto;
import blackjack.dto.DealerDto;
import blackjack.dto.PlayerDto;
import blackjack.dto.PlayersDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackController {

    public void play() {
        Deck deck = initDeck();
        List<Gamer> gamers = initGamers(deck);
        Dealer dealer = initDealer(deck);
        Betting bettingController = betGamersAmount(gamers);
        OutputView.printGameStartMessage(DealerDto.from(dealer), PlayersDto.from(gamers));

        progressGamersHitOrStand(gamers, deck);
        progressDealerHitOrStand(dealer, deck);

        OutputView.printPlayersScoreInfo(PlayerDto.from(dealer), PlayersDto.from(gamers));
        GameResult gameResult = GameResult.of(dealer, gamers);
        BettingResult bettingResult = bettingController.calculateGamersProfit(gameResult);
        OutputView.printBettingResult(BettingResultDto.from(bettingResult));
    }

    private Deck initDeck() {
        List<Card> wholeCards = Arrays.stream(Denomination.values())
            .flatMap(denomination -> Arrays.stream(Shape.values())
                .map(shape -> Card.of(denomination, shape)))
            .collect(Collectors.toList());
        Deck deck = new Deck(wholeCards);
        deck.shuffle();
        return deck;
    }

    private List<Gamer> initGamers(Deck deck) {
        String[] gamerNames = InputView.inputGamerNames();
        return Arrays.stream(gamerNames)
            .map(gamerName -> new Gamer(gamerName, StateFactory.generateState(deck.draw(), deck.draw())))
            .collect(Collectors.toList());
    }

    private Dealer initDealer(Deck deck) {
        State state = StateFactory.generateState(deck.draw(), deck.draw());
        return new Dealer(state);
    }

    private Betting betGamersAmount(List<Gamer> gamers) {
        Betting betting = new Betting();
        for (Gamer gamer : gamers) {
            double bettingAmount = InputView.inputBettingAmount(PlayerDto.from(gamer));
            betting.betMoney(gamer, Money.of(bettingAmount));
        }
        return betting;
    }

    private void progressGamersHitOrStand(List<Gamer> gamers, Deck deck) {
        for (Gamer gamer : gamers) {
            progressGamersHitOrStandInput(gamer, deck);
        }
    }

    private void progressGamersHitOrStandInput(Gamer gamer, Deck deck) {
        while(gamer.canDraw() && InputView.inputHitOrStand(gamer.getName())) {
            gamer.addCard(deck.draw());
            OutputView.printPlayerCardInfo(PlayerDto.from(gamer));
        }
        gamer.stay();
    }

    private void progressDealerHitOrStand(Dealer dealer, Deck deck) {
        while (dealer.canDraw()) {
            dealer.addCard(deck.draw());
            OutputView.printDealerOnemoreDrawedMessage();
        }
        dealer.stay();
    }
}
