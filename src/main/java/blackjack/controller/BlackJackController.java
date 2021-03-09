package blackjack.controller;

import blackjack.domain.BlackJackGame;
import blackjack.domain.GameResult;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.rule.BlackJackScoreRule;
import blackjack.dto.DealerResultDto;
import blackjack.dto.ScoreResultDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackController {

    public void play() {
        Deck deck = Deck.generate();
        BlackJackGame blackJackGame = new BlackJackGame(deck, getParticipants(InputView.inputNames()));
        OutputView.printInitialCardStatus(blackJackGame.setupInitialCards());

        Participants participants = playBlackJackGame(blackJackGame, deck);
        OutputView.printAllParticipantsCards(participants);
        Dealer dealer = participants.extractDealer();
        List<Player> players = participants.extractPlayers();
        OutputView.printScoreResults(getDealerResultDto(dealer, players), getScoreResultDto(dealer, players));
    }

    private Participants playBlackJackGame(BlackJackGame blackJackGame, Deck deck) {
        while (!blackJackGame.isEnd()) {
            Participant participant = blackJackGame.getCurrentParticipant();
            playCurrentTurn(participant, deck);
            blackJackGame.turn();
        }

        return blackJackGame.getParticipants();
    }

    private void playCurrentTurn(Participant participant, Deck deck) {
        while (InputView.inputAskMoreCard(participant) && participant.isReceiveCard()) {
            participant.receiveCard(deck.draw());
            OutputView.printParticipantCards(participant);
        }
    }

    private Participants getParticipants(List<String> names) {
        List<Participant> participants = names.stream()
                .map(name -> new Player(name, new BlackJackScoreRule()))
                .collect(Collectors.toList());
        bettingPlayer(participants);
        participants.add(0, new Dealer(new BlackJackScoreRule()));
        return new Participants(participants);
    }

    private void bettingPlayer(List<Participant> participantList) {
        for (Participant participant: participantList) {
            int bettingMoney = InputView.inputBettingMoney(participant);
            participant.betting(bettingMoney);
        }
    }

    public DealerResultDto getDealerResultDto(Dealer dealer, List<Player> players) {
        Map<GameResult, Long> dealerResult = statisticsDealerResult(dealer, players);

        Arrays.stream(GameResult.values())
                .forEach(gameResult -> dealerResult.putIfAbsent(gameResult, 0L));
        return new DealerResultDto(dealer.getName(), dealerResult);
    }

    private Map<GameResult, Long> statisticsDealerResult(Dealer dealer, List<Player> players) {
        return players.stream()
                .collect(Collectors.groupingBy(player -> dealer.calculateResult(player.sumTotalScore()),
                        () -> new EnumMap<>(GameResult.class),
                        Collectors.counting()));
    }

    public List<ScoreResultDto> getScoreResultDto(Dealer dealer, List<Player> players) {
        return players.stream()
                .map(player -> new ScoreResultDto(player.getName(), player.calculateResult(dealer.sumTotalScore())))
                .collect(Collectors.toList());
    }

}
