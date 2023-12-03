package Ulpgc.Itzhak;

import spark.Request;
import spark.Response;
import spark.Spark;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        commands.put("factorial", new FactorialCommand());
        Spark.port(8080);
        Spark.get("factorial/:number", (req,res)->new CommandExecutor(req,res).execute("factorial"));
    }

    static Map<String, Command>commands= new HashMap<>();
    private static class CommandExecutor {
        private final Request request;
        private  final  Response response;
        public CommandExecutor(Request req, Response res) {
            this.request=req;
            this.response=res;
        }

        public String execute(String name) {
            Command command= commands.get(name);
            Command.Output output= command.excute(input());
            response.status(output.responseCode());
            return output.result();
        }

        private Command.Input input() {
            return parameter -> oneOf(request.params(parameter), request.queryParams(parameter));
        }

        private String oneOf(String a, String b) {
            return a!=null ? a:b;
        }
    }
}
