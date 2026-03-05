package team.blackjack.service;

import java.util.List;
import team.blackjack.domain.BlackjackGame;
import team.blackjack.domain.Dealer;
import team.blackjack.domain.Deck;
import team.blackjack.domain.Hand;
import team.blackjack.domain.Player;

public class BlackJackService {
    private BlackjackGame blackjackGame;

    public void initGame(List<String> playerNames) {
        final Dealer dealer = new Dealer();
        final List<Player> players = playerNames.stream()
                .map(Player::new)
                .toList();

        blackjackGame = new BlackjackGame(dealer, players);
    }


    /**
     * TODO: Draw 위치에 대한 고민 다시 해보기
     */
    public void drawInitialCards() {
        final Deck deck = blackjackGame.getDeck();
        final Dealer dealer = blackjackGame.getDealer();

        // 플레이어 카드 초기화
        for (Player player : blackjackGame.getPlayers()) {
            Hand hand = new Hand();
            hand.addCard(dealer.draw(deck));
            hand.addCard(dealer.draw(deck));

            player.addHand(hand);
        }

        // 딜러 카드 초기화
        Hand dealerHand = dealer.getHand();
        dealerHand.addCard(dealer.draw(deck));
        dealerHand.addCard(dealer.draw(deck));
    }

    public BlackjackGame getBlackjackGame() {
        return this.blackjackGame;
    }


}
