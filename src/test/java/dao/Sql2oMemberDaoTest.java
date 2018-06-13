package dao;

import models.Member;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class Sql2oMemberDaoTest {
    private Sql2oMemberDao memberDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        memberDao = new Sql2oMemberDao(sql2o);
        conn = sql2o.open();
    }

    @Test
    public void addingMemberSetsId() throws Exception {
        Member name = new Member ("jeff", 1);
        int originalMemberId = name.getId();
        memberDao.add(name);
        assertNotEquals(originalMemberId, name.getId());
    }

    @Test
    public void existingMembersFoundById() throws Exception{
        Member name = new Member("jeff", 1);
        int originalMemberId = name.getId();
        memberDao.add(name);
        assertNotEquals(originalMemberId, name.getId());
    }

    @Test
    public void getAllCorrectlyGetsAll() throws Exception{
        Member name = new Member("jeff", 1);
        memberDao.add(name);
        Member name2 = new Member("paul", 1);
        memberDao.add(name2);
        assertEquals(2, memberDao.getAll().size());
    }

    @Test
    public void getAllReturnsNothingIfNoTasks() throws Exception{
        assertEquals(0, memberDao.getAll().size());
    }

    @Test
    public void updateWorksCorrectly() throws Exception{
        Member name = new Member("jeff", 1);
        memberDao.add(name);
        memberDao.update(name.getId(), "paul", 1);
        Member updatedName = memberDao.findById(name.getId());
        assertEquals("paul", updatedName.getName());
    }

    @Test
    public void deleteWorksCorrectly() throws Exception{
        Member name = new Member("jeff", 1);
        memberDao.add(name);
        memberDao.deleteById(name.getId());
        assertEquals(0, memberDao.getAll().size());
    }

    @Test
    public void clearAllMembers() throws Exception{
        Member name = new Member("jeff", 1);
        Member name1 = new Member("paul", 1);
        memberDao.add(name);
        memberDao.add(name1);
        memberDao.clearAllMembers();
        assertEquals(0, memberDao.getAll().size());
    }

    @Test
    public void teamIdReturnsCorrectly() throws Exception{
        Member name = new Member("jeff", 1);
        int originalTeamId = name.getTeamId();
        memberDao.add(name);
        assertEquals(originalTeamId, memberDao.findById(name.getId()).getTeamId());
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }
}
