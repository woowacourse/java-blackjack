package view;

import domain.card.Card;
import domain.game.BlackjackGame;
import domain.participant.Player;
import java.util.List;

public class OutputView {

    // TODO: 뷰 로직 수정 필요
    public void printCards(List<Card> cards) {
        for (Card card : cards) {
            System.out.print("카드 숫자: " + card.getRank().name() + ", ");
            System.out.println("카드 모양: " + card.getSymbol().name());
        }
    }

    public void printGame(BlackjackGame game) {
        for (Player player : game.getPlayers()) {
            System.out.print(player.getName() + "님의 카드입니다: ");
            printCards(player.getCards());
            System.out.println("점수 = " + player.score());
        }
    }
}
