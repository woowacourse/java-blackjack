package model;

import controller.Intent;
import model.card.Card;
import model.card.CardDeck;
import model.participant.Dealer;
import model.participant.Participant;
import model.participant.Player;
import model.participant.Players;
import model.score.MatchResult;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;

public class BlackjackGame {

    private static final int INITIAL_DEAL_COUNT = 2;

    private Players players;
    private Dealer dealer;
    private final CardDeck deck = new CardDeck();

    public BlackjackGame() {
    }

    public void start() {
        Players players = Players.from(InputView.readPlayerNames());
        this.players = players;
        Dealer dealer = Dealer.create();
        this.dealer = dealer;
        divideAllParticipant();
        OutputView.printDivisionStart(dealer, players);

        for (Player player : players.getPlayers()) {
            receiveAdditionalCard(player);
        }
        receiveAdditionalCard(dealer);

        OutputView.printAllParticipantScore(dealer, players);

        VictoryResultDto victoryResultDto = calculateVictory();
        OutputView.printResult(victoryResultDto);
    }

    private void receiveAdditionalCard(Player player) {
        while (satisfiedConditionByPlayer(player)) {
            divideCardByParticipant(player, 1);
            OutputView.printCurrentHands(player);
        }
    }

    private void receiveAdditionalCard(Dealer dealer) {
        while (satisfiedConditionByDealer(dealer)) {
            divideCardByParticipant(dealer, 1);
            OutputView.printStandingDealer(dealer);
        }
    }

    private boolean satisfiedConditionByPlayer(Player player) {
        return player.ableToDraw() && agreeIntent(player);
    }

    private boolean satisfiedConditionByDealer(Dealer dealer) {
        return dealer.ableToDraw();
    }

    private boolean agreeIntent(Player player) {
        return Intent.from(InputView.readIntent(player.getNickname())).equals(Intent.Y);
    }

    public void divideAllParticipant() {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players.getPlayers());
        for (Participant participant : participants) {
            divideCardByParticipant(participant, INITIAL_DEAL_COUNT);
        }
    }

    public void  divideCardByParticipant(Participant participant, int amount) {
        List<Card> pickCards = deck.pickCard(amount);
        participant.addCards(pickCards);
    }

    public VictoryResultDto calculateVictory() {
        HashMap<Player, MatchResult> playersMatchResult = players.getMatchResult(dealer);
        EnumMap<MatchResult, Integer> matchCounts = players.getMatchCount(dealer);
        EnumMap<MatchResult, Integer> dealerMatchResult = MatchResult.reverseAll(matchCounts);

        return new VictoryResultDto(playersMatchResult, dealerMatchResult);
    }
}

