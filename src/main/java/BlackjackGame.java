import domain.GameCommand;
import domain.GameState;
import domain.Hand;
import domain.Name;
import domain.Participant;
import domain.Participants;
import domain.card.Card;
import domain.card.Deck;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackjackGame {

    public void run() {
        List<String> playerNames = InputView.readPlayerNames();

        Deck deck = new Deck();
        Participants participants = new Participants();

        for (String playerName : playerNames) {
            Participant participant = new Participant(new Name(playerName), new Hand());
            participants.add(participant);
            for (int i = 0; i < 2; i++) {
                Card card = deck.drawCard();
                participant.receiveCard(card);
            }
        }

        Participant dealer = new Participant(new Name("딜러"), new Hand());
        for (int i = 0; i < 2; i++) {
            Card card = deck.drawCard();
            dealer.receiveCard(card);
        }

        OutputView.showIntroMessage(participants);
        OutputView.showDealerCardName(dealer);

        for (Participant participant : participants.getPlayers()) {
            OutputView.showCardName(participant);
        }

        for (Participant participant : participants.getPlayers()) {
            while (participant.getGameState() == GameState.HIT) {
                GameCommand gameCommand = GameCommand.from(
                        InputView.readHitOrStand(participant.getName()));
                if (!gameCommand.isYes()) {
                    participant.changeState();
                }
                if (gameCommand.isYes()) {
                    Card hitCard = deck.drawCard();
                    participant.receiveCard(hitCard);
                    if (participant.isBust()) {
                        participant.changeState();
                    }
                }
                OutputView.showCardName(participant);
            }
        }
        // 딜러도 16이하 시 카드 추
        while (dealer.getGameState() == GameState.HIT) {
            OutputView.showDealerMessage();
            Card receivedCard = deck.drawCard();
            dealer.receiveCard(receivedCard);
            if (dealer.isBust() && dealer.getScore() >= 17) {
                dealer.changeState();
            }
        }
        // 딜러&플레이 카드 목록 및 결과 출력
        OutputView.showResult(dealer, participants);

    }
}
