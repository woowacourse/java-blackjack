package controller;

import static java.util.stream.Collectors.toList;

import domain.BlackJackResult;
import domain.card.CardDeck;
import domain.player.Dealer;
import domain.player.Gambler;
import domain.player.Gamblers;
import dto.CardsAndScoreDto;
import dto.CardsDto;
import dto.NameDto;
import java.util.ArrayList;
import java.util.List;
import view.InputView;
import view.OutputView;
import vo.Wallet;

public class BlackJackController {
    public void start() {
        Gamblers gamblers = setupGamblers();
        Dealer dealer = new Dealer();
        CardDeck cardDeck = CardDeck.newInstance().shuffle();

        playGame(gamblers, dealer, cardDeck);
        printCardAndScore(dealer, gamblers);
        OutputView.printResult(BlackJackResult.of(dealer, gamblers));
    }

    public Gamblers setupGamblers() {
        List<Wallet> gamblerInfos = InputView.scanGamblerInfos();

        return new Gamblers(gamblerInfos
                .stream()
                .map(Gambler::new)
                .collect(toList()));
    }

    private void playGame(Gamblers gamblers, Dealer dealer, CardDeck cardDeck) {
        spreadCards(cardDeck, dealer, gamblers);
        playForGamblers(gamblers, cardDeck);
        playForDealer(dealer, cardDeck);
    }

    private void spreadCards(CardDeck cardDeck, Dealer dealer, Gamblers gamblers) {
        gamblers.addCard(cardDeck);
        dealer.addCard(cardDeck.drawCard());

        gamblers.addCard(cardDeck);
        dealer.addCard(cardDeck.drawCard());

        printCardsAfterInitialSpread(dealer, gamblers);
    }

    private void printCardsAfterInitialSpread(Dealer dealer, Gamblers gamblers) {
        OutputView.printSpreadAnnouncement(getNameDtosForPlayers(dealer, gamblers.getGamblers()));
        OutputView.printInitialOpenCards(getInitialOpenCardsDto(dealer, gamblers));
    }

    private List<NameDto> getNameDtosForPlayers(Dealer dealer, List<Gambler> gamblers) {
        List<NameDto> names = new ArrayList<>();
        names.add(NameDto.from(dealer));

        for (Gambler gambler : gamblers) {
            names.add(NameDto.from(gambler));
        }

        return names;
    }

    private List<CardsDto> getInitialOpenCardsDto(Dealer dealer, Gamblers gamblers) {
        List<CardsDto> cardsDtos = new ArrayList<>();
        cardsDtos.add(CardsDto.from(dealer));

        for (Gambler gambler : gamblers.getGamblers()) {
            cardsDtos.add(CardsDto.from(gambler));
        }

        return cardsDtos;
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
            OutputView.printCards(CardsDto.from(gambler));
            return;
        }

        do {
            gambler.addCard(cardDeck.drawCard());
            OutputView.printCards(CardsDto.from(gambler));
        } while (gambler.isHittable() && InputView.scanIsHit(gambler.getName()));
    }

    private void playForDealer(Dealer dealer, CardDeck cardDeck) {
        OutputView.printLineSeparator();

        while (dealer.isHittable()) {
            dealer.addCard(cardDeck.drawCard());
            OutputView.printDealerAddCard(dealer);
        }
    }

    private void printCardAndScore(Dealer dealer, Gamblers gamblers) {
        List<CardsAndScoreDto> cardsAndScoreDtos = new ArrayList<>();
        cardsAndScoreDtos.add(CardsAndScoreDto.from(dealer));

        for (Gambler gambler : gamblers.getGamblers()) {
            cardsAndScoreDtos.add(CardsAndScoreDto.from(gambler));
        }

        OutputView.printCardAndScoreDtos(cardsAndScoreDtos);
    }
}
