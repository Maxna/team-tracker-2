package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TeamTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
        Team.clearAllTeams();
    }

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

    @Test
    public void getMembers_CorrectlyGetsTeamMembers() throws Exception {
        Team testTeam = new Team("Blue", "They sure aren't Red");
        List<String> members = new ArrayList<>();
        assertEquals(members.getClass(), testTeam.getMembers().getClass());
    }

    @Test
    public void addMembers_CorrectlyAddsNewMembers() throws Exception{
        Team testTeam = new Team("Blue", "They sure aren't Red");
        String newMember = "Johnny Boi";
        testTeam.addMembers(newMember);
        assertTrue(testTeam.getMembers().contains(newMember));

    }

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
    public void findById_FindsCorrectTeamById() throws Exception {
        Team testTeam = new Team("Blue", "They sure aren't Red");
        Team team = new Team("Red", "They sure aren't Blue");
        assertEquals(1, Team.findById(testTeam.getId()).getId());
        assertEquals(2, Team.findById(team.getId()).getId());
    }

    @Test
    public void updateTeam_CorrectlyChangesTeamName() throws Exception {
        Team testTeam = new Team("Blue", "They sure aren't Red");
        int oldId = testTeam.getId();
        String oldDescription = testTeam.getDescription();
        String oldTeamName = testTeam.getTeamName();
//        String oldMember = testTeam.getMembers();

        testTeam.updateTeam("Green");

        assertEquals(oldId, testTeam.getId());
        assertEquals(oldDescription, testTeam.getDescription());
        assertNotEquals(oldTeamName, testTeam.getTeamName());
//        assertEquals(oldMember, testTeam.getMembers());
    }

}
