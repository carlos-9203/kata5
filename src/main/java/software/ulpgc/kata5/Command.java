package software.ulpgc.kata5;



public interface Command {
    Output execute(Input input);

    interface Input{
        String get(String parameter);
    }

    interface Output {
        int responseCode();
        String result();
    }
}
