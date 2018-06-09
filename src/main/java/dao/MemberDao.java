package dao;

import models.Member;
import java.util.List;

public interface MemberDao {
    // LIST
    List<Member> getAll();

    // CREATE
    void add(Member member);

    // READ
    Member findById(int id);

    // UPDATE Might have to rename member to content
    void update(int id, String newMember, int teamId);

    // DELETE
    void deleteById(int id);
    void clearAllMembers();
}
