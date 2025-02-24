package test;

import Game.App;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    private App game;

    @BeforeEach
    public void setUp() {
        game = new App();
        game.resetBoard();
    }

    @Test
    public void testPlaceMarker() {
        assertTrue(game.placeMarker(1, 'X'));
        assertFalse(game.placeMarker(1, 'O'));
    }

    @Test
    public void testCheckWin() {
        game.placeMarker(1, 'X');
        game.placeMarker(2, 'X');
        game.placeMarker(3, 'X');
        assertTrue(game.checkWin('X'));
    }

    @Test
    public void testIsBoardFull() {
        for (int i = 1; i <= 9; i++) {
            game.placeMarker(i, 'X');
        }
        assertTrue(game.isBoardFull());
    }

    @Test
    public void testResetBoard() {
        game.placeMarker(1, 'X');
        game.resetBoard();
        assertTrue(game.placeMarker(1, 'O'));
    }
}
