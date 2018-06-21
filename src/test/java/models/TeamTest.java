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
    public void setName_CorrectlySetsTeamName() throws Exception{
        Team testTeam = new Team("Blue", "They sure aren't Red");
        testTeam.setName("Green");
        assertEquals("Green", testTeam.getName());
    }

    @Test
    public void getName_CorrectlyGetsTeamName() throws Exception {
        Team testTeam = new Team("Blue", "They sure aren't Red");
        assertEquals("Blue", testTeam.getName());
    }

    @Test
    public void setDescription_CorrectlySetsTeamDescription() throws Exception{
        Team testTeam = new Team("Blue", "They sure aren't Red");
        testTeam.setDescription("They sure aren't Yellow");
        assertEquals("They sure aren't Yellow", testTeam.getDescription());
    }

    @Test
    public void getDescription_CorrectlyGetsDescription() throws Exception {
        Team testTeam = new Team("Blue", "They sure aren't Red");
        assertEquals("They sure aren't Red", testTeam.getDescription());
    }

    @Test
    public void setId_CorrectlySetsTeamId() throws Exception{
        Team testTeam = new Team("Blue", "They sure aren't Red");
        testTeam.setId(1);
        assertEquals(1, testTeam.getId());
    }

    @Test
    public void getId_CorrectlyGetsTeamId() throws Exception{
        Team testTeam = new Team("Blue", "They sure aren't Red");
        testTeam.setId(1);
        assertNotEquals(2, testTeam.getId());
    }

}
