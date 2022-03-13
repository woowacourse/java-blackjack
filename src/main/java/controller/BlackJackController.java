package controller;

import static java.util.stream.Collectors.toList;

import domain.BlackJackResult;
import domain.card.CardDeck;
import domain.player.Dealer;
import domain.player.Gambler;
import domain.player.Gamblers;
import dto.PlayerDto;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackJackController {
    public void start() {
        Gamblers gamblers = setupGamblers();
        Dealer dealer = new Dealer();
        CardDeck cardDeck = CardDeck.newInstance().shuffle();

        playGame(gamblers, dealer, cardDeck);
        OutputView.printResult(BlackJackResult.of(dealer, gamblers));
    }

    public Gamblers setupGamblers() {
        return new Gamblers(InputView.scanPlayerNames()
                .stream()
                .map(Gambler::new)
                .collect(toList()));
    }

    private void playGame(Gamblers gamblers, Dealer dealer, CardDeck cardDeck) {
        spreadCards(cardDeck, dealer, gamblers);
        playForGamblers(gamblers, cardDeck);
        playForDealer(dealer, cardDeck);
        printCardAndScore(dealer, gamblers);
    }

    public void spreadCards(CardDeck cardDeck, Dealer dealer, Gamblers gamblers) {
        gamblers.getGamblers()
                .forEach(gambler -> gambler.addCard(cardDeck.getCard()));
        dealer.addCard(cardDeck.getCard());

        gamblers.getGamblers()
                .forEach(gambler -> gambler.addCard(cardDeck.getCard()));
        dealer.addCard(cardDeck.getCard());

        printCardsAfterInitialSpread(dealer, gamblers);
    }

    private void printCardsAfterInitialSpread(Dealer dealer, Gamblers gamblers) {
        OutputView.printSpreadAnnouncement(dealer.getName(), gamblers.getGamblerNames());
        OutputView.printSingleCardForDealer(PlayerDto.from(dealer));
        OutputView.printTwoCardsForGamblers(getPlayerDtos(gamblers));
    }

    private List<PlayerDto> getPlayerDtos(Gamblers gamblers) {
        return gamblers.getGamblers()
                .stream()
                .map(PlayerDto::from)
                .collect(toList());
    }

    private void playForGamblers(Gamblers gamblers, CardDeck cardDeck) {
        OutputView.printLineSeparator();

        for (Gambler gambler : gamblers.getGamblers()) {
            playSingleGamblerGame(gambler, cardDeck);
        }
    }

    private void playSingleGamblerGame(Gambler gambler, CardDeck cardDeck) {
        boolean isHit = InputView.scanIsHit(gambler.getName());
        if (!isHit) {
            OutputView.printCards(PlayerDto.from(gambler));
            return;
        }

        while (isHit) {
            addCard(gambler, cardDeck);
            isHit = !gambler.isBust() && !gambler.isBlackJack() && InputView.scanIsHit(gambler.getName());
        }
    }

    private void addCard(Gambler gambler, CardDeck cardDeck) {
        gambler.addCard(cardDeck.getCard());
        OutputView.printCards(PlayerDto.from(gambler));
    }

    private void playForDealer(Dealer dealer, CardDeck cardDeck) {
        OutputView.printLineSeparator();

        while (dealer.isUnderSixteen()) {
            dealer.addCard(cardDeck.getCard());
            OutputView.printDealerAddCard(dealer);
        }
    }

    private void printCardAndScore(Dealer dealer, Gamblers gamblers) {
        OutputView.printLineSeparator();
        OutputView.printCardAndScore(PlayerDto.from(dealer));

        gamblers.getGamblers()
                .stream()
                .map(PlayerDto::from)
                .forEach(OutputView::printCardAndScore);
    }
}
