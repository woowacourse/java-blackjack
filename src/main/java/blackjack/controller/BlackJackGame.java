package blackjack.controller;

import static java.util.stream.Collectors.toList;

import blackjack.domain.BlackJackCommand;
import blackjack.domain.BlackJackResult;
import blackjack.domain.card.CardDeck;
import blackjack.domain.cardGenerator.RandomCardGenerator;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Player;
import blackjack.dto.BlackJackResultDto;
import blackjack.dto.PlayerDto;
import blackjack.dto.PlayersDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BlackJackGame {

    private static final int DEFAULT_SPREAD_COUNT_START_INDEX = 0;
    private static final int DEFAULT_SPREAD_COUNT_END_INDEX = 2;
    private static final int BURST_CRITERIA = 21;

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void start() {
        final List<Player> gamblers = getGambler();
        final Player dealer = new Dealer();
        final CardDeck cardDeck = new CardDeck(new RandomCardGenerator());

        spreadCards(gamblers, dealer, cardDeck);
        playGame(gamblers, dealer, cardDeck);
        processResult(dealer, gamblers);
    }

    public List<Player> getGambler() {
        final List<String> playerNames = inputView.scanPlayerNames();
        return playerNames.stream()
            .map(Gambler::new)
            .collect(toList());
    }

    public void spreadCards(List<Player> gamblers, Player dealer, CardDeck cardDeck) {
        IntStream.range(DEFAULT_SPREAD_COUNT_START_INDEX, DEFAULT_SPREAD_COUNT_END_INDEX)
            .forEach(i -> spreadCard(gamblers, dealer, cardDeck));
        printSpreadCards(dealer, gamblers);
    }

    private void spreadCard(final List<Player> gamblers, final Player dealer, final CardDeck cardDeck) {
        Stream.concat(
            gamblers.stream(),
            Stream.of(dealer)
        ).forEach(cardDeck::drawTo);
    }

    private void printSpreadCards(Player dealer, List<Player> gamblers) {
        outputView.printSpreadInstruction(PlayersDto.from(gamblers));
        outputView.printSingleCardForDealer(PlayerDto.from(dealer));
        outputView.printCardsForGambler(PlayersDto.from(gamblers));
    }

    private List<Player> concatPlayers(final Player dealer, final List<Player> gamblers) {
        return Stream.concat(
            Stream.of(dealer),
            gamblers.stream()
        ).collect(Collectors.toList());
    }

    private void playGame(final List<Player> gamblers, final Player dealer, final CardDeck cardDeck) {
        playGameForGambler(gamblers, cardDeck);
        playGameForDealer(dealer, cardDeck);
    }

    private void playGameForGambler(final List<Player> gamblers, CardDeck cardDeck) {
        outputView.printNewLine();
        for (Player gambler : gamblers) {
            playGame(gambler, cardDeck);
        }
    }

    private void playGame(Player gambler, CardDeck cardDeck) {
        PlayerDto currentGamblerDto = PlayerDto.from(gambler);

        while (isHit(currentGamblerDto) && !isBurst(gambler, cardDeck)) {
            outputView.printCards(PlayerDto.from(gambler));
        }

        outputView.printCards(PlayerDto.from(gambler));
    }

    private boolean isHit(final PlayerDto currentGamblerDto) {
        return askHitOrStay(currentGamblerDto).equals(BlackJackCommand.YES);
    }

    private BlackJackCommand askHitOrStay(final PlayerDto currentGamblerDto) {
        return BlackJackCommand.from(inputView.scanHitOrStay(currentGamblerDto));
    }

    private boolean isBurst(final Player gambler, final CardDeck cardDeck) {
        if (gambler.isFinished(cardDeck)) {
            outputView.printBurst(PlayerDto.from(gambler));
            return true;
        }
        return false;
    }

    private void playGameForDealer(Player dealer, CardDeck cardDeck) {
        while (!dealer.isFinished(cardDeck)) {
            outputView.printDealerAddCard(dealer);
        }
        checkDealerBurst(dealer);
    }

    private void checkDealerBurst(final Player dealer) {
        if (dealer.getSumOfCards() > BURST_CRITERIA) {
            outputView.printBurst(PlayerDto.from(dealer));
        }
    }

    private void processResult(Player dealer, List<Player> gamblers) {
        outputView.printNewLine();
        outputView.printCardAndScore(PlayersDto.from(concatPlayers(dealer, gamblers)));
        final BlackJackResult result = BlackJackResult.of(dealer, gamblers);
        outputView.printResult(BlackJackResultDto.from(result));
    }
}
