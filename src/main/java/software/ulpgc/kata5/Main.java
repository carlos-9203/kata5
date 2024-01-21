package software.ulpgc.kata5;

import spark.Request;
import spark.Response;
import spark.Spark;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        commands.put("factorial", new FactorialCommand());
        commands.put("fibonacci", new FibonacciCommand());
        Spark.port(8080);
        Spark.get("/factorial/:number", (request, response) -> new CommandExecutator(request,response).execute("factorial"));
        Spark.get("/fibonacci/:number", (request, response) -> new CommandExecutator(request,response).execute("fibonacci"));
    }

    static Map<String, Command> commands = new HashMap<>();
    public static class CommandExecutator {
        private final Request request;
        private final Response response;

        public CommandExecutator(Request request, Response response) {
            this.request = request;
            this.response = response;
        }

        public String execute(String name){
            Command command = commands.get(name);
            Command.Output output = command.execute(input());
            response.status(output.responseCode());
            return output.result();
        }

        private Command.Input input() {
            return parameter -> oneOf(request.params(parameter), request.queryParams(parameter));
        }

        private String oneOf(String params, String s) {
            return params != null ? params : s;
        }
    }
}
