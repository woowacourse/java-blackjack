package controller;

import static java.util.stream.Collectors.toList;

import domain.BlackJackResult;
import domain.card.CardDeck;
import domain.player.Dealer;
import domain.player.Gambler;
import domain.player.Gamblers;
import dto.BlackjackResultDto;
import dto.CardsAndScoreDto;
import dto.CardsDto;
import dto.NameDto;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import vo.Wallet;

public class BlackjackGame {
    private final Gamblers gamblers;
    private final Dealer dealer;
    private final CardDeck cardDeck;

    public BlackjackGame(Supplier<List<Wallet>> walletSupplier) {
        this.gamblers = setupGamblers(walletSupplier);
        this.dealer = new Dealer();
        this.cardDeck = CardDeck.newInstance().shuffle();
    }

    private Gamblers setupGamblers(Supplier<List<Wallet>> walletSupplier) {
        return new Gamblers(walletSupplier.get()
                .stream()
                .map(Gambler::new)
                .collect(toList()));
    }

    public void spreadCards(BiConsumer<List<NameDto>, List<CardsDto>> initialInfoPrinter) {
        gamblers.addCard(cardDeck);
        dealer.addCard(cardDeck.drawCard());

        gamblers.addCard(cardDeck);
        dealer.addCard(cardDeck.drawCard());

        initialInfoPrinter.accept(getNameDtos(), getInitialOpenCardsDto());
    }

    private List<NameDto> getNameDtos() {
        List<NameDto> names = new ArrayList<>();
        names.add(NameDto.from(dealer));

        for (Gambler gambler : gamblers.getGamblers()) {
            names.add(NameDto.from(gambler));
        }

        return names;
    }

    private List<CardsDto> getInitialOpenCardsDto() {
        List<CardsDto> cardsDtos = new ArrayList<>();
        cardsDtos.add(CardsDto.from(dealer));

        for (Gambler gambler : gamblers.getGamblers()) {
            cardsDtos.add(CardsDto.from(gambler));
        }

        return cardsDtos;
    }

    public void playForGamblers(Function<String, Boolean> hitScanner,
                                Consumer<CardsDto> cardPrinter) {
        for (Gambler gambler : gamblers.getGamblers()) {
            playSingleGamblerGame(hitScanner, cardPrinter, gambler);
        }
    }

    private void playSingleGamblerGame(Function<String, Boolean> hitScanner,
                                       Consumer<CardsDto> cardPrinter,
                                       Gambler gambler) {
        boolean isHit = gambler.isHittable() && hitScanner.apply(gambler.getName());
        if (!isHit) {
            cardPrinter.accept(CardsDto.from(gambler));
            return;
        }

        do {
            gambler.addCard(cardDeck.drawCard());
            cardPrinter.accept(CardsDto.from(gambler));
        } while (gambler.isHittable() && hitScanner.apply(gambler.getName()));
    }

    public void playForDealer(Consumer<String> printer, Consumer<String> dealerHitProgressPrinter) {
        printer.accept(System.lineSeparator());

        while (dealer.isHittable()) {
            dealer.addCard(cardDeck.drawCard());
            dealerHitProgressPrinter.accept(dealer.getName());
        }
    }

    public void printCardsAndScore(Consumer<List<CardsAndScoreDto>> cardsAndScoreDtoConsumer) {
        List<CardsAndScoreDto> cardsAndScoreDtos = new ArrayList<>();
        cardsAndScoreDtos.add(CardsAndScoreDto.from(dealer));

        for (Gambler gambler : gamblers.getGamblers()) {
            cardsAndScoreDtos.add(CardsAndScoreDto.from(gambler));
        }

        cardsAndScoreDtoConsumer.accept(cardsAndScoreDtos);
    }

    public void printRevenue(Consumer<BlackjackResultDto> blackjackResultDtoPrinter) {
        blackjackResultDtoPrinter.accept(new BlackjackResultDto(BlackJackResult.of(dealer, gamblers)));
    }
}
