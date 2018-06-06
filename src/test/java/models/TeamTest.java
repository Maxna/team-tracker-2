package models;

import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class TeamTest {

    @Test
    public void Team_InstantiatesNewInstanceCorrectly_true() throws Exception {
        Team testTeam = new Team("Blue", "They sure aren't Red");
        assertEquals(true, testTeam instanceof Team);
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

//    @Test
//    public void getMember_CorrectlyGetsMember() throws Exception {
//        Team testTeam = new Team("Blue", "They sure aren't Red");
//        assertEquals("Jon Jonson", testTeam.getMember());
//    }

    @Test
    public void getId_CorrectlyGetsId() throws Exception {
        Team testTeam = new Team("Blue", "They sure aren't Red");
        assertEquals(1, testTeam.getId());
    }

    @Test
    public void getAll_CorrectlyGetsAllTeams() throws Exception {
        Team testTeam = new Team("Blue", "They sure aren't Red");
        Team team = new Team("Red", "They sure aren't Blue");
        assertTrue(Team.getTeams().contains(testTeam));
        assertTrue(Team.getTeams().contains(team));
    }

    @Test
    public void findById_FindsCorrectJobById() throws Exception {
        Team testTeam = new Team("Blue", "They sure aren't Red");
        Team team = new Team("Red", "They sure aren't Blue");
        assertEquals(1, Team.findById(testTeam.getId()).getId());
        assertEquals(2, Team.findById(team.getId()).getId());
    }

    @Test
    public void updateTeam_CorrectlyChangesTeamProps() throws Exception {
        Team testTeam = new Team("Blue", "They sure aren't Red");
        String oldTeamName = testTeam.getTeamName();
        String oldDescription = testTeam.getDescription();
        String oldMember = testTeam.getMember();
        int oldId = testTeam.getId();

        testTeam.updateTeam("Green");

        assertEquals(oldId, testTeam.getId());
        assertEquals(oldDescription, testTeam.getDescription());
        assertEquals(oldMember, testTeam.getMember());
        assertNotEquals(oldTeamName, testTeam.getTeamName());
    }

    @After
    public void tearDown() throws Exception {
        Team.clearAllTeams();
    }
}
