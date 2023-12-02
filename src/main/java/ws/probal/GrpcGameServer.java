package ws.probal;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import ws.probal.service.GameService;

import java.io.IOException;

public class GrpcGameServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(6565)
                .addService(new GameService())
                .build();

        server.start();
        server.awaitTermination();
    }
}
