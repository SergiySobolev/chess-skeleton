package chess;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import static junit.framework.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Basic unit tests for the CLITest
 */
@RunWith(MockitoJUnitRunner.class)
public class CLITest {

    @Mock
    private PrintStream testOut;

    @Mock
    private InputStream testIn;

    /**
     * Make sure the CLI initially prints a welcome message
     */
    @Test
    public void testCLIWritesWelcomeMessage() {
        new CLI(testIn, testOut);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(testOut, times(1)).println(captor.capture());

        String message = captor.getValue();

        assertTrue("The CLI should initially print a welcome message", message.startsWith("Welcome"));
    }

    /**
     * Test that the CLI can initially accept input
     */
    @Test
    public void testHelpCommand() throws Exception {
        runCliWithInput("help");

        List<String> output = captureOutput();
        assertEquals("Should have 13 output calls", 13, output.size());
    }

    @Test
    public void testNewCommand() throws Exception {
        runCliWithInput("new");
        List<String> output = captureOutput();

        assertEquals("Should have had 6 calls to print strings", 6, output.size());
        assertEquals("It should have printed the board first", 721, output.get(2).length());
        assertEquals("It should have printed the board again", 721, output.get(4).length());
    }

    @Test
    public void testBoardCommand() throws Exception {
        runCliWithInput("new", "board");
        List<String> output = captureOutput();

        assertEquals("Should have had 9 output calls", 9, output.size());
        assertEquals("It should have printed the board three times", output.get(2), output.get(4));
    }

    @Test
    public void testListCommandFromStartPosition() throws Exception {
        runCliWithInput("new", "list");
        List<String> output = captureOutput();

        assertEquals("Should have had 50 calls to print strings", 50, output.size());
        assertEquals("It should have printed the board first", 721, output.get(2).length());
        assertEquals("It should have printed the board again", 721, output.get(4).length());
        assertEquals("It should have white possible moves title", "White's Possible Moves:", output.get(6));
        assertEquals("It should have print white knight move: nb1 - a3", "nb1 - a3", output.get(7));
        assertEquals("It should have print white knight move: nb1 - c3", "nb1 - c3", output.get(8));
        assertEquals("It should have print white knight move: ng1 - f3", "ng1 - f3", output.get(9));
        assertEquals("It should have print white knight move: ng1 - h3", "ng1 - h3", output.get(10));
        assertEquals("It should have print King's white pawn move: pe2 - e4", "pe2 - e4", output.get(20));
        assertEquals("It should have print King's white pawn move: pe2 - e3", "pe2 - e3", output.get(19));
        assertEquals("It should have black possible moves title", "Black's Possible Moves:", output.get(27));
        assertEquals("It should have print Rook's black pawn move: Pa7 - a5", "Pa7 - a5", output.get(32));
        assertEquals("It should have print Rook's black pawn move: Pa7 - a6", "Pa7 - a6", output.get(33));
        assertEquals("It should have printed the board first", 721, output.get(48).length());
        assertEquals("It should have printed current players move", "White's Move", output.get(49));
    }

    @Test
    public void testMoveE2E4() throws Exception {
        runCliWithInput("new", "move e2 e4");
        List<String> output = captureOutput();
        assertEquals("Should have had 50 calls to print strings", 8, output.size());
        assertEquals("It should have printed the board first", 721, output.get(6).length());
        assertEquals("It should have printed the board again", 721, output.get(4).length());
        assertTrue("4th line should contain white pawn moved from e2",  output.get(6).contains("4 |   |   |   |   | p |   |   |   | 4"));
        assertTrue("2nd line shouldn't contain king's white pawn",  output.get(6).contains("2 | p | p | p | p |   | p | p | p | 2"));
    }

    @Test
    public void testFirst5MovesFromSpainDebut() throws Exception {
        runCliWithInput("new", "move e2 e4", "move e7 e5", "move g1 f3", "move b8 c6", "move f1 b5");
        List<String> output = captureOutput();
        assertEquals("Should have had 16 calls to print strings", 16, output.size());
        assertTrue(output.get(14).contains("8 | R |   | B | Q | K | B | N | R | 8"));
        assertTrue(output.get(14).contains("7 | P | P | P | P |   | P | P | P | 7"));
        assertTrue(output.get(14).contains("6 |   |   | N |   |   |   |   |   | 6"));
        assertTrue(output.get(14).contains("5 |   | b |   |   | P |   |   |   | 5"));
        assertTrue(output.get(14).contains("4 |   |   |   |   | p |   |   |   | 4"));
        assertTrue(output.get(14).contains("3 |   |   |   |   |   | n |   |   | 3"));
        assertTrue(output.get(14).contains("2 | p | p | p | p |   | p | p | p | 2"));
        assertTrue(output.get(14).contains("1 | r | n | b | q | k |   |   | r | 1"));
    }

    @Test
    public void fastestPossibleCheckMate() throws Exception {
        runCliWithInput("new", "move g2 g4", "move e7 e5", "move f2 f3", "move d8 h4");
        List<String> output = captureOutput();
        assertEquals("Should have had 15 calls to print strings", 15, output.size());
        assertEquals("Should be message about black victory", "The game is over. Congrats to Black.", output.get(12));
    }

    @Test
    public void childCheckMate() throws Exception {
        runCliWithInput("new", "move e2 e4", "move e7 e5", "move f1 c4", "move f8 c5", "move d1 h5", "move g8 f6", "take h5 f7");
        List<String> output = captureOutput();
        assertEquals("Should have had 21 calls to print strings", 21, output.size());
        assertEquals("Should be message about white victory", "The game is over. Congrats to White.", output.get(18));
    }

    private List<String> captureOutput() {
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        // 9 times means we printed Welcome, the input prompt twice, and the 'help' screen
        verify(testOut, atLeastOnce()).println(captor.capture());

        return captor.getAllValues();
    }

    private CLI runCliWithInput(String... inputLines) {
        StringBuilder builder = new StringBuilder();
        for (String line : inputLines) {
            builder.append(line).append(System.getProperty("line.separator"));
        }

        ByteArrayInputStream in = new ByteArrayInputStream(builder.toString().getBytes());
        CLI cli = new CLI(in, testOut);
        cli.startEventLoop();

        return cli;
    }
}
