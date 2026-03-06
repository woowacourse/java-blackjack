import domain.GameCommand;
import domain.GameState;
import domain.GameStatistics;
import domain.Hand;
import domain.Name;
import domain.Participant;
import domain.Participants;
import domain.Referee;
import domain.card.Deck;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackjackGame {

    public void run() {
        Deck deck = new Deck();

        List<String> playerNames = InputView.readPlayerNames();
        Participants participants = new Participants(playerNames, deck);
        Participant dealer = initDealer(deck);

        OutputView.showIntroMessage(participants);
        OutputView.showDealerCardName(dealer);
        OutputView.showPlayerCardName(participants);

        for (Participant participant : participants.getParticipants()) {
            while (participant.getGameState() == GameState.HIT) {
                GameCommand gameCommand = GameCommand.from(
                        InputView.readHitOrStand(participant.getName()));
                if (gameCommand.isNo()) {
                    participant.changeState();
                    break;
                }
                participant.playTurn(deck);
                OutputView.showCardName(participant);
            }
        }
        // 딜러도 16이하 시 카드 추
        while (dealer.getGameState() == GameState.HIT) {
            OutputView.showDealerMessage();
            dealer.playTurn(deck);
            if (dealer.isBust() && dealer.getScore() >= 17) {
                dealer.changeState();
            }
        }
        // 딜러&플레이 카드 목록 및 결과 출력
        OutputView.showResult(dealer, participants);

        //승패 출력
        Referee referee = new Referee();
        GameStatistics statistics = referee.judge(dealer, participants);
        OutputView.showGameResult(statistics);
    }

    private static Participant initDealer(Deck deck) {
        Participant dealer = new Participant(new Name("딜러"), new Hand());
        dealer.initHand(deck);
        return dealer;
    }
}
