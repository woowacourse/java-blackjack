package controller;

import view.PlayerRegisterView;
import view.View;

public final class Controller {
    private final PlayerRegisterView playerRegisterView = new PlayerRegisterView();

    public View guideToInputName() {
        return playerRegisterView.guideToInputName();
    }

//    public String guideToBettingAmount(String playerName) {
//        return playerRegisterView.readBattingAmount(playerName);
//    }
//
//    public Players registerPlayers(Map<String, Integer> registerInput) {
//        return null;
//    }
}
