package domain.game;

import domain.card.Cards;
import domain.user.Dealer;
import domain.user.Player;
import java.util.ArrayList;
import java.util.List;

public class BlackJackGame {
    private final List<Player> players;
    private final Dealer dealer;
    private final Cards cards;

    public BlackJackGame(List<Player> players, Dealer dealer, Cards cards) {
        this.players = new ArrayList<>(players);
        this.dealer = dealer;
        this.cards = cards;
    }

    //플레이어 별 카드를 한장씩 더 뽑도록 하는 기능
    public void drawOneMoreCardForPlayer() {

    }
    //딜러 별 카드를 한장씩 더 뽑도록 하는 기능

    //딜러 상태가 Normal / Bust 상태가 되기 전까지 카드 뽑는 기능

    //딜러와 플레이어 전부와 승패를 비교하는 기능

    //딜러와 단일 플레이어와 승패를 비교하는 기능

    //딜러와 플레이어 점수를 비교하는 기능
}
