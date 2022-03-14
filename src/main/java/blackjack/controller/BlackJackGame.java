package blackjack.controller;

import static java.util.stream.Collectors.toList;

import blackjack.domain.BlackJackCommand;
import blackjack.domain.BlackJackResult;
import blackjack.domain.card.CardDeck;
import blackjack.domain.cardGenerator.RandomCardGenerator;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.dto.BlackJackResultDto;
import blackjack.dto.PlayerDto;
import blackjack.dto.PlayersDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackGame {

    private static final int DEFAULT_SPREAD_COUNT_START_INDEX = 0;
    private static final int DEFAULT_SPREAD_COUNT_END_INDEX = 2;
    private static final int BURST_CRITERIA = 21;
    private static final int GAMBLER_GET_CARD_UPPER_BOUND = 16;
    private static final int DEALER_GET_CARD_UPPER_BOUND = 21;

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void start() {
        final Players players = new Players(getDealer(), getGambler());
        final CardDeck cardDeck = new CardDeck(new RandomCardGenerator());

        spreadCards(players, cardDeck);
        playGame(players, cardDeck);
        processResult(players);
    }

    private Player getDealer() {
        return new Dealer();
    }

    public List<Player> getGambler() {
        final List<String> playerNames = inputView.scanPlayerNames();
        return playerNames.stream()
            .map(Gambler::new)
            .collect(toList());
    }

    protected void spreadCards(final Players players, final CardDeck cardDeck) {
        for (int i = DEFAULT_SPREAD_COUNT_START_INDEX; i < DEFAULT_SPREAD_COUNT_END_INDEX; i++) {
            spreadCard(players, cardDeck);
        }
        printSpreadCards(players);
    }


    private void spreadCard(final Players players, final CardDeck cardDeck) {
        players.receiveCard(cardDeck);
    }

    private void printSpreadCards(final Players players) {
        outputView.printSpreadInstruction(PlayersDto.from(players));
        outputView.printSingleCardForDealer(PlayerDto.getDealerFrom(players));
        outputView.printCardsForGambler(PlayersDto.getGamblersFrom(players));
    }

    private void playGame(final Players players, final CardDeck cardDeck) {
        playGameForGambler(players.getGamblers(), cardDeck);
        playGameForDealer(players.getDealer(), cardDeck);
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
        if (gambler.isFinished(cardDeck, GAMBLER_GET_CARD_UPPER_BOUND)) {
            outputView.printBurst(PlayerDto.from(gambler));
            return true;
        }
        return false;
    }

    private void playGameForDealer(Player dealer, CardDeck cardDeck) {
        while (!dealer.isFinished(cardDeck, DEALER_GET_CARD_UPPER_BOUND)) {
            outputView.printDealerAddCard(dealer);
        }
        checkDealerBurst(dealer);
    }

    private void checkDealerBurst(final Player dealer) {
        if (dealer.getSumOfCards() > DEALER_GET_CARD_UPPER_BOUND) {
            outputView.printBurst(PlayerDto.from(dealer));
        }
    }

    private void processResult(Players players) {
        outputView.printNewLine();
        outputView.printCardAndScore(PlayersDto.from(players));
        final BlackJackResult result = BlackJackResult.of(players);
        outputView.printResult(BlackJackResultDto.from(result));
    }
}
