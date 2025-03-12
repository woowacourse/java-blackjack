package controller;

import domain.ScoreResult.BattleResults;
import domain.card.GameCardDeck;
import domain.ScoreResult.ScoreBoard;
import domain.game.GameRule;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
import dto.BattleResultResponse;
import dto.BlackJackResultResponse;
import dto.CardDeckStatusResponse;
import dto.ParticipantResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import view.Answer;
import view.InputView;
import view.OutputView;

public class BlackJackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final Dealer dealer = new Dealer();
        final List<Player> players = getPlayers();

        final Participants participants = makeParticipants(dealer, players);
        final GameCardDeck gameCardDeck = GameCardDeck.generateFullPlayingCard();

        startBlackJack(participants, gameCardDeck);
        endBlackJack(participants);

        inputView.closeScanner();
    }

    private void startBlackJack(final Participants participants, final GameCardDeck gameCardDeck) {
        gameCardDeck.shuffle();

        final Participant dealer = participants.findDealer();
        final Participants onlyPlayers = participants.findOnlyPlayers();

        drawTwoCards(participants, gameCardDeck);

        for (Participant participant : onlyPlayers.getParticipants()) {
            startTurnOf(participant, gameCardDeck);
        }
        startDealerTurn(dealer, gameCardDeck);

        printBlackJackScore(dealer, onlyPlayers);
    }

    private void drawTwoCards(final Participants participants, GameCardDeck gameCardDeck) {
        participants.drawTwoCards(gameCardDeck);

        Participant dealer = participants.findDealer();
        Participants onlyPlayers = participants.findOnlyPlayers();
        ParticipantResponse participantResponse = ParticipantResponse.of(dealer, onlyPlayers);
        outputView.printDrawTwoCardsMessage(participantResponse);

        CardDeckStatusResponse cardDeckStatusResponse = CardDeckStatusResponse.from(participants.getParticipants());
        outputView.printCardDeckStatus(cardDeckStatusResponse);
    }

    private void startTurnOf(final Participant player, final GameCardDeck gameCardDeck) {
        while (player.ableToDraw()) {
            Answer answer = inputView.askDrawOneMore(player.getNickname());
            if (answer == Answer.NO) {
                break;
            }
            player.drawCard(gameCardDeck, 1);
            CardDeckStatusResponse singleCardDeckStatusResponse = CardDeckStatusResponse.from(player.getNickname(), player.getCardDeck());
            outputView.printCardDeckStatus(singleCardDeckStatusResponse);
        }
    }

    private void startDealerTurn(final Participant dealer, final GameCardDeck gameCardDeck) {
        while (dealer.ableToDraw()) {
            dealer.drawCard(gameCardDeck, 1);
            outputView.printDrawSingleCardToDealerMessage(dealer.getNickname(), GameRule.DEALER_STAY.getValue());
        }
    }

    private void printBlackJackScore(final Participant dealer, final Participants onlyPlayers) {
        List<BlackJackResultResponse> blackJackResultResponses = new ArrayList<>();

        blackJackResultResponses.add(generateResultResponseOfDealer(dealer));
        for (Participant participant : onlyPlayers.getParticipants()) {
            blackJackResultResponses.add(generateResultResponseOfPlayer(participant));
        }

        outputView.printBlackJackResult(blackJackResultResponses);
    }

    private BlackJackResultResponse generateResultResponseOfPlayer(final Participant player) {
        String playerNickname = player.getNickname();
        List<String> playerCardNames = player.getCardDeck()
                .getCards().stream()
                .map(card -> card.getName() + card.getCardSymbol())
                .toList();

        return new BlackJackResultResponse(playerNickname, playerCardNames, player.getScore());
    }

    private BlackJackResultResponse generateResultResponseOfDealer(final Participant dealer) {
        String dealerNickname = dealer.getNickname();
        List<String> dealerCardNames = dealer.getCardDeck()
                .getCards().stream()
                .map(card -> card.getName() + card.getCardSymbol())
                .toList();

        return new BlackJackResultResponse(dealerNickname, dealerCardNames, dealer.getScore());
    }

    private void endBlackJack(final Participants participants) {
        final ScoreBoard scoreBoard = new ScoreBoard(participants);
        scoreBoard.calculateScoreBoard();
        Map<Participant, BattleResults> battleResultsMap = scoreBoard.getScoreBoard();

        List<BattleResultResponse> battleResultResponses = new ArrayList<>();
        for (Map.Entry<Participant, BattleResults> entryBattleResult : battleResultsMap.entrySet()) {
            String participantNickname = entryBattleResult.getKey().getNickname();
            Map<String, Integer> battleResult = entryBattleResult.getValue().getResults().entrySet().stream()
                    .collect(Collectors.toMap(
                            entry -> entry.getKey().getName(),
                            Entry::getValue
                    ));
            BattleResultResponse battleResultResponse = new BattleResultResponse(participantNickname, battleResult);
            battleResultResponses.add(battleResultResponse);
        }

        outputView.printBattleResult(battleResultResponses);
    }

    private Participants makeParticipants(final Dealer dealer, final List<Player> players) {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players);

        return new Participants(participants);
    }

    private List<Player> getPlayers() {
        final List<String> playerNames = inputView.askPlayerNames();
        final List<Player> players = new ArrayList<>();

        for (String name : playerNames) {
            players.add(new Player(name));
        }

        return players;
    }

}
