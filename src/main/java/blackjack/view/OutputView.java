package blackjack.view;

import blackjack.model.BlackJackGame;
import blackjack.model.Card;
import blackjack.model.Cards;
import blackjack.model.Dealer;
import blackjack.model.Player;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public void printDistributedCardsInfo(BlackJackGame blackJackGame) {
        List<Player> players = blackJackGame.getPlayers();
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.%n", getPlayersNames(players));

        Dealer dealer = blackJackGame.getDealer();
        Card dealerCard = getDealerCard(dealer);
        String dealerCardText = convertCardText(dealerCard);
        System.out.printf("딜러: %s%n", dealerCardText);
        players.forEach(player -> System.out.printf("%s: %s%n", player.getName(), getCardsText(player.getCards())));
    }

    public void printPlayerCardsInfo(BlackJackGame blackJackGame, int index) {
        Player player = blackJackGame.getPlayers().get(index);
        System.out.printf("%s: %s%n", player.getName(), getCardsText(player.getCards()));
    }

    public void printDealerChange() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printFinalScore(BlackJackGame blackJackGame) {
        List<Player> players = blackJackGame.getPlayers();
        Dealer dealer = blackJackGame.getDealer();
        String dealerCardText = getCardsText(dealer.getCards());
        System.out.printf("딜러: %s - 결과: %d%n", dealerCardText, dealer.getCards().calculateScore());
        players.forEach(player ->
                System.out.printf("%s: %s - 결과: %d%n", player.getName(), getCardsText(player.getCards()),
                        player.getCards().calculateScore()));
    }

    private String getPlayersNames(List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
    }

    private Card getDealerCard(Dealer dealer) {
        return dealer.getCards()
                .getCards()
                .get(0);
    }

    private String getCardsText(Cards cards) {
        return cards.getCards()
                .stream()
                .map(this::convertCardText)
                .collect(Collectors.joining(", "));
    }

    private String convertCardText(Card card) {
        String cardNumberText = card.getCardNumber().getText();
        String cardShapeText = card.getCardShape().getText();
        return cardNumberText + cardShapeText;
    }
}
