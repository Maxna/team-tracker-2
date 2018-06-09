package dao;

import models.Member;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class sql2oMemberDaoTest {
    private sql2oMemberDao memberDao;
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
        Member member = new Member ("jeff", 1);
        int originalMemberId = Member.getId();
        memberDao.add(member);
        assertNotEquals(originalMemberId, member.getId());
    }

    @Test
    public void existingMembersFoundById() throws Exception{
        Member member = new Member("jeff", 1);
        int originalMemberId = Member.getId();
        memberDao.add(member);
        assertNotEquals(originalMemberId, member.getId());
    }

    @Test
    public void getAllCorrectlyGetsAll() throws Exception{
        Member member = new Member("jeff", 1);
        memberDao.add(member);
        Member member2 = new Member("paul", 1);
        memberDao.add(member2);
        assertEquals(2, memberDao.getAll().size());
    }

    @Test
    public void getAllReturnsNothingIfNoTasks() throws Exception{
        assertEquals(0, memberDao.getAll().size());
    }

    @Test
    public void updateWorksCorrectly() throws Exception{
        Member member = new Member("jeff", 1);
        memberDao.add(member);
        memberDao.update(member.getId(), "paul", 1);
        Member updatedMember = memberDao.findById(member.getId());
        assertEquals("paul", updatedMember.getMember());
    }

    @Test
    public void deleteWorksCorrectly() throws Exception{
        Member member = new Member("jeff", 1);
        memberDao.add(member);
        memberDao.deleteById(member.getId());
        assertEquals(0, memberDao.getAll().size());
    }

    @Test
    public void clearAllMembers() throws Exception{
        Member member = new Member("jeff", 1);
        Member member1 = new Member("paul", 1);
        memberDao.add(member);
        memberDao.add(member1);
        memberDao.clearAllMembers();
        assertEquals(0, memberDao.getAll().size());
    }

    @Test
    public void teamIdReturnsCorrectly() throws Exception{
        Member member = new Member("jeff", 1);
        int originalTeamId = member.getTeamId();
        memberDao.add(member);
        assertEquals(originalTeamId, memberDao.findById(member.getId()).getTeamId());
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }
}
