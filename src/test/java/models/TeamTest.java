package models;

import org.junit.Test;
import static org.junit.Assert.*;

public class TeamTest {

    @Test
    public void team_instantiatesNewInstanceCorrectly_true() throws Exception {
        Team testTeam = new Team("Blue", "They sure aren't Red", "Jon Jonson");
        assertEquals(true, testTeam instanceof Team);
    }
}
