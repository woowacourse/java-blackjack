package blackjack.controller;

import blackjack.domain.BlackJackGame;
import blackjack.domain.Command;
import blackjack.domain.Money;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.*;
import blackjack.dto.BlackJackProfitDto;
import blackjack.dto.PlayerProfitDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.*;
import java.util.function.Supplier;

public class BlackJackGameController {

    private Deck deck;

    public void run() {
        final BlackJackGame blackJackGame = readUntilValidate(this::generateBlackJackGame);
        deck = new Deck();

        final Dealer dealer = blackJackGame.getDealer();
        final Players players = blackJackGame.getPlayers();

        final Map<Player, Money> playerBetting = generateBettingMoney(players);

        blackJackGame.handOutInitCards(deck);
        OutputView.printInitCard(players.getPlayers(), dealer.getFirstCard());

        handOutHitCard(blackJackGame, players, dealer);
        judgeGameResult(blackJackGame, players, dealer, playerBetting);
    }

    private BlackJackGame generateBlackJackGame() {
        final String playerNames = InputView.readNames();
        return new BlackJackGame(playerNames);
    }

    private Map<Player, Money> generateBettingMoney(final Players players) {
        final Map<Player, Money> playerBetting = new LinkedHashMap<>();
        for (final Player player : players.getPlayers()) {
            final Money bettingMoney = readUntilValidate(() -> generateBettingMoney(player));
            playerBetting.put(player, bettingMoney);
        }
        return playerBetting;
    }

    private Money generateBettingMoney(final Player player) {
        final int money = InputView.readBettingMoney(player.getName());
        return Money.forBetting(money);
    }

    private void handOutHitCard(BlackJackGame blackJackGame, Players players, Dealer dealer) {
        for (final Player player : players.getPlayers()) {
            handOutCardToEachPlayer(blackJackGame, player);
        }
        handOutCardToDealer(blackJackGame, dealer);
    }

    private void handOutCardToDealer(BlackJackGame blackJackGame, Dealer dealer) {
        while (dealer.isUnderThanBoundary(Dealer.DRAWING_BOUNDARY)) {
            blackJackGame.handOutCardTo(deck, dealer);
            OutputView.printDealerReceiveOneMoreCard();
        }
    }

    private void handOutCardToEachPlayer(BlackJackGame blackJackGame, Player player) {
        boolean isHitCommand = true;
        while (player.isUnderThanBoundary(Participant.BUST_BOUNDARY) && isHitCommand) {
            Command gameCommand = readUntilValidate(() -> generateGameCommand(player));
            isHitCommand = handOutCardByCommand(blackJackGame, player, gameCommand);
        }
    }

    private boolean handOutCardByCommand(BlackJackGame blackJackGame, Player player, Command playerAnswer) {
        if (playerAnswer.isHit()) {
            blackJackGame.handOutCardTo(deck, player);
            OutputView.printParticipantCards(player.getName(), player.getCards());
            return true;
        }
        OutputView.printParticipantCards(player.getName(), player.getCards());
        return false;
    }

    private Command generateGameCommand(final Player player) {
        final String gameCommand = InputView.readGameCommandToGetOneMoreCard(player.getName());
        return Command.from(gameCommand);
    }

    private void judgeGameResult(BlackJackGame blackJackGame, Players players, Dealer dealer, Map<Player, Money> playerBetting) {
        final DealerResult dealerResult = new DealerResult();
        final PlayerResult playerResult = new PlayerResult();
        blackJackGame.calculateParticipantResult(dealerResult, playerResult);

        final Map<Player, Money> playerProfit = blackJackGame.calculatePlayerProfit(playerResult, playerBetting);
        final Money dealerProfit = blackJackGame.calculateDealerProfit(playerProfit);

        final List<PlayerProfitDto> playerProfitDto = new ArrayList<>();
        for (Player player : playerProfit.keySet()) {
            playerProfitDto.add(new PlayerProfitDto(player.getName(), playerProfit.get(player).getValue()));
        }
        final BlackJackProfitDto blackJackProfitDto = new BlackJackProfitDto(dealerProfit.getValue(), playerProfitDto);

        OutputView.printCardsWithSum(dealer, players.getPlayers());
        OutputView.printFinalProfit(blackJackProfitDto);
    }

    private <T> T readUntilValidate(final Supplier<T> supplier) {
        Optional<T> userInput;
        do {
            userInput = readUserInput(supplier);
        } while (userInput.isEmpty());
        return userInput.get();
    }

    private <T> Optional<T> readUserInput(final Supplier<T> supplier) {
        try {
            return Optional.of(supplier.get());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return Optional.empty();
        }
    }
}
