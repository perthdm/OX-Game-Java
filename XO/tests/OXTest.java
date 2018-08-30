import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OXTest {

    @Test
    public void shouldCreateOXObjectCorrectly(){
        OX ox = new OX();
        assertEquals(" 012\n" +
                              "0---\n" +
                              "1---\n" +
                              "2---\n" , ox.getTableString());

        assertEquals("X",ox.getCurrentPlayer());
        assertEquals(0,ox.getCountO());
        assertEquals(0,ox.getCountX());
        assertEquals(0,ox.getCountDraw());

    }

    @Test
    public void put(){
        OX ox = new OX();
        ox.put(1,1);
        assertEquals(" 012\n" +
                              "0---\n" +
                              "1-X-\n" +
                              "2---\n",ox.getTableString());

        ox.put(0,0);
        assertEquals(" 012\n" +
                              "0X--\n" +
                              "1-X-\n" +
                              "2---\n",ox.getTableString());

        ox.put(2,2);
        assertEquals(" 012\n" +
                              "0X--\n" +
                              "1-X-\n" +
                              "2--X\n",ox.getTableString());

        ox.switchPlayer();
        ox.put(0,2);
        assertEquals(" 012\n" +
                               "0X--\n" +
                               "1-X-\n" +
                               "2O-X\n",ox.getTableString());

        assertFalse(ox.put(0, 2));
        assertFalse(ox.put(0, 0));


    }

    @Test
    public void GetAt0_0() {
        OX ox = new OX();
        ox.put(0, 0);
        assertEquals("X", ox.get(0,0));
    }

    @Test
    public void switchPlayer(){
        OX ox =new OX();
        ox.switchPlayer();
        assertEquals("O",ox.getCurrentPlayer());

        ox.switchPlayer();
        assertEquals("X",ox.getCurrentPlayer());


    }

    @Test
    public void checkWin1(){
        OX ox =new OX();
        ox.put(0,0);
        ox.put(0,1);
        ox.put(0,2);
        assertEquals(" 012\n" +
                              "0X--\n" +
                              "1X--\n" +
                              "2X--\n",ox.getTableString());

        assertTrue(ox.checkWin(0,0));
        assertTrue(ox.checkWin(0,1));
        assertTrue(ox.checkWin(0,2));
        assertFalse(ox.checkWin(1,0));
        assertFalse(ox.checkWin(1,1));
        assertFalse(ox.checkWin(1,2));


    }

    @Test
    public void checkWin2(){
        OX ox =new OX();
        ox.put(0,0);
        ox.put(0,1);

        assertEquals(" 012\n0X--\n1X--\n2---\n",ox.getTableString());

        assertFalse(ox.checkWin(0, 0));
        assertFalse(ox.checkWin(0, 1));

    }


    @Test
    void checkWinCol2() {
        OX ox = new OX();
        ox.put(2,0);
        ox.put(2,1);
        ox.put(2,2);
        assertTrue(ox.checkWin(2,0));
        assertTrue(ox.checkWin(2,1));
        assertTrue(ox.checkWin(2,2));
        assertFalse(ox.checkWin(1,0));
        assertFalse(ox.checkWin(1,1));
        assertFalse(ox.checkWin(1,2));
    }

    @Test
    void checkWinRo2() {
        OX ox = new OX();
        ox.put(0,2);
        ox.put(1,2);
        ox.put(2,2);
        assertTrue(ox.checkWin(0,2));
        assertTrue(ox.checkWin(1,2));
        assertTrue(ox.checkWin(2,2));
        assertFalse(ox.checkWin(1,0));
        assertFalse(ox.checkWin(1,1));
        assertFalse(ox.checkWin(2,1));
    }

    @Test
    void checkWinES() {
        OX ox = new OX();
        ox.put(0,0);
        ox.put(1,1);
        ox.put(2,2);
        assertTrue(ox.checkWin(0,0));
        assertTrue(ox.checkWin(1,1));
        assertTrue(ox.checkWin(2,2));
    }

    @Test
    void checkWinSS() {
        OX ox = new OX();
        ox.put(2,0);
        ox.put(1,1);
        ox.put(0,2);
        assertTrue(ox.checkWin(2,0));
        assertTrue(ox.checkWin(1,1));
        assertTrue(ox.checkWin(0,2));
    }

    @Test
    public void getOver(){
        OX ox =new OX();
        assertNull(ox.get(0,-1));
        assertNull(ox.get(0,3));
        assertNull(ox.get(3, -1));
        assertNull(ox.get(-1, 3));
    }

    @Test
    void putOver() {
        OX ox = new OX();
        assertFalse(ox.put(0, -1));
        assertFalse(ox.put(0, 3));
    }

    @Test
    void reset() {
        OX ox = new OX();
        ox.put(2,0);
        ox.put(1,1);
        ox.put(0,2);
        ox.reset();
        assertEquals(" 012\n" +
                "0---\n" +
                "1---\n" +
                "2---\n", ox.getTableString());
        assertEquals("X", ox.getCurrentPlayer());
        assertEquals(0, ox.getTurnCount());
    }

    @Test
    void getTurnCount() {
        OX ox  = new OX();
        assertEquals(0,ox.getTurnCount());
        ox.put(0,0);
        assertEquals(1,ox.getTurnCount());

    }

    @Test
    void isDraw() {
        OX ox  = new OX();
        ox.put(0,0);
        ox.put(0,1);
        ox.put(0,2);
        assertFalse(ox.isDraw());

        ox.put(2,0);
        ox.put(2,1);
        ox.put(2,2);
        assertTrue(ox.isDraw());
    }

    @Test
    void getScoreO() {
        OX ox = new OX();
        ox.switchPlayer();
        assertEquals(0,ox.getScoreO());
        ox.put(0,0);
        ox.put(1,1);
        ox.put(2,2);
        assertEquals(1,ox.getScoreO());
    }

    @Test
    void getScoreDraw() {
        OX ox = new OX();

        assertEquals(0,ox.getScoreDraw());
        ox.put(0,0);
        ox.put(0,1);
        ox.put(0,2);
        assertFalse(ox.isDraw());

        ox.put(1,0);
        ox.put(1,1);
        ox.put(1,2);
        assertFalse(ox.isDraw());

        ox.put(2,0);
        ox.put(2,1);
        ox.put(2,2);
        assertTrue(ox.isDraw());
        assertEquals(1,ox.getScoreDraw());
    }


}