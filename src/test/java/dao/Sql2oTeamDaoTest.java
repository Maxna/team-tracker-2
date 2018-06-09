package dao;

import models.Team;
import models.Member;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class Sql2oTeamDaoTest {
    private Sql2oTeamDao teamDao;
    private Sql2oMemberDao memberDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        teamDao = new Sql2oTeamDao(sql2o);
        memberDao = new Sql2oMemberDao(sql2o);
        conn = sql2o.open();
    }

    @Test
    public void addingTeamSetsId() throws Exception {
        Team team = new Team ("epicodus");
        int originalTeamId = Team.getId();
        teamDao.add(team);
        assertNotEquals(originalTeamId, team.getId());
    }

    @Test
    public void existingTeamsFoundById() throws Exception{
        Team team = new Team("epicodus");
        int originalTeamId = Team.getId();
        teamDao.add(team);
        assertNotEquals(originalTeamId, team.getId());
    }

    @Test
    public void getAllCorrectlyGetsAll() throws Exception{
        Team team = new Team("epicodus");
        teamDao.add(team);
        Team team2 = new Team("students");
        teamDao.add(team2);
        assertEquals(2, teamDao.getAll().size());
    }

    @Test
    public void getAllReturnsNothingIfNoTasks() throws Exception{
        assertEquals(0, teamDao.getAll().size());
    }

    @Test
    public void updateWorksCorrectly() throws Exception{
        Team team = new Team("epicodus");
        teamDao.add(team);
        teamDao.update(team.getId(), "students");
        Team updatedTeam = teamDao.findById(team.getId());
        assertEquals("students", updatedTeam.getTeam());
    }

    @Test
    public void deleteWorksCorrectly() throws Exception{
        Team team = new Team("epicodus");
        teamDao.add(team);
        teamDao.deleteById(team.getId());
        assertEquals(0, teamDao.getAll().size());
    }

    @Test
    public void clearAllTeams() throws Exception{
        Team team = new Team("epicodus");
        Team team1 = new Team("students");
        teamDao.add(team);
        teamDao.add(team1);
        teamDao.clearAllTeams();
        assertEquals(0, teamDao.getAll().size());
    }

    @Test
    public void getAllMembersByTeamWorks() throws Exception{
        Team team = new Team("epicodus");
        teamDao.add(team);
        int teamId = team.getId();
        Member member1 = new Member("mike");
        Member member2 = new Member("jim");
        Member member3 = new Member("ross");
        memberDao.add(member1);
        memberDao.add(member2);
        assertEquals(2, teamDao.getAllMembersByTeam(teamId).size());
        assertTrue(teamDao.getAllMembersByTeam(teamId).contains(member1));
        assertTrue(teamDao.getAllMembersByTeam(teamId).contains(member2));
        assertFalse(teamDao.getAllMembersByTeam(teamId).contains(member3));
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }
}