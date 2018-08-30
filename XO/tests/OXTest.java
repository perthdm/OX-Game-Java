import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OXTest {

    @Test
    public void shouldCreateOXObjectCorrectly(){
        OX ox = new OX();
        assertEquals(" 012\n0---\n1---\n2---\n" , ox.getTableString());

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

        assertEquals(false,ox.put(0,2));


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

        assertTrue(ox.checkWin(0, 1));
        assertTrue(ox.checkWin(0, 1));
        assertTrue(ox.checkWin(0, 2));


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
    public void getOver(){
        OX ox =new OX();
        assertNull(ox.get(0,-1));
        assertNull(ox.get(0,3));
    }

}