package controller;

import domain.Answer;
import domain.CardDeck;
import domain.Dealer;
import domain.Game;
import domain.Participant;
import domain.Players;
import dto.DealerHandsDto;
import dto.PlayerDto;
import dto.PlayersDto;
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
        final Players players = Players.from(inputView.readNames());
        final CardDeck cardDeck = CardDeck.generate();
        final Dealer dealer = new Dealer(cardDeck);
        final Game game = new Game(dealer, players);

        dealer.startDeal(players);
        outputView.printStartDeal(DealerHandsDto.from(dealer), PlayersDto.from(players));

        for (Participant participant : players.getPlayers()) {
            deal(participant, dealer);
        }

        if (players.isAllBust()) {
            outputView.printHandsResult(PlayersDto.from(dealer, players));
            outputView.printGameResult(game.getDealerResult(), game.getPlayersResult());
            return;
        }

        while (dealer.getTotalCardSum() <= 16) {  //TODO 테스트 코드 고민
            dealer.deal();
            outputView.printDealerCard();
        }

        outputView.printHandsResult(PlayersDto.from(dealer, players));
        outputView.printGameResult(game.getDealerResult(), game.getPlayersResult());
    }

    private void deal(final Participant participant, final Dealer dealer) {
        boolean changed = false;
        Answer answer = Answer.HIT;

        while (Answer.HIT.equals(answer)) {
            answer = Answer.from(inputView.readAnswer(participant.getName()));
            dealer.deal(participant, answer);

            if (handsChanged(changed, answer)) {
                outputView.printHands(PlayerDto.from(participant));
            }
            if (participant.isBust()) {
                outputView.printBustMessage();
                break;
            }
            changed = true;

            if (participant.isBlackJack()) {
                outputView.printBlackJack();
                break;
            }
        }
    }

    private boolean handsChanged(final boolean changed, final Answer answer) {
        return (Answer.STAY.equals(answer) && !changed) || Answer.HIT.equals(answer);
    }
}
