package ws.probal.request;

import io.grpc.stub.StreamObserver;
import ws.probal.app.models.Die;
import ws.probal.app.models.GameState;
import ws.probal.app.models.Player;
import ws.probal.utils.SnakeLadderMap;

import java.util.concurrent.ThreadLocalRandom;

public class DieStreamingRequest implements StreamObserver<Die> {

    private Player clientPlayer;
    private Player serverPlayer;
    private StreamObserver<GameState> gameStateStreamObserver;

    public DieStreamingRequest(Player clientPlayer, Player serverPlayer, StreamObserver<GameState> gameStateStreamObserver) {
        this.clientPlayer = clientPlayer;
        this.serverPlayer = serverPlayer;
        this.gameStateStreamObserver = gameStateStreamObserver;
    }

    @Override
    public void onNext(Die die) {
        this.clientPlayer = this.getPlayerNewPosition(this.clientPlayer, die.getValue());
        if (this.clientPlayer.getPosition() != 100) {
            this.serverPlayer = this.getPlayerNewPosition(
                    this.serverPlayer, ThreadLocalRandom.current().nextInt(1,7));
        }
        this.gameStateStreamObserver.onNext(getGameState());
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onCompleted() {
        this.gameStateStreamObserver.onCompleted();
    }

    private GameState getGameState() {
        return GameState.newBuilder()
                .addPlayer(this.clientPlayer)
                .addPlayer(this.serverPlayer)
                .build();
    }

    private Player getPlayerNewPosition(Player player, int dieValue) {
        int newPosition = player.getPosition() + dieValue;
        newPosition = SnakeLadderMap.getPosition(newPosition);
        if (newPosition <= 100) {
            player = player.toBuilder().setPosition(newPosition).build();
        }
        return player;
    }
}
