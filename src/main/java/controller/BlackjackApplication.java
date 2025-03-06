package controller;

import domain.BlackjackReferee;
import domain.CardGiver;
import domain.CardRandomGenerator;
import domain.Dealer;
import domain.GameResult;
import domain.GivenCards;
import domain.Player;
import domain.PlayerRepository;
import java.util.List;
import view.AnswerType;
import view.InputView;
import view.OutputView;

public class BlackjackApplication {

    private final InputView inputView;
    private final OutputView outputView;
    private final PlayerRepository playerRepository;
    private final CardGiver cardGiver = new CardGiver(new CardRandomGenerator(), GivenCards.createEmpty());

    public BlackjackApplication(InputView inputView, OutputView outputView,
                                PlayerRepository playerRepository) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.playerRepository = playerRepository;
    }

    public void execute() {
        Dealer dealer = initializeDealer();

        initializeGame(dealer);

        askForAdditionalCard();

        decideAdditionalCardForDealer(dealer);

        calculateResult(dealer);
    }

    private Dealer initializeDealer() {
        return Dealer.createEmpty();
    }

    private void askForAdditionalCard() {
        List<Player> players = playerRepository.getAll();
        players.forEach(player -> {
            while (true) {
                AnswerType answerType = inputView.requestAdditionalCard(player);
                if(answerType.isNo()) {
                    outputView.printCurrentCard(player);
                    break;
                }
                player.getCards().add(cardGiver.giveOne());
                outputView.printCurrentCard(player);
                boolean isOver = player.getCards().isEqualAndMoreMaxSum();
                if(isOver) {
                    System.out.println("21 이상, 너 더 못받음");
                    break;
                }
            }
        });
    }

    private void calculateResult(Dealer dealer) {
        List<Player> tempParticipants = playerRepository.getAll();

        outputView.printCardsResult(dealer, tempParticipants);
        BlackjackReferee blackjackReferee = new BlackjackReferee();
        GameResult gameResult = blackjackReferee.judge(dealer, tempParticipants);
        outputView.printGameResults(gameResult);
    }

    private void decideAdditionalCardForDealer(Dealer dealer) {
        if(dealer.getCards().isUnderDrawLimit()) {
            dealer.getCards().add(cardGiver.giveOne());
            outputView.printDealerDraw();
            return;
        }
        outputView.printDealerNoDraw();
    }

    private void initializeGame(Dealer dealer) {
        List<String> playerNames = inputView.requestPlayerNames();

        List<Player> players = playerNames.stream()
                .map(playerName -> new Player(playerName, cardGiver.giveDefault()))
                .toList();
        playerRepository.addAll(players);

        dealer.addCards(cardGiver.giveDefault());

        outputView.printInitialCards(dealer, playerRepository.getAll());
    }
}
