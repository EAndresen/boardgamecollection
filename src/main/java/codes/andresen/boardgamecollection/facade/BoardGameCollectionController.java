package codes.andresen.boardgamecollection.facade;

import codes.andresen.boardgamecollection.integration.GameCollectionService;
import codes.andresen.boardgamecollection.model.Game.BoardGame;
import codes.andresen.boardgamecollection.model.Game.BoardGames;
import codes.andresen.boardgamecollection.model.collection.Items;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class BoardGameCollectionController {

    private GameCollectionService gameCollectionService;

    public BoardGameCollectionController(GameCollectionService gameCollectionService) {
        this.gameCollectionService = gameCollectionService;
    }

    @PostMapping("/collection/add")
    public StringBuilder addCollection(
            @RequestHeader(value = "userName") String userName) {
        return gameCollectionService.getCollection(userName);
    }

    @PostMapping("/collection/add/{gameId}")
    public String addSingleGame(
            @RequestHeader(value = "userName") String userName,
            @PathVariable(value = "gameId") int gameId) {
        return gameCollectionService.getBoardGame(gameId, userName);
    }

    @GetMapping("/collection/read")
    public List<BoardGame> getCollection(
            @RequestHeader(value = "userName") String userName) throws ExecutionException, InterruptedException {
        return gameCollectionService.getGameCollection(userName);
    }

    @GetMapping("/getXMLGame")
    public String getBoardGame(
            @RequestHeader(value = "userName") String userName,
            @RequestHeader(value = "gameId") int gameId) {
        return gameCollectionService.getBoardGame(gameId, userName);
    }

    @DeleteMapping(value = "/collection/delete/game/{gameName}")
    public String deleteSingleGame(
            @RequestHeader(value = "userName") String userName,
            @PathVariable(value = "gameName") String gameName) {
     return gameCollectionService.deleteSingleGame(userName, gameName);
    }

    @DeleteMapping(value = "/collection/delete")
    public String deleteCollection(
            @RequestHeader(value = "userName") String userName) {
        return gameCollectionService.deleteCollection(userName);
    }
}
