/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author User
 */
public class StatisticsTest {
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }  
    
    @Test
    public void testSearchPlayer() {
        Player player = stats.search("Gretzky");
        assertEquals("EDM", player.getTeam());
        assertEquals(35, player.getGoals());
        assertEquals(89, player.getAssists());
        Assert.assertNull(stats.search("Not a player"));
    }
    
    @Test
    public void testTeam() {
        List<Player> players = stats.team("EDM");
        assertEquals(3, players.size());
    }
    
    @Test
    public void testTopScorer() {
        List<Player> players = stats.topScorers(3);
        System.out.println(players.size());
        assertEquals("Gretzky", players.get(0).getName());
        assertEquals("Yzerman", players.get(2).getName());
    }
}
