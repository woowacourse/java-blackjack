package model;

import view.Intent;
import model.card.Card;
import model.card.CardDeck;
import model.participant.*;
import model.score.MatchResult;
import view.InputView;
import view.OutputView;

import java.util.*;
import java.util.stream.Collectors;

public class BlackjackGame {

    private static final int INITIAL_DEAL_COUNT = 2;

    private Players players;
    private Dealer dealer;
    private final CardDeck deck = new CardDeck();

    public BlackjackGame() {
    }

    public void start() {
        this.players = createPlayers();
        Dealer dealer = Dealer.create();
        this.dealer = dealer;
        distributeStartingHand();
        OutputView.printDivisionStart(dealer, players);

        for (Player player : players.getPlayers()) {
            receiveAdditionalCard(player);
        }
        receiveAdditionalCard(dealer);

        OutputView.printAllParticipantScore(dealer, players);

        VictoryResultDto victoryResultDto = calculateVictory();
        OutputView.printResult(victoryResultDto);
    }

    private Players createPlayers() {
        Map<Nickname, Money> batingMoneys = new HashMap<>();
        for (Nickname nickname : readNicknames()) {
            Money money = readBatingMoneyBy(nickname);
            batingMoneys.put(nickname, money);
        }
        return Players.from(batingMoneys);
    }

    private Money readBatingMoneyBy(Nickname nickname) {
        return Money.from(InputView.readMoney(nickname));
    }

    private List<Nickname> readNicknames() {
        return InputView.readPlayerNames().stream()
                .map(Nickname::new)
                .collect(Collectors.toList());
    }

    private void receiveAdditionalCard(Player player) {
        while (satisfiedConditionByPlayer(player)) {
            distributeCardByParticipant(player, 1);
            OutputView.printCurrentHands(player);
        }
    }

    private void receiveAdditionalCard(Dealer dealer) {
        while (satisfiedConditionByDealer(dealer)) {
            distributeCardByParticipant(dealer, 1);
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

    public void distributeStartingHand() {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players.getPlayers());
        for (Participant participant : participants) {
            distributeCardByParticipant(participant, INITIAL_DEAL_COUNT);
        }
    }

    public void distributeCardByParticipant(Participant participant, int amount) {
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
