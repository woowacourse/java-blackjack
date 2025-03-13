package domain;

import static domain.GameResult.BLACKJACK;
import static domain.GameResult.LOSE;
import static domain.GameResult.TIE;
import static domain.GameResult.WIN;

public class Player extends Gamer {
    private static final int PLAYER_DRAWABLE_THRESHOLD = 20;
    private final PlayerName playerName;


    public Player(PlayerName playerName) {
        super();
        this.playerName = playerName;
    }

    public Player(Player player) {
        super(player);
        this.playerName = new PlayerName(player.playerName.username());
    }

    public static Player copyOf(Player player) {
        return new Player(player);
    }

    public boolean isDrawable() {
        return this.isDrawable(PLAYER_DRAWABLE_THRESHOLD);
    }

    public GameResult decideGameResult(Dealer dealer) {
        int playerScore = this.getScore();
        int dealerScore = dealer.getScore();
        if (playerScore > GAMER_BUST_THRESHOLD || dealerScore > GAMER_BUST_THRESHOLD) {
            return decideGameResultWithBust(playerScore);
        }
        return decideGameResultWithoutBust(dealer);
    }

    private GameResult decideGameResultWithBust(int playerScore) {
        if (playerScore > GAMER_BUST_THRESHOLD) { // 플레이어 버스트인 경우 (딜러가 버스트여도 이 경우)
            return LOSE;
        }
        return WIN; // 딜러만 버스트인 경우
    }

    private GameResult decideGameResultWithoutBust(Dealer dealer) {
        if (cards.isBlackJack()) { // 플레이어만 블랙잭인 경우
            return BLACKJACK;
        }
        if (cards.calculateScore() == dealer.getScore()) { // 점수 무승부
            return TIE;
        }
        if (cards.isBlackJack() && dealer.isBlackJack()
                || cards.calculateScore() > dealer.getScore()) { // 딜러 & 플레이어 모두 블랙잭 혹은 플레이어가 점수 높은 경우
            return WIN;
        }
        return LOSE;
    }
}
