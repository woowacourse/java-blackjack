package blackjack.controller;

import blackjack.domain.BlackJackGame;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardDeckShuffleStrategy;
import blackjack.domain.card.RandomCardDeckShuffleStrategy;
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

import java.util.LinkedList;
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
        Players players = readPlayersNameAndBet();
        BlackJackGame blackJackGame = initBlackJackGame(players);

        printInitialDealMessage(blackJackGame);
        printInitialHand(blackJackGame);
        repeatHitUntilStand(blackJackGame);

        printFinalHandAndScore(blackJackGame);
        printSettlement(blackJackGame);
    }

    private Players readPlayersNameAndBet() {
        List<String> names = inputView.readPlayerNames();

        List<Integer> bets = new LinkedList<>();
        for (String name : names) {
            int betAmount = inputView.readBetAmount(name);
            bets.add(betAmount);
        }

        return new Players(names, bets);
    }

    private BlackJackGame initBlackJackGame(Players players) {
        CardDeckShuffleStrategy randomCardDeckShuffleStrategy = new RandomCardDeckShuffleStrategy();
        CardDeck cardDeck = CardDeck.of(randomCardDeckShuffleStrategy);
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

    private void printFinalHandAndScore(BlackJackGame blackJackGame) {
        ParticipantsHandDto participantHandDtos = ParticipantsHandDto.from(blackJackGame.getEveryParticipants());
        outputView.printFinalHandAndScore(participantHandDtos);
    }

    private void printSettlement(BlackJackGame blackJackGame) {
        Map<Participant, Integer> gameResults = blackJackGame.settleBets();
        ParticipantSettlementDto participantSettlementDto = ParticipantSettlementDto.of(gameResults);
        outputView.printSettlement(participantSettlementDto);
    }
}
