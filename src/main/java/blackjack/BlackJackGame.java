package blackjack;

import blackjack.domain.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackGame {

    //TODO 2개로 줄이기 (논의사항)
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final Deck deck = Deck.createShuffledDeck();

    public void run() {
        Dealer dealer = new Dealer();
        List<String> names = inputView.inputPlayerNames();
        Players players = Players.from(names);

        dealer.drawStartCards(deck);
        players.drawStartCards(deck);
        // TODO 카드 출력하기
        outputView.printStartCards(dealer, players);

        players.play(this::playTurn);
        while (dealer.isDrawable()) {
            // TODO 출력 -> 딜러는 16이하라 한장의 카드를 더 받았습니다.
            dealer.add(deck.draw());
        }

        // TODO 딜러, 플레이어들 카드 결과 및 점수 출력
        // TODO 딜러, 플레이어의 최종 승패 출력
    }

    private void playTurn(Player player) {
        while (player.isDrawable() && inputView.isPlayerWantDraw(player.getName())) {
            player.add(deck.draw());
            outputView.printPlayerCards(player);
        }
    }
}
