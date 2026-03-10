package view;

public class OutputView {
    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void printAskExtraCard(String name) {
        System.out.printf((Message.REQUEST_GET_EXTRA_CARD) + "%n", name);
    }

}
