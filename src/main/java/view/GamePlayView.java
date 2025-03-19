package view;

import participant.Player;

public final class GamePlayView extends BlackjackView {
    public String getPlayerDealGuide(final Player player) {
        return String.format("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", player.getName());
    }

    public String getDealerHitGuide() {
        return String.format("%s는 16이하라 한장의 카드를 더 받았습니다.%n", DEALER_NAME);
    }
}
