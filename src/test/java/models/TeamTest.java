package models;

import org.junit.Test;
import static org.junit.Assert.*;

public class TeamTest {

    @Test
    public void Team_instantiatesNewInstanceCorrectly_true() throws Exception {
        Team testTeam = new Team("Blue", "They sure aren't Red", "Jon Jonson");
        assertEquals(true, testTeam instanceof Team);
    }

    @Test
    public void getName_correctlyGetsTeamName_Blue() throws Exception {
        Team testTeam = new Team("Blue", "They sure aren't Red", "Jon Jonson");
        assertEquals("Blue", testTeam.getTeamName());
    }
}
