package blackjack.domain;

import java.util.List;

public class CardGame {
    public void giveCard(final Player player, final Card card) {
        player.addCards(card);
    }

    // TODO: 이름 수정
    void giveTwoCardsEachPlayer(final List<Player> players) {
        // TODO: 주입 받도록 변경
        final CardGenerator cardGenerator = new RandomCardGenerator();
        for (Player player : players) {
            // TODO: 개선 필요
            giveCard(player, cardGenerator.generate());
            giveCard(player, cardGenerator.generate());
        }
    }
}
