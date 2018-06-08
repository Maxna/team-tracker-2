package models;

import org.junit.Test;
import static org.junit.Assert.*;

public class MemberTest {

    @Test
    public void member_CorrectlyInstantiatesMember() throws Exception {
        Member member = new Member("jeff");
        assertTrue(member instanceof Member);
    }

    @Test
    public void getMember_CorrectlyGetsMember() throws Exception{
        Member member = new Member("jeff");
        assertEquals("jeff", member.getMember());
    }

}
