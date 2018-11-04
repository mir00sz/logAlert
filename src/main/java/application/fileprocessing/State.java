package application.fileprocessing;

public enum State {
    STARTED("STARTED"), FINISHED("FINISHED");


    private final String value;


    State(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
