package blackjack.controller;

import blackjack.domain.Bet;
import blackjack.domain.BettingPot;
import blackjack.domain.BlackJackGame;
import blackjack.domain.PlayerGameResult;
import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.dto.ParticipantHandDto;
import blackjack.dto.ParticipantSettlementDto;
import blackjack.dto.ParticipantsHandDto;
import blackjack.dto.PlayersNameDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.Map;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Players players = readPlayers();
        BettingPot bettingPot = initBettingPot(players);
        BlackJackGame blackJackGame = initBlackJackGame(players);

        printInitialDealMessage(blackJackGame);
        printInitialHand(blackJackGame);
        repeatHitUntilStand(blackJackGame);

        printGameResult(blackJackGame);
        printSettlement(blackJackGame, bettingPot);
    }

    private Players readPlayers() {
        List<String> inputNames = inputView.readPlayerNames();

        return new Players(inputNames);
    }

    private BettingPot initBettingPot(Players players) {
        BettingPot bettingPot = new BettingPot();
        List<Player> everyPlayers = players.getPlayers();

        for (Player player : everyPlayers) {
            int betAmount = inputView.readBetAmount(player.getName());
            Bet bet = new Bet(betAmount);
            bettingPot.put(player, bet);
        }

        return bettingPot;
    }

    private BlackJackGame initBlackJackGame(Players players) {
        CardDeck cardDeck = CardDeck.of();
        Dealer dealer = new Dealer(cardDeck);

        BlackJackGame blackJackGame = new BlackJackGame(players, dealer);
        blackJackGame.initHand();

        return blackJackGame;
    }

    private void printInitialDealMessage(BlackJackGame blackJackGame) {
        List<Player> players = blackJackGame.getPlayers();
        PlayersNameDto playersNameDto = PlayersNameDto.from(players);
        outputView.printInitialDealMessage(playersNameDto);
    }

    private void printInitialHand(BlackJackGame blackJackGame) {
        List<Participant> initialParticipants = blackJackGame.getEveryParticipants();
        ParticipantsHandDto participantsHandDto = ParticipantsHandDto.from(initialParticipants);
        outputView.printInitialHand(participantsHandDto);
    }

    private void repeatHitUntilStand(BlackJackGame blackJackGame) {
        for (Player player : blackJackGame.getPlayers()) {
            repeatHitUntilPlayerStand(blackJackGame, player);
        }

        repeatHitUntilDealerStand(blackJackGame);
    }

    private void repeatHitUntilPlayerStand(BlackJackGame blackJackGame, Player player) {
        while (player.isHittable() && inputView.readCommand(player.getName()).equals("y")) {
            blackJackGame.hitParticipant(player);
            outputView.printHandAfterHit(ParticipantHandDto.from(player));
        }
    }

    private void repeatHitUntilDealerStand(BlackJackGame blackJackGame) {
        while (blackJackGame.isDealerHittable()) {
            blackJackGame.hitDealer();
            outputView.printDealerHitMessage();
        }
    }

    private void printGameResult(BlackJackGame blackJackGame) {
        ParticipantsHandDto participantHandDtos = ParticipantsHandDto.from(blackJackGame.getEveryParticipants());
        outputView.printFinalHandAndScore(participantHandDtos);
    }

    private void printSettlement(BlackJackGame blackJackGame, BettingPot bettingPot) {
        Map<Player, PlayerGameResult> gameResults = blackJackGame.getPlayerGameResult();
        Map<Player, Integer> playerSettlement = bettingPot.settlePlayerBet(gameResults);
        int dealerSettlement = bettingPot.settleDealerBet(gameResults);

        ParticipantSettlementDto participantSettlementDto
                = ParticipantSettlementDto.of(Dealer.DEALER_NAME, dealerSettlement, playerSettlement);
        outputView.printSettlement(participantSettlementDto);
    }
}
