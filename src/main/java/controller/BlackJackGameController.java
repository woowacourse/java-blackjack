package controller;

import java.util.ArrayList;
import java.util.List;

import domain.User;
import view.InputView;

public class BlackJackGameController {

    public static void run() {
        String name = InputView.inputUserNames();
        List<User> users = userNamesSetting(name);

    }

    public static List<User> userNamesSetting(String names) {
        List<User> users = new ArrayList<>();
        String[] userNames = names.split(",");
        for (String name : userNames) {
            users.add(new User(name));
        }
        return users;
    }

}
