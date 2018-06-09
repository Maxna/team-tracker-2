package models;

import org.junit.Test;
import static org.junit.Assert.*;

public class TeamTest {

    @Test
    public void Team_InstantiatesNewInstanceCorrectly_true() throws Exception {
        Team testTeam = new Team("Blue", "They sure aren't Red");
        assertTrue(testTeam instanceof Team);
    }

    @Test
    public void getName_CorrectlyGetsTeamName() throws Exception {
        Team testTeam = new Team("Blue", "They sure aren't Red");
        assertEquals("Blue", testTeam.getTeamName());
    }

    @Test
    public void getDescription_CorrectlyGetsDescription() throws Exception {
        Team testTeam = new Team("Blue", "They sure aren't Red");
        assertEquals("They sure aren't Red", testTeam.getDescription());
    }

}
