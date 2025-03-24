package game;

import card.Card;
import card.CardDeck;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import user.Dealer;
import user.Participant;
import user.Participants;
import user.Player;
import view.InputView;
import view.OutputView;
import view.YesOrNo;

public class BlackjackConsole {
    private final OutputView outputView;
    private final InputView inputView;

    public BlackjackConsole(OutputView outputView, InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void run() {
        try {
            playBlackjack();
        } catch (IllegalArgumentException exception) {
            outputView.displayError(exception.getMessage());
        } finally {
            inputView.close();
        }
    }

    private void playBlackjack() {
        Dealer dealer = new Dealer();
        List<Player> players = getPlayers();
        BlackjackGame blackjackGame = BlackjackGame.of(players, dealer, new CardDeck());
        betMoney(blackjackGame);

        blackjackGame.firstHandOutCard();
        openFirstCards(blackjackGame);

        controlTurn(players, blackjackGame, dealer);

        openAllCards(players, dealer, blackjackGame);
        calculateBettingReward(blackjackGame, dealer);
    }

    private void betMoney(BlackjackGame blackjackGame) {
        Participants participants = blackjackGame.getParticipants();
        for (Participant participant : participants.getPlayers()) {
            Long bettingMoney = inputView.inputBettingMoney(participant.getName());
            participant.betMoney(bettingMoney);
        }
    }

    private void openFirstCards(BlackjackGame blackjackGame) {
        List<Card> dealerCards = blackjackGame.openFirstDealerCard();
        outputView.displayOpenCards(Dealer.DEALER_NAME, dealerCards);

        Map<String, List<Card>> playersCard = blackjackGame.openFirstPlayersCard();
        for (Entry<String, List<Card>> openCardSet : playersCard.entrySet()) {
            outputView.displayOpenCards(openCardSet.getKey(), openCardSet.getValue());
        }
    }

    private void controlTurn(List<Player> players, BlackjackGame blackjackGame, Dealer dealer) {
        for (Player player : players) {
            controlPlayerTurn(blackjackGame, player);
        }
        while (dealer.isDrawable()) {
            blackjackGame.controlTurn(dealer, YesOrNo.YES);
            outputView.displayDealerAddCard();
        }
    }

    private void controlPlayerTurn(BlackjackGame blackjackGame, Player player) {
        YesOrNo yesOrNo = YesOrNo.YES;
        while (player.isDrawable() && yesOrNo == YesOrNo.YES) {
            try {
                yesOrNo = inputView.inputYesOrNo(player.getName());
                blackjackGame.controlTurn(player, yesOrNo);
                displayOpenCard(player);
            } catch (IllegalArgumentException exception) {
                outputView.displayError(exception.getMessage());
            }
        }
    }

    private void calculateBettingReward(BlackjackGame blackjackGame, Dealer dealer) {
        Score score = new Score(blackjackGame.getParticipants());
        Map<Participant, GameResult> gameResult = score.calculatePlayerScore();

        Map<Participant, Double> rewards = score.calculateRewards(gameResult, dealer);

        outputView.displayRewards(rewards);
    }

    private List<Player> getPlayers() {
        List<String> playerNames = inputView.inputUsers();
        return playerNames.stream()
                .map(Player::new)
                .toList();
    }

    private void openAllCards(List<Player> players, Dealer dealer, BlackjackGame blackjackGame) {
        openAllCardIn(dealer, blackjackGame);

        for (Player player : players) {
            openAllCardIn(player, blackjackGame);
        }
    }

    private void openAllCardIn(Participant participant, BlackjackGame blackjackGame) {
        List<Card> cards = participant.openAllCard();
        int score = blackjackGame.calculateScore(participant);
        outputView.displayOpenCardsResult(participant.getName(), cards, score);
    }

    private void displayOpenCard(Participant participant) {
        List<Card> cards = participant.openInitialCard();
        outputView.displayOpenCards(participant.getName(), cards);
    }
}