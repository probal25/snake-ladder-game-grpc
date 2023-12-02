package ws.probal.service;

import io.grpc.stub.StreamObserver;
import ws.probal.app.models.Die;
import ws.probal.app.models.GameServiceGrpc;
import ws.probal.app.models.GameState;
import ws.probal.app.models.Player;
import ws.probal.request.DieStreamingRequest;

public class GameService extends GameServiceGrpc.GameServiceImplBase {
    @Override
    public StreamObserver<Die> roll(StreamObserver<GameState> responseObserver) {
        Player clientPlayer =  Player.newBuilder().setName("client-player").setPosition(0).build();
        Player serverPlayer =  Player.newBuilder().setName("server-player").setPosition(0).build();
        return new DieStreamingRequest(clientPlayer, serverPlayer, responseObserver);
    }
}
