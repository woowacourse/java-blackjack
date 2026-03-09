package domain;

import view.Message;
import vo.Name;

public class User extends Participant {
    private final Name name;

    public User(String name) {
        this.name = new Name(name);
    }

    public String getName() {
        return name.getName();
    }

    public String getFormatedAskGetExtraCard() {
        return String.format(Message.REQUEST_GET_EXTRA_CARD, name.getName());
    }
}